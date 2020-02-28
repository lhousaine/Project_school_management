package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,String> {
     public TimeTable findTimeTableByGroupName(String groupName);
}
