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
            className={`${bgColor} max-w-sm mx-auto rounded-lg shadow-lg
            overflow-hidden transition-transform duration-300 transform hover:scale-105
            hover:shadow-xl hover:ring-2 hover:ring-yellow-green-gradient`}
            onClick={handleClick}
            style={{width: "15rem", minWidth: '10rem'}}
        >
            <div className="p-6">
                <h2 className="text-2xl font-bold mb-2">{vehicle.modelName}</h2>
                <p className="mb-2">
                    <span className="font-semibold">Registration Plate:</span> {vehicle.registrationPlate}
                </p>
                <p className="mb-2">
                    <span className="font-semibold">Location:</span> {vehicle.locationId.id}
                </p>
                <p className="mb-2">
                    <span className="font-semibold">Price per day:</span> {vehicle.pricePerDay}€
                </p>
            </div>
        </div>);
};

export default VehicleCard;
