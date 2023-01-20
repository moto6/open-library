package io.openlibrary.service;

import io.openlibrary.domain.Account;
import io.openlibrary.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    public Account login(String iamCode) {
        return accountRepository.findByIamCode(iamCode);
    }
}
