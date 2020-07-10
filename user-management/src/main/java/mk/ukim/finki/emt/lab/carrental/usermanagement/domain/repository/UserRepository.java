package mk.ukim.finki.emt.lab.carrental.usermanagement.domain.repository;

import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, UserId> {

    List<User> findAllByCanMakeRent(boolean canMakeRent);
}
