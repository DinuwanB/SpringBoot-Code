package com.nod.ecom.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;

}
