package io.openlibrary.entity.system;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Getter
@Table(name = "FAULT_LOG")
@NoArgsConstructor
public class FaultLog {
    @Id
    @GeneratedValue
    @Column(name = "FAULT_LOG_ID")
    private Long faultLogId;
    @NotEmpty
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "FAULT_TIMESTAMP")
    private Long faultTimestamp;
    @Column(length = 500, name = "DATA")
    private String data;

    public FaultLog(Long transactionId, String data) {
        this.transactionId = transactionId;
        this.faultTimestamp = Instant.now().getEpochSecond();
        this.data = data;
    }
}
