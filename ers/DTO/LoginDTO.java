package com.revature.ers.DTO;

import java.util.Objects;

public class LoginDTO {

    private int employee_id;
    private String password;

    public LoginDTO() {
        super();
    }

    public LoginDTO(int employee_id, String password) {
        this.employee_id = employee_id;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginDTO)) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(getEmployee_id(), loginDTO.getEmployee_id()) && Objects.equals(getPassword(), loginDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee_id(), getPassword());
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "employee_id='" + employee_id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}