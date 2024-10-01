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
        navigate('/empPanel/pending')
        console.log('userid: ', user?user:'ternary operator - no user')
        const fetchAvailableVehicles = async () => {
            try {
                const response = await fetch(`/api/vehicle/available`);
                const vehi = await response.json();
                // console.log(vehi)
                setVehicles(vehi);
            } catch (error) {
                console.error("Error fetching available dates:", error);
            }
        };

        fetchAvailableVehicles();
    }, []);

    return (
        <div className="flex min-h-screen px-20">
            <div className="w-1/4 p-4">
                <ul className="space-y-4">
                    <li>
                        <Link to="/empPanel/create" className="text-black bg-yellow-green-gradient py-2 px-4 rounded-lg block text-center">
                            Create New Reservation
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/pending" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            Pending Reservations
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/started" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            In-progress Reservations
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/completed" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            Completed Reservations
                        </Link>
                    </li>
                    <li>
                        <Link to="/empPanel/cancelled" className="text-white bg-dark-grey hover:bg-gray-700 py-2 px-4 rounded-lg block text-center">
                            Cancelled Reservations
                        </Link>
                    </li>
                </ul>
            </div>

            <div className="w-3/4 p-4 rounded-2xl bg-opacity-10 bg-white h-auto max-h-fit overflow-y-scroll my-5">
                <Outlet />
            </div>
        </div>
    );
}

export default EmployeePage;
