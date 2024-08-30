import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import type { Event } from './types'

export const eventsApi = createApi({
  reducerPath: 'eventsApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/events' }),
  tagTypes: ['Event'],
  endpoints: (builder) => ({
    getEventDetails: builder.query<Event, number>({
      query: (id) => `/${id}`,
    }),
    getEvents: builder.query<[Event], void>({
      query: () => '',
      providesTags: ['Event'],
    }),
    createEvent: builder.mutation<void, Event>({
      query: (event) => ({
        url: '',
        method: 'POST',
        body: {
          name: event.name,
          description: event.description,
          startDate: event.startDate.format('YYYY-MM-DDTHH:mm:ss.SSSZ'),
          endDate: event.endDate.format('YYYY-MM-DDTHH:mm:ss.SSSZ'),
        }
      }),
      invalidatesTags: ['Event'],
    }),
  }),
})

export const { useGetEventDetailsQuery, useGetEventsQuery, useCreateEventMutation } = eventsApi