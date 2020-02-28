package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {
     public Training findTrainingByName(String name);
     public List<Training> findTrainingsByEducationLevelName(String eduLevelName);
     public List<Training> findTrainingsByEducationLevelId(Long id);
}
