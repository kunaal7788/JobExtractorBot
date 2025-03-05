**Job Extractor Bot**

*Introduction*

The Job Extractor Bot is an automated tool designed to extract job listings from websites and save them into an Excel file. This project is developed purely for educational purposes, helping learners understand web scraping techniques using Selenium and data storage using Apache POI in Java. The tool can fetch details such as:

1.Job Title

2.Company Name

3.Location

4.Salary (CTC)

5.Recruiter Email

6.Job Link

7.Job Description

⚠ **Disclaimer**: This project is strictly for educational purposes only. It is not intended for any unauthorized or unethical web scraping activities. Ensure compliance with the terms and conditions of any website before using scraping techniques.

*Features*

Web Scraping: Uses Selenium WebDriver to extract job details from websites.

Data Storage: Saves extracted data into an Excel sheet (job_listings.xlsx).

Error Handling: Handles missing values gracefully by assigning default values.

Utility-based Design: Organized structure with separate classes for scraping and data writing.

Prevent Instantiation: Utility classes (JobExtractor and ExcelWriter) follow best practices by having private constructors.

*Technologies Used*

Java (Core logic)

Selenium WebDriver (Web scraping)

Apache POI (Excel file handling)

Maven (Dependency management)

*Installation & Setup*

*Prerequisites*

Install Java (JDK 11 or later)

Install Maven

Download ChromeDriver (compatible with your Chrome version)

Set up dependencies for Selenium WebDriver and Apache POI

*Steps to Run the Project*

Clone this repository:

git clone https://github.com/kunaal7788/JobExtractorBot.git

Navigate to the project directory:

cd job-extractor-bot

Install dependencies using Maven:

mvn clean install

Run the main Java class to start extracting job data:

java -jar target/job-extractor-bot.jar

*Usage*

Modify the JobExtractor class to specify the job portal or website from which you want to extract job details.

Ensure that the website allows web scraping before running the bot.

Run the program and check the job_listings.xlsx file for extracted job data.

*Legal & Ethical Considerations*

🚨 Important: Web scraping can be legally restricted by website terms and conditions. Always ensure you have permission before scraping any website. This tool is built for learning purposes only and should not be used for commercial or malicious activities.

*Contribution*

If you'd like to contribute to this project, follow these steps:

Fork the repository

Create a new branch (feature-branch)

Commit your changes

Push the changes and open a Pull Request

*License*

This project is licensed under the MIT License. See the LICENSE file for details.

*Contact*

For any questions or issues, feel free to reach out via GitHub Issues.

Happy Coding! 🚀

