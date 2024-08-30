import { light } from "@mui/material/styles/createPalette";

/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        background: "#213a45",
        "dark-font": "#213a45",
        red: "#e93b6f",
        "light-green": "#5fe2dc",
      },
    },
  },
  plugins: [],
};
