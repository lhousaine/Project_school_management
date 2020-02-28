package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    public Group findGroupByName(String groupName);
    public List<Group> findGroupsByTrainingName(String name);
    public List<Group> findGroupsByTrainingId(Long id);
    public Group findGroupByTimeTableCode(String code);
}
