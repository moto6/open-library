package io.openlibrary.service;

import io.openlibrary.domain.Accounts;
import io.openlibrary.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Accounts login(String iamCode) {
        return accountRepository
                .findAccountsByIamCode(iamCode)
                .orElseThrow(() -> new RuntimeException("없어요"));
    }
}
