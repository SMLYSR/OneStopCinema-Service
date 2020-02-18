package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmDescVO implements Serializable {

    private String biography;
    private Long filmId;

}
