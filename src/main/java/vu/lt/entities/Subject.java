package vu.lt.entities;

import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Subject.findAll", query = "select i from Subject as i"),
        @NamedQuery(name = "Subject.getById", query = "select i from Subject as i where i.id = :id")
})
@Table(name = "SUBJECT")
public class Subject {
    public Subject(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

//    @Column(name = "UNIVERSITY")
//    private String university;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "subjects")
    @Column(name="STUDENT_ID")
    private List<Student> students;

}
