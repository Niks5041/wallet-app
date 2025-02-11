package ru.anikson.example.walletapp.dto;


import ru.anikson.example.walletapp.entity.OperationType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

public record WalletOperationRequest(
        @NotNull
        UUID walletId,
        @NotNull
        OperationType operationType,
        @PositiveOrZero
        BigDecimal amount
) {
}

