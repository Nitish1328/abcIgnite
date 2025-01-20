package com.abcIgnite.abcIgnite.repository;

import com.abcIgnite.abcIgnite.model.ClubClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClubClassRepository extends CrudRepository<ClubClass,Long> {
    public ClubClass findByClubClassId(Long clubClassId);
}
