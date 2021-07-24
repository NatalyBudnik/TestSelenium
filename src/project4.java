import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
public class project4 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();



        driver.get("https://www.carmax.com/");


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,2000)");
        jse.executeScript("window.scrollBy(0,-800)");
        jse.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.xpath("//button[@id='button-VIN']")).click();
        driver.findElement(By.id("ico-form-vin")).sendKeys("4T1BE46K67U162207", Keys.TAB, "22182", Keys.ENTER);



        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.='LE 4D Sedan 2.4L']"))).click();
        new WebDriverWait(driver, 8).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("drive"))));
        new Select(driver.findElement(By.name("drive"))).selectByValue("4WD/AWD");



        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (int i = 0; i < 12; i++) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", checkboxes.get(i));
            checkboxes.get(i).click();
        }
        driver.findElement(By.xpath("//span[.='Mileage and condition']")).click();




        driver.findElement(By.name("currentMileage")).sendKeys("60000");
        List<WebElement> checkboxes2 = driver.findElements(By.xpath("//label[.='No']"));
        for (int i = 0; i <= 21; i++) {
            JavascriptExecutor executor2 = (JavascriptExecutor) driver;
            executor2.executeScript("arguments[0].scrollIntoView(true);", checkboxes2.get(i));
            if (i % 2 != 0)
                checkboxes2.get(i).click();
        }
        driver.findElement(By.xpath("//input[@id='radio-ico-r-600-1']")).click();
        driver.findElement(By.id("ico-continue-button")).click();





        driver.findElement(By.id("ico-step-Vehicle_Profile-btn")).click();
        List<WebElement> carInfo = driver.findElements(By.xpath("//td//p[contains (@id, 'vehicleInfo')]"));
        List<String> actualCarInfo = new ArrayList<>();
        for (int i = 4; i < carInfo.size(); i++) {
            actualCarInfo.add(carInfo.get(i).getText());
        }
        List<String> expectedCarInfo = Arrays.asList("2007 Toyota Camry", "4WD/AWD", "Automatic", "4T1BE46K67U162207", "60,000");
        Assert.assertEquals(actualCarInfo, expectedCarInfo);





        String actualOffer = driver.findElement(By.xpath("//div[@class='kmx-ico-offer-offerinfoffer-module__offerInfo--26dFt']")).getText();
        Assert.assertTrue(actualOffer.contains("6,400"));





        driver.findElement(By.id("ico-set-my-appointment")).click();





        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if(driver.getTitle().equals("Appraisal Appointment | CarMax")){
                break;
            }
        }


        Select selcSelect = new Select(driver.findElement(By.className("mdc-select__native-control")));
        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//select[@class='mdc-select__native-control']")));
        Thread.sleep(2000);





        List<WebElement> productElems = driver.findElements(By.xpath("//select/option[@value]"));

        int maxProducts = productElems.size();


        java.util.Random random = new java.util.Random();
        int randomProduct = random.nextInt(maxProducts);


        productElems.get(randomProduct).click();





        driver.findElement(By.className("date-container")).click();
        org.openqa.selenium.JavascriptExecutor jse2 = (org.openqa.selenium.JavascriptExecutor) driver;
        jse2.executeScript("window.scrollBy(0,300)");






        Thread.sleep(2000);
        WebElement firstAvailableDateButton = driver.findElement(By.xpath("//div[@aria-disabled='false']"));




        firstAvailableDateButton.click();
        Thread.sleep(2000);





        driver.findElement(By.id("react-timepicker")).click();
        driver.findElement(By.xpath("//li[@class='react-datepicker__time-list-item ']")).click();
        Thread.sleep(2000);





        driver.findElement(By.xpath("//button[@class='kmx-button--primary kmx-button']")).click();


        List<WebElement> elements = driver.findElements(By.xpath("//input[@type='radio']"));
        for (WebElement element : elements) {
            if (element.getAttribute("value").equals("Loan") && !element.isSelected()) {
                element.click();
            }
        }



        com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
        driver.findElement(By.id("fname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lname")).sendKeys(faker.name().lastName());
        WebElement emailAddressTextbox=driver.findElement(By.id("email"));
        String email= faker.internet().emailAddress();
        emailAddressTextbox.sendKeys(email);



        WebElement phoneNumberTextbox=driver.findElement(By.id("phone"));
        String phoneNumber= faker.phoneNumber().phoneNumber();
        phoneNumberTextbox.sendKeys(phoneNumber);


        com.github.javafaker.Faker fakeData= new com.github.javafaker.Faker();
        System.out.println(fakeData.phoneNumber().cellPhone());





        driver.findElement(By.linkText("Privacy Policy")).click();


        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("CarMax"));



       Thread.sleep(2000);



        java.util.Set<String> windowHandles2 = driver.getWindowHandles();
        for (String windowHandleBefore : windowHandles2) {
            driver.switchTo().window(windowHandleBefore);
            if(driver.getTitle().equals("CarMax - Browse used cars and new cars online")){
                break;
            }
        }
        driver.findElement(By.xpath("//button[.='Save this offer']")).click();





        Thread.sleep(2000);
        driver.findElement(By.className("Preffered email")).sendKeys("cristian.fortuna01@gmail.com" + Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[.='SEND MY OFFER']")).click();

    }
}

