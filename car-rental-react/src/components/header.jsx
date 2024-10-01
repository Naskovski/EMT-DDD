import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../AuthContext";
import {useNavigate} from "react-router-dom";

function Header() {
    const {user, setUser} = useContext(AuthContext);
    const navigate = useNavigate();
    const [location, setLocation] = useState('Location loading...');

    const logout = () => {
        localStorage.removeItem("accessToken");
        localStorage.removeItem("user");
        setUser(null);
    };

    const fetchAddress = async (locationId) => {
        try {
            const response = await fetch(`/api/location/id/${locationId}`);

            if (!response.ok) {
                throw new Error(`Error fetching address. Status: ${response.status}`);
            }

            const data = await response.json();
            return `${data.address.street} ${data.address.number}, ${data.city}`;
        } catch (error) {
            console.error("Error fetching address:", error);
            return null;
        }
    };

    useEffect(() => {
        if(user?.role === 'EMPLOYEE') {
            fetchAddress(user.locationId).then((response) => {
                setLocation(response);
            })
        }
    }, [user]);

    return (
        <header className="text-white p-4 shadow-md">
            <div className="container mx-auto flex justify-between items-center">
                <h1 onClick={() => navigate("/")} className="text-2xl font-bold text-yellow-green-start">
                    Swift<span className={'text-white'}>Rentals</span></h1>
                <div>
                    {!user && <button
                        onClick={() => navigate("/login")}
                        className="text-white px-4 py-2 rounded-lg hover:opacity-90 transition hover:bg-white hover:text-dark-grey hover:bg-opacity-85 transition">
                        Login
                    </button>}
                    {user && <div>
                        {user.role === 'EMPLOYEE' &&
                            <span className="text-white px-4 py-2 hover:text-dark-grey">
                                {location}
                            </span>}
                        <span className="text-yellow-green-start">
                            {user.name}
                        </span>
                        <button
                            onClick={logout}
                            className="ml-4 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition">
                            Logout
                        </button>
                    </div>}

                </div>
            </div>
        </header>
    );
};

export default Header;