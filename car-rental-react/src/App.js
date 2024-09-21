import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Homepage from "./pages/homepage";
import Login from "./pages/login";
import Register from "./pages/register";
import {AuthProvider} from "./AuthContext";
import Header from "./components/header";

function App() {
  return (
      <AuthProvider>
        <div className="min-h-screen bg-racing-green-gradient text-white">
          <BrowserRouter>
              <Header/>
            <Routes>
              <Route path="/" element={<Homepage/>}/>
              <Route path="/login" element={<Login/>}/>
              <Route path="/register" element={<Register/>}/>
              <Route path="*" element={<Navigate to={'/'}/>}/>
            </Routes>
          </BrowserRouter>
        </div>
      </AuthProvider>
  );
}

export default App;
