package org.backend.senebank.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class WebUtils {
    @Value("${api.v1.prefix}")
    private String apiV1Prefix;

    /**
     * Возвращает базовый URL сайта на основе запроса.
     *
     * @param request объект HttpServletRequest
     * @return базовый URL сайта
     */
    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    /**
     * Отправляет JSON-ответ на основе указанных параметров.
     *
     * @param response       объект HttpServletResponse, через который будет отправлен ответ
     * @param statusCode     статусный код HTTP для ответа
     * @param responseObject объект, который будет преобразован в JSON и отправлен в ответе
     * @throws IOException если произошла ошибка ввода-вывода при отправке ответа
     */
    public void sendJsonResponse(HttpServletResponse response, int statusCode, Object responseObject) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(responseObject);
        response.getWriter().write(json);
    }
}