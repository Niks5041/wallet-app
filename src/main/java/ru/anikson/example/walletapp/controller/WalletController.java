package ru.anikson.example.walletapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.anikson.example.walletapp.dto.WalletOperationRequest;
import ru.anikson.example.walletapp.dto.WalletOperationResponse;
import ru.anikson.example.walletapp.service.WalletService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallet")
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @RequestMapping(value = "/api/v1/wallet", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public WalletOperationResponse updateWallet(@RequestBody WalletOperationRequest walletOperationRequest) {
        log.info("==> POST. Добавление кошелька для клиента с ID: {}", walletOperationRequest.walletId());
        WalletOperationResponse receivedWallet = walletService.updateWallet(walletOperationRequest);
        log.info("<== POST. Добавлен кошелек для клиента с ID: {}", receivedWallet.walletId());
        return receivedWallet;
    }

    @RequestMapping(value = "/api/v1/wallet/{walletId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WalletOperationResponse getBalance(@PathVariable UUID walletId) {
        log.info("==> GET. Получение кошелька для клиента с ID: {}", walletId);
        WalletOperationResponse receivedWallet = walletService.getBalance(walletId);
        log.info("<== GET. Получен кошелек для клиента с ID: {}", receivedWallet.walletId());
        return receivedWallet;
    }
}
