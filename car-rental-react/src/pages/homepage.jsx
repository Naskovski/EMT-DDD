import '../App.css';
import Carousel from "../components/Carousel";
import VehicleCard from "../components/VehicleCard";
import {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import ReservationsList from "../components/ReservationsList";
import {AuthContext} from "../AuthContext";




function Homepage() {
    const navigate = useNavigate();
    const [vehicles, setVehicles] = useState([]);
    const { user, setUser } = useContext(AuthContext);

    useEffect(() => {
        if(user?.role === 'EMPLOYEE') navigate('/empPanel')

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
        <div className="homepage px-20 flex flex-wrap justify-around">
            <div className="w-1/2 custom-bg-gradient-neon p-5 rounded-2xl m-6 inline-block">
                <span className="text-4xl ml-6 font-bold bg-gradient-to-r from-yellow-400 to-yellow-green-end bg-clip-text text-transparent">
                    Choose the perfect car
                </span>
                <span className="text-6xl"></span>
                <Carousel>
                    {vehicles.map((vehicle, index) => (
                        <VehicleCard key={index} vehicle={vehicle}/>
                    ))}
                </Carousel>
                {vehicles.length === 0 && <h2 className="text-center "> Sorry, currently no cars are available</h2>}
            </div>
            {user && <div className="w-1/3 bg-opacity-10 bg-white p-5 rounded-2xl m-6 inline-block">
                <ReservationsList clientId={user?.userId} />
            </div>}
        </div>
    );
}

export default Homepage;
