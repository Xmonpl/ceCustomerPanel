package org.eu.xmon.customerpanel.object;

import com.dieselpoint.norm.serialize.DbSerializer;
import lombok.*;
import org.eu.xmon.customerpanel.converters.UserBanConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author Xmon
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User{
    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public String id;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "password")
    private String password;

    @Column(name = "create_time")
    private String create_time;

    @Column(name = "ip_first")
    private String ip_first;

    @Column(name = "ip_last")
    private String ip_last;

    @Column(name = "balance")
    private double balance;

    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private int active;

    @Column(name = "ban")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Convert(converter = UserBanConverter.class)
    public UserBan ban;

    @Transient
    public String getUUID(){
        return id;
    }

    @Transient
    public UserBan getBan(){
        return ban;
    }


}
