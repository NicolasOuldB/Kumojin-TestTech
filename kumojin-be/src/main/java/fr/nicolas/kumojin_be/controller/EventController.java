package fr.nicolas.kumojin_be.controller;

import fr.nicolas.kumojin_be.dto.EventDto;
import fr.nicolas.kumojin_be.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    private EventDto getEventDetails(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    private List<EventDto> getEvents() {
        return eventService.getEvents();
    }

    @PostMapping
    private void createEvent(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
    }
}
