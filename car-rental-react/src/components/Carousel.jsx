import React, { useState } from 'react';

const Carousel = ({ children }) => {
    const [currentIndex, setCurrentIndex] = useState(0);
    const itemsToShow = 3; // Number of items to show in the carousel

    const nextItem = () => {
        setCurrentIndex((prevIndex) => (prevIndex + 1) % React.Children.count(children));
    };

    const prevItem = () => {
        setCurrentIndex((prevIndex) => (prevIndex - 1 + React.Children.count(children)) % React.Children.count(children));
    };

    return (
        <div className="relative overflow-hidden">
            <div className="flex transition-transform duration-300" style={{ transform: `translateX(-${currentIndex * (100 / itemsToShow)}%)` }}>
                {React.Children.map(children, (child, index) => (
                    <div key={index} className="min-w-0 flex-shrink-0 w-1/3 p-4">
                        {child}
                    </div>
                ))}
            </div>
            <button onClick={prevItem} className="absolute left-0 top-1/2 transform -translate-y-1/2 bg-white bg-opacity-55 text-black px-4 py-2 rounded-full hover:opacity-90 transition">
                Prev
            </button>
            <button onClick={nextItem} className="absolute right-0 top-1/2 transform -translate-y-1/2 bg-white bg-opacity-55 text-black px-4 py-2 rounded-full hover:opacity-90 transition">
                Next
            </button>
        </div>
    );
};

export default Carousel;
