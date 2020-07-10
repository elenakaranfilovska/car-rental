package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class UserId extends DomainObjectId {

    private String id;
    private UserId(){super(DomainObjectId.randomId(RentId.class).toString());}

    @JsonCreator
    public UserId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
