package com.abcIgnite.abcIgnite.controller;

import com.abcIgnite.abcIgnite.model.BookingDetails;
import com.abcIgnite.abcIgnite.model.ClubClass;
import com.abcIgnite.abcIgnite.model.Response;
import com.abcIgnite.abcIgnite.service.ClubClassService;
import com.abcIgnite.abcIgnite.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AbcIgniteController {

    @Autowired
    ClubClassService clubClassService;

    @Autowired
    BookingDetailsService bookingDetailsService;

    //sample url for testing: http://localhost:8082/abcIgnite/clubClass
    @PostMapping("/clubClass")
    @CrossOrigin
    public ResponseEntity<Response> createClubClass(@RequestBody ClubClass clubClass){
        ResponseEntity<Response> response = clubClassService.createClubClass(clubClass);
        return response;
    }

    //sample url for testing: http://localhost:8082/abcIgnite/clubClass/1
    @PutMapping("/clubClass/{id}")
    @CrossOrigin
    public ResponseEntity<Response> updateClubClass(@RequestBody ClubClass clubClass,@PathVariable("id") Long id){
        ResponseEntity<Response> response = clubClassService.updateClubClass(clubClass,id);
        return response;
    }

    //sample url for testing: http://localhost:8082/abcIgnite/clubClass/1
    @DeleteMapping("/clubClass/{id}")
    @CrossOrigin
    public ResponseEntity<String> deleteClubClass(@PathVariable("id") Long id){
        ResponseEntity<String> response = clubClassService.deleteClubClass(id);
        return response;
    }

    //sample url for testing: http://localhost:8082/abcIgnite/clubClass/1
    @GetMapping("/clubClass/{id}")
    @CrossOrigin
    public ResponseEntity<Response> getClubClass(@PathVariable("id") Long id){
        ResponseEntity<Response> response = clubClassService.getClubClass(id);
        return response;
    }


    //sample url for testing: http://localhost:8082/abcIgnite/bookClubClass?memberName=Nitish&classId=1&participationDate=2025-01-23
    @PostMapping("/bookClubClass")
    @CrossOrigin
    public ResponseEntity<Response> bookClubClass(@RequestParam("memberName") String memberName, @RequestParam("classId") Long classId, @RequestParam("participationDate") LocalDate participationDate) {
        ResponseEntity<Response> response = bookingDetailsService.bookClubClass(memberName,classId,participationDate);
        return response;
    }


    //sample url for testing: http://localhost:8082/abcIgnite/bookClubClass/1?memberName=Nitish11&classId=2&participationDate=2025-01-22
    @PutMapping("/bookClubClass/{id}")
    @CrossOrigin
    public ResponseEntity<Response> updateBooking(@PathVariable("id") Long id,@RequestParam("memberName") String memberName, @RequestParam("classId") Long classId, @RequestParam("participationDate") LocalDate participationDate) {
        ResponseEntity<Response> response = bookingDetailsService.updateBooking(id,memberName,classId,participationDate);
        return response;
    }


    //sample url for testing: http://localhost:8082/abcIgnite/bookClubClass/1?memberName=Nitish11&classId=2&participationDate=2025-01-22
    @DeleteMapping("/bookClubClass/{id}")
    @CrossOrigin
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id) {
        ResponseEntity<String> response = bookingDetailsService.deleteBooking(id);
        return response;
    }


    //sample url for testing: http://localhost:8082/abcIgnite/bookClubClass/1?memberName=Nitish11&classId=2&participationDate=2025-01-22
    @GetMapping("/bookClubClass/{id}")
    @CrossOrigin
    public ResponseEntity<Response> getBooking(@PathVariable("id") Long id) {
        ResponseEntity<Response> response = bookingDetailsService.getBooking(id);
        return response;
    }


    /*sample url's for testing:
    http://localhost:8082/abcIgnite/searchBookings?memberName=Nitish&date1=2025-01-27&date2=2025-01-27
    http://localhost:8082/abcIgnite/searchBookings?memberName=Nitish
    http://localhost:8082/abcIgnite/searchBookings?date1=2025-01-27&date2=2025-01-27 */
    @GetMapping("/searchBookings")
    @CrossOrigin
    public ResponseEntity<List<BookingDetails>> searchBookings(@RequestParam(name = "memberName", required = false) String memberName, @RequestParam(name = "date1", required = false) LocalDate date1, @RequestParam(name = "date2", required = false) LocalDate date2) {
        ResponseEntity<List<BookingDetails>> response = bookingDetailsService.searchBookings(memberName,date1,date2);
        return response;
    }


}
