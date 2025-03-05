package jobExtractorBot;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            LinkedInLogin.login(driver);
            System.out.println("Login Successful!");

            driver.get("https://www.linkedin.com/jobs/search/?keywords=Software%20Engineer");

            List<JobData> jobListings = JobExtractor.extractJobListings(driver);

            ExcelWriter.writeToExcel(jobListings);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
