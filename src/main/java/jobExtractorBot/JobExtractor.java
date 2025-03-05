package jobExtractorBot;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class JobExtractor {
	
	private JobExtractor() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
	
    public static List<JobData> extractJobListings(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<JobData> jobDataList = new ArrayList<>();
        Set<String> processedJobLinks = new HashSet<>();

        int maxJobsToProcess = 10; 

        for (int i = 0; i < maxJobsToProcess; i++) {
            try {
                List<WebElement> jobListings = driver.findElements(By.className("job-card-container"));

                if (i >= jobListings.size()) {
                    break; 
                }

                WebElement job = jobListings.get(i);

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", job);
                Thread.sleep(1000); 
                job.click(); 

                WebElement jobTitleElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div/div[2]/div")));
                String jobTitle = jobTitleElem.getText();

                WebElement companyNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div/div[1]/div[1]/div")));
                String companyName = companyNameElem.getText();

                String jobCTC = "Not Available";  
                String jobLocation = "Not Available"; 

                try {
                    WebElement jobDetailsElem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='main']/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div/div[4]/ul/li[1]/span/span[1]")));

                    String elementText = jobDetailsElem.getText().trim();
                    
                    if (elementText.matches(".*\\d.*")) {  
                        jobCTC = elementText;
                        
                        try {
                            WebElement locationElem = driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div/div[4]/ul/li[1]/span/span[2]"));
                            String locationText = locationElem.getText().trim();
                            
                            if (locationText.matches(".*(?i)(Hybrid|On-Site|Remote).*")) {
                                jobLocation = locationText;
                            }
                        } catch (NoSuchElementException ignored) {
                          
                        }
                        
                    } else if (elementText.matches(".*(?i)(Hybrid|On-Site|Remote).*")) {  
                        jobLocation = elementText;
                    }

                } catch (NoSuchElementException e) {
                    System.out.println("Element not found.");
                }

                System.out.println("Job CTC: " + jobCTC);
                System.out.println("Job Location: " + jobLocation);

                
                
                WebElement jobLinkElem = job.findElement(By.cssSelector("a.ember-view"));  
                String jobLink = jobLinkElem.getAttribute("href");

                if (!jobLink.startsWith("http")) {
                    jobLink = "https://www.linkedin.com" + jobLink;
                }

                if (processedJobLinks.contains(jobLink)) {
                    System.out.println("Skipping already processed job: " + jobLink);
                    continue;
                }

                processedJobLinks.add(jobLink);

                System.out.println("Job Link: " + jobLink);
                
                String jobDescription = "No Description Available";
                try {
                    WebElement descElement = driver.findElement(By.xpath("//*[@id=\"job-details\"]/div/p[1]"));
                    jobDescription = descElement.getText();
                } catch (NoSuchElementException ignored) {}
                jobDataList.add(new JobData(jobTitle, companyName, jobLocation, jobCTC, "Not Available", jobLink, jobDescription));

                System.out.println("Job title: " + jobTitle);
                System.out.println("Company: " + companyName);
                System.out.println("Location: " + jobLocation);
                System.out.println("CTC: " + jobCTC);
                System.out.println("Job Link: " + jobLink);
                System.out.println("Description: " + jobDescription);

                Thread.sleep(2000);

            } catch (StaleElementReferenceException e) {
                System.out.println("Stale Element Exception: Retrying...");
                i--; 
            } catch (Exception e) {
                System.out.println("Error processing job: " + e.getMessage());
            }
        }

        Thread.sleep(5000); 
        return jobDataList;
    }
}
