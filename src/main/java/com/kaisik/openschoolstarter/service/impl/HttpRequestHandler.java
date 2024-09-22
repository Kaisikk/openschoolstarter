package com.kaisik.openschoolstarter.service.impl;

import com.kaisik.openschoolstarter.service.intr.RequestHandler;
import com.kaisik.openschoolstarter.service.intr.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;

@Component
@AllArgsConstructor
@Slf4j
public class HttpRequestHandler implements HandlerInterceptor {

    /**
     * Надо ли логировать тип запроса
     */
    private final Boolean logRequestType;

    /**
     * Надо ли логировать тело запроса
     */
    private final Boolean logRequestBody;

    /**
     * Надо ли логировать тело ответа
     */
    private final Boolean logResponseBody;

    private final Boolean logHeaders;

    /**
     * Надо ли логировать время запроса
     */
    private final Boolean logResponseTime;

    /**
     * Сервис логирования запросов (можно реализовать свой)
     */
    private final RequestHandler requestHandler;

    /**
     * Сервис логирования ответов (можно реализовать свой)
     */
    private final ResponseHandler responseHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        requestHandler.handleRequest(request,
                Boolean.TRUE.equals(logResponseTime),
                Boolean.TRUE.equals(logRequestType),
                Boolean.TRUE.equals(logHeaders),
                Boolean.TRUE.equals(logRequestBody));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
