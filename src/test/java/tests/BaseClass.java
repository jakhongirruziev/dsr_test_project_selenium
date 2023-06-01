package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.VacancyFormPage;

import java.time.Duration;


public class BaseClass {

    public static WebDriver driver;
    public static VacancyFormPage vacancyFormPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Init pages
        vacancyFormPage = new VacancyFormPage(driver);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
