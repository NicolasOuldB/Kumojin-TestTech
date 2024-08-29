package fr.nicolas.kumojin_be.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.nicolas.kumojin_be.dto.EventDto;
import fr.nicolas.kumojin_be.service.EventService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    public void getEventDetails() throws Exception {
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer();

        EventDto expectedEvent = new EventDto();
        expectedEvent.setId(1L);
        expectedEvent.setName("Test Event");
        expectedEvent.setDescription("Test Event description");
        expectedEvent.setStartDate(OffsetDateTime.now());
        expectedEvent.setEndDate(OffsetDateTime.now().plusWeeks(1L));

        String expectedEventJson = ow.writeValueAsString(expectedEvent);

        when(eventService.getEventById(1L)).thenReturn(expectedEvent);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualEventJson = result.getResponse().getContentAsString();
        Assert.assertEquals(expectedEventJson, actualEventJson);
    }

    @Test
    public void getEvents() throws Exception {
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer();

        List<EventDto> expectedEvents = new ArrayList<EventDto>(2);

        EventDto expectedEvent1 = new EventDto();
        expectedEvent1.setId(1L);
        expectedEvent1.setName("Test Event");
        expectedEvent1.setDescription("Test Event description");
        expectedEvent1.setStartDate(OffsetDateTime.now());
        expectedEvent1.setEndDate(OffsetDateTime.now().plusWeeks(1L));

        EventDto expectedEvent2 = new EventDto();
        expectedEvent2.setId(2L);
        expectedEvent2.setName("Test Event");
        expectedEvent2.setDescription("Test Event description");
        expectedEvent2.setStartDate(OffsetDateTime.now());
        expectedEvent2.setEndDate(OffsetDateTime.now().plusWeeks(1L));

        expectedEvents.add(expectedEvent1);
        expectedEvents.add(expectedEvent2);

        when(eventService.getEvents()).thenReturn(expectedEvents);

        mockMvc.perform(MockMvcRequestBuilders.get("/events/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
