import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { AuthContext } from "../AuthContext";
import CalendarComponent from "./calendarComponent";
import {unmountComponentAtNode} from "react-dom";

function CreateReservationForm() {
    const navigate = useNavigate();
    const { user, setUser } = useContext(AuthContext);
    const location = useLocation();
    const { vehicle } = location.state || {};

    const [clientId, setClientId] = useState("error");
    const [employeeId] = useState("null");
    const [reservationStart, setReservationStart] = useState("");
    const [reservationEnd, setReservationEnd] = useState("");
    const [availableDates, setAvailableDates] = useState([]);

    useEffect(() => {
        if (!user) {
            navigate("/");
            return;
        }

        setClientId(user.userId);

        const fetchAvailableDates = async () => {
            try {
                const today = new Date();
                const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 2);
                const lastDayOfMonth = new Date(today.getFullYear(), today.getMonth() + 2, 0);
                const formatDate = (date) => date.toISOString().split('T')[0];

                const startDate = formatDate(firstDayOfMonth);
                const endDate = formatDate(lastDayOfMonth);

                const response = await fetch(`/api/vehicle/${vehicle.vehicleId}/available_dates?startDate=${startDate}&endDate=${endDate}`);
                const dates = await response.json();

                console.log('current vehicle:', vehicle);
                console.log('available dates response status:', response.status);
                setAvailableDates(dates);
                console.log('available dates @ createReservationForm', availableDates);
            } catch (error) {
                console.error("Error fetching available dates:", error);
            }
        };


        fetchAvailableDates();
    }, [user, navigate, vehicle.id]);

    const handleReservationSubmit = async (e) => {
        e.preventDefault();

        console.log('user: ', user)
        const reservationData = {
            clientId: user.userId,
            employeeId,
            vehicleId: vehicle.vehicleId,
            reservationStart: new Date(reservationStart).toISOString(),
            reservationEnd: new Date(reservationEnd).toISOString(),
        };

        try {
            console.log('reservationData: ', reservationData);
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
                    <CalendarComponent
                        availableDates={availableDates}
                        onDateSelect={(start, end) => {
                            setReservationStart(start);
                            setReservationEnd(end);
                        }}
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
