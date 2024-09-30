import {useContext, useEffect} from "react";
import {AuthContext} from "../AuthContext";
import {useNavigate} from "react-router-dom";

function Header() {
    const {user, setUser} = useContext(AuthContext);
    const navigate = useNavigate();

    const logout = () => {
        localStorage.removeItem("accessToken");
        localStorage.removeItem("user");
        setUser(null);
    };

    useEffect(() => {

    }, []);

    return (
        <header className="text-white p-4 shadow-md">
            <div className="container mx-auto flex justify-between items-center">
                <h1 onClick={() => navigate("/")} className="text-2xl font-bold">Swift Rentals</h1>
                <div>
                    {!user && <button
                        onClick={() => navigate("/login")}
                        className="text-white px-4 py-2 rounded-lg hover:opacity-90 transition hover:bg-white hover:text-dark-grey hover:bg-opacity-85 transition">
                        Login
                    </button>}
                    {user && <div>
                        <span className="text-yellow-200">
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