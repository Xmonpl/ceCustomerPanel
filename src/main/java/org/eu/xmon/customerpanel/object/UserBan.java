package org.eu.xmon.customerpanel.object;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class UserBan {
    private String who;
    private String reason;
    private long until_when;
}
