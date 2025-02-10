package ru.anikson.example.walletapp.dao;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.anikson.example.walletapp.entity.Wallet;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long > {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findByWalletId(UUID walletId);
}
