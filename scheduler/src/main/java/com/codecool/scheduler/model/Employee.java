package com.codecool.scheduler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee implements Comparable<Employee>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Request> requests;
    private int hoursPerMonth;
    private int remainingHoursThisMonth;
    @Transient
    private List<LocalDate> workDays;

    public void addWorkedHours(int workHours){
        remainingHoursThisMonth -= workHours;
    }

    public void addRequest(Request request){
        this.requests.add(request);
    }

    public boolean isAvailable(LocalDate date){
        return requests.stream().noneMatch(request -> request.getDate().equals(date));

    }
    public void resetWorkHours(){
        remainingHoursThisMonth = hoursPerMonth;
    }

    @Override
    public int compareTo(Employee o) {
        return  o.requests.size() - this.requests.size();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requests=" + requests +
                ", hoursPerMonth=" + hoursPerMonth +
                ", remainingHoursThisMonth=" + remainingHoursThisMonth +
                ", workDays=" + workDays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
