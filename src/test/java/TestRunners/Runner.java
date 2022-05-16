package TestRunners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import Utils.ExcelUtils;
import Utils.SendEmailUtils;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features"
		,glue = "StepDef"
		,tags= "@search"
//		,dryRun= true
		,plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json"}
		,monochrome = true		
		)

public class Runner {
	@BeforeClass
	public static void beforeClass() {
		ExcelUtils.prepareFile();
	}
	@AfterClass
	public static void afterClass() {
		File reportOutputDirectory = new File("target/cucumberReports");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/cucumber-reports/Cucumber.json");
		String projectName = "price-checker";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
		        reportBuilder.generateReports();
		        SendEmailUtils.sendEmailToRecipients();   
	}

}
