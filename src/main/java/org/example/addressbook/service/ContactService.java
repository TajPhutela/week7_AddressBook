package org.example.addressbook.service;

import org.example.addressbook.dto.ContactDTO;
import org.example.addressbook.exception.AddressBookException;
import org.example.addressbook.model.Contact;
import org.example.addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        log.debug("Retrieving all contacts");
        return contactRepository.findAll();
    }

    public Contact getContactById(int id) {
        log.debug("Retrieving contact with ID: {}", id);
        return contactRepository.findById(id)
            .orElseThrow(() -> new AddressBookException("Contact with ID " + id + " not found."));
    }

    public Contact addContact(ContactDTO dto) {
        Contact contact = new Contact(dto);
        Contact savedContact = contactRepository.save(contact);
        log.info("Added new contact: {}", savedContact);
        return savedContact;
    }

    public Contact updateContact(int id, ContactDTO dto) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new AddressBookException("Contact with ID " + id + " not found."));
        
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        
        Contact updatedContact = contactRepository.save(contact);
        log.info("Updated contact with ID: {}", id);
        return updatedContact;
    }

    public boolean deleteContact(int id) {
        if (!contactRepository.existsById(id)) {
            throw new AddressBookException("Contact with ID " + id + " not found.");
        }
        
        contactRepository.deleteById(id);
        log.info("Deleted contact with ID: {}", id);
        return true;
    }
}