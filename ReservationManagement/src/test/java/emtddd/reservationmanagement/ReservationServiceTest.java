package emtddd.reservationmanagement;

import emtddd.reservationmanagement.domain.exceptions.InvalidReservationIdException;
import emtddd.reservationmanagement.domain.models.Client;
import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.valueobjects.Employee;
import emtddd.reservationmanagement.domain.valueobjects.Location;
import emtddd.reservationmanagement.domain.valueobjects.Vehicle;
import emtddd.reservationmanagement.service.ClientService;
import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.reservationmanagement.xport.client.EmployeeClient;
import emtddd.reservationmanagement.xport.client.LocationClient;
import emtddd.reservationmanagement.xport.client.VehicleClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehicleClient vehicleClient;
    @Autowired
    private EmployeeClient employeeClient;

    @Test
    public void testPlaceReservation(){
        List<Vehicle> vehicles = vehicleClient.findAll();
        Vehicle vehicle = vehicles.get(2);

        List<Employee> employees = employeeClient.findAll();
        Employee employee = employees.get(0);

        List<Client> clients = clientService.listAll();
        Client client = clients.get(1);

        LocalDateTime start = LocalDateTime.now().plusDays(1);

        LocalDateTime end = LocalDateTime.now().plusDays(10);


        ReservationForm reservationForm = new ReservationForm(client.getId(), employee.getUserID(), vehicle.getVehicleID(), start, end);

        ReservationID reservationID = reservationService.placeReservation(reservationForm);
        Reservation reservation = reservationService.findById(reservationID).orElseThrow(InvalidReservationIdException::new);

        Assertions.assertEquals(reservation.getReservation_start(), start);
        Assertions.assertEquals(reservation.getReservation_end(), end);
        Assertions.assertEquals(reservation.getEmployee_id(), employee.getUserID());
        Assertions.assertEquals(reservation.getVehicle_id(), vehicle.getVehicleID());
        Assertions.assertEquals(reservation.getClient_id(), client.getId());

    }
}
