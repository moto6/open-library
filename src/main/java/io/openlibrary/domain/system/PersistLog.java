package io.openlibrary.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersistLog {
    @Id
    private Long persistLogId;
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column
    private Long connectLogId;
    @Column
    private Long beginTimestamp;
    @Column
    private Long endTimestamp;
    @Column
    private Long DurationTimeMills; // end-begin
    @Column
    private Boolean isSuccess;
    @Column
    private String query;
}
