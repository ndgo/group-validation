package car;

import interfaces.OldCarCheck;
import interfaces.OneYearOldCarCheck;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Car {
    public static final int MINIMAL_NUMBER_OF_OWNERS = 1;
    public static final int ONE_YEAR_OLD = 1;
    public static final int TWENTY_YEARS_OLD = 20;
    @NotNull
    Long price;
    @NotNull
    CarModel carModel;
    @Min.List({@Min(value = TWENTY_YEARS_OLD, groups = OldCarCheck.class), @Min(value = ONE_YEAR_OLD, groups = OneYearOldCarCheck.class)})
    @Max(value = ONE_YEAR_OLD, groups = OneYearOldCarCheck.class)
    long age;
    @NotNull
    @Size(min = MINIMAL_NUMBER_OF_OWNERS)
    List<String> owners;
}
