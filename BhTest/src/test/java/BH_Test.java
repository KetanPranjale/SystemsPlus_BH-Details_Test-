import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;

import java.util.List;

public class BH_Test {
    public static void main(String[] args) throws Exception{
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Ketan.Pranjale\\Documents\\AutomationBH\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //1 Navigate to BH home page
        driver.get("https://www.brighthorizons.com/");
        Thread.sleep(2);
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        Thread.sleep(6);
        //2 Click on Find a Center option (top header)
        driver.findElement(By.xpath("//nav[@class='nav-shared txt-nav-hierarchy nav-top js-nav-shared js-nav-top']//li[@class='nav-item displayed-desktop']/a")).click();
        Thread.sleep(3000);
        //3 Verify URL
        if(driver.getCurrentUrl().contains("/child-care-locator")){
            System.out.println("Verification: Correct URL");
        } else{
            System.out.println("Verification: Incrrect URL");
        }
        //4 Type New York into search box
        WebElement inSearch= driver.findElement(By.xpath("//input[@id='addressInput']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",inSearch);
        Thread.sleep(1000);
        inSearch.sendKeys("New York");
        Thread.sleep(2000);
        inSearch.sendKeys(Keys.RETURN);
        inSearch.sendKeys(Keys.TAB);
        //5 verify a number of found centers
        Thread.sleep(3000);
        int noofresults=Integer.parseInt(driver.findElement(By.xpath("//div[@id='centerLocator_list']//span[@class='resultsNumber']")).getText());
        if(noofresults==20){
            System.out.println("Verification: Correct number of found centers");
        } else{
            System.out.println("Verification: Incrrect number of found centers");
        }
        //6 Click on the first center on the list
        List<WebElement> elementList=driver.findElements(By.xpath("//div[@id='center-results-container']/div[contains(@class,'centerResult infoWindow track_center_select covidOpen')]"));
        elementList.get(0).click();
        Thread.sleep(2000);
        //7 Verify if center name and address are the same
        String id= elementList.get(0).getAttribute("id");
        String cenName1 =driver.findElement(By.xpath(new String("//div[@id='****']//h3").replace("****",id))).getText();
        String cenName2 = driver.findElement(By.xpath("//div[@class='mapTooltip']/span[@class='mapTooltip__headline']")).getText();
        if(cenName1.equals(cenName2)){
            System.out.println("Verification: Correct center name displayed");
        } else{
            System.out.println("Verification: Incrrect center name displayed");
        }
        String address1 =driver.findElement(By.xpath(new String("//div[@id='****']//span[@class='centerResult__address']").replace("****",id))).getText();
        String address2 =driver.findElement(By.xpath(new String("//div[@class='mapTooltip__address']"))).getText();
        address2=address2.replace("\n","");
        if(address1.equals(address2)){
            System.out.println("Verification: Correct center address displayed");
        } else{
            System.out.println("Verification: Incrrect center address displayed");
        }
        Thread.sleep(3000);
        driver.quit();
    }
}