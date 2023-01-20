package io.openlibrary.domain.system;

import io.openlibrary.common.util.CommonUtils;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Entity
@NoArgsConstructor
public class ConnectLog {
    @Id
    private Long connectLogId;
    @Id
    private String uri;
    @Column
    private Long requestTimestamp;
    @Column
    private Long responseTimestamp;
    @Column
    private Long durationMills;
    @Column(length = 500)
    private String requestHeader;
    @Column(length = 500)
    private String requestBody;
    @Column(length = 500)
    private String responseBody;

    public ConnectLog(String uri, Long requestTimestamp, Long responseTimestamp, String requestHeader, String requestBody, String responseBody) {
        this.uri = uri;
        this.requestTimestamp = requestTimestamp;
        this.responseTimestamp = responseTimestamp;
        this.durationMills = responseTimestamp - requestTimestamp;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public ConnectLog(Long requestTimestamp, Long responseTimestamp, CommonUtils commonUtils, ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = commonUtils.getHttpServletRequest();
        HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
        this.uri = request.getPathInfo();
        this.requestHeader = commonUtils.requestHeader(request);
        this.requestBody = commonUtils.requestBody(request);
        this.responseBody = commonUtils.responseBody(response);

        this.requestTimestamp = requestTimestamp;
        this.responseTimestamp = responseTimestamp;
        this.durationMills = responseTimestamp - requestTimestamp;
    }
}
