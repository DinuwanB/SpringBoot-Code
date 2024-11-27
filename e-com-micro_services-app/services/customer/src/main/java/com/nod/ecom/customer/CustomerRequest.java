package com.nod.ecom.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,

         @NotNull(message = "First name is required!")
         String firstName,

         @NotNull(message = "Last name is required!")
         String lastName,

         @NotNull(message = "Email is required!")
         @Email(message = "Email is not valid")
         String email,
         @NotNull(message = "Email is required!")
         Address address
) {
}
