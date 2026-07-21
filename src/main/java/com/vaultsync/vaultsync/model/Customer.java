package com.vaultsync.vaultsync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    private String phone;

    private String address;

    

    @Column(name = "aadhaar_number")
    private String aadhaarNumber;

    @Column(name = "pan_number")
    private String panNumber;

    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "account_type")
    private String accountType;

    private Double balance;

    private String pin;

    private String status;

    public String getFullName() {
    return fullName;
}
}