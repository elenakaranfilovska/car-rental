package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "rents")
public class Rent extends AbstractEntity<RentId> {

    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="vehicle_id",nullable = false))
    private VehicleId vehicleId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="user_id",nullable = false))
    private UserId userId;

    @Column(name = "rented_on", nullable = false)
    private Instant rentedOn;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "billing_name", nullable = false)),
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private RecipientAddress recipientAddress;

    @Column(name = "rent_state",nullable = false)
    @Enumerated(EnumType.STRING)
    private RentState state;

    public Rent(){

    }
    public Rent(@NonNull Instant rentedOn,@NonNull VehicleId vehicleId,@NonNull UserId userId,RecipientAddress recipientAddress){
       super(DomainObjectId.randomId(RentId.class));
       setRentedOn(rentedOn);
       setUserId(userId);
       setVehicleId(vehicleId);
       setRecipientAddress(recipientAddress);
       setState(RentState.RECEIVED);
    }

    public void setRecipientAddress(RecipientAddress recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public void setRentedOn(Instant rentedOn) {
        this.rentedOn = rentedOn;
    }

    public void setState(RentState state) {
        this.state = state;
    }

    public Instant getRentedOn() {
        return rentedOn;
    }

    public RentState getState() {
        return state;
    }

    public void setVehicleId(VehicleId vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public VehicleId getVehicleId() {
        return vehicleId;
    }

    public UserId getUserId() {
        return userId;
    }



    @NonNull
    @JsonProperty("state")
    public RentState state() {
        return state;
    }


}
