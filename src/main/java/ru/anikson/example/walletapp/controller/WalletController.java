package ru.anikson.example.walletapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anikson.example.walletapp.dto.WalletOperationRequest;
import ru.anikson.example.walletapp.dto.WalletOperationResponse;
import ru.anikson.example.walletapp.service.WalletService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallet")
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletOperationResponse updateWallet(@Valid @RequestBody WalletOperationRequest walletOperationRequest) {
        log.info("==> POST. Добавление кошелька для клиента с ID: {}", walletOperationRequest.walletId());
        WalletOperationResponse receivedWallet = walletService.updateWallet(walletOperationRequest);
        log.info("<== POST. Добавлен кошелек для клиента с ID: {}", receivedWallet.walletId());
        return receivedWallet;
    }

    @GetMapping("/{WALLET_UUID}")
    @ResponseStatus(HttpStatus.OK)
    public WalletOperationResponse getBalance(@PathVariable UUID walletId) {
        log.info("==> GET. Получение кошелька для клиента с ID: {}", walletId);
        WalletOperationResponse receivedWallet = walletService.getBalance(walletId);
        log.info("<== GET. Получен кошелек для клиента с ID: {}", receivedWallet.walletId());
        return receivedWallet;
    }
}
