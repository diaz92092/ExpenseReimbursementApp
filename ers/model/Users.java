package com.revature.ers.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column
    private String password;

    @Column(name = "roleId")
    private int roleId;

    private int username;

    public void setUsername(int username) {
        this.username = username;
    }


    public int getUsername() {
        return username;
    }

    public Users() {
        super();
    }

    public Users(int user_id, String password, int roleId) {
        this.userId = user_id;
        this.password = password;
        this.roleId = roleId;
    }

    public Users(int user_id, String password) {
        this.userId = user_id;
        this.password = password;
    }

    public Users(int reimbursement_id, int employee_id, String password, String employee_permissions) {
        System.out.print("aaaaaa");
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getPassword(), getRoleId());
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + userId +
                ", password='" + password + '\'' +
                ", role_id='" + roleId + '\'' +
                '}';
    }
}

