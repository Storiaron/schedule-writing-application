import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import reportWebVitals from "./reportWebVitals";
import Header from "./components/Header";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./components/Login";
import AdminPage from "./components/employerComponents/AdminPage";
import DayOffRequest from "./components/employeeComponents/DayOffRequest";
import ScheduleRequest from "./components/employerComponents/ScheduleRequest";
import DailyRequirements from "./components/employerComponents/DailyRequirements";
import Employee from "./components/employeeComponents/Employee";
import Register from "./components/employerComponents/Register";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Header />,
    // errorElement: <ErrorElement />,

    children: [
      {
        path: "/",
        element: <Login />,
      },
      {
        path: "/register",
        element: <Register />
      },
      {
        path: "/admin",
        element: <AdminPage />
      },
      {
        path: "/request",
        element: <DayOffRequest />
      },
      {
        path: "/schedule",
        element: <ScheduleRequest />
      },
      {
        path: "/dailyrequirements",
        element: <DailyRequirements />
      }, 
      {
        path: "/employee/:employeeName",
        element: <Employee />
      }
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
reportWebVitals();
