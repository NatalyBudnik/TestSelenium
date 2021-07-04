import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Project2 {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        WebElement inputBox1 = driver.findElement(By.id("ctl00_MainContent_username"));
        String username = "Tester";
        inputBox1.sendKeys(username);

        WebElement inputBox2 = driver.findElement(By.id("ctl00_MainContent_password"));
        String password = "test";
        inputBox2.sendKeys(password + Keys.ENTER);
    }
}
