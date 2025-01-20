package com.abcIgnite.abcIgnite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Optional;

@Table(name = "BOOKING_DETAILS_")
@Entity
public class BookingDetails{
    @jakarta.persistence.Id
    @Id
    @Column(name="UID_")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingClubClassid;

    @Column(name = "MEMBER_NAME_")
    private String memberName;

    @ManyToOne
    @JoinColumn(name="CLUB_CLASS_")
    private ClubClass clubClass;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "PARTICIPATION_DATE_")
    private LocalDate participationDate;

    public void setId(Long bookingClubClassid) {
        this.bookingClubClassid = bookingClubClassid;
    }

    public Long getId() {
        return bookingClubClassid;
    }

    public void setMemberName(String memberName){
        this.memberName=memberName;
    }

    public String getMemberName(){
        return memberName;
    }

    public void setClubClass(ClubClass clubClass){
        this.clubClass=clubClass;
    }

    public ClubClass getClubClass(){
        return clubClass;
    }

    public void setParticipationDate(LocalDate participationDate){
        this.participationDate=participationDate;
    }

    public LocalDate getParticipationDate(){
        return participationDate;
    }
}
