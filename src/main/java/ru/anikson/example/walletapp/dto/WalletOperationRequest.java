package ru.anikson.example.walletapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import ru.anikson.example.walletapp.entity.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletOperationRequest(
        @NotNull
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "Invalid UUID format")
        UUID walletId,

        @NotNull
        OperationType operationType,

        @NotNull
        @Positive
        BigDecimal amount
) {
}

