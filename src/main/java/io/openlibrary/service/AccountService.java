package io.openlibrary.service;

import io.openlibrary.domain.Accounts;
import io.openlibrary.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    public Accounts login(String iamCode) {
        return accountRepository.findByIamCode(iamCode);
    }
}
