package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.RentId;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.UserId;
import mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model.VehicleId;

import java.time.Instant;

@Getter
public class RentCreatedEvent implements DomainEvent {

    @JsonProperty("rentId")
    private final RentId rentId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;
    @JsonProperty("vehicleId")
    private final VehicleId vehicleId;
    @JsonProperty("userId")
    private final UserId userId;

    @JsonCreator
    public RentCreatedEvent(@JsonProperty("rentId")@NonNull RentId rentId, @JsonProperty("vehicleId")@NonNull VehicleId vehicleId, @JsonProperty("userId")@NonNull UserId userId, @JsonProperty("occurredOn")@NonNull Instant occurredOn) {
        this.rentId = rentId;
        this.occurredOn = occurredOn;
        this.vehicleId=vehicleId;
        this.userId=userId;
    }


    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
