import '../App.css';
import Carousel from "../components/Carousel";
import VehicleCard from "../components/VehicleCard";


const vehicleData = [
    {
        modelName: 'Tesla Model S',
        registrationPlate: 'AB123CD',
        location: 'London, UK',
        pricePerDay: 100,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    },
    {
        modelName: 'Ford Mustang',
        registrationPlate: 'EF456GH',
        location: 'New York, USA',
        pricePerDay: 120,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    }, {
        modelName: 'Tesla Model S',
        registrationPlate: 'AB123CD',
        location: 'London, UK',
        pricePerDay: 100,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    },
    {
        modelName: 'Ford Mustang',
        registrationPlate: 'EF456GH',
        location: 'New York, USA',
        pricePerDay: 120,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    }, {
        modelName: 'Tesla Model S',
        registrationPlate: 'AB123CD',
        location: 'London, UK',
        pricePerDay: 100,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    },
    {
        modelName: 'Ford Mustang',
        registrationPlate: 'EF456GH',
        location: 'New York, USA',
        pricePerDay: 120,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    }, {
        modelName: 'Tesla Model S',
        registrationPlate: 'AB123CD',
        location: 'London, UK',
        pricePerDay: 100,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    },
    {
        modelName: 'Ford Mustang',
        registrationPlate: 'EF456GH',
        location: 'New York, USA',
        pricePerDay: 120,
        id: '7b1eaf87-ab74-4527-b9af-99400ec2bc49',
    },
    // Add more vehicles as needed
];

const v2 = {
    modelName: 'Tesla Model NO',
    registrationPlate: 'AB123CD',
    location: 'London, UK',
    pricePerDay: 100,
}


function Homepage() {
    return (
        <div className="homepage px-20">
            <div className="w-1/2 custom-bg-gradient-neon p-5 rounded-2xl m-6">
                <span className="text-4xl ml-6 font-bold bg-gradient-to-r from-yellow-400 to-yellow-green-end bg-clip-text text-transparent">
                    Choose your next car
                </span>
                <span className="text-6xl"></span>
                <Carousel>
                    {vehicleData.map((vehicle, index) => (
                        <VehicleCard key={index} vehicle={vehicle}/>
                    ))}
                </Carousel>
            </div>

            <VehicleCard vehicle={v2}/>
        </div>
    );
}

export default Homepage;
