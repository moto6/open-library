package io.openlibrary.service;

import io.openlibrary.entity.domain.BookMaster;
import io.openlibrary.entity.domain.BookStock;
import io.openlibrary.entity.repositroy.BookMasterRepository;
import io.openlibrary.entity.repositroy.BookStockRepository;
import io.openlibrary.service.result_object.BookDetailRO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public List<BookMaster> searchByTitleV0Like(String keyword) {
        return bookMasterRepository.findAllByTitleLike("%"+ keyword+ "%");
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public List<BookMaster> searchByTitleV1Ngram(String keyword) {
        return null;
        //return bookMasterRepository;
    }

    public List<BookMaster> searchByTitleV2Elastic(String keyword) {
        return null;
    }
    public List<BookMaster> searchByAuthorV0Like(String keyword) {
        return bookMasterRepository.findAllByAuthorLike("%" + keyword + "%");
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
