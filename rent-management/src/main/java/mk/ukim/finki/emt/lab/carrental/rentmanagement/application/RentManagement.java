package mk.ukim.finki.emt.lab.carrental.rentmanagement.application;


import lombok.NonNull;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.form.RentForm;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.event.RentCreated;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.*;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.repository.RentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class RentManagement {

    private final RentRepository rentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VehicleCatalog vehicleCatalog;
    private final UserCatalog userCatalog;
    private final Validator validator;

//    public RentManagement(RentRepository rentRepository, ApplicationEventPublisher applicationEventPublisher) {
//        this.rentRepository = rentRepository;
//        this.applicationEventPublisher = applicationEventPublisher;
//        validator=null;
//    }

    public RentManagement(RentRepository rentRepository, ApplicationEventPublisher applicationEventPublisher, VehicleCatalog vehicleCatalog, UserCatalog userCatalog, Validator validator) {
        this.rentRepository = rentRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.vehicleCatalog = vehicleCatalog;
        this.userCatalog = userCatalog;
        this.validator = validator;
    }

    public List<Rent> findAll() {
        return this.rentRepository.findAll();
    }

    public RentId createRent(@NonNull Instant rentedOn, @NonNull VehicleId vehicleId, @NonNull UserId userId, RecipientAddress recipientAddress) {
        var newRent = rentRepository.saveAndFlush(new Rent(rentedOn, vehicleId, userId, recipientAddress));
        RentCreated rentCreated = new RentCreated(newRent.id(), newRent.getVehicleId(), newRent.getUserId(), newRent.getRentedOn());
        applicationEventPublisher.publishEvent(rentCreated);
        return newRent.getId();
    }

    @NonNull
    public Optional<Rent> findById(@NonNull RentId rentId) {
        Objects.requireNonNull(rentId, "orderId must not be null");
        return rentRepository.findById(rentId);
    }

    @NonNull
    private Rent toDomainModel(@NonNull RentForm rentForm) {
        var rent = new Rent(Instant.now(), rentForm.getVehicle().getId(), rentForm.getUser().getId(),
                toDomainModel(rentForm.getBillingAddress()));
        return rent;
    }

    @NonNull
    private RecipientAddress toDomainModel(@org.springframework.lang.NonNull RecipientAddressForm form) {
        return new RecipientAddress(form.getName(), form.getAddress(), form.getCity(), form.getCountry());
    }

    public RentId createRent(RentForm rent) {
        Objects.requireNonNull(rent, "rent must not be null");
        var constraintViolations = validator.validate(rent);
        if (constraintViolations.size() > 0)
            throw new ConstraintViolationException("The rent form is not valid", constraintViolations);
        var newRent = rentRepository.saveAndFlush(toDomainModel(rent));
        applicationEventPublisher.publishEvent(new RentCreated(newRent.id(), newRent.getVehicleId(), newRent.getUserId(), newRent.getRentedOn()));
        return newRent.id();
    }
}