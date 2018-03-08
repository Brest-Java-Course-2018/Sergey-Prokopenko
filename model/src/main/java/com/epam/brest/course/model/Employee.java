package com.epam.brest.course.model;

/**
 * POJO employee for model.
 */
public class Employee {

    /**
     * Id of employee.
     */
    private Integer employeeId;

    /**
     * Name of employee.
     */
    private String employeeName;

    /**
     * Salary of employee.
     */
    private Integer salary;

    /**
     * Id of department.
     */
    private Integer departmentId;

    /**
     * Constructor with arguments.
     * @param employeeName - Employee name.
     * @param salary - Salary of employee.
     * @param departmentId - Department ID.
     */
    public Employee(final String employeeName,
                    final Integer salary,
                    final Integer departmentId) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    /**
     * Constructor with all fields.
     * @param employeeId - ID of employee.
     * @param employeeName - Employee name.
     * @param salary - Salary of employee.
     * @param departmentId - Department ID.
     */
    public Employee(final Integer employeeId,
                    final String employeeName,
                    final Integer salary,
                    final Integer departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    /**
     * Default constructor.
     */
    public Employee() {
    }

    /**
     * Get Employee Id.
     * @return employeeId.
     */
    public final Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Setter for Employee Id.
     * @param employeeId - Employee Id.
     */
    public final void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Getter for Employee name.
     * @return employeeName.
     */
    public final String getEmployeeName() {
        return employeeName;
    }

    /**
     * Setter for Employee name.
     * @param employeeName - Employee name.
     */
    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Getter for salary.
     * @return salary.
     */
    public final Integer getSalary() {
        return salary;
    }

    /**
     * Setter for salary.
     * @param salary - salary.
     */
    public final void setSalary(final Integer salary) {
        this.salary = salary;
    }

    /**
     * Getter for department ID.
     * @return departmentId.
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter for department ID.
     * @param departmentId - department ID.
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * String representation.
     * @return string representation of employee object.
     */
    @Override
    public final String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", salary=" + salary
                + ", departmentId=" + departmentId
                + '}';
    }
}
