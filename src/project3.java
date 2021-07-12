import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class project3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        driver.get("http://carfax.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);


        driver.findElement(By.linkText("Find a Used Car")).click();


        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains("Used Cars"));


        new Select(driver.findElement(By.xpath("//select[@name='make']"))).selectByIndex(31);


        List<String> actualModels = new ArrayList<>();
        List<String> expectedModels = Arrays.asList("Model 3", "Model S", "Model X", "Model Y");
        List<WebElement> elements = new Select(driver.findElement(By.xpath("//select[@name='model']"))).getOptions();
        for (int i = 1; i < elements.size() - 1; i++) {
            actualModels.add(elements.get(i).getText().trim());
        }
        Assert.assertEquals(actualModels, expectedModels);



        new Select(driver.findElement(By.xpath("//select[@name='model']"))).selectByIndex(2);


        driver.findElement(By.name("zip")).sendKeys("22182" + Keys.ENTER);



        assertTrue(driver.getPageSource().contains("Step 2 - Show me cars with"));
        Thread.sleep(2000);
        System.out.println(driver.getPageSource().contains("Step 2 - Show me cars with"));



        List<WebElement> checkboxes = driver.findElements(By.xpath("//span[@class='checkbox-list-item--fancyCbx']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }



        String countOfResults = driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText();
        System.out.println(driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText());


        driver.findElement(By.xpath("//span[@class='totalRecordsText']")).click();



        int displayNum = Integer.parseInt(driver.findElement(By.xpath("//span[@id='totalResultCount']")).getText().substring(0, 2));
        int webElementsNum = driver.findElements(By.xpath("//article[@class='srp-list-item']")).size();
        Assert.assertEquals(displayNum, webElementsNum);



          List<WebElement> results = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
        for (WebElement result : results) {
            Assert.assertTrue(result.getText().contains("Tesla Model S"));



            List<String> priceList = new ArrayList<>();
            List<WebElement> prices = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
            for (WebElement price : prices) {
                if (price.getText().contains("$"))
                    priceList.add(price.getText().substring(7));
            }
            System.out.println(priceList);



            WebElement sortClick = driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']"));
            Select selectOption = new Select(sortClick);
            selectOption.selectByIndex(1);



            Thread.sleep(2000);
            int price = 0;
            int nextPrice = 0;
            for (int i = 0; i < priceList.size() - 1; i++) {
                price = Integer.parseInt(priceList.get(i).substring(1).replace(",", ""));
                nextPrice = Integer.parseInt(priceList.get(i + 1).substring(1).replace(",", ""));
            }
            Assert.assertTrue(price > nextPrice);



            WebElement sortClick2 = driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']"));
            Select selectOption2 = new Select(sortClick2);
            selectOption2.selectByIndex(4);


            List<WebElement> mileage = driver.findElements(By.className("srp-list-item-basic-info-value"));
            String lowestMileage = mileage.get(0).getText().substring(9);
            Assert.assertEquals(lowestMileage, "8,191 miles");



            WebElement sortClick3 = driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']"));
            Select selectOption3 = new Select(sortClick3);
            selectOption3.selectByIndex(5);



            Thread.sleep(2000);

            List<WebElement> newToOld = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
            int firstCarsYear = Integer.parseInt(newToOld.get(1).getText().substring(0, 4));
            int secondCarsYear = Integer.parseInt(newToOld.get(2).getText().substring(0, 4));
            if (firstCarsYear > secondCarsYear) {
                System.out.println(true);
            }
        }
    }
}

