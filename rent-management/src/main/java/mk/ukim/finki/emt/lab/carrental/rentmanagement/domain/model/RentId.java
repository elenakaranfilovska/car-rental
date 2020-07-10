package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class RentId extends DomainObjectId {

    public RentId(@NonNull String id) {
        super(id);
    }
    private RentId(){super(DomainObjectId.randomId(RentId.class).toString());}
}
