import React, { useState, createContext } from 'react';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {

    const [user, setUser] = useState(localStorage.getItem("user"))
    const [accessToken, setAccessToken] = useState(localStorage.getItem("accessToken"))
    const value = {
        user, setUser,
        accessToken, setAccessToken
    };

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
}

export { AuthContext, AuthProvider };