package ru.anikson.example.walletapp.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.anikson.example.walletapp.dto.WalletOperationResponse;
import ru.anikson.example.walletapp.entity.Wallet;

@UtilityClass
public class WalletMapper {
    public WalletOperationResponse toWalletOperationResponse(Wallet wallet) {
        return new WalletOperationResponse(
                wallet.getWalletId(),
                wallet.getAmount()
        );
    }
}
