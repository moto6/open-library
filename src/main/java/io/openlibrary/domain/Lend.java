package io.openlibrary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Lend {
    @Id
    @GeneratedValue
    @Column(name = "LEND_ID")
    private Long lendId;
    @NotBlank
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @NotBlank
    @Column(name = "BOOK_STOCK_ID")
    private Long bookStockId;
    @NotBlank
    @Column(name = "LEND_TIMESTAMP")
    private Long lendTimestamp;
    @NotBlank
    @Column(name = "RETRIEV_TIMESTAMP")
    private Long retrieveTimestamp;
    @NotBlank
    @Column(name = "RETRIEV_EXTENSION_COUNT")
    private Long retrieveExtensionCount;
    @NotBlank
    @Column(name = "IS_RETRIEVED")
    private Boolean isRetrieved;

}
