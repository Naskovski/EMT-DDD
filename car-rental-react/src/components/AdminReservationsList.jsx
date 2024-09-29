import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../AuthContext";

const AdminReservationsList = ({ status }) => {
    const [reservations, setReservations] = useState([]);
    const {user} = useContext(AuthContext);

    useEffect(() => {
        // Fetch reservations based on status
        const fetchReservations = async () => {
            const response = await fetch(`/api/reservation/filter/status/${status}/${user.locationId}`);
            const data = await response.json();
            console.log('reservations: ', reservations)
            setReservations(data);
        };
        fetchReservations();
    }, [status]);

    return (
        <div>
            <h1 className="text-2xl font-bold mb-4">Reservations - {status}</h1>
            <ul>
                {reservations?.content?.map((reservation) => (
                    <li key={reservation.id} className="bg-dark-grey p-4 mb-2 rounded">
                        Render your reservation details
                        <p>Client ID: {reservation.clientId.id}</p>
                        <p>Vehicle ID: {reservation.vehicleID.id}</p>
                        <p>{reservation.reservationStart && <span>{new Date(reservation.reservationStart).toDateString()}</span>}
                            {reservation.reservationEnd && <span> <em>to</em> {new Date(reservation.reservationEnd).toDateString()}</span>}</p>

                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AdminReservationsList;
