package org.example.addressbook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactDTO {

    @NotBlank(message = "Name is required and cannot be blank")  // Validation for required field
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain alphabets and spaces") // Pattern validation
    private String name;

    private String phone;

    public ContactDTO() {
    }

    public ContactDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}