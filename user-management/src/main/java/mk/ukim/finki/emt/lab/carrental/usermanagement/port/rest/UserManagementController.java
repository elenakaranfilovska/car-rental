package mk.ukim.finki.emt.lab.carrental.usermanagement.port.rest;

import mk.ukim.finki.emt.lab.carrental.usermanagement.application.UserManagement;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserManagementController {

    private final UserManagement userManagement;

    public UserManagementController(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String userId) {
        return userManagement.findById(new UserId(userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<User> findAll() {
        return userManagement.findAll();
    }

    @GetMapping("/available")
    public List<User> findAvailableUsers() {
        return userManagement.availableUsers();
    }
}
