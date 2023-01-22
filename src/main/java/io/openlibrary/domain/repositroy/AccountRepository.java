package io.openlibrary.domain.repositroy;

import io.openlibrary.domain.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findAccountsByIamCode(String iamCode);

    Optional<Accounts> findAccountsByIamCodeContains(String iamcode);
}
