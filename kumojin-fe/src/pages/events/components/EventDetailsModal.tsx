import { Button, ButtonProps, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material"
import { styled } from '@mui/material/styles'
import { DateTimePicker } from "@mui/x-date-pickers"
import dayjs, { Dayjs } from "dayjs"
import { useEffect, useState } from "react"
import { useCreateEventMutation } from "../../../services/events"
import type { Event } from '../../../services/types'
import { EventDetailsModalProps } from "../type"

const ColorButton = styled(Button)<ButtonProps>(() => ({
  color: '#5fe2dc',
  borderColor: '#5fe2dc',
  borderRadius: '50px',
  '&:hover': {
    backgroundColor: "#5fe2dc",
    color: 'white',
    borderBlockColor: '#5fe2dc',
  },
}));

function EventDetailsModal({toggle, isOpen, event}: EventDetailsModalProps) {

  const [isViewMode, setIsViewMode] = useState(false)
  const [eventName, setEventName] = useState("")
  const [eventDescription, setEventDescription] = useState("")
  const [eventStartDate, setEventStartDate] = useState(dayjs())
  const [eventEndDate, setEventEndDate] = useState(dayjs().add(1, 'day'))
  const [createEvent] = useCreateEventMutation()
 
  useEffect(() => {
    if (!event) {
      setIsViewMode(false)
    } else {
      setIsViewMode(true)
      setEventName(event.name)
      setEventDescription(event.description)
      setEventStartDate(dayjs(event.startDate))
      setEventEndDate(dayjs(event.endDate))
    }
  }, [event])

  const isStartDateBeforeEndDate = (startDate: Dayjs, endDate: Dayjs) => {
    if (startDate && endDate) {
      return startDate.isBefore(endDate)
    }
  }

  const handleNameChange = (value: string) => {
    if (value.length <= 32) {
      setEventName(value)
    }
  }

  const handleDescriptionChange = (value: string) => {
    setEventDescription(value)
  }

  const handleStartDateChange = (date: Dayjs) => {
    if (isStartDateBeforeEndDate(date, eventEndDate)) {
      setEventStartDate(date)
    }
  }

  const handleEndDateChange = (date: Dayjs) => {
    if (isStartDateBeforeEndDate(eventStartDate, date)) {
      setEventEndDate(date)
    }
  }

  const isFormComplete = () => {
    return eventName && eventDescription && eventStartDate && eventEndDate
  }

  const handleCreateEvent = () => {
    const eventToCreate = {} as Event
    eventToCreate.name = eventName
    eventToCreate.description = eventDescription
    eventToCreate.startDate = eventStartDate
    eventToCreate.endDate = eventEndDate
    createEvent(eventToCreate)
    clearFields()
  }

  const clearFields = () => {
    setEventName('')
    setEventDescription('')
    setEventStartDate(dayjs())
    setEventEndDate(dayjs().add(1, 'day'))
  }

  const handleClose = () => {
    clearFields()
    toggle()
  }

  return (<>
    <Dialog onClose={handleClose} open={isOpen}>
      <DialogTitle>Create new event</DialogTitle>
      <DialogContent className="flex flex-col space-y-8 !p-4">
        <div className="flex flex-row space-x-4">
          <TextField className="flex-grow" disabled={isViewMode} value={eventName} onChange={(e) => (handleNameChange(e.target.value))} label="Name" variant="outlined" />
          <TextField className="flex-grow" disabled={isViewMode} name="description" value={eventDescription} onChange={(e) => (handleDescriptionChange(e.target.value))} label="Description" variant="outlined" />
        </div>
        <div className="flex flex-row space-x-4">
          <DateTimePicker className="flex-grow" disabled={isViewMode} value={eventStartDate} onChange={(value) => (value && handleStartDateChange(value))} label="Starting date" />
          <DateTimePicker className="flex-grow" disabled={isViewMode} value={eventEndDate} onChange={(value) => (value && handleEndDateChange(value))} label="Ending date" />
        </div>
      </DialogContent>
      <DialogActions className="!p-4">
        <ColorButton disabled={isViewMode || !isFormComplete()} size="large" variant="outlined" onClick={() => (isFormComplete() && handleCreateEvent())}>Create</ColorButton>
      </DialogActions>
    </Dialog>
  </>)
}

export default EventDetailsModal 