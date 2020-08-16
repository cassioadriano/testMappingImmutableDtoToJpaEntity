package br.com.cassio.testmapimmutable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TestmapimmutableApplicationTests {

	@Autowired
	private CarRepository carRepository;

	@Test
	public void shouldMapCarToDto() {
		//given
		CarEntity car = new CarEntity();
		car.setMake("Morris");
		car.setModel("Oxford");
		car.setNumberOfSeats(5);
		car.setType(CarType.SEDAN);

		//when
		CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );

		//then
		assertThat( carDto ).isNotNull();
		assertThat( carDto.getMake() ).isEqualTo( "Morris" );
		assertThat( carDto.getModel() ).isEqualTo( "Oxford" );
		assertThat( carDto.getSeatCount() ).isEqualTo( 5 );
		assertThat( carDto.getType()).isEqualTo( "SEDAN" );

	}

	@Test
	public void shouldMapDtoToCar() {
		//given
		CarDto dto = CarDto.builder()
				.make("Morris")
				.model("Oxford")
				.seatCount(5)
				.type(CarType.SEDAN.toString())
				.build();

		//when
		CarEntity car = CarMapper.INSTANCE.carDtoToCar( dto );

		//then
		assertThat( car ).isNotNull();
		assertThat( car.getMake() ).isEqualTo( "Morris" );
		assertThat( car.getModel() ).isEqualTo( "Oxford" );
		assertThat( car.getNumberOfSeats() ).isEqualTo( 5 );
		assertThat( car.getType()).isEqualTo( CarType.SEDAN );

	}

	@Test
	public void shouldFindMapCarToDto() {
		//given
		List<CarEntity> cars = carRepository.findAll();

		//when
		List<CarDto> carsDto = cars.stream()
				.map( carDto -> CarMapper.INSTANCE.carToCarDto( carDto ))
				.collect(Collectors.toList());

		//then
		carsDto.stream()
				.forEach(carDto -> {
					CarEntity car = cars.stream().filter(c -> c.getId()
							.equals(carDto.getId())).findFirst().get();
					assertThat( carDto ).isNotNull();
					assertThat( carDto.getMake() ).isEqualTo( car.getMake() );
					assertThat( carDto.getModel() ).isEqualTo( car.getModel() );
					assertThat( carDto.getSeatCount() ).isEqualTo( car.getNumberOfSeats() );
					assertThat( carDto.getType()).isEqualTo( car.getType().toString() );
				});
	}

	@Test
	public void shouldSaveMapCarToDto() {
		//given
		CarDto carDto = CarDto.builder()
				.make("Morris")
				.model("Oxford")
				.seatCount(5)
				.type(CarType.SEDAN.toString())
				.build();

		//when
		CarEntity car = carRepository.save(CarMapper.INSTANCE.carDtoToCar( carDto ));

		//then
		assertThat( car ).isNotNull();
		assertThat( car.getMake() ).isEqualTo( carDto.getMake() );
		assertThat( car.getModel() ).isEqualTo( carDto.getModel() );
		assertThat( car.getNumberOfSeats() ).isEqualTo( carDto.getSeatCount() );
		assertThat( car.getType().toString()).isEqualTo( carDto.getType() );

		carDto = CarMapper.INSTANCE.carToCarDto(car).withMake("GM").withModel("Corsa").withType(CarType.SEDAN.toString());

		CarEntity carUpdated = carRepository.save(CarMapper.INSTANCE.carDtoToCar( carDto ));
		assertThat( carUpdated ).isNotNull();
		assertThat( carUpdated.getId() ).isEqualTo( carDto.getId() );
		assertThat( carUpdated.getMake() ).isEqualTo( carDto.getMake() );
		assertThat( carUpdated.getModel() ).isEqualTo( carDto.getModel() );
		assertThat( carUpdated.getNumberOfSeats() ).isEqualTo( carDto.getSeatCount() );
		assertThat( carUpdated.getType().toString()).isEqualTo( carDto.getType() );

	}
}
