package com.epam.brest.course.model;

/**
 * POJO Department model for model.
 */

public class Department {

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
    private String description;

    /**
     * Default constructor.
     */
    public Department() {
    }

    /**
     * Constructor with fields.
     * @param departmentName - Name of department.
     * @param description - Description of department.
     */
    public Department(final String departmentName, final String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

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

    /**
     * Getter for Description department.
     * @return description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Setter for Description department.
     * @param description - Description department.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * String representation.
     * @return string representation of employee object.
     */
    @Override
    public final String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
