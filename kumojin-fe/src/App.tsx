import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { Outlet } from 'react-router-dom';

function App() {
  
  return (
    <>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <header className="flex justify-between items-center py-[20px] px-[20px]">
          <h2>Test technique Kumojin</h2>
          <h3>Nicolas Ould Bouamama</h3>
        </header>
        <main className="bg-background flex-grow py-[50px] px-[20px]">
          <section>
            <Outlet />
          </section>
        </main>
      </LocalizationProvider>
    </>
  )
}

export default App
