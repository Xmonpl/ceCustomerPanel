package org.eu.xmon.customerpanel.object;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TicketMessage {
    private String author;
    private String created;
    private String message;
}
