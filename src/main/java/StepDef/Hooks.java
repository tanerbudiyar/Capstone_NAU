package StepDef;

import Utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before
	public void setUp(){
		
		Driver.getDriver().manage().window().maximize();
	}
	
	@After
	
	public void tearDown() {
		
		Driver.closeDriver();
	}
	

}
