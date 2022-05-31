package vu.lt.rest;


import lombok.*;
import vu.lt.entities.Student;
import vu.lt.entities.University;
import vu.lt.persistence.StudentsDAO;
import vu.lt.persistence.UniversitiesDAO;

import vu.lt.rest.contracts.StudentDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/students")
//@Produces(MediaType.APPLICATION_JSON)
public class StudentsController {

    @Inject
    @Setter @Getter
    private StudentsDAO studentsDAO;

    @Inject
    @Setter @Getter
    private UniversitiesDAO universitiesDAO;

//    private Student student = new Student();
    private Student studentToEdit = new Student();
//
//    private List<Long> SubjectIds;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentsDAO.getStudentById(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setYear(student.getYear());
        studentDto.setUniversityId(student.getUniversity().getId());

        return Response.ok(studentDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer studentId,
            StudentDto studentData) {
        try {
            Student existingStudent = studentsDAO.findOne(studentId);
            if (existingStudent == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingStudent.setName(studentData.getName());
//            existingStudent.setJerseyNumber(studentData.getJerseyNumber());
            studentsDAO.update(existingStudent);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            ole.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCategory(StudentDto studentDto) {
        University uni = universitiesDAO.findOne(studentDto.getUniversityId());
        Student student = new Student(studentDto.getName(), studentDto.getYear(), uni);
        studentsDAO.persist(student);
        return Response.ok().build();
    }


    @Transactional
    public String updateStudent() {
        String redirect = "index?faces-redirect=true";
        try {
            studentsDAO.update(studentToEdit);
        } catch (OptimisticLockException e) {
            e.printStackTrace();
            return redirect + "&error=optimistic-lock-exception";
        }
        return redirect;
    }

}
