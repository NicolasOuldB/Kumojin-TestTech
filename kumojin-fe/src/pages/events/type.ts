
import type { Event } from '../../services/types'

export type EventDetailsModalProps = {
    toggle: () => void, 
    isOpen: boolean, 
    event?: Event
}