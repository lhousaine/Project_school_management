package com.isma.school_ms_schools.data.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "schools")
@NoArgsConstructor
@AllArgsConstructor
public final class School implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length = 1000)
    private String description;
    @OneToMany(mappedBy = "school",
            cascade = CascadeType.MERGE)
    private List<Branch> branches = new ArrayList<>();

    public void addBranch(Branch branch) {
        this.branches.add(branch);
    }

    public void removeBranch(Branch branch) {
        this.branches.remove(branch);
        branch.setSchool(null);
    }

}
