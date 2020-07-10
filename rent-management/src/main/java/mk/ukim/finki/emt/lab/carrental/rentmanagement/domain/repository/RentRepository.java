package mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.repository;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Rent;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.RentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, RentId> {

}
