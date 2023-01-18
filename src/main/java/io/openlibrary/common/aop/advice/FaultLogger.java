package io.openlibrary.common.aop.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FaultLogger {
}
// MyException 터질때 로그담기
// 아니믄 errorlog 터질때 담기
// NPE 터질때 담기