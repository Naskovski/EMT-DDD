import React, { useEffect, useState } from "react";

const Calendar = ({ availableDates, onDateSelect, mode = "portrait" }) => {
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    useEffect(() => {
        console.log("Available dates: ", availableDates);
    }, [availableDates]);

    const formatDate = (date) => {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");
        return `${year}-${month}-${day}`;
    };

    const handleDateClick = (date, e) => {
        e.preventDefault();
        const formattedDate = formatDate(date);

        if (!startDate || (startDate && endDate)) {
            setStartDate(formattedDate);
            setEndDate("");
        } else if (!endDate && formattedDate >= startDate) {
            setEndDate(formattedDate);
            onDateSelect(startDate, formattedDate);
        } else {
            setStartDate(formattedDate);
            setEndDate("");
        }
    };

    const isDateInPast = (date) => {
        const today = new Date();
        return date < new Date(today.getFullYear(), today.getMonth(), today.getDate());
    };

    const isDateAvailable = (date) => {
        const formattedDate = formatDate(date);
        return availableDates.includes(formattedDate) && !isDateInPast(date);
    };

    const isDateInRange = (date) => {
        const formattedDate = formatDate(date);
        if (startDate && endDate) {
            return formattedDate >= startDate && formattedDate <= endDate;
        }
        if (startDate && !endDate) {
            return formattedDate === startDate;
        }
        return false;
    };

    const getMonthName = (month) => {
        const monthNames = [
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        return monthNames[month];
    };

    const groupDatesByMonth = () => {
        const groupedDates = {};

        availableDates.forEach((dateStr) => {
            const date = new Date(dateStr);
            const year = date.getFullYear();
            const month = date.getMonth();

            const key = `${year}-${month}`;
            if (!groupedDates[key]) {
                groupedDates[key] = [];
            }
            groupedDates[key].push(date);
        });

        return groupedDates;
    };

    const renderCalendarForMonth = (year, month, dates) => {
        const daysInMonth = new Date(year, month + 1, 0).getDate();
        const calendarDays = [];

        for (let i = 1; i <= daysInMonth; i++) {
            const currentDate = new Date(year, month, i);
            const isAvailable = isDateAvailable(currentDate);
            const inRange = isDateInRange(currentDate);

            calendarDays.push(
                <button
                    key={i}
                    disabled={!isAvailable}
                    className={`p-2 m-1 transition rounded ${isAvailable
                        ? inRange
                            ? "bg-yellow-green-end text-slate-900"
                            : "bg-green-500 hover:bg-yellow-green-end"
                        : "bg-gray-500 hover:cursor-not-allowed hover:bg-gray-400"}`}
                    onClick={(e) => handleDateClick(currentDate, e)}
                >
                    {i}
                </button>
            );
        }

        return calendarDays;
    };

    const renderCalendars = () => {
        const groupedDates = groupDatesByMonth();
        const monthKeys = Object.keys(groupedDates);

        if (mode === "landscape") {
            return (
                <div className="flex justify-center space-x-10">
                    {monthKeys.map((key, index) => {
                        const [year, month] = key.split("-");
                        return (
                            <div key={key} className="calendar-month mb-6 w-1/2">
                                <h2 className="text-xl font-bold mb-2 text-center">
                                    {getMonthName(parseInt(month))} {year}
                                </h2>
                                <div className="grid grid-cols-7 gap-2">
                                    {renderCalendarForMonth(parseInt(year), parseInt(month))}
                                </div>
                            </div>
                        );
                    })}
                </div>
            );
        }

        return monthKeys.map((key) => {
            const [year, month] = key.split("-");
            return (
                <div key={key} className="calendar-month mb-6">
                    <h2 className="text-xl font-bold mb-2 text-center">
                        {getMonthName(parseInt(month))} {year}
                    </h2>
                    <div className="grid grid-cols-7 gap-2">
                        {renderCalendarForMonth(parseInt(year), parseInt(month))}
                    </div>
                </div>
            );
        });
    };

    return (
        <div className="calendar">
            {renderCalendars()}
            <div className="mt-4 text-center">
                <p>{startDate && <span>{new Date(startDate).toDateString()}</span>} {endDate && <span> <em>to</em> {new Date(endDate).toDateString()}</span>}</p>
            </div>
        </div>
    );
};

export default Calendar;
