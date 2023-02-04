package io.openlibrary.entity.repositroy;

import io.openlibrary.common.aop.advice.PersistLogger;
import io.openlibrary.entity.domain.BookMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@PersistLogger
public interface BookMasterRepository extends JpaRepository<BookMaster, Long> {
    BookMaster findByIsbnCode(String isbnCode);

    BookMaster save(BookMaster bookMaster);

    //@Modifying
    //@Query(value = "insert into BOOK_MASTER (title,author,publisher,ISBN_CODE) where not exists (select ISBN_CODE from BOOK_MASTER where ISBN_CODE='s') limit 1", nativeQuery = true)
    //void insertif(BookMaster typeMapping);





    List<BookMaster> findAllByTitleLike(String keyword);

    @Query(value = "SELECT * FROM BOOK_MASTER where match(TITLE) AGAINST(:keyword IN BOOLEAN MODE)",nativeQuery = true)
    List<BookMaster> findAllByTitleIndex(@Param("keyword") String keyword);

    List<BookMaster> findAllByAuthorLike(String keyword);

}
