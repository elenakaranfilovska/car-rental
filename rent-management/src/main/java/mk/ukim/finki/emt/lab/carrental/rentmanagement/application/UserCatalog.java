package mk.ukim.finki.emt.lab.carrental.rentmanagement.application;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.UserId;

import java.util.List;

public interface UserCatalog {

    List<User> findAll();

    List<User> findAllAvailable();

    User findById(UserId userId);
}
