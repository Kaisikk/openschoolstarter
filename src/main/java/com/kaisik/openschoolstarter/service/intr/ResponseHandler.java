package com.kaisik.openschoolstarter.service.intr;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ResponseHandler {


    void handleResponse(HttpServletRequest request, HttpServletResponse response, boolean needLogTime);

}
