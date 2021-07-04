import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelen {
    public static void main(String[] args){


        System.setProperty("webdriver.chrome.driver","C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.duotech.io");

        driver.navigate().to("https://www.amazon.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

    }
}


