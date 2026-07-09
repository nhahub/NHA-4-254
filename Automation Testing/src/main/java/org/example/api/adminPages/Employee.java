package org.example.api.adminPages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Maps the core fields of the OrangeHRM /api/v2/pim/employees payload.
 * Marked JsonIgnoreProperties(ignoreUnknown = true) because the real API
 * response contains many more fields (photo, terminationId, etc.) than this
 * project needs to assert on.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @JsonProperty("empNumber")
    private Integer empNumber;

    @JsonProperty("employeeId")
    private String employeeId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("terminationId")
    private Integer terminationId;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }

    public Integer getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(Integer empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTerminationId() {
        return terminationId;
    }

    public void setTerminationId(Integer terminationId) {
        this.terminationId = terminationId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNumber=" + empNumber +
                ", employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}