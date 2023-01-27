package webdemo.seleniumDemo;

import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XPathTestDuckDuckGo {

    private static WebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.google.chrome", "H:\\seleniumDemo\\seleniumDemo\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testImages() {
        driver.get("https://duckduckgo.com/?q=Cats&t=h_&iax=images&ia=images");
        List<WebElement> links = driver.findElements(By.xpath("//img"));
        assertNotNull(links.size());
    }

    @Test
    public void testLinksVisit() {
        driver.get("https://duckduckgo.com/?q=Cats&t=h_&ia=web");
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        int size = links.size();

        for (int i = 0; i <= size; i++) {
            if (!(links.get(i).getText().isEmpty())) {
                links.get(i).click();
                driver.navigate().back();
                links = driver.findElements(By.tagName("a"));
                size = links.size();
            }
        }
        assertNotNull(links.size());
    }

    @Test
    public void testInputFields() {
        driver.get("https://gmail.com");
        List<WebElement> textfield = driver.findElements(By.xpath("//input[@type='text' or @type='password']"));
        assertEquals(1, textfield.size());
    }
}
