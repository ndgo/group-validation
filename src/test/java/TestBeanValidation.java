import car.Car;
import car.CarModel;
import interfaces.OldCarCheck;
import interfaces.OneYearOldCarCheck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Validator;

import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

public class TestBeanValidation {

    private Validator validator;

    @Before
    public void initTest() {
        validator = new Validator();
    }

    public Car getDefaultCar() {
        Car car = new Car();
        car.setPrice(1L);
        car.setCarModel(CarModel.BENTLEY);
        List<String> owners = new ArrayList<String>();
        owners.add("OWNER-1");
        car.setOwners(owners);
        return car;
    }

    @Test
    public void testUsualValidation() {
        Car car = getDefaultCar();
        Assert.assertEquals("[]", validator.validate(car));
    }

    @Test
    public void testOldCarAge() {
        Car car = getDefaultCar();
        car.setAge(25);
        Assert.assertEquals("[]", validator.validate(car, OldCarCheck.class));
        // машине 25 лет, но не 1
        Assert.assertNotEquals("[]", validator.validate(car, OneYearOldCarCheck.class));
    }

    @Test
    public void testOneYearOldCar() {
        Car car = getDefaultCar();
        car.setAge(1);
        // Проверить что
        Assert.assertEquals("[]", validator.validate(car, OneYearOldCarCheck.class));
    }

    @Test
    public void testOneYearOldCarAndNonGroupedConstraints() {
        Car car = getDefaultCar();
        car.setAge(1);
        // проверить поля с аннотациями, относящиеся к группе по умолчанию, а так же поля, с аннотацииями,
        // относящиеся к группе OneYearOldCarCheck
        Assert.assertEquals("[]", validator.validate(car, Default.class, OneYearOldCarCheck.class));

        car.setPrice(null);
        car.setOwners(null);
        // согласно аннотоации @NotNull проверки Car.owners и Car.price должны приводить к ошибке,
        // но так как не указана группа по умолчанию, а указана ТОЛЬКО группа OneYearOldCarCheck,
        // то проверяются ограничения, относящиеся только к группе OneYearOldCarCheck
        Assert.assertEquals("[]", validator.validate(car, OneYearOldCarCheck.class));

        // Car.owners не должен быть null и должен содержать как минимум 1 элемент
        Assert.assertNotEquals("[]", validator.validate(car, Default.class, OneYearOldCarCheck.class));
    }
}
