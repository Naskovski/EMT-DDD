import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Homepage from "./pages/homepage";
import Login from "./pages/login";
import Register from "./pages/register";
import {AuthProvider} from "./AuthContext";
import Header from "./components/header";
import CreateReservationForm from "./components/createReservationForm";
import EmployeePage from "./pages/employeePage";
import AdminCreateReservation from "./components/AdminCreateReservation";
import AdminReservationsList from "./components/AdminReservationsList";

function App() {
  return (
      <AuthProvider>
          <BrowserRouter>
              <Header/>
            <Routes>
              <Route path="/" element={<Homepage/>}/>
                <Route path="/empPanel" element={<EmployeePage/>}>
                    <Route path="create" element={<AdminCreateReservation />} />
                    <Route path="pending" element={<AdminReservationsList status="RESERVED" />} />
                    <Route path="started" element={<AdminReservationsList status="STARTED" />} />
                    <Route path="completed" element={<AdminReservationsList status="COMPLETED" />} />
                    <Route path="cancelled" element={<AdminReservationsList status="CANCELED" />} />
                </Route>
              <Route path="/login" element={<Login/>}/>
              <Route path="/register" element={<Register/>}/>
              <Route path="/create-reservation" element={<CreateReservationForm/>} />
              <Route path="*" element={<Navigate to={'/'}/>}/>
            </Routes>
          </BrowserRouter>
      </AuthProvider>
  );
}

export default App;
