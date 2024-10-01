import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';

const VehicleCard = ({vehicle, onClick, selected}) => {
    const navigate = useNavigate();
    const [bgColor, setBgColor] = useState('bg-gray-800 text-white')

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
        if(selected) setBgColor('bg-yellow-green-gradient text-black')
        else setBgColor('bg-gray-800 text-white')
    }, [selected]);
    return (
        <div
            className={`${bgColor} max-w-sm mx-auto rounded-lg shadow-lg
    overflow-hidden transition-transform duration-300 transform hover:scale-105
    hover:shadow-xl hover:ring-2 hover:ring-yellow-green-start`}
            onClick={handleClick}
            style={{width: "18rem", minWidth: "12rem"}}
        >
            <div className="p-6">
                <h2 className={`${bgColor.includes('bg-yellow-green-gradient')?'text-slate-900':'text-yellow-green-start'} text-2xl font-bold  mb-4`}>{vehicle.modelName}</h2>
                <p className=" mb-2">
                    <span className="font-semibold ">Registration Plate:</span> {vehicle.registrationPlate}
                </p>
                <p className="mb-2">
                    <span
                        className="font-semibold ">Location:</span> {vehicle.location.address.street} {vehicle.location.address.number}, {vehicle.location.city}
                </p>
                <p className=" mb-2">
                    <span className="font-semibold ">Price per day:</span> {vehicle.pricePerDay}€
                </p>
            </div>
        </div>
    );
};

export default VehicleCard;
