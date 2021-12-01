package org.eu.xmon.customerpanel.object;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "actions")
public class Action {
    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Basic
    @Column(name = "id", nullable = false, unique = true)
    public Integer id;

    @Column(name = "userid")
    private String user_id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "useragent")
    private String useragent;

    @Column(name = "actiontype")
    private String actionStatus;

    @Column(name = "created_at")
    private String timestamp;

    @Transient
    public Integer gettwojastara() {
        return id;
    }
}
