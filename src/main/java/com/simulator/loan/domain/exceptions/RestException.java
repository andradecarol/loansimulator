package com.simulator.loan.domain.exceptions;


import com.simulator.loan.domain.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public abstract class RestException extends RuntimeException {

    private final String code;

    public abstract HttpStatus getStatus();


    protected RestException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * If an exception has a properties mapped response body code and message, it must override this method. If it has a runtime customized response
     * body, override this method {@link RestException#getResponseBody() getResponseBody}.
     *
     * @return response body code
     */
    public String getResponseBodyCode() {
        return code;
    }

    /**
     * If an exception has a runtime customized response body, it must override this method. If it has a properties mapped response body code and
     * message, override the method {@link RestException#getResponseBodyCode() getResponseBodyCode}.
     *
     * @return response body
     */
    public ErrorResponse getResponseBody() {
        return new ErrorResponse(code);
    }

}
