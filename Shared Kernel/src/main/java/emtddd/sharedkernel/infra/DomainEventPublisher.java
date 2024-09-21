package emtddd.sharedkernel.infra;

import emtddd.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
