import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void locatorTest() {

        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id(""));
        driver.findElement(By.name(""));
        driver.findElement(By.className(""));

    }
}
