package mk.ukim.finki.emt.lab.carrental.vehiclecatalog;


import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.RegistrationPlate;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Type;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.VehicleId;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.repository.VehicleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class DataGeneratorVehicles {

    private final VehicleRepository vehicleRepository;

    DataGeneratorVehicles(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData(){
        if(this.vehicleRepository.findAll().size()==0){
            var vehicles=new ArrayList<Vehicle>();
            Currency currency=Currency.MKD;
            vehicles.add(createVehicle(new VehicleId("a"),new RegistrationPlate("SK5647AA"),"Toyota Yaris", Type.sedan,new Money(currency,1200000)));
            vehicles.add(createVehicle(new VehicleId("b"),new RegistrationPlate("SK7777AA"),"Mercedes AMG C63", Type.sedan,new Money(currency,300000)));
            vehicles.add(createVehicle(new VehicleId("c"),new RegistrationPlate("PP7857AA"),"Toyota Supra", Type.sportcar,new Money(currency,500000)));
            this.vehicleRepository.saveAll(vehicles);
        }
    }

    private Vehicle createVehicle(VehicleId vehicleId,RegistrationPlate registrationPlate,String model,Type type,Money price){
        return new Vehicle(vehicleId,registrationPlate,model,type,price);
    }
}
