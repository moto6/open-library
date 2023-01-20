package io.openlibrary.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CommonUtils {
    public HttpServletRequest getHttpServletRequest() {
        return Objects.requireNonNull(((((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest())));
    }

    public String responseBody(final HttpServletResponse response) {
        try {
            String payload = null;
            ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {
                wrapper.setCharacterEncoding("UTF-8");
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                    wrapper.copyBodyToResponse();
                }
            }
            return null == payload ? " - " : payload;
        } catch (IOException e) {
            System.out.println("에러발생");
        }
        return "EMPTY HTTP-RESPONSE BODY";
    }

    public String requestBody(HttpServletRequest request) {
        try (
                InputStream InputStream = request.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(InputStream));
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            String body = stringBuilder.toString();

            if (StringUtils.hasText(body)) {
                return body;
            }
            //return "";???
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "EMPTY HTTP-RESPONSE BODY";
    }

    public String requestHeader(HttpServletRequest request) {
        return headerToString(request.getParameterMap());
    }

    private String headerToString(Map<String, String[]> headerMap) {
        return headerMap.entrySet().stream()
                .map(header -> String.format("%s:%s",header.getKey(), Arrays.toString(header.getValue())))
                .collect(Collectors.joining(", "));
    }

    public String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || "".equals(ip)) {
            ip = "???";
        }
        return ip;
    }


//    private static Operation getOperation(ProceedingJoinPoint proceedingJoinPoint) {
//        return ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(Operation.class);
//    }
}
