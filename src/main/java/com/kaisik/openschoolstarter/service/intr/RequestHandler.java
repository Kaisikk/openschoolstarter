package com.kaisik.openschoolstarter.service.intr;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestHandler {

    /**
     * Реализация логирования входящих запросов
     *
     * @param request            Запрос
     * @param needLogTime        Надо ли логировать время потраченное на ответ
     * @param needLogRequestType Надо ли логировать тип запроса
     * @param needLogHeaders     Надо ли логировать хидэры
     * @param needLogBody        Надо ли логировать тело запроса
     */
    void handleRequest(HttpServletRequest request, boolean needLogTime,
                       boolean needLogRequestType, boolean needLogHeaders, boolean needLogBody);

}
