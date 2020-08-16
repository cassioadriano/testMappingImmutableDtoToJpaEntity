package br.com.cassio.testmapimmutable;

import lombok.*;

@Value
@Builder
@Getter
@With
public class CarDto {

    private Integer id;
    private String make;
    private String model;
    private Integer seatCount;
    private String type;

}
