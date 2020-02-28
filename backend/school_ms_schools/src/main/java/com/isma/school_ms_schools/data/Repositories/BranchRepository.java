package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    public Branch getBranchByName(String name);
    public List<Branch> findAllBySchoolName(String schoolName);
}
