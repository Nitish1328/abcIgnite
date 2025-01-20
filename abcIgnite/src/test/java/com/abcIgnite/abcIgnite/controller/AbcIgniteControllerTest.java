package com.abcIgnite.abcIgnite.controller;

import com.abcIgnite.abcIgnite.model.BookingDetails;
import com.abcIgnite.abcIgnite.model.ClubClass;
import com.abcIgnite.abcIgnite.model.Response;
import com.abcIgnite.abcIgnite.service.BookingDetailsService;
import com.abcIgnite.abcIgnite.service.ClubClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(AbcIgniteController.class)
public class AbcIgniteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookingDetailsService bookingDetailsService;

    @MockitoBean
    ClubClassService clubClassService;


    @Test
    public void createClubClass_basic() throws Exception {

        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(1);

        when(clubClassService.createClubClass(any(ClubClass.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(new Response("Success", null, clubClass)));
        RequestBuilder request = MockMvcRequestBuilders.post("/clubClass")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"ClubClass\", \"startDate\": \"2025-01-20\", \"endDate\": \"2025-01-30\", \"startTime\": \"12:00:00\", \"duration\": 20, \"capacity\": 1}")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(201,result.getResponse().getStatus());
        assertEquals("{\"status\":\"Success\",\"message\":null,\"data\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":1,\"id\":1}}",result.getResponse().getContentAsString());
    }



    @Test
    public void updateClubClass_basic() throws Exception {

        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(1);

        when(clubClassService.updateClubClass(any(ClubClass.class),anyLong())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(new Response("Success",null, clubClass)));
        RequestBuilder request = MockMvcRequestBuilders.put("/clubClass/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"ClubClass\", \"startDate\": \"2025-01-20\", \"endDate\": \"2025-01-30\", \"startTime\": \"12:00:00\", \"duration\": 20, \"capacity\": 1}")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("{\"status\":\"Success\",\"message\":null,\"data\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":1,\"id\":1}}",result.getResponse().getContentAsString());
    }



    @Test
    public void deleteClubClass_basic() throws Exception {
        when(clubClassService.deleteClubClass(anyLong())).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Club Class is deleted"));
        RequestBuilder request = MockMvcRequestBuilders.delete("/clubClass/1")
                .contentType(MediaType.APPLICATION_JSON);

       MvcResult result = mockMvc.perform(request).andReturn();
       assertEquals(200,result.getResponse().getStatus());
       assertEquals("Club Class is deleted",result.getResponse().getContentAsString());
    }



    @Test
    public void getClubClass_basic() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(1);

        when(clubClassService.getClubClass(anyLong())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(new Response("Success",null, clubClass)));
        RequestBuilder request = MockMvcRequestBuilders.get("/clubClass/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("{\"status\":\"Success\",\"message\":null,\"data\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":1,\"id\":1}}",result.getResponse().getContentAsString());
    }



    @Test
    public void bookClubClass_basic() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(1);

        BookingDetails bookingClubClass = new BookingDetails();
        bookingClubClass.setId(1L);
        bookingClubClass.setMemberName("Nitish");
        bookingClubClass.setClubClass(clubClass);
        bookingClubClass.setParticipationDate(LocalDate.parse("2025-01-23"));

        when(bookingDetailsService.bookClubClass(any(String.class),anyLong(),any(LocalDate.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(new Response("Success",null, bookingClubClass)));
        RequestBuilder request = MockMvcRequestBuilders.post("/bookClubClass?memberName=Nitish&classId=1&participationDate=2025-01-23")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(201,result.getResponse().getStatus());
        assertEquals("{\"status\":\"Success\",\"message\":null,\"data\":{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":1,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1}}",result.getResponse().getContentAsString());
    }

    @Test
    public void updateBooking_basic() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(1);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setId(1L);
        bookingDetails.setMemberName("Nitish");
        bookingDetails.setClubClass(clubClass);
        bookingDetails.setParticipationDate(LocalDate.parse("2025-01-23"));

        when(bookingDetailsService.updateBooking(anyLong(),any(String.class),anyLong(),any(LocalDate.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(new Response("Success",null, bookingDetails)));
        RequestBuilder request = MockMvcRequestBuilders.put("/bookClubClass/1?memberName=Nitish&classId=1&participationDate=2025-01-23")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("{\"status\":\"Success\",\"message\":null,\"data\":{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":1,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1}}",result.getResponse().getContentAsString());
    }

    @Test
    public void deleteBooking_basic() throws Exception {
        when(bookingDetailsService.deleteBooking(anyLong())).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Booking is deleted"));
        RequestBuilder request = MockMvcRequestBuilders.delete("/bookClubClass/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("Booking is deleted",result.getResponse().getContentAsString());
    }



    @Test
    public void getBooking_basic() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(1);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setId(1L);
        bookingDetails.setMemberName("Nitish");
        bookingDetails.setClubClass(clubClass);
        bookingDetails.setParticipationDate(LocalDate.parse("2025-01-23"));

        when(bookingDetailsService.getBooking(anyLong())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(new Response("Success",null, bookingDetails)));
        RequestBuilder request = MockMvcRequestBuilders.get("/bookClubClass/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("{\"status\":\"Success\",\"message\":null,\"data\":{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":1,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1}}",result.getResponse().getContentAsString());
    }

    @Test
    public void searchBookings_withMemberNameAndDateRange() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(2);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setId(1L);
        bookingDetails.setMemberName("Nitish");
        bookingDetails.setClubClass(clubClass);
        bookingDetails.setParticipationDate(LocalDate.parse("2025-01-23"));

        List<BookingDetails> list = new ArrayList<>();
        list.add(bookingDetails);
        list.add(bookingDetails);

        when(bookingDetailsService.searchBookings(any(String.class),any(LocalDate.class),any(LocalDate.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(list));
        RequestBuilder request = MockMvcRequestBuilders.get("/searchBookings?memberName=Nitish&date1=2025-01-20&date2=2025-01-23")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("[{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":2,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1},{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":2,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1}]",result.getResponse().getContentAsString());
    }



    @Test
    public void searchBookings_withMemberName() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(2);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setId(1L);
        bookingDetails.setMemberName("Nitish");
        bookingDetails.setClubClass(clubClass);
        bookingDetails.setParticipationDate(LocalDate.parse("2025-01-23"));

        List<BookingDetails> list = new ArrayList<>();
        list.add(bookingDetails);
        list.add(bookingDetails);

        when(bookingDetailsService.searchBookings(any(String.class),eq(null),eq(null))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(list));
        RequestBuilder request = MockMvcRequestBuilders.get("/searchBookings?memberName=Nitish")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("[{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":2,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1},{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":2,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1}]",result.getResponse().getContentAsString());
    }



    @Test
    public void searchBookings_withDateRange() throws Exception {
        ClubClass clubClass = new ClubClass();
        clubClass.setId(1L);
        clubClass.setName("ClubClass");
        clubClass.setStartDate(LocalDate.parse("2025-01-20"));
        clubClass.setEndDate(LocalDate.parse("2025-01-30"));
        clubClass.setStartTime(LocalTime.parse("12:00:00"));
        clubClass.setDuration(20);
        clubClass.setCapacity(2);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setId(1L);
        bookingDetails.setMemberName("Nitish");
        bookingDetails.setClubClass(clubClass);
        bookingDetails.setParticipationDate(LocalDate.parse("2025-01-23"));

        List<BookingDetails> list = new ArrayList<>();
        list.add(bookingDetails);
        list.add(bookingDetails);

        when(bookingDetailsService.searchBookings(eq(null),any(LocalDate.class),any(LocalDate.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(list));
        RequestBuilder request = MockMvcRequestBuilders.get("/searchBookings?date1=2025-01-20&date2=2025-01-23")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        assertEquals("[{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":2,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1},{\"memberName\":\"Nitish\",\"clubClass\":{\"name\":\"ClubClass\",\"startDate\":\"2025-01-20\",\"endDate\":\"2025-01-30\",\"startTime\":\"12:00:00\",\"duration\":20,\"capacity\":2,\"id\":1},\"participationDate\":\"2025-01-23\",\"id\":1}]",result.getResponse().getContentAsString());
    }
}
