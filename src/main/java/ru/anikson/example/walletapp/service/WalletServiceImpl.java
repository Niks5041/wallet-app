package ru.anikson.example.walletapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anikson.example.walletapp.dao.WalletRepository;
import ru.anikson.example.walletapp.dto.WalletOperationRequest;
import ru.anikson.example.walletapp.dto.WalletOperationResponse;
import ru.anikson.example.walletapp.dto.mapper.WalletMapper;
import ru.anikson.example.walletapp.entity.Wallet;
import ru.anikson.example.walletapp.exception.NotFoundException;
import ru.anikson.example.walletapp.exception.ValidationException;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public WalletOperationResponse updateWallet(WalletOperationRequest walletOperationRequest) {
        log.info("==> Updating wallet: {}", walletOperationRequest);

        var existWallet = walletRepository.findByWalletId(walletOperationRequest.walletId())
                .orElseThrow(() -> new NotFoundException("Wallet not found: " + walletOperationRequest.walletId()));
        var amount = walletOperationRequest.amount();

        switch (walletOperationRequest.operationType()) {
            case DEPOSIT -> deposit(amount, existWallet);
            case WITHDRAW -> withdraw(amount, existWallet);
            default -> throw new IllegalArgumentException("Invalid operation type");
        }

        WalletOperationResponse response = WalletMapper.toWalletOperationResponse(existWallet);
        log.info("<== Wallet updated: {}", response);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public WalletOperationResponse getBalance(UUID walletId) {
        log.info("==> Getting balance for wallet ID: {}", walletId);

        WalletOperationResponse response = WalletMapper.toWalletOperationResponse(
                walletRepository.findByWalletId(walletId)
                        .orElseThrow(() -> new NotFoundException("Wallet not found: " + walletId))
        );

        log.info("<== Balance retrieved: {}, Amount: {}", walletId, response.amount());
        return response;
    }

    @Transactional
    private void deposit(BigDecimal amount, Wallet wallet) {
        log.info("==> Depositing: Wallet ID: {}, Amount: {}", wallet.getWalletId(), amount);
        wallet.setAmount(wallet.getAmount().add(amount));
        walletRepository.save(wallet);
        log.info("Deposit successful. Wallet ID: {}, New Balance: {}", wallet.getWalletId(), wallet.getAmount());
    }

    @Transactional
    private void withdraw(BigDecimal amount, Wallet wallet) {
        log.info("==> Withdrawing: Wallet ID: {}, Amount: {}", wallet.getWalletId(), amount);

        if (wallet.getAmount().compareTo(amount) < 0) {
            log.warn("Withdrawal failed. Insufficient funds. Wallet ID: {}, Balance: {}", wallet.getWalletId(), wallet.getAmount());
            throw new ValidationException("Insufficient funds");
        }

        wallet.setAmount(wallet.getAmount().subtract(amount));
        walletRepository.save(wallet);
        log.info("Withdrawal successful. Wallet ID: {}, New Balance: {}", wallet.getWalletId(), wallet.getAmount());
    }
}

