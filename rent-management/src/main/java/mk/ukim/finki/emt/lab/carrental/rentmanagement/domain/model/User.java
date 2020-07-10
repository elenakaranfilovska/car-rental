package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model;

import lombok.Getter;

@Getter
public class User {

    private UserId id;

    private String firstName;

    private String lastName;

    private boolean canMakeRent;

}
