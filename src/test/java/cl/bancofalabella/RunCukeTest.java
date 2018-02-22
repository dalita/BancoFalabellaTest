package cl.bancofalabella;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/",
		plugin ={
				 "pretty", "html:target/cucumberHtmlReport",
			     "html:target/cucumberHtmlReport",     //  for html result
				 "pretty:target/cucumber-json-report.json"   // for json result
			     }
		//,glue = {"steps"}
		
		
		)
		
		

public class RunCukeTest {

}



