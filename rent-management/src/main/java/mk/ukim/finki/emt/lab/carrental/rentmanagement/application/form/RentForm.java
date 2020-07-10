package mk.ukim.finki.emt.lab.carrental.rentmanagement.application.form;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Vehicle;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RentForm implements Serializable {

    @NotNull
    private User user;

    @NotNull
   private Vehicle vehicle;

    @Valid
    @NotNull
    private RecipientAddressForm billingAddress = new RecipientAddressForm();

    public RecipientAddressForm getBillingAddress() {
        return billingAddress;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setBillingAddress(RecipientAddressForm billingAddress) {
        this.billingAddress = billingAddress;
    }
}
