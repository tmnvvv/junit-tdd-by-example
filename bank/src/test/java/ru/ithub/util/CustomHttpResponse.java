package ru.ithub.util;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

/**
 * Класс заплатки HttpResponse - эмитирует ошибочный ответ при запросе к api
 *
 * @see HttpResponse
 * @see FCCAPIUtil
 */
public class CustomHttpResponse implements HttpResponse {
    private int statusCode;
    private Object body;

    public CustomHttpResponse(int statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public int statusCode() {
        return this.statusCode;
    }

    @Override
    public HttpRequest request() {
        return null;
    }

    @Override
    public Optional<HttpResponse> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @Override
    public Object body() {
        return this.body;
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public HttpClient.Version version() {
        return null;
    }
}
