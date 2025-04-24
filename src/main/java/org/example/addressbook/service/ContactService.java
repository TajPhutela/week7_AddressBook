package org.example.addressbook.service;
import org.example.addressbook.dto.ContactDTO;
import org.example.addressbook.model.Contact;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class ContactService {
    private final Map<Integer, Contact> contactMap = new HashMap<>();
    private int idCounter = 1;

    public List<Contact> getAllContacts() {
        log.debug("Retrieving all contacts - Total contacts: {}", contactMap.size());
        return new ArrayList<>(contactMap.values());
    }

    public Contact getContactById(int id) {
        Contact contact = contactMap.get(id);
        if (contact != null) {
            log.debug("Contact found with ID: {}", id);
        } else {
            log.warn("Attempt to retrieve non-existent contact with ID: {}", id);
        }
        return contact;
    }

    public Contact addContact(ContactDTO dto) {
        Contact contact = new Contact(idCounter++, dto.getName(), dto.getPhone());
        contactMap.put(contact.getId(), contact);
        log.info("Added new contact - ID: {}, Name: {}", contact.getId(), contact.getName());
        return contact;
    }

    public Contact updateContact(int id, ContactDTO dto) {
        if (!contactMap.containsKey(id)) {
            log.error("Failed to update contact - Contact not found with ID: {}", id);
            return null;
        }
        Contact updated = new Contact(id, dto.getName(), dto.getPhone());
        contactMap.put(id, updated);
        log.info("Updated contact - ID: {}, New Name: {}", id, dto.getName());
        return updated;
    }

    public boolean deleteContact(int id) {
        boolean removed = contactMap.remove(id) != null;
        if (removed) {
            log.info("Deleted contact with ID: {}", id);
        } else {
            log.warn("Attempt to delete non-existent contact with ID: {}", id);
        }
        return removed;
    }
}