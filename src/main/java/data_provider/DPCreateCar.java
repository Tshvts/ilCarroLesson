package data_provider;

import dto.CreateCarDto;
import enums.Fuel;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

import static utilits.RandomUtils.*;

public class DPCreateCar
{
        @DataProvider
        public CreateCarDto[] createNewCarPositive()
        {
            List<Fuel> fuels = new ArrayList<>();
            fuels.add(Fuel.DIESEL);
            fuels.add(Fuel.PETROL);
            fuels.add(Fuel.HYBRID);
            fuels.add(Fuel.ELECTRIC);
            fuels.add(Fuel.GAS);

            CreateCarDto createCar = CreateCarDto.builder()
                    .manufacture(generateString(7))
                    .model(generateString(6))
                    .year(generateYear())
                    .fuels(fuels)
                    .seats(generateSeats())
                    .carClass(generateString(5))
                    .carRegistrationNumber(generateCarRegistrationNumber(8,3))
                    .price(generatePrice())
                    .about(generateString(50))
                    .build();

            CreateCarDto createCar1 = CreateCarDto.builder()
                    .manufacture(generateString(8))
                    .model(generateString(7))
                    .year(generateYear())
                    .fuels(fuels)
                    .seats(generateSeats())
                    .carClass(generateString(6))
                    .carRegistrationNumber(generateCarRegistrationNumber(8,3))
                    .price(generatePrice())
                    .about(generateString(499))
                    .build();

            CreateCarDto createCar2 = CreateCarDto.builder()
                    .manufacture(generateString(9))
                    .model(generateString(10))
                    .year(generateYear())
                    .fuels(fuels)
                    .seats(generateSeats())
                    .carClass(generateString(6))
                    .carRegistrationNumber(generateCarRegistrationNumber(8,3))
                    .price(generatePrice())
                    .about(generateString(15))
                    .build();

                return new CreateCarDto[]{createCar, createCar1, createCar2};
        }
}
