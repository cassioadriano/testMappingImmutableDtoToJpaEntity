package br.com.cassio.testmapimmutable;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(CarEntity car);

    @Mapping(source = "seatCount", target = "numberOfSeats")
    CarEntity carDtoToCar(CarDto car);
}
