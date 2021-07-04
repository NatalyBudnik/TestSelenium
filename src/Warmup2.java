import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Warmup2 {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.manage().deleteAllCookies();

        driver.get("https://www.bloomberg.com/markets/stocks");
        String verifiedHeader = driver.findElement(By.xpath("//tr[@class='data-table-headers']")).getText();
        System.out.println("Verified Header Table : "+verifiedHeader);

        List<WebElement> verifyFirstCol = driver.findElements(By.xpath("//th[@class='data-table-row-cell']"));
        System.out.println(" \"List of all " + verifyFirstCol.size() + " "
                + "prices in the order they appear\"");
        for (WebElement getFirstCol : verifyFirstCol) {
            System.out.println(getFirstCol.getText());


        }
    }
}
