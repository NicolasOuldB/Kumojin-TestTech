package fr.nicolas.kumojin_be.service;

import fr.nicolas.kumojin_be.dto.EventDto;
import fr.nicolas.kumojin_be.mapper.EventMapper;
import fr.nicolas.kumojin_be.model.Event;
import fr.nicolas.kumojin_be.repositorie.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        return EventMapper.MAPPER.fromEvent(event);
    }

    public List<EventDto> getEvents() {
        List<Event> events = eventRepository.findAll();
        return EventMapper.MAPPER.fromEvents(events);
    }

    public void createEvent(@RequestBody EventDto eventDto) {
        Event event = EventMapper.MAPPER.toEvent(eventDto);
        eventRepository.save(event);
    }
}
