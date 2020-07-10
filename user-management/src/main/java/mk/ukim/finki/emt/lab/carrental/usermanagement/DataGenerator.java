package mk.ukim.finki.emt.lab.carrental.usermanagement;

import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.CityName;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.Country;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.UserAddress;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.lab.carrental.usermanagement.domain.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class DataGenerator {

    private final UserRepository userRepository;

    public DataGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData(){
        if(userRepository.findAll().size()==0){
            var users=new ArrayList<User>();
            users.add(createUser(new UserId("1"),"Anna","Givens",new UserAddress("St.Partizanska",new CityName("Skopje"), Country.MK)));
            users.add(createUser(new UserId("2"),"Thomas","Shelby",new UserAddress("St.Ilindenska",new CityName("Skopje"), Country.MK)));
            users.add(createUser(new UserId("3"),"Charles","Weber",new UserAddress("St.Koco Racin",new CityName("Bitola"), Country.MK)));
            this.userRepository.saveAll(users);
        }
    }

    private User createUser(UserId userId, String fullName, String lastName, UserAddress userAddress){
        return new User(userId,fullName,lastName,userAddress);
    }
}
