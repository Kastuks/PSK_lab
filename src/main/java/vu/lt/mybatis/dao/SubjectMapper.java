package vu.lt.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Subject;

@Mapper
public interface SubjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUBJECT
     *
     * @mbg.generated Tue Apr 19 23:57:44 EEST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUBJECT
     *
     * @mbg.generated Tue Apr 19 23:57:44 EEST 2022
     */
    int insert(Subject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUBJECT
     *
     * @mbg.generated Tue Apr 19 23:57:44 EEST 2022
     */
    Subject selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUBJECT
     *
     * @mbg.generated Tue Apr 19 23:57:44 EEST 2022
     */
    List<Subject> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUBJECT
     *
     * @mbg.generated Tue Apr 19 23:57:44 EEST 2022
     */
    int updateByPrimaryKey(Subject record);
}