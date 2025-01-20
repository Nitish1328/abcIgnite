package com.abcIgnite.abcIgnite.service;

import com.abcIgnite.abcIgnite.model.BookingDetails;
import com.abcIgnite.abcIgnite.model.ClubClass;
import com.abcIgnite.abcIgnite.model.Response;
import com.abcIgnite.abcIgnite.repository.BookingDetailsRepository;
import com.abcIgnite.abcIgnite.repository.ClubClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingDetailsService {
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    private ClubClassRepository clubClassRepository;

    public ResponseEntity<Response> bookClubClass(String memberName, Long classId, LocalDate participationDate) {
        try {
            ClubClass clubClass = clubClassRepository.findByClubClassId(classId);
            if(clubClass != null){
                LocalDate presentDate = LocalDate.now();
                if (participationDate.isBefore(presentDate) || participationDate.isEqual(presentDate)) {
                    throw new IllegalArgumentException("Participation-date must be in the Future.");
                }

                List<BookingDetails> existingBookings = bookingDetailsRepository.findByClubClassAndParticipationDate(clubClass, participationDate);

                if (existingBookings.size() >= clubClass.getCapacity()) {
                    throw new IllegalArgumentException("Bookings Exhausted for the class");
                }

                if(participationDate.isAfter(clubClass.getEndDate())){
                    throw new IllegalArgumentException("Class is not Scheduled for the given date");
                }

                BookingDetails bookingDetails = new BookingDetails();
                bookingDetails.setMemberName(memberName);
                bookingDetails.setClubClass(clubClass);
                bookingDetails.setParticipationDate(participationDate);
                bookingDetailsRepository.save(bookingDetails);
                Response customResponse = new Response("Success", null,bookingDetails );
                return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
            }
            else{
                throw new IllegalArgumentException("Given Club Class in Not found");
            }
        }
        catch(Exception e){
            Response customResponse = new Response("Failure", e.getMessage(),null );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }


    public ResponseEntity<Response> updateBooking(Long id,String memberName, Long classId, LocalDate participationDate) {
        try {
            BookingDetails bookingDetails = bookingDetailsRepository.findByBookingClubClassid(id);
            if(bookingDetails != null){
                ClubClass clubClass = clubClassRepository.findByClubClassId(classId);
                if(clubClass != null){
                    LocalDate presentDate = LocalDate.now();
                    if (participationDate.isBefore(presentDate) || participationDate.isEqual(presentDate)) {
                        throw new IllegalArgumentException("Participation-date must be in the Future.");
                    }

                    List<BookingDetails> existingBookings = bookingDetailsRepository.findByClubClassAndParticipationDate(clubClass, participationDate);

                    if (existingBookings.size() >= clubClass.getCapacity()) {
                        throw new IllegalArgumentException("Bookings Exhausted for the class");
                    }

                    if(participationDate.isAfter(clubClass.getEndDate())){
                        throw new IllegalArgumentException("Class is not Scheduled for the given date");
                    }

                    bookingDetails.setMemberName(memberName);
                    bookingDetails.setClubClass(clubClass);
                    bookingDetails.setParticipationDate(participationDate);
                    bookingDetailsRepository.save(bookingDetails);
                    Response customResponse = new Response("Success", null,bookingDetails );
                    return ResponseEntity.status(HttpStatus.OK).body(customResponse);
                }
                else{
                    throw new IllegalArgumentException("Given Club Class in Not found");
                }
            }
            else{
                throw new IllegalArgumentException("There is no Booking with id:" + id);
            }
        }
        catch(Exception e){
            Response customResponse = new Response("Failure", e.getMessage(),null );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }


    public ResponseEntity<String> deleteBooking(Long id) {
        try {
            BookingDetails bookingDetails = bookingDetailsRepository.findByBookingClubClassid(id);
            if (bookingDetails != null) {
                bookingDetailsRepository.delete(bookingDetails);
                return ResponseEntity.status(HttpStatus.OK).body("Booking with id:" + id +" is deleted");
            }
            else{
                throw new IllegalArgumentException("There is no Booking with id:" + id);
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }


    public ResponseEntity<Response> getBooking(Long id) {
        try {
            BookingDetails bookingDetails = bookingDetailsRepository.findByBookingClubClassid(id);
            if (bookingDetails != null) {
                Response customResponse = new Response("Success", null,bookingDetails );
                return ResponseEntity.status(HttpStatus.OK).body(customResponse);
            }
            else{
                throw new IllegalArgumentException("There is no Booking with id:" + id);
            }
        } catch (Exception exception) {
            Response customResponse = new Response("Failure", exception.getMessage(),null );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }


    public ResponseEntity<List<BookingDetails>> searchBookings(String memberName, LocalDate date1, LocalDate date2) {
        List<BookingDetails>  bookings = new ArrayList<>();
        if(memberName != null && date1 != null && date2 != null){
            bookings = bookingDetailsRepository.findByMemberNameAndParticipationDateBetween(memberName,date1,date2);
            if(bookings.isEmpty()) {
                throw new IllegalArgumentException("No Bookings found for given data");
            }
        }
        else if(memberName != null){
            bookings = bookingDetailsRepository.findByMemberName(memberName);
            if(bookings.isEmpty()) {
                throw new IllegalArgumentException("No Bookings found for given data");
            }
        }
        else if(date1 != null && date2 != null){
            bookings = bookingDetailsRepository.findByParticipationDateBetween(date1,date2);
            if(bookings.isEmpty()) {
                throw new IllegalArgumentException("No Bookings found for given data");
            }
        }
        else{
            throw new IllegalArgumentException("No Bookings found for given data");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }
}
