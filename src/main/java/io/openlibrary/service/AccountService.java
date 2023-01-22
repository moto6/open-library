package io.openlibrary.service;

import io.openlibrary.domain.entity.Accounts;
import io.openlibrary.domain.repositroy.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Accounts> accountList() {
        return null;
    }

    public Accounts accoutDetail() {
        return null;
    }
}
