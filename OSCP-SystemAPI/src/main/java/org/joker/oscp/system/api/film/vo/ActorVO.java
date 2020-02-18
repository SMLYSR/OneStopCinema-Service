package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActorVO implements Serializable {

    private String imgAddress;
    private String directorName;
    private String roleName;

}
