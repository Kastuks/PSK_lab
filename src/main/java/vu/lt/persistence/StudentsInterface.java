package vu.lt.persistence;

import vu.lt.entities.Student;

public interface StudentsInterface {
    public void persist(Student student);

    public Student update(Student student);

    public Student findOne(Integer id);

}
