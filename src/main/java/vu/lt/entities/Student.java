package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select a from Student as a")
})
@Table(name = "STUDENT")
@Getter @Setter
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private Integer year;

    @ManyToOne
    @JoinColumn(name="UNIVERSITY_ID")
    private University university;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @ManyToMany
    @JoinTable(name="subjects", joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @Column(name="SUBJECT_ID")
    private List<Subject> subjects;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}