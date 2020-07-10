package mk.ukim.finki.emt.lab.carrental.usermanagement.application;

import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.lab.carrental.usermanagement.integration.RentCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserManagement {

    private final UserRepository userRepository;

    public UserManagement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public Optional<User> findById(@NonNull UserId userId){
        return this.userRepository.findById(userId);
    }

    public List<User> availableUsers(){
        return this.userRepository.findAllByCanMakeRent(true);
    }//klienti koi nemaat momentalno iznajmeno vozilo

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRentCreatedEvent(RentCreatedEvent event) {
       User u=this.userRepository.findById(event.getUserId()).orElseThrow(RuntimeException::new);
       u.setCanMakeRent(false);
       this.userRepository.save(u);
    }
}
