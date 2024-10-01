import React, {useContext} from "react";
import {AuthContext} from "../AuthContext";

const ReservationCard = ({ reservation, onCancel }) => {
    const {user} = useContext(AuthContext);
    const { vehicle, client, employee, reservationStart, reservationEnd, reservationStatus } = reservation;

    const handleCancel = async () => {
        try {
            const response = await fetch(`/api/reservation/cancel?reservationId=${reservation.id.id}`, {
                method: "POST",
            });
            if (response.ok) {
                alert("Reservation canceled successfully");
                onCancel(reservation.id.id); // Handle state update in parent component
            } else {
                alert("Failed to cancel the reservation");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while canceling the reservation.");
        }
    };

    const getStatusStyles = () => {
        switch (reservationStatus) {
            case "RESERVED":
                return "bg-green-700";
            case "STARTED":
                return "bg-blue-700";
            case "COMPLETED":
                return "bg-sky-950";
            case "CANCELED":
                return "bg-red-700";
            default:
                return "bg-gray-700";
        }
    };

    return (
        <div className={`max-w-md mx-auto my-4 p-6 rounded-lg bg- shadow-lg text-white ${getStatusStyles()}`}>
            <h2 className="text-xl font-bold mb-2">Reservation for Vehicle {vehicle?.modelName} - {vehicle?.registrationPlate}</h2>
            {user?.userId !== client?.userId.id && <p className="mb-2">Client ID: {client?.name}</p>}
            {(user?.userId === client?.userId.id && employee?.userId.id === 'null')?<p>This reservation was created by <em>you</em>.</p> :<p className="mb-2">Employee: {employee?.name}</p>}
            <p>{reservationStart && <span>{new Date(reservationStart).toDateString()}</span>}
                {reservationEnd && <span> <em>to</em> {new Date(reservationEnd).toDateString()}</span>}</p>
            <p className="mb-4">Status: {reservationStatus}</p>

            {reservationStatus === "RESERVED" && (
                <button
                    onClick={handleCancel}
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                >
                    Cancel Reservation
                </button>
            )}
        </div>
    );
};

export default ReservationCard;
