package dto;

import enums.Fuel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@Builder

public class CreateCarDto
{
    private String address;
    private String manufacture;
    private String model;
    private String year;
    private List<Fuel> fuels;
    private String seats;
    private String carClass;
    private String carRegistrationNumber;
    private String price;
    private String about;
}
