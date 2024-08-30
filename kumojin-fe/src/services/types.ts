import { Dayjs } from "dayjs"

export type Event = {
    id: number,
    name: string,
    description: string,
    startDate: Dayjs,
    endDate: Dayjs
}