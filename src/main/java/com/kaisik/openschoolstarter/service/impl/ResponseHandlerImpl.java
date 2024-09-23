package com.kaisik.openschoolstarter.service.impl;

import com.kaisik.openschoolstarter.service.intr.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class ResponseHandlerImpl implements ResponseHandler {
    @Override
    public void handleResponse(HttpServletRequest request, HttpServletResponse response, boolean needLogTime) {

        if (needLogTime) {
            Long startTime = (Long) request.getAttribute("startTime");
            Long executeTime = System.currentTimeMillis() - startTime;
            log.info("Запрос: метод запроса - {}, url запроса - {}, время ответа - {}", request.getMethod(), request.getRequestURI(), executeTime);
        } else {
            log.info("Запрос: метод запроса - {}, url запроса - {}, время ответа - {}", request.getMethod(), request.getRequestURI());
        }
    }

}
