package io.openlibrary.service;

import io.openlibrary.entity.domain.BookMaster;
import io.openlibrary.entity.domain.BookStock;
import io.openlibrary.entity.repositroy.BookMasterRepository;
import io.openlibrary.entity.repositroy.BookStockRepository;
import io.openlibrary.service.result_object.BookDetailRO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public List<BookMaster> searchByTitleLike(String keyword) {
        String match = "%"+ keyword+ "%";
        return bookMasterRepository.findAllByTitleLike(match);
    }

    public List<BookMaster> searchByAuthorLike(String keyword) {
        return bookMasterRepository.findAllByAuthorLike(keyword);
    }

    public List<BookMaster> searchByTitleNgram(String keyword) {
        //todo : 엔그램인덱스 타랏
        return null;
    }

    public List<BookMaster> searchByAuthorNgram(String keyword) {
        //todo : 엔그렘인덱스
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
