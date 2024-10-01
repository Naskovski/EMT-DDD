import React, {useContext, useEffect, useState} from "react";
import {AuthContext} from "../AuthContext";

const AdminReservationsList = ({ status }) => {
    const [reservations, setReservations] = useState({ content: [], totalPages: 0 });
    const [currentPage, setCurrentPage] = useState(0); // Track the current page
    const {user} = useContext(AuthContext);

    const fetchReservations = async () => {
        let url = `/api/reservation/filter/status/${status}?page=${currentPage}&size=10&`; // Add pagination parameters
        if (status !== "STARTED") url += `locationId=${user.locationId}&`;
        url += "sort=reservationStart,asc";

        try {
            const response = await fetch(url);
            const data = await response.json();
            console.log(data)
            setReservations(data);
        } catch (error) {
            console.error("Failed to fetch reservations:", error);
        }
    };

    useEffect(() => {
        fetchReservations();
    }, [status, currentPage]);

    const handleCancel = async (reservation) => {
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

    const handleStart = async (reservation) => {
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

    const handleComplete = async (reservation) => {
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

    const handlePageChange = (newPage) => {
        if (newPage >= 0 && newPage < reservations.totalPages) {
            setCurrentPage(newPage);
        }
    }

    return (
        <div>
            <h1 className="text-2xl font-bold mb-4">Reservations - {status}</h1>
            <ul>
                <div className={'text-center'}>
                    {reservations.content.length === 0 && <span>
                        Currently, there are no reservations with this status.
                    </span>}
                    {!reservations && <span>
                        Error loading reservations.
                    </span>}
                </div>

                {reservations.content.map((reservation) => (
                    <li key={reservation.id?.id || 'reservation-key'} className="bg-dark-grey p-4 mb-2 rounded-l">
                        <p><strong>Client:</strong> {reservation.client?.name || 'N/A'} - {reservation.client?.email || 'N/A'}</p>

                        <p><strong>Employee:</strong> {reservation.employee?.name || 'N/A'} - {reservation.employee?.email || 'N/A'}</p>

                        <p><strong>Vehicle:</strong> {reservation.vehicle?.modelName || 'N/A'} - {reservation.vehicle?.registrationPlate || 'N/A'}</p>

                        <p>
                            <strong>Location:</strong> {reservation.vehicle?.location?.city || 'N/A'}, {reservation.vehicle?.location?.address?.street || 'N/A'} {reservation.vehicle?.location?.address?.number || ''}
                        </p>

                        <p><strong>Reservation Period: </strong>
                            {reservation.reservationStart &&
                                <span>{new Date(reservation.reservationStart).toDateString()}</span>}
                            {reservation.reservationEnd &&
                                <span> <em>to</em> {new Date(reservation.reservationEnd).toDateString()}</span>}
                        </p>

                        <div className={'actions flex-1'}>
                            {reservation.reservationStatus === 'RESERVED' && <div>
                                <button
                                    className={'button bg-gray-600 hover:bg-gray-400 transition rounded m-2 p-1.5'}
                                    onClick={() => handleStart(reservation)}
                                >
                                    START
                                </button>
                                <button
                                    className={'button bg-red-500 hover:bg-red-400 transition rounded m-2 p-1.5'}
                                    onClick={() => handleCancel(reservation)}
                                >
                                    CANCEL
                                </button>
                            </div>}
                            {reservation.reservationStatus === 'STARTED' && <div>
                                <button
                                    className={'button bg-yellow-400 hover:bg-yellow-900 transition rounded m-2 p-1.5'}
                                    onClick={() => handleComplete(reservation)}
                                >
                                    COMPLETE
                                </button>
                            </div>}
                        </div>
                    </li>

                ))}
            </ul>

            <div className="flex justify-between mt-4">
                <button
                    onClick={() => handlePageChange(currentPage - 1)}
                    disabled={currentPage === 0}
                    className="bg-gray-300 bg-opacity-20 text-white px-4 py-2 rounded disabled:opacity-50"
                >
                    Previous
                </button>
                <span>
                    Page {currentPage + 1} of {reservations.totalPages}
                </span>
                <button
                    onClick={() => handlePageChange(currentPage + 1)}
                    disabled={currentPage + 1 === reservations.totalPages}
                    className="bg-gray-300 bg-opacity-20 text-white px-4 py-2 rounded disabled:opacity-50"
                >
                    Next
                </button>
            </div>
        </div>
    );
};

export default AdminReservationsList;
