package com.revature.ers.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int employee_id;

    @Column
    private String password;

    @Column(name = "employee_permissions")
    private String employee_permissions;


    public Employee() {
        super();
    }

    public Employee(int employee_id, String password, String employee_permissions) {
        this.employee_id = employee_id;
        this.password = password;
        this.employee_permissions = employee_permissions;
    }

    public Employee(int employee_id, String password) {
        this.employee_id = employee_id;
        this.password = password;
    }

    public Employee(int reimbursement_id, int employee_id, String password, String employee_permissions) {
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployee_permissions() {
        return employee_permissions;
    }

    public void setEmployee_permissions(String employee_permissions) {
        this.employee_permissions = employee_permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getEmployee_id() == employee.getEmployee_id() && Objects.equals(getPassword(), employee.getPassword()) && Objects.equals(getEmployee_permissions(), employee.getEmployee_permissions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee_id(), getPassword(), getEmployee_permissions());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", password='" + password + '\'' +
                ", employee_permissions='" + employee_permissions + '\'' +
                '}';
    }
}

