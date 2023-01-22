package io.openlibrary.service;

import io.openlibrary.connect.dto.BookQueryDto;
import io.openlibrary.entity.domain.BookMaster;
import io.openlibrary.entity.domain.BookStock;
import io.openlibrary.entity.repositroy.BookMasterRepository;
import io.openlibrary.entity.repositroy.BookStockRepository;
import io.openlibrary.service.result_object.BookDetailRO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookMasterRepository bookMasterRepository;
    private final BookStockRepository bookStockRepository;

    public List<BookStock> stockList() {
        List<BookStock> o = null;
        return o;
    }

    public List<BookMaster> search(BookQueryDto bookQueryDto) {
        return null;
    }


    public List<BookMaster> masterList() {
        List<BookMaster> o = null;
        return o;
    }

    public BookMaster masterAdd() {
        return null;
    }

    public BookMaster masterModify(String isbnCode) {
        return null;
    }

    public BookDetailRO detailByCode(String bookMasterCode) {
        return null;
    }

    public BookDetailRO detailByisbn(String isbnCode) {
        return null;
    }
}
