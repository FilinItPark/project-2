package ru.itpark.domain.Item.electronic;

import lombok.Getter;
import lombok.Setter;
import ru.itpark.domain.Item.Item;

import java.time.LocalDateTime;


@Getter
@Setter
public class Electronic extends Item {
    private ElectronicItemType type;
    private Integer requiredPowerVoltage;
    private Double power;

    public Electronic(String name, int price, String description, ElectronicItemType type, Integer requiredPowerVoltage, Double power, LocalDateTime now) {
        super(name, price, now, description);
        this.type = type;
        this.requiredPowerVoltage = requiredPowerVoltage;
        this.power = power;
    }
}
