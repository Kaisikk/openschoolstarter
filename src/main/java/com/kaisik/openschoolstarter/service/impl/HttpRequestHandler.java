package com.kaisik.openschoolstarter.service.impl;

import com.kaisik.openschoolstarter.service.intr.RequestHandler;
import com.kaisik.openschoolstarter.service.intr.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Setter
public class HttpRequestHandler implements HandlerInterceptor {

    /**
     * Надо ли логировать тип запроса
     */
    private Boolean logRequestType;

    /**
     * Надо ли логировать тело запроса
     */
    private Boolean logRequestBody;

    /**
     * Надо ли логировать тело ответа
     */
    private Boolean logResponseBody;

    private Boolean logHeaders;

    /**
     * Надо ли логировать время запроса
     */
    private Boolean logResponseTime;

    /**
     * Сервис логирования запросов (можно реализовать свой)
     */
    private final RequestHandler requestHandler;

    /**
     * Сервис логирования ответов (можно реализовать свой)
     */
    private final ResponseHandler responseHandler;

    /**
     * Конструктор для хэндлеров реквеста и респонса
     *
     * @param requestHandler
     * @param responseHandler
     */
    public HttpRequestHandler(RequestHandler requestHandler, ResponseHandler responseHandler) {
        this.requestHandler = requestHandler;
        this.responseHandler = responseHandler;
    }

    /**
     * Выполняется при получении запроса
     *
     * @param request  Запрос
     * @param response Ответ
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // сама логика логирования
        requestHandler.handleRequest(request,
                Boolean.TRUE.equals(logResponseTime),
                Boolean.TRUE.equals(logRequestType),
                Boolean.TRUE.equals(logHeaders),
                Boolean.TRUE.equals(logRequestBody));
        return true;
    }

    /**
     * Выполняется перед отдачей ответа
     *
     * @param request  Запрос
     * @param response Ответ
     * @param handler  Сам хэндлер
     * @param ex       Ошибка (если есть)
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            log.error("При запроса произошла ошибка, uri запроса - {}", request.getRequestURI());
            handleException(response, ex);
            return;
        }
        responseHandler.handleResponse(request, response, Boolean.TRUE.equals(logResponseTime));
    }

    /**
     * Обработка ошибок
     *
     * @param response
     * @param ex
     */
    private void handleException(HttpServletResponse response, Exception ex) {
        log.error("При обработке запроса произошла ошибка: Message = " + ex.getMessage());
        try {
            // Устанавливаем статус ответа в зависимости от типа исключения
            if (ex instanceof NullPointerException) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Null pointer exception: " + ex.getMessage());
            } else if (ex instanceof IllegalArgumentException) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Illegal argument: " + ex.getMessage());
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Internal server error: " + ex.getMessage());
            }
        } catch (Exception exception) {
            log.error("Ошибка при обработке ошибки :). Message = " + exception.getMessage());
        }
    }
}

