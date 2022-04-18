package vu.lt.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Student;

@Mapper
public interface StudentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    int insert(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    Student selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    List<Student> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    int updateByPrimaryKey(Student record);
}