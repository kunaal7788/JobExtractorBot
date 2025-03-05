package jobExtractorBot;

class JobData {
    String jobTitle;
    String companyName;
    String jobLocation;
    String jobCTC;
    String recruiterEmail;
    String jobLink;
    String jobDescription;

    public JobData(String jobTitle, String companyName, String jobLocation, String jobCTC, 
                   String recruiterEmail, String jobLink, String jobDescription) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.jobLocation = jobLocation;
        this.jobCTC = jobCTC;
        this.recruiterEmail = recruiterEmail;
        this.jobLink = jobLink;
        this.jobDescription = jobDescription;
    }
}
