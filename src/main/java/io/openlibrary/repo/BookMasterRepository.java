package io.openlibrary.repo;

import io.openlibrary.domain.BookMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMasterRepository extends JpaRepository<BookMaster,Long> {
}
