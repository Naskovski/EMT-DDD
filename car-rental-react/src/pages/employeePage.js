import '../App.css';
import Carousel from "../components/Carousel";
import VehicleCard from "../components/VehicleCard";
import {useContext, useEffect, useState} from "react";
import {Link, Outlet, useNavigate} from "react-router-dom";
import ReservationsList from "../components/ReservationsList";
import {AuthContext} from "../AuthContext";

function EmployeePage() {
    const navigate = useNavigate();
    const [vehicles, setVehicles] = useState([]);
    const { user, setUser } = useContext(AuthContext);

    useEffect(() => {
        console.log('userid: ', user?user:'ternary operator - no user')
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
        <div className="flex min-h-screen px-20">
            {/* Sidebar */}
            <div className="w-1/4 p-4">
                <ul className="space-y-4">
                    <li>
                        <Link to="/empPanel/create" className="text-white bg-yellow-green-gradient py-2 px-4 rounded-lg block text-center">
                            Create New Reservation
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/pending" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            View Pending Reservations
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/started" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            View Started Reservations
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/completed" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            View Completed Reservations
                        </Link>
                    </li>
                </ul>
            </div>

            {/* Main Content */}
            <div className="w-3/4 p-4 rounded-2xl bg-opacity-10 bg-white h-2/3">
                <Outlet /> {/* This will render the components based on the route */}
            </div>
        </div>
    );
}

export default EmployeePage;