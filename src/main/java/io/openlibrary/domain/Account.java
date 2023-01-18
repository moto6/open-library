package io.openlibrary.domain;


import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private Long accountId;
    @Column(name = "ACCOUNT_BARCODE")
    private String accountBarCode;
    @Column
    private String iamCode;
    @Column
    private String note;

}
