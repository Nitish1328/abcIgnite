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
import java.util.List;

@Service
public class ClubClassService {

    @Autowired
    private ClubClassRepository clubClassRepository;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    public ResponseEntity<Response> createClubClass(ClubClass clubClass) {
        try {
            if (clubClass.getCapacity() >= 1) {
                LocalDate today = LocalDate.now();
                if (clubClass.getEndDate().isBefore(today)) {
                    throw new IllegalArgumentException("End date should be in future");
                }
                if (today.isEqual(clubClass.getEndDate())) {
                    throw new IllegalArgumentException("End date can't be the present day");
                }
                else {
                    ClubClass savedClubClass = clubClassRepository.save(clubClass);
                    Response customResponse = new Response("Success", null,clubClass );
                    return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
                }
            } else {
                throw new IllegalArgumentException("Capacity of Club Class must be atleast 1");
            }
        } catch (Exception exception) {
            Response customResponse = new Response("Failure", exception.getMessage(),clubClass );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

    public ResponseEntity<Response> updateClubClass(ClubClass clubClass, Long id) {
        try {
            ClubClass existingClubClass = clubClassRepository.findByClubClassId(id);
            if (existingClubClass != null) {
                if (clubClass.getCapacity() >= 1) {
                    LocalDate today = LocalDate.now();
                    if (clubClass.getEndDate().isBefore(today)) {
                        throw new IllegalArgumentException("End date should be in future");
                    }
                    if (today.isEqual(clubClass.getEndDate())) {
                        throw new IllegalArgumentException("End date can't be the present day");
                    }
                    else {
                        existingClubClass.setName(clubClass.getName());
                        existingClubClass.setStartDate(clubClass.getStartDate());
                        existingClubClass.setEndDate(clubClass.getEndDate());
                        existingClubClass.setStartTime(clubClass.getStartTime());
                        existingClubClass.setDuration(clubClass.getDuration());
                        existingClubClass.setCapacity(clubClass.getCapacity());
                        ClubClass savedClubClass = clubClassRepository.save(existingClubClass);
                        Response customResponse = new Response("Success", null,clubClass );
                        return ResponseEntity.status(HttpStatus.OK).body(customResponse);
                    }
                } else {
                    throw new IllegalArgumentException("Capacity of Club Class must be atleast 1");
                }
            }
            else{
                throw new IllegalArgumentException("There is no Club Class with id:" + id);
            }
        } catch (Exception exception) {
            Response customResponse = new Response("Failure", exception.getMessage(),clubClass );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

    public ResponseEntity<String> deleteClubClass(Long id) {
        try {
            ClubClass existingClubClass = clubClassRepository.findByClubClassId(id);
            if (existingClubClass != null) {
                List<BookingDetails> list =  bookingDetailsRepository.findByClubClass(existingClubClass);
                if(!list.isEmpty()){
                    throw new IllegalArgumentException("There are Bookings for this club class Club class, So it can't be deleted");
                }
                clubClassRepository.delete(existingClubClass);
                return ResponseEntity.status(HttpStatus.OK).body("Club Class with id:" + id +" is deleted");
            }
            else{
                throw new IllegalArgumentException("There is no Club Class with id:" + id);
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    public ResponseEntity<Response> getClubClass(Long id) {
        try {
            ClubClass existingClubClass = clubClassRepository.findByClubClassId(id);
            if (existingClubClass != null) {
                Response customResponse = new Response("Success", null,existingClubClass );
                return ResponseEntity.status(HttpStatus.OK).body(customResponse);
            }
            else{
                throw new IllegalArgumentException("There is no Club Class with id:" + id);
            }
        } catch (Exception exception) {
            Response customResponse = new Response("Failure", exception.getMessage(),null );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }
}
