package vu.lt.mybatis.model;

public class Student {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDENT.ID
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDENT.JERSEY_NUMBER
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    private Integer jerseyNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDENT.NAME
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDENT.UNIVERSITY_ID
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    private Integer universityId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STUDENT.ID
     *
     * @return the value of PUBLIC.STUDENT.ID
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STUDENT.ID
     *
     * @param id the value for PUBLIC.STUDENT.ID
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STUDENT.JERSEY_NUMBER
     *
     * @return the value of PUBLIC.STUDENT.JERSEY_NUMBER
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STUDENT.JERSEY_NUMBER
     *
     * @param jerseyNumber the value for PUBLIC.STUDENT.JERSEY_NUMBER
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STUDENT.NAME
     *
     * @return the value of PUBLIC.STUDENT.NAME
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STUDENT.NAME
     *
     * @param name the value for PUBLIC.STUDENT.NAME
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STUDENT.UNIVERSITY_ID
     *
     * @return the value of PUBLIC.STUDENT.UNIVERSITY_ID
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public Integer getUniversityId() {
        return universityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STUDENT.UNIVERSITY_ID
     *
     * @param universityId the value for PUBLIC.STUDENT.UNIVERSITY_ID
     *
     * @mbg.generated Fri Apr 15 03:22:25 EEST 2022
     */
    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }
}