package mk.ukim.finki.emt.lab.carrental.vehiclecatalog.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentCreatedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public RentCreatedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
       return remoteEvent.domainEventClassName().equals("mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.event.RentCreated");

    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, RentCreatedEvent.class));
    }
}
