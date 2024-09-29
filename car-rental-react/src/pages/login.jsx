import {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../AuthContext";

function Login() {
    const {user, setUser} = useContext(AuthContext);
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        if (user) {
            navigate("/profile");
        }
    }, [user]);

    function handleKeyUp(event, action) {
        if (event.key === "Enter") {
            if (action === "Confirm") {
                logInWithEmailAndPassword(email, password);
            }
            if (action === "Next") {
                document.getElementById("loginPasswordInput").focus();
            }
        }
    }

    async function logInWithEmailAndPassword(email, password) {
        try {
            const response = await fetch('/api/auth/login', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({email, password}),
            });

            if (response.ok) {
                const data = await response.json();
                const token = data.token;
                localStorage.setItem("accessToken", token);
                localStorage.setItem("user", data);
                setUser(data);
                navigate("/profile");
            } else {
                alert("Failed login, try again!");
            }
        } catch (error) {
            console.error("Грешка при најава: ", error);
        }
    }

    return (
        <div>
            <div className="flex items-center justify-center min-h-screen">
                <div className="bg-dark-grey p-8 rounded-lg shadow-lg w-full max-w-md">
                    <h2 className="text-2xl font-bold mb-6 text-center">Login</h2>
                    <div id="input-fields" className="space-y-4">
                        <input
                            className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                            placeholder="email"
                            type="email"
                            value={email}
                            onKeyUp={(e) => handleKeyUp(e, 'Next')}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <input
                            id="loginPasswordInput"
                            className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                            placeholder="password"
                            type="password"
                            value={password}
                            onKeyUp={(e) => handleKeyUp(e, 'Confirm')}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <button
                            className="text-dark-grey w-full py-3 rounded-lg bg-yellow-green-gradient hover:opacity-90 transition"
                            onClick={() => logInWithEmailAndPassword(email, password)}
                        >
                            Login
                        </button>
                    </div>
                    <div className="text-center mt-4">
                        <a href="/register"
                           className="text-lime-50 py-2 px-4 rounded-lg hover:bg-gray-600">
                            Register
                        </a>
                    </div>
                </div>
            </div>
        </div>

    );
}

export default Login;