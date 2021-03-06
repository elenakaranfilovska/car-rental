package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class VehicleId extends DomainObjectId {
    private  VehicleId() {
        super(DomainObjectId.randomId(VehicleId.class).toString());
    }

    @JsonCreator
    public VehicleId( String id) {
        super(id);
    }
}