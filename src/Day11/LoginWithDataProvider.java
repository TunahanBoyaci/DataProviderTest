package Day11;

import Utilities.BaseDriver;
import Utilities.ParameterDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginWithDataProvider extends ParameterDriver {
    @Test (dataProvider = "keywordList")
    public void loginWithDataProvider(String keyword){
        if (keyword.equalsIgnoreCase("mac")){
            login();
        }

        WebElement searchInbox = driver.findElement(By.cssSelector("input[placeholder='Search']"));
        searchInbox.clear();
        searchInbox.sendKeys(keyword);

        WebElement searchButton = driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']"));
        searchButton.click();

        List<WebElement> results = driver.findElements(By.cssSelector("div[class='caption'] a"));

        for (WebElement r : results){
            Assert.assertTrue(r.getText().toLowerCase().contains(keyword));
        }
    }

    @DataProvider
    public Object[][] keywordList(){
        Object[][] keywords = {
                {"mac"},
                {"samsung"},
                {"ipod"}
        };

        return keywords;
    }
}
