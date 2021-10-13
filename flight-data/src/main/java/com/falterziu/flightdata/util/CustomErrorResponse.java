package com.falterziu.flightdata.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CustomErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private Integer status;
    private List<String> message;

}
