package com.example.AddressBookApp.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
}
