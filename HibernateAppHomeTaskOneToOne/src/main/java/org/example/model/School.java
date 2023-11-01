package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_number")
    private int schoolNumber;

    @OneToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal schoolPrincipal;

    public School() {
    }

    public School(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public Principal getSchoolPrincipal() {
        return schoolPrincipal;
    }

    public void setSchoolPrincipal(Principal schoolPrincipal) {
        this.schoolPrincipal = schoolPrincipal;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolNumber=" + schoolNumber +
                ", schoolPrincipal=" + schoolPrincipal.getName() +
                '}';
    }
}
