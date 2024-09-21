package emtddd.vehiclemanagement.xport.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import emtddd.sharedkernel.domain.config.TopicHolder;
import emtddd.sharedkernel.domain.events.DomainEvent;
import emtddd.sharedkernel.domain.events.reservations.ReservationCreatedEvent;
import emtddd.vehiclemanagement.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationEventListener {
    private final VehicleService vehicleService;

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_CREATED, groupId = "reservations")
    public void consumeReservationCreatedEvent(String jsonMessage) {
        System.out.println("received json message: " + jsonMessage);
        try {
            ReservationCreatedEvent event = DomainEvent.fromJson(jsonMessage, ReservationCreatedEvent.class);
            System.out.println("Processed ReservationCreatedEvent: " + event.toString());
//            vehicleService.handleReservation(event);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to deserialize ReservationCreatedEvent: " + e.getMessage());
        }
    }
}
