package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.domain.BookMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookMasterRepository extends JpaRepository<BookMaster,Long> {
    BookMaster findByIsbnCode(String isbnCode);
}
