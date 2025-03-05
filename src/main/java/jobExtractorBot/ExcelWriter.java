package jobExtractorBot;

import java.io.*;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelWriter {
	
	private ExcelWriter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
	
	public static void writeToExcel(List<JobData> jobDataList) throws IOException {
        File file = new File("job_listings.xlsx");
        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("Job Listings");
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Job Listings");
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Job Title", "Company Name", "Location", "CTC", "Recruiter Email", "Job Link", "Job Description"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
        }

        int rowNum = sheet.getLastRowNum() + 1;
        for (JobData job : jobDataList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(job.jobTitle != null ? job.jobTitle : "N/A");
            row.createCell(1).setCellValue(job.companyName != null ? job.companyName : "N/A");
            row.createCell(2).setCellValue(job.jobLocation != null ? job.jobLocation : "N/A");
            row.createCell(3).setCellValue(job.jobCTC != null ? job.jobCTC : "N/A");
            row.createCell(4).setCellValue(job.recruiterEmail != null ? job.recruiterEmail : "N/A");
            row.createCell(5).setCellValue(job.jobLink != null ? job.jobLink : "N/A");
            row.createCell(6).setCellValue(job.jobDescription != null ? job.jobDescription : "N/A");
        }

        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }

        workbook.close();
        System.out.println("Job Listings saved to job_listings.xlsx");
    }
}