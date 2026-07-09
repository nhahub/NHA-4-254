import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class try1 {

    WebDriver driver;

    public void main() {
    }

    @Test
    public void try1() {
        driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/dynamic_loading");
        driver.findElement(By.partialLinkText("/dynamic_loading/2")).click();
        driver.findElement(By.cssSelector("#start button")).click();

    }
}
