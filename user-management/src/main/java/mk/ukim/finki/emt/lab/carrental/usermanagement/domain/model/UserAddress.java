package mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.CityName;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.Country;

import javax.persistence.Embeddable;

@Embeddable
public class UserAddress extends Address {

    protected UserAddress(){

    }

    public UserAddress(@NonNull String address,@NonNull CityName city, @NonNull Country country) {
        super(address, city, country);
    }


}
