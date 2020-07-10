package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model;

import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle extends AbstractEntity<VehicleId> {

    @Version
    private long version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "registrationPlate", column = @Column(name = "regPlate"))
    })
    private
    RegistrationPlate registrationPlate;


    @Column(name = "vehicle_model",nullable = false)
    private String model;

    @Column(name="vehicle_type")
    @Enumerated(EnumType.STRING)
    private
    Type type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money price;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private
    Status status;

    public Vehicle(){

    }
    public Vehicle(VehicleId vehicleId,RegistrationPlate registrationPlate,String model,Type type,Money price){
        super(vehicleId);
        this.registrationPlate=registrationPlate;
        this.model=model;
        this.type=type;
        this.price=price;
        this.status=Status.FREE;
    }

    public Vehicle(RegistrationPlate registrationPlate,String model,Type type,Money price){
        this.registrationPlate=registrationPlate;
        this.model=model;
        this.type=type;
        this.price=price;
        this.status=Status.FREE;
    }


    public String getModel() {
        return model;
    }

    public RegistrationPlate getRegistrationPlate() {
        return registrationPlate;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus(Status status) {
        if(this.status.equals(Status.RENTED) && status.equals(Status.RENTED))
            throw new RuntimeException("This car is already rented");
        this.status = status;
    }
}
