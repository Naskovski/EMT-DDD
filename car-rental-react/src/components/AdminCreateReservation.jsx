import React, {useContext, useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {AuthContext} from "../AuthContext";
import CalendarComponent from "./calendarComponent";
import VehicleCard from "./VehicleCard"; // You need to create this component or reuse an existing one

function AdminCreateReservation() {
    const navigate = useNavigate();
    const {user} = useContext(AuthContext);
    const [clientId, setClientId] = useState("error");
    const [employeeId, setEmployeeId] = useState("null");
    const [reservationStart, setReservationStart] = useState("");
    const [reservationEnd, setReservationEnd] = useState("");
    const [availableDates, setAvailableDates] = useState([]);
    const [vehicles, setVehicles] = useState([]);
    const [selectedVehicle, setSelectedVehicle] = useState(null);
    const [searchEmail, setSearchEmail] = useState("");
    const [clientSearchResult, setClientSearchResult] = useState(undefined);
    const [mailOK, setMailOK] = useState(undefined);

    useEffect(() => {
        if (!user) {
            navigate("/");
            return;
        }

        setEmployeeId(user.userId);
        const fetchVehicles = async () => {
            try {
                const response = await fetch(`/api/vehicle/all`); // Adjust endpoint if needed
                const data = await response.json();
                setVehicles(data);
            } catch (error) {
                console.error("Error fetching vehicles:", error);
            }
        };

        fetchVehicles();
    }, [user, navigate]);

    const fetchAvailableDates = async (vehicleId) => {
        try {
            const today = new Date();
            const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 2);
            const lastDayOfMonth = new Date(today.getFullYear(), today.getMonth() + 2, 0);
            const formatDate = (date) => date.toISOString().split('T')[0];

            const startDate = formatDate(firstDayOfMonth);
            const endDate = formatDate(lastDayOfMonth);

            const response = await fetch(`/api/vehicle/${vehicleId}/available_dates?startDate=${startDate}&endDate=${endDate}`);
            const dates = await response.json();

            setAvailableDates(dates);
        } catch (error) {
            console.error("Error fetching available dates:", error);
        }
    };

    const handleReservationSubmit = async (e) => {
        e.preventDefault();

        const reservationData = {
            clientId,
            employeeId,
            vehicleId: selectedVehicle.vehicleId,
            locationId: selectedVehicle.location.locationID.id,
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
                navigate("/empPanel/pending");
            } else {
                alert("Failed to create reservation. Try again!");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while creating the reservation.");
        }
    };

    const handleSearchClientByEmail = async () => {
        try {
            const response = await fetch(`/api/client/searchByMail?query=${searchEmail}`);
            if (response.ok) {
                const result = await response.json();
                console.log('mail seatch result: ', result);
                setMailOK(true);
                setClientSearchResult(result);
                setClientId(result.userId)
            } else if (response.status === 404) {
                setMailOK(false);
            }

        } catch (error) {
            console.error("Error searching client by email:", error);
        }
    };

    return (
        <div className="p-5">
            <h1 className="text-3xl font-bold mb-6 text-center">ADMIN Create Reservation</h1>
            <form id="input-fields" className="space-y-4" onSubmit={handleReservationSubmit}>
                <div className="mb-4">
                    <label className="text-white">Search Client by Email:</label>
                    <div className="flex justify-around">
                        <input
                            type="text"
                            className="w-full p-2 bg-gray-800 text-white border my-1 border-gray-600 rounded-lg"
                            value={searchEmail}
                            onChange={(e) => setSearchEmail(e.target.value)}
                            placeholder="Enter client email"
                        />
                        <button
                            type="button"
                            className="m-2 bg-yellow-green-gradient text-slate-900 py-2 px-4 rounded-lg"
                            onClick={handleSearchClientByEmail}
                        >
                            Search
                        </button>
                    </div>
                    <div className="mt-2">
                        {mailOK && <span>
                                    User Found: {clientSearchResult.name}
                                </span>}
                        {mailOK === false && <span>
                                    User with that mail does not exist
                                </span>}
                    </div>
                </div>

                <h3 className="text-2xl text-white mb-4">Select a Vehicle</h3>
                <div className="overflow-x-scroll overflow-y-hidden flex space-x-4">
                    {vehicles.map((vehicle) => (
                        <VehicleCard
                            key={vehicle.vehicleId}
                            vehicle={vehicle}
                            onClick={() => {
                                setSelectedVehicle(vehicle);
                                fetchAvailableDates(vehicle.vehicleId);
                            }}
                            selected={selectedVehicle && selectedVehicle.vehicleId === vehicle.vehicleId}
                        />
                    ))}
                </div>

                <div className="flex justify-center">
                    <CalendarComponent
                        availableDates={availableDates}
                        onDateSelect={(start, end) => {
                            setReservationStart(start);
                            setReservationEnd(end);
                        }}
                        mode={'landscape'}
                    />
                </div>

                <button
                    type="submit"
                    className="text-dark-grey w-full py-3 rounded-lg bg-yellow-green-gradient hover:opacity-80 transition"
                >
                    Reserve
                </button>
            </form>
        </div>
    );
}

export default AdminCreateReservation;
