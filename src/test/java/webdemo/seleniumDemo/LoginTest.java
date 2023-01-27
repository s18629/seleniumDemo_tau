package webdemo.seleniumDemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.google.chrome", "H:\\seleniumDemo\\seleniumDemo\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://accounts.google.com/" +
                "ServiceLogin/identifier?hl=en&passive=true&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAmgQ&ifkv" +
                "=AWnogHdDEU8nBIxQpqRyjXqzbENeV-f5iabQSPr5y8ay7idRF59YqZgsVe24p5nHCuZ-79iIRMznZg&flowName=GlifWebSignIn&" +
                "flowEntry=ServiceLogin");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLogin(){
        driver.findElement(By.id("identifierId")).sendKeys("seleniumTAU@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        WebElement password = driver.findElement(By.name("Passwd"));
        password.sendKeys("selenium1717");
        WebElement passwordNext = driver.findElement(By.id("passwordNext"));
        passwordNext.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.name("q"));
        String WebTitle = driver.getTitle();
        assertEquals("Google", WebTitle);
    }

    @Test
    public void testLoginWithWrongEmail(){
        driver.findElement(By.id("identifierId")).sendKeys("");
        driver.findElement(By.id("identifierNext")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.className("LXRPh"));
        String title = driver.getTitle();
        assertEquals("Sign in - Google Accounts", title);
    }
}
