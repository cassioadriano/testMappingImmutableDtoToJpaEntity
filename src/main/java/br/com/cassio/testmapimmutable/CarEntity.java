package br.com.cassio.testmapimmutable;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String make;
    private String model;
    private Integer numberOfSeats;

    @Enumerated(EnumType.STRING)
    private CarType type;

}
