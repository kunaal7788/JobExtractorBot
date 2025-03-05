package jobExtractorBot;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.Properties;

class LinkedInLogin {

    private LinkedInLogin() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static void login(WebDriver driver) throws IOException {
        Properties prop = new Properties();
        InputStream input = LinkedInLogin.class.getClassLoader().getResourceAsStream("config.properties");
        if (input == null) {
            throw new FileNotFoundException("Property file not found in classpath");
        }
        prop.load(input);

        String email = prop.getProperty("email");
        String password = prop.getProperty("password");

        driver.get("https://www.linkedin.com/login");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            wait.until(ExpectedConditions.urlContains("feed"));
            System.out.println("Login Successful!");
        } catch (Exception e) {
            System.out.println("Login Failed: " + e.getMessage());
        }
    }
}
