import '../App.css';
import Carousel from "../components/Carousel";
import VehicleCard from "../components/VehicleCard";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";




function Homepage() {
    const navigate = useNavigate();
    const [vehicles, setVehicles] = useState([]);

    useEffect(() => {
        const fetchAvailableVehicles = async () => {
            try {
                const response = await fetch(`/api/vehicle/available`);
                const vehi = await response.json();
                // console.log(await response.json())
                setVehicles(vehi);
            } catch (error) {
                console.error("Error fetching available dates:", error);
            }
        };

        fetchAvailableVehicles();
    }, []);

    return (
        <div className="homepage px-20">
            <div className="w-1/2 custom-bg-gradient-neon p-5 rounded-2xl m-6">
                <span className="text-4xl ml-6 font-bold bg-gradient-to-r from-yellow-400 to-yellow-green-end bg-clip-text text-transparent">
                    Choose your next car
                </span>
                <span className="text-6xl"></span>
                <Carousel>
                    {vehicles.map((vehicle, index) => (
                        <VehicleCard key={index} vehicle={vehicle}/>
                    ))}

                </Carousel>
                {vehicles.length === 0 && <h2 className="text-center "> Sorrry, Currently no cars are available</h2>}
            </div>
        </div>
    );
}

export default Homepage;
