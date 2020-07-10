package mk.ukim.finki.emt.lab.carrental.rentmanagement.application;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.VehicleId;

import java.util.List;

public interface VehicleCatalog {

    List<Vehicle> findAll();

    List<Vehicle> findAllAvailable();

    Vehicle findById(VehicleId vehicleId);
}
