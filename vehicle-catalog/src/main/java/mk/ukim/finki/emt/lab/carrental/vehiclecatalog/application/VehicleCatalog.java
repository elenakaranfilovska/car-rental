package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.application;

import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Status;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.VehicleId;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.repository.VehicleRepository;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.integration.RentCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class VehicleCatalog {

    private final VehicleRepository vehicleRepository;

    public VehicleCatalog(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @NonNull
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @NonNull
    public Optional<Vehicle> findById(@NonNull VehicleId vehicleId) {
        Objects.requireNonNull(vehicleId, "vehicleId must not be null");
        return vehicleRepository.findById(vehicleId);
    }

    public List<Vehicle> availableVehicles(){
        return this.vehicleRepository.findAllByStatusEquals(Status.FREE);//gi vrakja site sto ne se iznajmeni
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRentCreatedEvent(RentCreatedEvent event) {
        Vehicle v = vehicleRepository.findById(event.getVehicleId()).orElseThrow(RuntimeException::new);
        v.changeStatus(Status.RENTED);
        this.vehicleRepository.save(v);
    }

}
