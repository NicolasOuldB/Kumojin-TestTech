package fr.nicolas.kumojin_be.mapper;

import fr.nicolas.kumojin_be.dto.EventDto;
import fr.nicolas.kumojin_be.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {

    EventMapper MAPPER = Mappers.getMapper( EventMapper.class );

    Event toEvent(EventDto eventDto);
    EventDto fromEvent(Event event);
    List<EventDto> fromEvents(List<Event> events);
}
