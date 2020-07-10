package mk.ukim.finki.emt.lab.carrental.rentmanagement.port.rest;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.RentManagement;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Rent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
public class RentManagementController {

    private final RentManagement rentManagement;

    public RentManagementController(RentManagement rentManagement) {
        this.rentManagement = rentManagement;
    }

    @GetMapping
    public List<Rent> findAll(){return this.rentManagement.findAll();}

}
