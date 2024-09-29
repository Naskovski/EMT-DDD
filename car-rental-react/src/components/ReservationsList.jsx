import React, { useEffect, useState } from "react";
import ReservationCard from "./ReservationCard"; // Assuming the ReservationCard is in the same folder

const ReservationsList = ({ clientId }) => {
    const [reservations, setReservations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const fetchReservations = async () => {

        try {
            const response = await fetch(`/api/reservation/filter/client/${clientId}?page=0&size=10`);
            if (response.ok) {
                const data = await response.json();
                console.log(data)
                console.log('çlientid', clientId)
                setReservations(data.content); // Assuming `content` is where the reservations are stored
            } else {
                setError("Failed to fetch reservations");
            }
        } catch (err) {
            console.error(err);
            setError("An error occurred while fetching reservations");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchReservations();
    }, []);

    const handleCancel = () => {
        fetchReservations();
    };

    if (loading) {
        return <div>Loading reservations...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="reservation-list inline-block">
            <h2 className={'text-2xl text-center'}>My reservations</h2>
            {reservations.length === 0 ? (
                <p>No reservations found.</p>
            ) : (
                reservations.map((reservation) => (
                    <ReservationCard
                        key={reservation.id.id}
                        reservation={reservation}
                        onCancel={handleCancel}
                    />
                ))
            )}
        </div>
    );
};

export default ReservationsList;
