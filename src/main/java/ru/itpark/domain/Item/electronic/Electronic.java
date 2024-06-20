package ru.itpark.domain.Item.electronic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.domain.Item.Item;



@Getter
@Setter
public class Electronic extends Item {
    private ElectronicItemType type;
    private Integer requiredPowerVoltage;
    private Double power;

    public Electronic(String name, int price, String description, ElectronicItemType type, Integer requiredPowerVoltage, Double power) {
        super(name, price, description);
        this.type = type;
        this.requiredPowerVoltage = requiredPowerVoltage;
        this.power = power;
    }
}
