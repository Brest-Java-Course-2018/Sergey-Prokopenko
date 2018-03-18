package com.epam.brest.course.dto;

/**
 * POJO Department model for model.
 */

public class DepartmentDTO {

    /**
     * Id of department.
     */
    private Integer departmentId;

    /**
     * Name of department.
     */
    private String departmentName;



    /**
     * Description of department.
     */
    private Integer avgSalary;

    /**
     * Default constructor.
     */
    public DepartmentDTO() {
    }

    /**
     * Constructor with fields.
     * @param departmentName - Name of department.
     * @param description - Description of department.
     */


    /**
     * Getter for Department ID.
     * @return departmentId
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter for Department ID.
     * @param departmentId - Department ID.
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Getter for Department Name.
     * @return departmentName
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * Setter for Department Name.
     * @param departmentName - Department Name.
     */
    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

    public DepartmentDTO(Integer departmentId, String departmentName, Integer avgSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}
