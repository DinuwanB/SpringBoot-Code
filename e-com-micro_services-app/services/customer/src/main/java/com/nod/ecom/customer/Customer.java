package com.nod.ecom.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

}
