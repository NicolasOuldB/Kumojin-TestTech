import { Paper, Table, TableBody, TableCell, TableHead, TableRow } from '@mui/material';
import TableContainer from '@mui/material/TableContainer';
import { useState } from 'react';
import { useGetEventsQuery } from '../../services/events';
import type { Event } from '../../services/types';
import EventDetailsModal from './components/EventDetailsModal';

function Events() {

    const [isEventModalOpen, setIsEventModalOpen] = useState(false)
    const [currentEvent, setCurrentEvent] = useState<Event>()
    const { data } = useGetEventsQuery()

    const toggleEventModal = () => {
        if (isEventModalOpen) {
            setCurrentEvent(undefined)
        }
        setIsEventModalOpen(!isEventModalOpen)
    }

    const openEvent = (event: Event) => {
        setCurrentEvent(event)
        toggleEventModal()
    }

    return (<>
        <div className="my-0 mx-auto">
            <h1 className="text-white pb-3">Events</h1>
            <TableContainer sx={{ width: 800 }} component={Paper}>
                <Table aria-label="events table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Description</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data?.map((event) => (
                            <TableRow className="hover:bg-black/[.04] hover:cursor-pointer" key={event.id} onClick={() => openEvent(event)}>
                                <TableCell component="th" scope="row">
                                    {event.name}
                                </TableCell>
                                <TableCell>{event.description}</TableCell>
                            </TableRow>
                        ))}
                        <TableRow>
                            <TableCell component="th" scope="row">
                                <a onClick={() => toggleEventModal()}>Ajouter un événement</a>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
        <EventDetailsModal isOpen={isEventModalOpen} toggle={toggleEventModal} event={currentEvent} />
    </>)
}

export default Events