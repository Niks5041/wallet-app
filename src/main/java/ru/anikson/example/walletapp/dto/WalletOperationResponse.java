package ru.anikson.example.walletapp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletOperationResponse(
        UUID walletId,
        BigDecimal amount
) {
}
