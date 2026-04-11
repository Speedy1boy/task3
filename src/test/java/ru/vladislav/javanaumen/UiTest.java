package ru.vladislav.javanaumen;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UiTest {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void loginAndLogoutTest() {
        driver.get("http://localhost:8080/login");

        driver.findElement(By.name("username")).sendKeys("admin");

        driver.findElement(By.name("password")).sendKeys("bobs");

        driver.findElement(By.cssSelector("input[type='submit']")).click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Главная"));

        assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("Главная"));

        driver.findElement(By.xpath("//button[contains(text(), 'Выйти')]")).click();

        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("login"));
    }
}
