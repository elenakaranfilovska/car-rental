package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.port.rest;

import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.application.VehicleCatalog;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.VehicleId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/vehicles")
public class VehicleCatalogController {

    private final VehicleCatalog vehicleCatalog;

    public VehicleCatalogController(VehicleCatalog vehicleCatalog) {
        this.vehicleCatalog = vehicleCatalog;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable("id") String vehicleId) {
        return vehicleCatalog.findById(new VehicleId(vehicleId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Vehicle> findAll() {
        return vehicleCatalog.findAll();
    }

    @GetMapping("/available")
    public List<Vehicle> findAvailableVehicles() {
        return vehicleCatalog.availableVehicles();
    }
}
