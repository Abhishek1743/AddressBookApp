package com.example.AddressBookApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class AddressBookDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
}