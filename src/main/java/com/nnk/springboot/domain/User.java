package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Role is mandatory")
    private String role;

    public User() {
    }

    public User(@Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Username is mandatory") String username, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Password is mandatory") String password, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "FullName is mandatory") String fullname, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Role is mandatory") String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
