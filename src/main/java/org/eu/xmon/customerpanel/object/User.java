package org.eu.xmon.customerpanel.object;

import com.dieselpoint.norm.serialize.DbSerializer;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class User implements Serializable {
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

    @Column(name = "first_ip")
    private String first_ip;

    @Column(name = "last_ip")
    private String last_ip;

    @Column(name = "balance")
    private double balance;

    @Transient
    public String getUUID(){
        return id;
    }

   /*@Column(name = "actions")
    @DbSerializer(JSONSerializer.class)
    private List<String> actions;
    */


}
