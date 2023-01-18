package io.openlibrary.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FaultLog {
    @Id
    private Long faultLogId;
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column
    private Long timestamp;
    @Column(length = 500)
    private String data;
}
