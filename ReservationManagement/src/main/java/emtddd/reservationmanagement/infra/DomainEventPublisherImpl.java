package emtddd.reservationmanagement.infra;

import emtddd.sharedkernel.domain.events.DomainEvent;
import emtddd.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    @Autowired
    private final KafkaTemplate<String, String> KafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        System.out.println("Event to publish - data: " + event.toJson());
        this.KafkaTemplate.send(event.topic(), event.topic(), event.toJson());
    }
}
