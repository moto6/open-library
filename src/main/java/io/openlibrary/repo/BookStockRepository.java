package io.openlibrary.repo;

import io.openlibrary.domain.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStockRepository extends JpaRepository<BookStock, Long> {

}
