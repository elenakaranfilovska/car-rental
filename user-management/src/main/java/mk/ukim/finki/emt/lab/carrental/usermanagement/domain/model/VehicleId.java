package mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class VehicleId extends DomainObjectId {

    private String id;
    private VehicleId(){super(DomainObjectId.randomId(RentId.class).toString());}

    @JsonCreator
    public VehicleId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
