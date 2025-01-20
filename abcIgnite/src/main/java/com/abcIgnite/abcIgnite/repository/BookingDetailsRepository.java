package com.abcIgnite.abcIgnite.repository;

import com.abcIgnite.abcIgnite.model.BookingDetails;
import com.abcIgnite.abcIgnite.model.ClubClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDetailsRepository extends CrudRepository<BookingDetails,Long> {

    public List<BookingDetails> findByClubClassAndParticipationDate(ClubClass clubClass, LocalDate participationDate);

    public List<BookingDetails> findByMemberNameAndParticipationDateBetween(String memberName, LocalDate date1, LocalDate date2);

    public List<BookingDetails> findByMemberName(String memberName);

    public List<BookingDetails> findByParticipationDateBetween(LocalDate date1, LocalDate date2);

    public List<BookingDetails> findByClubClass(ClubClass clubClass);

    public BookingDetails findByBookingClubClassid(Long id);
}

