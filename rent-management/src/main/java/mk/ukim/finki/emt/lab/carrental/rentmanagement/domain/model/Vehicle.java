package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Getter
public class Vehicle {

    private VehicleId id;

    private String model;

    private Money price;

    private Status status;

}
