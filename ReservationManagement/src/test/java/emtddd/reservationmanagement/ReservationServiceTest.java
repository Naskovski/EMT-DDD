package emtddd.reservationmanagement;

import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.xport.client.EmployeeClient;
import emtddd.reservationmanagement.xport.client.VehicleClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;


    @Autowired
    private VehicleClient vehicleClient;

    @Autowired
    private EmployeeClient employeeClient;

    @Test
    public void testPlaceReservation(){

        /*List<Vehicle> vehicles = vehicleClient.findAll();


        Vehicle vehicle = vehicles.get(2);

        List<Employee> employees = employeeClient.findAll();
        employees.forEach(e -> System.out.println(e.getUserID()));
        Employee employee = employees.getFirst();

        List<Client> clients = clientService.listAll();
        Client client = clients.get(1);
        System.out.println(client.getId());
        System.out.println("CLIENT ID: " + client.getId().toString());

        LocalDateTime start = LocalDateTime.now().plusDays(1);

        LocalDateTime end = LocalDateTime.now().plusDays(10);


        ReservationForm reservationForm = new ReservationForm(client.getId(), employee.getUserID(), vehicle.getVehicleID(), start, end);

        ReservationID reservationID = reservationService.placeReservation(reservationForm);
        Reservation reservation = reservationService.findById(reservationID).orElseThrow(InvalidReservationIdException::new);


        Assertions.assertEquals(reservation.getReservation_start().withNano(0), start.withNano(0));
        Assertions.assertEquals(reservation.getReservation_end().withNano(0), end.withNano(0));
//        Assertions.assertEquals(reservation.getEmployee_id().getId(), employee.getUserID().getId());
//        Assertions.assertEquals(reservation.getVehicle_id().getId(), vehicle.getVehicleID().getId());
//        Assertions.assertEquals(reservation.getClient_id().getId(), client.getId().getId());
*/
    }
}
