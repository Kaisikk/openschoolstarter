package com.kaisik.openschoolstarter.service.impl;

import com.kaisik.openschoolstarter.service.intr.RequestHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.time.Instant;

@Slf4j
public class RequestHandlerImpl implements RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, boolean needLogTime,
                              boolean needLogRequestType, boolean needLogHeaders, boolean needLogBody) {

        if (needLogTime) {
            Instant startTime = Instant.now();
            request.setAttribute("startTime", startTime);
        }

        if (needLogRequestType) {
            log.info("Запрос: метод запроса - {}, url запроса - {}", request.getMethod(), request.getRequestURI());
        }

        if (needLogHeaders) {
            request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                    log.info("Header: {} = {}", headerName, request.getHeader(headerName))
            );
        }

        if (needLogBody) {
            StringBuilder body = new StringBuilder();
            String line;
            // чтение тела запроса
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
                log.info("Тело запроса: {}", line);
                // глушение ошибок
            } catch (Exception exception) {
            }
        }
    }
}
