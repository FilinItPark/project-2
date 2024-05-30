package ru.itpark.domain.Item.electronic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.domain.Item.Item;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Electronic extends Item {
    private ElectronicItemType type;
    private Integer requiredPowerVoltage;
    private Double power;
}
