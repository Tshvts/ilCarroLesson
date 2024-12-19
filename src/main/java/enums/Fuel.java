package enums;

import lombok.Getter;

@Getter
public enum Fuel
{
    DIESEL("//option[text()=' Diesel ']"),
    PETROL("//option[text()=' Petrol ']"),
    HYBRID("//option[text()=' Hybrid ']"),
    ELECTRIC("//option[text()=' Electric ']"),
    GAS("//option[text()=' Gas ']");


    private String locator;

    Fuel(String locator) {
        this.locator = locator;
    }

}
