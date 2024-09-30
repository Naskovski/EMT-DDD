import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';

const VehicleCard = ({vehicle, onClick, selected}) => {
    const navigate = useNavigate();
    const [bgColor, setBgColor] = useState('bg-dark-grey text-white')

    const handleClick = () => {
        if(onClick) {
            onClick();
        } else {
            navigate('/create-reservation', {
                state: { vehicle }
            });
        }
    };

    useEffect(() => {
        if(selected) setBgColor('bg-yellow-green-end text-black')
        else setBgColor('bg-dark-grey text-white')
    }, [selected]);
    return (
        <div
            className={`bg-gray-800 max-w-sm mx-auto rounded-lg shadow-lg
    overflow-hidden transition-transform duration-300 transform hover:scale-105
    hover:shadow-xl hover:ring-2 hover:ring-yellow-500`}
            onClick={handleClick}
            style={{width: "18rem", minWidth: "12rem"}}
        >
            <div className="p-6">
                <h2 className="text-2xl font-bold text-yellow-400 mb-4">{vehicle.modelName}</h2>
                <p className="text-gray-300 mb-2">
                    <span className="font-semibold text-white">Registration Plate:</span> {vehicle.registrationPlate}
                </p>
                <p className="text-gray-300 mb-2">
                    <span
                        className="font-semibold text-white">Location:</span> {vehicle.location.address.street} {vehicle.location.address.number}, {vehicle.location.city}
                </p>
                <p className="text-gray-300 mb-2">
                    <span className="font-semibold text-white">Price per day:</span> {vehicle.pricePerDay}€
                </p>
            </div>
        </div>
    );
};

export default VehicleCard;
