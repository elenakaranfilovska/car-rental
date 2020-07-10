package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.RentId;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.UserId;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.VehicleId;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class RentCreated implements DomainEvent {

    @JsonProperty("rentId")
    private final RentId rentId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;
    @JsonProperty("vehicleId")
    private final VehicleId vehicleId;
    @JsonProperty("userId")
    private final UserId userId;


    public RentCreated(RentId rentId,@NonNull VehicleId vehicleId,@NonNull UserId userId,@NonNull Instant occurredOn) {
        this.rentId = rentId;
        this.occurredOn = occurredOn;
        this.vehicleId=vehicleId;
        this.userId=userId;
    }


    public VehicleId getVehicleId() {
        return vehicleId;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
