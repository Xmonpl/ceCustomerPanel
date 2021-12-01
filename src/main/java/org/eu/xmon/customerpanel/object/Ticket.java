package org.eu.xmon.customerpanel.object;

import com.dieselpoint.norm.serialize.DbSerializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;
import org.eu.xmon.customerpanel.converters.TicketMessageConverter;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Basic
    @Column(name = "id", nullable = false, unique = true)
    public String id;

    @Column(name = "userid")
    private String user_id;

    @Column(name = "priority")
    @Enumerated
    private TicketPriorityEnum priority;

    @Column(name = "topic")
    private String topic;

    @Column
    @Convert(converter = TicketMessageConverter.class)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public List<TicketMessage> messages;

    @Column(name = "closed")
    private String closed;

    @Transient
    public UUID getId() {
        return UUID.fromString(id);
    }

    @Transient
    public List<TicketMessage> getMessages() {
        return messages;
    }
}
