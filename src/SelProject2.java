import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class SelProject2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\natas\\Documents\\sel drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Thread.sleep(1000);


        WebElement inputBox1 = driver.findElement(By.id("ctl00_MainContent_username"));
        String username = "Tester";
        inputBox1.sendKeys(username);

        WebElement inputBox2 = driver.findElement(By.id("ctl00_MainContent_password"));
        String password = "test";
        inputBox2.sendKeys(password + Keys.ENTER);
        Thread.sleep(1000);


        driver.findElement(By.linkText("Order")).click();
        Thread.sleep(1000);

        String product = driver.findElement(By.name("ctl00$MainContent$fmwOrder$ddlProduct")).getAttribute("value");
        int randomQuantity = (int) (Math.random() * 99) + 1;


        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE, String.valueOf(randomQuantity), Keys.ENTER);
        Thread.sleep(1000);


        String value = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");
        double actualTotalValue = Double.parseDouble(value);
        double expectedTotalValue = 0;
        if (randomQuantity > 10){
            expectedTotalValue = randomQuantity * 100 - (randomQuantity * 100 * 0.08);
        } else if (randomQuantity < 10 && randomQuantity > 0){
            expectedTotalValue = randomQuantity * 100;
        } else {
            expectedTotalValue = 0;
        }
        assertTrue(actualTotalValue == expectedTotalValue);
        Thread.sleep(1000);


        List<String[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("MOCK_DATA.csv"));
        String line;
        while((line = bufferedReader.readLine()) != null ){
            String[] split = line.split(",");
            list.add(split);
        }
        String[] randomMockData = null;
        for (int i = 1; i < list.size(); i++){
            int randomIndex = (int)(Math.random()* list.size()-1)+1;
            randomMockData = list.get(randomIndex);
        }
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(randomMockData[0]+" "+randomMockData[1],Keys.TAB, randomMockData[2], Keys.TAB, randomMockData[3], Keys.TAB, randomMockData[4], Keys.TAB, randomMockData[5]);
        Thread.sleep(1000);



        String[] cardTypeId = {"ctl00_MainContent_fmwOrder_cardList_0","ctl00_MainContent_fmwOrder_cardList_1","ctl00_MainContent_fmwOrder_cardList_2"};
        String randomCardTypeId = null;
        for (int i = 0; i < cardTypeId.length; i++){
            int randomIndex = (int)(Math.random()* cardTypeId.length);
            randomCardTypeId = cardTypeId[randomIndex];
        }
        driver.findElement(By.id(randomCardTypeId)).click();
        Thread.sleep(1000);


        String randomCardNo = "";
        Random randomNum = new Random();
        for (int i = 0; i < 15; i++) {
            randomCardNo += randomNum.nextInt(10);
        }
        String inputtedCardNumber;
        if (randomCardTypeId.equals("ctl00_MainContent_fmwOrder_cardList_0")){
            inputtedCardNumber = "4" + randomCardNo;
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(inputtedCardNumber);
        }else if(randomCardTypeId.equals("ctl00_MainContent_fmwOrder_cardList_1")) {
            inputtedCardNumber = "5" + randomCardNo;
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(inputtedCardNumber);
        }else {
            inputtedCardNumber = "3" + randomCardNo.substring(1);
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(inputtedCardNumber);
        }
        String cardType = driver.findElement(By.id(randomCardTypeId)).getAttribute("value");
        Thread.sleep(1000);


        String expireDate = "12/22";
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expireDate);
        Thread.sleep(1000);

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        Thread.sleep(1000);


        assertTrue(driver.getPageSource().contains("New order has been successfully added."));
        Thread.sleep(1000);


        driver.findElement(By.linkText("View all orders")).click();
        Thread.sleep(1000);


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String todaysDate= formatter.format(date);
        String actualFirstRowOfTr = driver.findElements(By.tagName("tr")).get(2).getText();
        String expectedFirstRowOfTr = randomMockData[0]+" "+randomMockData[1]+" "+product+" "+randomQuantity+" "
                +todaysDate+" "+randomMockData[2]+" "+randomMockData[3]+" "+randomMockData[4]+" "
                +randomMockData[5]+" "+cardType+" "+inputtedCardNumber+" "+expireDate;
        assertEquals(actualFirstRowOfTr, expectedFirstRowOfTr);
        Thread.sleep(1000);


        driver.findElement(By.id("ctl00_logout")).click();
        driver.quit();




    }
}
