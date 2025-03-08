package jobExtractorBot;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

class LinkedInLogin {

    private LinkedInLogin() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static void login(WebDriver driver) throws IOException, InterruptedException {
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

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            typeLikeHuman(emailField, email);

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            typeLikeHuman(passwordField, password);

            Thread.sleep(2000 + new Random().nextInt(1000)); // Random delay before clicking login
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            wait.until(ExpectedConditions.urlContains("feed"));
            System.out.println("Login Successful!");
        } catch (Exception e) {
            System.out.println("Login Failed: " + e.getMessage());
        }
    }

    private static void typeLikeHuman(WebElement element, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
            Thread.sleep(100 + new Random().nextInt(200)); // Random delay between key presses
        }
    }
}
