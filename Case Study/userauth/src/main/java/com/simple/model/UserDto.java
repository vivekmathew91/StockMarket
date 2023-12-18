package com.simple.model;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	@NotBlank(message="username shouldn't be null ")
    private String username;
	@NotBlank(message="password shouldn't be null ")
    private String password;
	@NotBlank(message="firstname shouldn't be null ")
    private String firstname;
	@NotBlank(message="lastname shouldn't be null ")
    private String lastname;
	@NotBlank(message="email shouldn't be null" )
    @Email(message="invalid email address")
    private String email;
    @NotNull(message="contact cannot be empty")
    @Pattern(regexp="\\d{10}$",message="not valid phone number")
    private String contact;
    private String role="user";

}
