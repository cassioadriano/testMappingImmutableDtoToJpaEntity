package br.com.cassio.testmapimmutable;

import br.com.cassio.testmapimmutable.CarDto.CarDtoBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-16T18:29:32-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto carToCarDto(CarEntity car) {
        if ( car == null ) {
            return null;
        }

        CarDtoBuilder carDto = CarDto.builder();

        carDto.seatCount( car.getNumberOfSeats() );
        carDto.id( car.getId() );
        carDto.make( car.getMake() );
        carDto.model( car.getModel() );
        if ( car.getType() != null ) {
            carDto.type( car.getType().name() );
        }

        return carDto.build();
    }

    @Override
    public CarEntity carDtoToCar(CarDto car) {
        if ( car == null ) {
            return null;
        }

        CarEntity carEntity = new CarEntity();

        carEntity.setNumberOfSeats( car.getSeatCount() );
        carEntity.setId( car.getId() );
        carEntity.setMake( car.getMake() );
        carEntity.setModel( car.getModel() );
        if ( car.getType() != null ) {
            carEntity.setType( Enum.valueOf( CarType.class, car.getType() ) );
        }

        return carEntity;
    }
}
