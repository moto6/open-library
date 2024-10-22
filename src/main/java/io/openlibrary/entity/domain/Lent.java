package io.openlibrary.entity.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "LENT")
public class Lent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
