import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { AuthContext } from "../AuthContext";

function CreateReservationForm() {
    const navigate = useNavigate();
    const { user, setUser } = useContext(AuthContext);
    const location = useLocation();
    const { vehicle } = location.state || {};

    const [clientId, setClientId] = useState("error"); // Set as needed
    const [employeeId] = useState("null"); // Set as needed
    const [reservationStart, setReservationStart] = useState("");
    const [reservationEnd, setReservationEnd] = useState("");

    useEffect(() => {
        if (!user) navigate("/");

        setClientId(user.userId);
    }, [user, navigate]);

    const handleReservationSubmit = async (e) => {
        e.preventDefault();

        const reservationData = {
            clientId,
            employeeId,
            vehicleId: location.state.vehicle.id, // Add vehicleId here
            reservationStart: new Date(reservationStart).toISOString(),
            reservationEnd: new Date(reservationEnd).toISOString(),
        };

        try {
            const response = await fetch("/api/reservation/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(reservationData),
            });

            if (response.ok) {
                alert("Reservation created successfully!");
                navigate("/");
            } else {
                alert("Failed to create reservation. Try again!");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while creating the reservation.");
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen">
            <div className="bg-dark-grey p-8 rounded-lg shadow-lg w-full max-w-md">
                <h1 className="text-3xl font-bold mb-6 text-center">Create Reservation</h1>
                <form id="input-fields" className="space-y-4" onSubmit={handleReservationSubmit}>
                    <input
                        className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                        placeholder="Reservation Start"
                        type="datetime-local"
                        value={reservationStart}
                        onChange={(e) => setReservationStart(e.target.value)}
                        required
                    />
                    <input
                        className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                        placeholder="Reservation End"
                        type="datetime-local"
                        value={reservationEnd}
                        onChange={(e) => setReservationEnd(e.target.value)}
                        required
                    />
                    <button
                        type="submit"
                        className="text-dark-grey w-full py-3 rounded-lg bg-yellow-green-gradient hover:opacity-80 transition"
                    >
                        Reserve
                    </button>
                </form>
                <div className="mt-4 text-center">
                    <p>Vehicle: {vehicle.modelName}</p>
                    <p>Registration Plate: {vehicle.registrationPlate}</p>
                    <p>Location: {vehicle.location}</p>
                    <p>Price per Day: {vehicle.pricePerDay}€</p>
                </div>
            </div>
        </div>
    );
}

export default CreateReservationForm;
