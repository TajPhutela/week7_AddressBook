package org.example.addressbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.addressbook.dto.ContactDTO;

import java.util.Objects;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String phone;

    public Contact(ContactDTO contactDTO) {
        getAllContacts(contactDTO);
    }

    public void getAllContacts(ContactDTO contactDTO) {
        this.name = contactDTO.getName();
        this.phone = contactDTO.getPhone();
    }

}