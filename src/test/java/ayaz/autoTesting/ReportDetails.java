package ayaz.autoTesting;

import com.aventstack.extentreports.reporter.configuration.Theme;


public class ReportDetails {

    private final String reportFilePath, documentTitle, reportName;

    private Theme theme;

    public ReportDetails(String reportFilePath, String documentTitle, String reportName){
        this.reportFilePath = reportFilePath;
        this.documentTitle = documentTitle;
        this.reportName = reportName;

        System.out.println("Report created here: " + reportFilePath);
    }

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getReportFilePath() {
		return reportFilePath;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public String getReportName() {
		return reportName;
	}


}
