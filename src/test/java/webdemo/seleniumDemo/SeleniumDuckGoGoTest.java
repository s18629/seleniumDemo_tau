package webdemo.seleniumDemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

public class SeleniumDuckGoGoTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.google.chrome", "H:\\seleniumDemo\\seleniumDemo\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }


    @Test
    public void testThirdElement() {
        driver.findElement(By.name("q")).sendKeys("DuckDuckGo");
        driver.findElement(By.id("search_button_homepage")).submit();
        WebElement results = driver.findElement(By.cssSelector(".results.js-results"));
        List<WebElement> resultList = results.findElements(By.tagName("div"));
        assertNotNull(resultList.get(2));
    }

    @Test
    public void testWithoutResults(){
        driver.findElement(By.name("q")).sendKeys("aeaeaeaaeaeaeaeaeafafafaffafafzczczczczczczczczczcz");
        driver.findElement(By.id("search_button_homepage")).submit();
        WebElement results = driver.findElement(By.cssSelector(".results.js-results"));
        List<WebElement> resultList = results.findElements(By.tagName("div"));
        assertNotNull(resultList.get(0));
    }

    @Test
    public void testSearchResult() {
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.findElement(By.id("search_button_homepage")).click();
        WebElement result = driver.findElement(By.className("module__title__link"));
        assertEquals(result.getText(), "Selenium");
    }

    @Test
    public void testWebSource(){
        String source = driver.getTitle();
                assertTrue(source.contains("DuckDuckGo"));
    }
}
