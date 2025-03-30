package com.anoop.flipkartDataRetriving.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Marketer_Table")
public class Marketer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotBlank(message = "Username Can't be blank")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    @Column(unique = true)
    private String userName;



    @NotBlank(message = "Email Id can't be blank")
    @Email(message = "Invalid Email Id Format")
    @Size(max = 100, message = "Email Id can't exceed 100 characters")
    private String email;


    
    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, max = 30,  message = "PassWord must be at least 8 characters")
    private String password;



    @NotBlank(message = "Mobile No can't be blank")
    private int phone;


    private LocalDate createdDate;


    private String status;

    @NotBlank(message = "Country Can't be blank")
    private String country;


}
