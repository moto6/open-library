package io.openlibrary.entity.system;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "PERSIST_LOG")
@Getter
public class PersistLog {
    @Id
    @GeneratedValue
    @Column(name = "PERSIST_LOG_ID")
    private Long persistLogId;
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "CONNECT_LOG_ID")
    private Long connectLogId;
    @Column(name = "BEGIN_TIMESTAMP")
    private Long beginTimestamp;
    @Column(name = "END_TIMESTAMP")
    private Long endTimestamp;
    @Column(name = "DURATION_TIME_MILLS")
    private Long DurationTimeMills; // end-begin
    @Column(name = "IS_SUCCESS")
    private Boolean isSuccess;
    //@Column(name = "QUERY")
    //private String query;

    public PersistLog(Long transactionId, Long connectLogId, Long beginTimestamp, Long endTimestamp, Boolean isSuccess) {
        this.transactionId = transactionId;
        this.connectLogId = connectLogId;
        this.beginTimestamp = beginTimestamp;
        this.endTimestamp = endTimestamp;
        this.DurationTimeMills = endTimestamp - beginTimestamp;
        this.isSuccess = isSuccess;
    }
}
