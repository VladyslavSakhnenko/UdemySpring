package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "School")
public class School {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_number")
    private int schoolNUmber;

    @OneToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal principal;

    public School(){}

    public School(int schoolNUmber) {
        this.schoolNUmber = schoolNUmber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolNUmber() {
        return schoolNUmber;
    }

    public void setSchoolNUmber(int schoolNUmber) {
        this.schoolNUmber = schoolNUmber;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolNUmber=" + schoolNUmber +
                ", principal=" + principal.getName() +
                '}';
    }
}
