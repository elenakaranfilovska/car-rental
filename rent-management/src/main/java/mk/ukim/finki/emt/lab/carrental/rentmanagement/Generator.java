package mk.ukim.finki.emt.lab.carrental.rentmanagement;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.RentManagement;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.RecipientAddress;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.UserId;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.VehicleId;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.CityName;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.Country;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

//@Component
public class Generator implements ApplicationListener<ApplicationReadyEvent> {

   private final RentManagement rentManagement;

    public Generator(RentManagement rentManagement) {
        this.rentManagement = rentManagement;
    }


    @Transactional
    public  void generateData(){
        if(this.rentManagement.findAll().size()==0){
            RecipientAddress address=new RecipientAddress("Pero","HFK",new CityName("Skopje"), Country.MK);
            rentManagement.createRent(Instant.now(),new VehicleId("a"),new UserId("2"),address);
        }
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        generateData();
    }
}
