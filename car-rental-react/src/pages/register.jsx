import React, {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

import {jwtDecode} from "jwt-decode";
import {AuthContext} from "../AuthContext";

function Register() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password1, setPassword1] = useState("");
    const [password2, setPassword2] = useState("");
    const {user, setUser} = useContext(AuthContext);


    useEffect(() => {
        if (user) navigate("/");
    }, [user]);


    async function registerWithEmailAndPassword(name, email, password1, password2) {
        if (password1 !== password2) {
            alert("Passwords do not match!");
            return;
        }

        if (name.trim().length === 0 || email.trim().length === 0 || password1.trim().length === 0 || password2.trim().length === 0) {
            alert("Fill in all required fields!");
            return;
        }

        try {
            const response = await fetch("/api/auth/register", {
                method: "POST", headers: {
                    "Content-Type": "application/json",
                }, body: JSON.stringify({
                    name: name,
                    email: email,
                    password: password1,
                }),
            });


            if (response.ok) {
                const data = await response.json();
                setUser(jwtDecode(data.token));
                localStorage.setItem("accessToken", data.token);
                localStorage.setItem("user", JSON.stringify(data));
                navigate("/login");
            } else if (response.status === 409){
                alert("An account with that email already exists.")
            } else {
                alert("Failed registration. Try again!");
            }
        } catch (error) {
            console.error("Error: ", error);
        }
    }

    function handleKeyUp(event, action, elementId) {
        if (event.key === "Enter") {
            if (action === "Confirm") {
                registerWithEmailAndPassword(name, email, password1, password2);
            }
            if (action === "Next") {
                document.getElementById(elementId).focus();
            }
        }
    }

    return (
        <div className="flex items-center justify-center min-h-screen">
            <div className="bg-dark-grey p-8 rounded-lg shadow-lg w-full max-w-md">
                <h1 className="text-3xl font-bold mb-6 text-center">Register</h1>
                <div id="input-fields" className="space-y-4">
                    <input
                        id="regNameInput"
                        className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                        placeholder="name"
                        type="text"
                        value={name}
                        onKeyUp={(e) => handleKeyUp(e, 'Next', 'regEmailInput')}
                        onChange={(e) => setName(e.target.value)}
                    />
                    <input
                        id="regEmailInput"
                        className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                        placeholder="email"
                        type="email"
                        value={email}
                        onKeyUp={(e) => handleKeyUp(e, 'Next', 'regPassword1')}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <input
                        id="regPassword1"
                        className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                        placeholder="password"
                        type="password"
                        value={password1}
                        onKeyUp={(e) => handleKeyUp(e, 'Next', 'regPassword2')}
                        onChange={(e) => setPassword1(e.target.value)}
                    />
                    <input
                        id="regPassword2"
                        className="w-full p-3 border border-gray-600 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-yellow-green-start"
                        placeholder="repeat password"
                        type="password"
                        value={password2}
                        onKeyUp={(e) => handleKeyUp(e, 'Confirm')}
                        onChange={(e) => setPassword2(e.target.value)}
                    />
                    <button
                        className="text-dark-grey w-full py-3 rounded-lg bg-yellow-green-gradient hover:opacity-80 transition"
                        onClick={() => registerWithEmailAndPassword(name, email, password1, password2)}
                    >
                        Register
                    </button>
                </div>
            </div>
        </div>);
}

export default Register;