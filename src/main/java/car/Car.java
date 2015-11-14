package car;

import interfaces.OldCarCheck;
import interfaces.OneYearOldCarCheck;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Car {
    @NotNull
    Long price;
    @NotNull
    CarModel carModel;
    //    не работает
//    @Min(1)
//    Long age;
    @Min.List({@Min(value = 20, groups = OldCarCheck.class), @Min(value = 1, groups = OneYearOldCarCheck.class)})
    @Max(value = 1, groups = OneYearOldCarCheck.class)
    long age;
    @NotNull
    @Size(min = 1)
    List<String> owners;
}
