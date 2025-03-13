package com.example.AddressBookApp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.AddressBookApp.dto.AddressBookDTO;
import com.example.AddressBookApp.Interface.AddressBookServiceInterface;
import com.example.AddressBookApp.repository.AddressRepository;
import com.example.AddressBookApp.model.AddressBookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService implements AddressBookServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(AddressBookService.class);

    @Autowired
    private AddressRepository repository;

    // Retrieve all contacts
    @Override
    public List<AddressBookDTO> getAllContacts() {
        log.info("Fetching all contacts from the database.");
        return repository.findAll().stream()
                .map(contact -> new AddressBookDTO(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail()))
                .collect(Collectors.toList());
    }

    // Save a new contact
    @Override
    public AddressBookDTO saveContact(AddressBookDTO dto) {
        log.info("Saving new contact: {}", dto);
        AddressBookModel contact = new AddressBookModel();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        AddressBookModel savedContact = repository.save(contact);
        log.info("Contact saved successfully with ID: {}", savedContact.getId());
        return new AddressBookDTO(savedContact.getId(), savedContact.getName(), savedContact.getPhone(), savedContact.getEmail());
    }

    // Retrieve a single contact by ID
    @Override
    public AddressBookDTO getContactById(Long id) {
        log.info("Fetching contact with ID: {}", id);
        Optional<AddressBookModel> contact = repository.findById(id);
        if (contact.isPresent()) {
            log.info("Contact found: {}", contact.get());
        } else {
            log.warn("Contact with ID {} not found.", id);
        }
        return contact.map(c -> new AddressBookDTO(c.getId(), c.getName(), c.getPhone(), c.getEmail()))
                .orElse(null);
    }

    // Update an existing contact by ID
    @Override
    public AddressBookDTO updateContact(Long id, AddressBookDTO dto) {
        log.info("Updating contact with ID: {}", id);
        Optional<AddressBookModel> existingContact = repository.findById(id);

        if (existingContact.isPresent()) {
            AddressBookModel contact = existingContact.get();
            contact.setName(dto.getName());
            contact.setPhone(dto.getPhone());
            contact.setEmail(dto.getEmail());
            AddressBookModel updatedContact = repository.save(contact);
            log.info("Contact updated successfully: {}", updatedContact);
            return new AddressBookDTO(updatedContact.getId(), updatedContact.getName(), updatedContact.getPhone(), updatedContact.getEmail());
        } else {
            log.warn("Attempted to update non-existing contact with ID: {}", id);
        }
        return null;
    }

    // Delete a contact by ID
    @Override
    public boolean deleteContact(Long id) {
        log.info("Deleting contact with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("Contact with ID {} deleted successfully.", id);
            return true;
        } else {
            log.warn("Attempted to delete non-existing contact with ID: {}", id);
        }
        return false;
    }
}