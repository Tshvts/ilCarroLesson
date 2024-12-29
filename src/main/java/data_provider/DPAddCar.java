package data_provider;

import dto.CarDto;
import enums.Fuel;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DPAddCar
{
    @DataProvider
    public CarDto[] addNewCarPositive()
    {
        CarDto car1 = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "Nfd")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-99")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .price(123.99)
                .about("About my car")
                .build();

        CarDto car2 = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "Nfd")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-99")
                .year("2022")
                .fuel(Fuel.PETROL.getLocator())
                .seats(2)
                .carClass("A")
                .price(150)
                .about("About my car")
                .build();

        CarDto car3 = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "Nfd")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-99")
                .year("2022")
                .fuel(Fuel.GAS.getLocator())
                .seats(3)
                .carClass("A")
                .price(145.99)
                .about("About my car")
                .build();
        return new CarDto[]{car1, car2,car3};
    }

    @DataProvider
    public CarDto[] addCarNegative_WrongCity()
    {
        CarDto car1 = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "Nfd")
                .city(new Random().nextInt(1000) + "")
                .manufacture("Mazda")
                .model("CX-99")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .price(123.99)
                .about("About my car")
                .build();

        CarDto car2 = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "Nfd")
                .city(new Random().nextInt(1000) + "")
                .manufacture("Mazda")
                .model("CX-99")
                .year("2022")
                .fuel(Fuel.PETROL.getLocator())
                .seats(2)
                .carClass("A")
                .price(150)
                .about("About my car")
                .build();

        CarDto car3 = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "Nfd")
                .city(new Random().nextInt(1000) + "")
                .manufacture("Mazda")
                .model("CX-99")
                .year("2022")
                .fuel(Fuel.GAS.getLocator())
                .seats(3)
                .carClass("A")
                .price(145.99)
                .about("About my car")
                .build();
        return new CarDto[]{car1, car2,car3};
    }
}
