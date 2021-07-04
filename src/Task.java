import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertTrue;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.duotifyapp.us-east-2.elasticbeanstalk.com/register.php");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Welcome to Duotify!";
        if (actualTitle.equalsIgnoreCase(expectedTitle))
            System.out.println("Title PASSED");
        else
            System.out.println("Title FAILED");
        driver.findElement(By.id("hideLogin")).click();


        String s = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPGTSTIVWXYZ";
        String username = "";
        for (int i = 0; i < 8; i++) {
            username += s.charAt((int) (Math.random() * s.length()));
        }

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("firstName")).sendKeys("Nata");
        driver.findElement(By.id("lastName")).sendKeys("Budnik");
        String email = username + "@gmail.com";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("email2")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("Bober88");
        driver.findElement(By.id("password2")).sendKeys("Bober88" + Keys.ENTER);


        String actualUrl= driver.getCurrentUrl();
        String expectedUrl= "http://www.duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?";
        if(actualUrl.equalsIgnoreCase(expectedUrl)) {
            System.out.println("URL PASSED") ;
        } else {
            System.out.println("URL FAILED");
        }

        Thread.sleep(2000);
        assertTrue(driver.getPageSource().contains("Nata Budnik"));
        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(2000);

        assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Nata Budnik"));
        Thread.sleep(2000);
        driver.findElement(By.id("Natty")).click();

        Thread.sleep(2000);
        String actualUrl2 = driver.getCurrentUrl();
        assertTrue(actualUrl2.contains("http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php"));
        Thread.sleep(2000);

        driver.findElement(By.id("loginUsername")).sendKeys(username);
        driver.findElement(By.id("loginPassword")).sendKeys("Bober88" + Keys.ENTER);

        Thread.sleep(2000);
        System.out.println(driver.getPageSource().contains("You Might Also Like"));
        driver.findElement(By.id("nameFirstAndLast")).click();
        driver.quit();

    }

}

