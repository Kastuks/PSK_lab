package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "University.findAll", query = "select t from University as t")
})
@Table(name = "UNIVERSITY")
@Getter @Setter
public class University {

    public University(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "university")
    private List<Student> students = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University university = (University) o;
        return Objects.equals(name, university.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}