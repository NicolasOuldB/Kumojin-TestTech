import {
    createBrowserRouter,
    Navigate,
} from "react-router-dom";
import App from "../App";
import Events from "../pages/events/Events";
  
export const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        children: [
            { index: true, element: <Navigate to="/events" replace /> },
            {path: 'events', element: <Events />},
        ]
    },
]);