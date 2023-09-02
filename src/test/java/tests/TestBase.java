package tests;


import driverSettigns.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {

    protected volatile WebDriver driver = null;


    @BeforeEach
    public synchronized void a_setup() {
        driver= new DriverFactory().getDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public synchronized void z_teardown() {
//        takeScreenshot(driver, getClass().getName());
        driver.quit();
    }


}
