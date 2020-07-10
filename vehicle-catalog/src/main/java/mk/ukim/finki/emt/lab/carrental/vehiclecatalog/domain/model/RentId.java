package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class RentId extends DomainObjectId {

    private String id;
    private RentId(){super(DomainObjectId.randomId(RentId.class).toString());}

    @JsonCreator
    public RentId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
