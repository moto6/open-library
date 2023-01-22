package io.openlibrary.common.aop;

import io.openlibrary.common.util.CommonUtils;
import io.openlibrary.domain.system_entity.ConnectLog;
import io.openlibrary.domain.repositroy.ConnectRepository;
import io.openlibrary.domain.repositroy.FaultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class SystemAspects {

    private final ConnectRepository connectRepository;
    private final FaultRepository faultRepository;
    private final CommonUtils commonUtils;
    @Pointcut("@annotation(io.openlibrary.common.aop.advice.FaultLogger)")
    private void FaultLogPointcut() {
    }


    @Pointcut("@annotation(io.openlibrary.common.aop.advice.PersistLogger)")
    private void PersistLogPointcut() {
    }


    @Pointcut("@annotation(io.openlibrary.common.aop.advice.ConnectLogger)")
    private void ConnectLogPointcut() {
    }


    @Around("ConnectLogPointcut()")
    public Object connectLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long requestTime = Instant.now().getEpochSecond();
        Object proceed = joinPoint.proceed();
        long responseTime = Instant.now().getEpochSecond();
        connectRepository.save(new ConnectLog(requestTime, responseTime, commonUtils, joinPoint));
        return proceed;
    }

    //within(java.lang.RuntimeException)
    @After("FaultLogPointcut()")
    public void writeLog(JoinPoint joinPoint) throws RuntimeException {
        //logging
        //joinPoint.getArgs() 를 사용하여 해당 메서드의 파라미터를 가져올 수 있다.
    }


//    @AfterThrowing(value = "execution(io.openlibrary.common.exception.MyLibraryException)", throwing = "exception")
//    public void writeFailLog(JoinPoint joinPoint, Exception exception) throws RuntimeException {
//        StringWriter errors = new StringWriter();
//        exception.printStackTrace(new PrintWriter(errors));
//        faultRepository.save(new FaultLog());
//    }
}

/*
PrintWriter : 문자 기반 클래스, 문자 배열과 함께 작동
  - 일반적인 텍스트작업에
  - 파일IO, 네트워크소켓
  - 버퍼플러시 기능도 있음

PrintStream : 바이트 기반 클래스, 바이트 및 바이트 배열과 함께 작동하도록 설계된거임
 - 바이너리 데이터
*/