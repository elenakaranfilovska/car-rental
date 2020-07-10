package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.repository;

import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Status;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.VehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, VehicleId> {
    List<Vehicle> findAllByStatusEquals(Status status);
}
