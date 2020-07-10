package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class RegistrationPlate implements ValueObject {

    private final String registrationPlate;

    private RegistrationPlate(){
        this.registrationPlate="";
    }//by jpa

    public RegistrationPlate(@NonNull String registrationPlate) {
        if(registrationPlate.length()!=8)
            throw new IllegalArgumentException("Registration plate must be 8 characters long");
        this.registrationPlate = registrationPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationPlate that = (RegistrationPlate) o;
        return Objects.equals(registrationPlate, that.registrationPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationPlate);
    }

    @Override
    public String toString() {
        return "RegistrationPlate{" +
                "registrationPlate='" + registrationPlate + '\'' +
                '}';
    }
}
