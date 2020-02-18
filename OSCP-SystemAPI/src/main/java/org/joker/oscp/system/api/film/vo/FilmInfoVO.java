package org.joker.oscp.system.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class FilmInfoVO implements Serializable {

    private Long filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    private int expectNum;
    private LocalDate showTime;
    private int boxNum;
    private String score;

}
