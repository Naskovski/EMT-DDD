import React from "react";

const ReservationCard = ({ reservation, onCancel }) => {
    const { vehicleID, clientId, employeeID, reservationStart, reservationEnd, reservationStatus } = reservation;

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
                return "bg-yellow-500";
            case "STARTED":
                return "bg-blue-500";
            case "COMPLETED":
                return "bg-green-500";
            case "CANCELED":
                return "bg-red-500";
            default:
                return "bg-gray-500";
        }
    };

    return (
        <div className={`max-w-md mx-auto my-4 p-6 rounded-lg shadow-lg text-white ${getStatusStyles()}`}>
            <h2 className="text-xl font-bold mb-2">Reservation for Vehicle {vehicleID.id}</h2>
            <p className="mb-2">Client ID: {clientId.id}</p>
            <p className="mb-2">Employee ID: {employeeID.id}</p>
            <p className="mb-2">Start Date: {new Date(reservationStart).toLocaleString()}</p>
            <p className="mb-2">End Date: {new Date(reservationEnd).toLocaleString()}</p>
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
