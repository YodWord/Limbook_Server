package com.daelim.Limbook.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Board {

    private Integer board_number;
    private String user_id;
    private String board_title;
    private String board_contents;
    private Integer board_price;
    private Timestamp board_create_date;

}
