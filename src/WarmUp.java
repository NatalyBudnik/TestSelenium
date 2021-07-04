import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class WarmUp {
    public static void main(String [] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");

        WebDriver wd = new ChromeDriver();
        wd.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        WebElement inputBox1 = wd.findElement(By.id("ctl00_MainContent_username"));
        String username = "Tester";
        inputBox1.sendKeys(username);

        WebElement inputBox2 = wd.findElement(By.id("ctl00_MainContent_password"));
        String password = "test";
        inputBox2.sendKeys(password + Keys.ENTER);


        String actualTitle = "Web Orders";
        String expectedTitle = wd.getTitle();

       assertTrue(actualTitle.contains(expectedTitle));

        String HTMLCode = wd.getPageSource();

        assertTrue(HTMLCode.contains("Welcome, Tester"));
        assertTrue(HTMLCode.contains("List of All Orders"));

        List<WebElement> links =  wd.findElements(By.tagName("a"));

        Assert.assertEquals(links.size(), 6);
        for(WebElement link : links) {
            System.out.println(link.getText());
        }
        wd.findElement(By.id("ctl00_logout")).click();
        assertTrue(wd.getTitle().equals("Web Orders Login"));
        }

    }

