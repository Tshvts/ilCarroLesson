package data_provider;

import dto.CarDto;
import enums.Fuel;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import static utilits.PropertiesReader.*;

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

    @DataProvider
    public Iterator<CarDto> dataProviderCarFile() throws IOException
    {
        List<CarDto> carDtoList = new ArrayList<>();
        BufferedReader bufferedReader;
        try
        {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/data_carNew.csv"));
            String line = bufferedReader.readLine();
            while(line!=null)
            {
                String[] splitArray = line.split(";");
                CarDto car = CarDto.builder()
                        .city(splitArray[0])
                        .serialNumber(splitArray[1])
                        .manufacture(splitArray[2])
                        .model(splitArray[3])
                        .year(splitArray[4])
                        .fuel(splitArray[5])
                        .seats(Integer.parseInt(splitArray[6]))
                        .carClass(splitArray[7])
                        .price(Double.parseDouble(splitArray[8]))
                        .about(splitArray[9])
                        .build();
                carDtoList.add(car);
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return carDtoList.listIterator();
    }

    @DataProvider
    public Iterator<CarDto> dataProviderCarFileProperties() throws IOException
    {
        List<CarDto> carDtoList = new ArrayList<>();
        BufferedReader bufferedReader;
        try
        {
            bufferedReader = new BufferedReader(new FileReader
                    ("src/main/resources/" + getProperty("login.properties", "fileNameDPCar")));
            String line = bufferedReader.readLine();
            while(line!=null)
            {
                String[] splitArray = line.split(";");
                CarDto car = CarDto.builder()
                        .city(splitArray[0])
                        .serialNumber(splitArray[1])
                        .manufacture(splitArray[2])
                        .model(splitArray[3])
                        .year(splitArray[4])
                        .fuel(splitArray[5])
                        .seats(Integer.parseInt(splitArray[6]))
                        .carClass(splitArray[7])
                        .price(Double.parseDouble(splitArray[8]))
                        .about(splitArray[9])
                        .build();
                carDtoList.add(car);
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return carDtoList.listIterator();
    }
}
