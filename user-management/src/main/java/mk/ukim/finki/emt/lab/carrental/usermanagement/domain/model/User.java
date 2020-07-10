package mk.ukim.finki.emt.lab.carrental.usermanagement.domain.model;

import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractEntity<UserId> {

    @Version
    private Long version;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    private String lastName;

    private boolean canMakeRent;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "user_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "user_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "user_country", nullable = false))
    })
    private UserAddress userAddress;

    public User(){

    }

    public User(UserId userId,String firstName,String lastName,UserAddress userAddress){
        super(userId);
        this.firstName=firstName;
        this.lastName=lastName;
        this.userAddress=userAddress;
        this.canMakeRent=true;
    }

    public User(String firstName,String lastName,UserAddress userAddress){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userAddress=userAddress;
        this.canMakeRent=true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isCanMakeRent() {
        return canMakeRent;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setCanMakeRent(boolean canMakeRent) {
        if(!this.canMakeRent && !canMakeRent)// ako this.canMakeRent e false vekje ima iznajmeno
            throw new RuntimeException("This user has already made a rent");
        this.canMakeRent = canMakeRent;
    }
}
