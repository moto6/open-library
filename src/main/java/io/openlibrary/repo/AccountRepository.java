package io.openlibrary.repo;

import io.openlibrary.domain.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Accounts findByIamCode(String iamCode);
}
