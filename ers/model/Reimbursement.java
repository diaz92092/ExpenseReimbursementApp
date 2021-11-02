package com.revature.ers.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reimbursement_id")
    private int reimbursement_id;

    @Column
    private int employee_id;

    @Column
    private double amount;

    @Column
    private String description;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date expense_date;

    @Column
    private String message;

    @Column
    private String status;

    public Reimbursement() {
        super();
    }

    public Reimbursement(int employee_id, double amount, String description, Date expense_date, String message, String status) {
        this.employee_id = employee_id;
        this.amount = amount;
        this.description = description;
        this.expense_date = expense_date;
        this.message = message;
        this.status = status;
    }

    public Reimbursement(int employee_id, double amount, String description, Date expense_date) {
        this.employee_id = employee_id;
        this.amount = amount;
        this.description = description;
        this.expense_date = expense_date;
    }

    public Reimbursement(int employee_id, String message, String status) {
        this.employee_id = employee_id;
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursement_id=" + reimbursement_id +
                ", employee_id=" + employee_id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", expense_date=" + expense_date +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;
        Reimbursement that = (Reimbursement) o;
        return getReimbursement_id() == that.getReimbursement_id() && getEmployee_id() == that.getEmployee_id() && Double.compare(that.getAmount(), getAmount()) == 0 && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getExpense_date(), that.getExpense_date()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimbursement_id(), getEmployee_id(), getAmount(), getDescription(), getExpense_date(), getMessage(), getStatus());
    }

    public int getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(Date expense_date) {
        this.expense_date = expense_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}