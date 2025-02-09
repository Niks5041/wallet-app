package ru.anikson.example.walletapp.service;

import ru.anikson.example.walletapp.dto.WalletOperationRequest;
import ru.anikson.example.walletapp.dto.WalletOperationResponse;

import java.util.UUID;

public interface WalletService {

    WalletOperationResponse updateWallet(WalletOperationRequest walletOperationRequest);

    WalletOperationResponse getBalance(UUID walletId);
}
