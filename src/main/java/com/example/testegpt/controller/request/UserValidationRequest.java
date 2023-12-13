package com.example.testegpt.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserValidationRequest(
    @NotEmpty @Size(min = 6, max = 6) String codigo
) {}
