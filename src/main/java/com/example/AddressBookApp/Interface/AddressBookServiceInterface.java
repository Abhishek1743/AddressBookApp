package com.example.AddressBookApp.Interface;

import com.example.AddressBookApp.dto.AddressBookDTO;

import java.util.List;

public interface AddressBookServiceInterface {
    List<AddressBookDTO> getAllContacts();
    AddressBookDTO saveContact(AddressBookDTO dto);
}
