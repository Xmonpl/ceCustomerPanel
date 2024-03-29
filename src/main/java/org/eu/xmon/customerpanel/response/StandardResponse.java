package org.eu.xmon.customerpanel.response;

import com.google.gson.JsonElement;
import lombok.Builder;

@Builder
public class StandardResponse {
    private StatusResponse status;
    private String message;
    private JsonElement data;

    public StandardResponse(StatusResponse status) {
        this.status = status;
    }
    public StandardResponse(StatusResponse status, String message) {
        this.status = status;
        this.message = message;
    }
    public StandardResponse(StatusResponse status, String message, JsonElement data) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    public StandardResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.data = data;
    }
}