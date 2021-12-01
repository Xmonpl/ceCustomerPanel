package org.eu.xmon.customerpanel.object;

import com.dieselpoint.norm.serialize.DbSerializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

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
    public Integer id;

    @Column(name = "userid")
    private String user_id;

    @Column(name = "priority")
    private String priority;

    @Column(name = "topic")
    private String topic;

    @Column(name = "message")
    private String message;

    @Column
    @Convert(converter = ColorConverter.class)
    private Color color;

    @Transient
    public Integer gettwojastara() {
        return id;
    }
}
