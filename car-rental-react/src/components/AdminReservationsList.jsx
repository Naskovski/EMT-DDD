import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../AuthContext";

const AdminReservationsList = ({ status }) => {
    const [reservations, setReservations] = useState([]);
    const {user} = useContext(AuthContext);

    const fetchReservations = async () => {
        let url = `/api/reservation/filter/status/${status}?`;
        if (status !== "STARTED") url += `locationId=${user.locationId}&`;
        url += "sort=reservationStart,asc";

        try {
            const response = await fetch(url);
            const data = await response.json();
            setReservations(data);
        } catch (error) {
            console.error("Failed to fetch reservations:", error);
        }
    };

    useEffect(() => {
        fetchReservations();
    }, [status]);

    const handleCancel = async (reservation) =>{
        try {
            const response = await fetch(`/api/reservation/cancel?reservationId=${reservation.id.id}`, {
                method: "POST",
            });
            if (response.ok) {
                alert("Reservation canceled successfully");
                fetchReservations();
            } else {
                alert("Failed to cancel the reservation");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while canceling the reservation.");
        }
    }

    const handleStart = async (reservation) =>{
        try {
            const response = await fetch(`/api/reservation/start?reservationId=${reservation.id.id}&employeeId=${user.userId}`, {
                method: "POST",
            });
            if (response.ok) {
                alert("Reservation started successfully");
                fetchReservations();
            } else {
                alert("Failed to start the reservation");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while starting the reservation.");
        }
    }

    const handleComplete = async (reservation) =>{
        try {
            const response = await fetch(`/api/reservation/complete?reservationId=${reservation.id.id}&locationId=${user.locationId}&employeeId=${user.userId}`, {
                method: "POST",
            });
            if (response.ok) {
                alert("Reservation completed successfully");
                fetchReservations();
            } else {
                alert("Failed to complete the reservation");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while completing the reservation.");
        }
    }


    return (
        <div>
            <h1 className="text-2xl font-bold mb-4">Reservations - {status}</h1>
            <ul>
                <div className={'text-center'}>
                    {reservations?.content?.length === 0 && <span>
                        Currently, there are no reservations, with this status.
                    </span>}
                    {!reservations && <span>
                        Error loading reservations.
                    </span>}
                </div>

                {reservations?.content?.map((reservation) => (
                    <li key={reservation.id.id} className="bg-dark-grey p-4 mb-2 rounded">
                        <p>Client ID: {reservation.clientId.id}</p>
                        <p>Vehicle ID: {reservation.vehicleID.id}</p>
                        <p>{reservation.reservationStart && <span>{new Date(reservation.reservationStart).toDateString()}</span>}
                            {reservation.reservationEnd && <span> <em>to</em> {new Date(reservation.reservationEnd).toDateString()}</span>}</p>
                        <div className={'actions flex-1'}>
                            {reservation.reservationStatus === 'RESERVED' && <div>
                                <button className={'button bg-gray-600 hover:bg-gray-400 transition rounded m-2 p-1.5'}
                                    onClick={() => handleStart(reservation)}
                                >
                                    START
                                </button>
                                <button className={'button bg-red-500 hover:bg-red-400 transition rounded m-2 p-1.5'}
                                    onClick={() => handleCancel(reservation)}
                                >
                                    CANCEL
                                </button>
                            </div>}
                            {reservation.reservationStatus === 'STARTED' && <div>
                                <button className={'button bg-yellow-400 hover:bg-yellow-900 transition rounded m-2 p-1.5'}
                                    onClick={() => handleComplete(reservation)}
                                >
                                    COMPLETE
                                </button>
                            </div>}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AdminReservationsList;
