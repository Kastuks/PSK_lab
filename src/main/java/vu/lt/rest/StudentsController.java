package vu.lt.rest;


import lombok.*;
import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;
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
public class StudentsController {

    @Inject
    @Setter @Getter
    private StudentsDAO studentsDAO;

    private List<Long> SubjectIds;


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentsDAO.findOne(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
//        studentDto.setJerseyNumber(student.getJerseyNumber());
        studentDto.setUniversityName(student.getUniversity().getName());

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
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
