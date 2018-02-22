package cl.bancofalabella.stepdefinitions;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

//import com.google.common.base.Predicate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login2fail {

	private String baseUrl = "https://www.bancofalabella.cl/BancoFalabellaChile/index.html";
	private WebDriver driver;
	// private String driverPath = "E:/drivers/chrome29/chromedriver_win32/";
	// static String driverPath = "E:/drivers/IEDriverServer_x64_3.4.0/";
	private String browserInUse;

	@Given("^Se determina el navegador2 \"(.*?)\"$")
	public void se_determina_el_navegador2(String browser) throws Throwable {
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("*******************" + browser);
			System.out.println("launching Chrome browser");
			URL url = ClassLoader.getSystemResource("chromedriver2.35.exe");
			// System.setProperty("webdriver.chrome.driver", driverPath +
			// "chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", url.getPath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			browserInUse = browser;
		} else if (browser.equalsIgnoreCase("iexplorer")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, baseUrl);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			URL url = ClassLoader.getSystemResource("IEDriverServer.exe");
			// URL url =
			// ClassLoader.getSystemResource("IEDriverServer_x64_3.4.0.exe"); el
			// sendkeys es muy lento
			System.setProperty("webdriver.ie.driver", url.getPath());
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			browserInUse = browser;

		} else {
			Assert.fail("DEBE ESPECIFICAR UN NOMBRE DE NAVEGADOR DEFINIDO");
		}

	}

	@Given("^El usuario navega a la pagina de falabella$")
	public void el_usuario_navega_a_la_pagina_de_falabella() throws Throwable {
		// navengando en https://www.bancofalabella.cl/BancoFalabellaChile/index.html
		//if (!browserInUse.equalsIgnoreCase("iexplorer")) {
			driver.navigate().to(baseUrl);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//}
	}

	@When("^Se introduce el \"(.*?)\" y la \"(.*?)\" no registrados$")
	public void se_introduce_el_y_la_no_registrados(String arg1, String arg2) throws Throwable {
		// Introduce datos de usuarios no registrados
		driver.findElement(By.id("bfrut1")).clear();
		driver.findElement(By.id("bfrut1")).sendKeys(arg1);
		driver.findElement(By.id("bfclave")).clear();
		driver.findElement(By.id("bfclave")).sendKeys(arg2);

	}

	@When("^Se procede a autenticar$")
	public void se_procede_a_autenticar() throws Throwable {
		// click boton entrar
		if (browserInUse.equalsIgnoreCase("chrome"))
			driver.findElement(By.id("enviar")).click();
		else {
			// via alternativa ya que en IE se queda pegado esperando que cargue
			// el sumbit del formulario de login
			driver.findElement(By.id("bfclave")).sendKeys(Keys.ENTER);
			Alert alert = null;
			alert = driver.switchTo().alert();
			String alertMessage= driver.switchTo().alert().getText();		
     		// Displaying alert message		
		    System.out.println("MCV//////   " + alertMessage);
		}

		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Then("^Se debe mostrar mensaje de error$")
	public void se_debe_mostrar_mensaje_de_error() throws Throwable {
		// Se valida mensaje de error
		Alert alert = null;
		alert = driver.switchTo().alert();
		System.out.println("########################################## " + alert.getText());

		
		
		try
		{
			Assert.assertTrue(alert.getText().equalsIgnoreCase("Por favor, ingrese un RUT válido2"));
			System.out.println("Test Correcto SE COMPRUEBA QUE SE MUESTRA EL MENSAJE DE ERROR DE VALIDACION DEL LOGIN");
			
		}
		catch (AssertionError as)
		{
			
			System.out.println(as.getLocalizedMessage());
			throw new AssertionError("PRUEBA FALLIDA NO SE VERIFICA EL MENSAJE DE ALERT ESPERADO");
		}
		finally{
			driver.close();
			driver.quit();
		}
		
		

	}
	
	  @AfterMethod
	    public void tearDown()
	    {
		  driver.close();	        
		  driver.quit();
	    }

}
