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
    const { user } = useContext(AuthContext);

    useEffect(() => {
        if (user?.role === 'EMPLOYEE') navigate('/empPanel');

        const fetchAvailableVehicles = async () => {
            try {
                const response = await fetch(`/api/vehicle/available`);
                const vehi = await response.json();
                console.log(vehi);
                setVehicles(vehi);
            } catch (error) {
                console.error("Error fetching available dates:", error);
            }
        };

        fetchAvailableVehicles();
    }, [user, navigate]);

    return (
        <div>
            <div className={`hero-section relative bg-cover bg-center ${user ? 'h-40' : 'h-screen'}`}
                 style={{backgroundImage: 'url("hero-bg.jpg")'}}>
                <div className="absolute inset-0 bg-black opacity-40"></div>
                <div className="relative z-10 flex items-center justify-center h-full">
                    {!user && <div className="text-center text-white px-6 md:px-10">
                        <h1 className="text-5xl md:text-7xl font-extrabold mb-4">
                            Drive Your Dream Car Today
                        </h1>
                        <p className="text-lg md:text-xl mb-8">
                            Choose from a wide range of luxury cars for your perfect journey.
                        </p>
                        <button
                            className="text-xl bg-yellow-green-gradient text-dark-grey py-3 px-6 rounded-full hover:opacity-90 transition">
                            Explore Cars
                        </button>
                    </div>}
                </div>
            </div>

            <div className="homepage px-20 flex flex-wrap justify-around">
                <div className="w-1/2 custom-bg-gradient-neon p-5 rounded-2xl m-6 inline-block"
                     style={{maxHeight: '22rem'}}>
                    <span
                        className="text-4xl ml-6 font-bold bg-gradient-to-r from-yellow-400 to-yellow-green-end bg-clip-text text-transparent">
                        Browse our catalog of cars
                    </span>
                    <Carousel>
                        {vehicles.map((vehicle, index) => (
                            <VehicleCard key={index} vehicle={vehicle} />
                        ))}
                    </Carousel>
                    {vehicles.length === 0 && <h2 className="text-center">Sorry, currently no cars are available</h2>}
                </div>
                {user && (
                    <div className="w-1/3 bg-opacity-10 bg-white p-5 rounded-2xl m-6 inline-block overflow-y-scroll"
                         style={{maxHeight: '35rem'}}>
                        <ReservationsList clientId={user?.userId} />
                    </div>
                )}
            </div>
        </div>
    );
}

export default Homepage;
