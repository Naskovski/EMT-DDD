import React from 'react';
import { useNavigate } from 'react-router-dom';

const VehicleCard = ({vehicle}) => {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate('/create-reservation', {
            state: { vehicle }
        });
    };
    return (
        <div
            className="max-w-sm mx-auto bg-dark-grey text-white rounded-lg shadow-lg
            overflow-hidden transition-transform duration-300 transform hover:scale-105
            hover:shadow-xl hover:ring-2 hover:ring-yellow-green-gradient"
            onClick={handleClick}
        >
            <div className="p-6">
                <h2 className="text-2xl font-bold mb-2">Model Name: {vehicle.modelName}</h2>
                <p className="mb-2">
                    <span className="font-semibold">Registration Plate:</span> {vehicle.registrationPlate}
                </p>
                <p className="mb-2">
                    <span className="font-semibold">Location:</span> {vehicle.location}
                </p>
                <p className="mb-2">
                    <span className="font-semibold">Price per day:</span> {vehicle.pricePerDay}€
                </p>
            </div>
        </div>);
};

export default VehicleCard;
