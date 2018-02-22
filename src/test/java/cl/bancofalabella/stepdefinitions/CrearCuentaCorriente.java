package cl.bancofalabella.stepdefinitions;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CrearCuentaCorriente {
	private String baseUrl = "https://cuentacorriente.bancofalabella.cl/#/";
	private WebDriver driver;
	// private String driverPath = "E:/drivers/chrome29/chromedriver_win32/";
	// static String driverPath = "E:/drivers/IEDriverServer_x64_3.4.0/";
	private String browserInUse;

	@Given("^Se determina el navegador \"(.*?)\"$")
	public void se_determina_el_navegador(String browser) throws Throwable {
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("*******************" + browser);
			System.out.println("launching Chrome browser");
			URL url = ClassLoader.getSystemResource("chromedriver2_29.exe");
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

	@Given("^El usuario navega a la pagina de Falabella$")
	public void el_usuario_navega_a_la_pagina_de_Falabella() throws Throwable {
		// navengando en https://cuentacorriente.bancofalabella.cl/#/
		if (!browserInUse.equalsIgnoreCase("iexplorer")) {
			driver.navigate().to(baseUrl);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	@When("^Introduce \"(.*?)\" y \"(.*?)\" y \"(.*?)\"$")
	public void introduce_y_y(String arg1, String arg2, String arg3) throws Throwable {
		// rut
		driver.findElement(By.id("rut")).clear();
		driver.findElement(By.id("rut")).sendKeys(arg1);
		// Telefono
		driver.findElement(By.id("tel")).clear();
		driver.findElement(By.id("tel")).sendKeys(arg2);
		// correo
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(arg3);
	}

	@When("^Se procede a solicitar cuenta$")
	public void se_procede_a_solicitar_cuenta() throws Throwable {
		// para ie y chrome
		driver.findElement(By.id("solicitud-cuenta")).click();

	}

	@When("^Se procede a ingresar <nombre y apellido >$")
	public void se_procede_a_ingresar_nombre_y_apellido(String arg4) throws Throwable {
		// nombre
		driver.findElement(By.id("nombre")).clear();
		driver.findElement(By.id("nombre")).sendKeys(arg4);
	}

	@When("^Se procede a ingresar <fehca de nacimiento>$")
	public void se_procede_a_ingresar_fehca_de_nacimiento(String arg5) throws Throwable {
		// fecha
		driver.findElement(By.id("fechanac")).clear();
		driver.findElement(By.id("fechanac")).sendKeys(arg5);
	}

	@When("^Se procede a ingresar \"(.*?)\"$")
	public void se_procede_a_ingresar(String arg6) throws Throwable {
		// renta liquida
		driver.findElement(By.id("renta")).clear();
		driver.findElement(By.id("renta")).sendKeys(arg6);
	}

	@When("^Se presiona boton continuar$")
	public void se_presiona_boton_continuar() throws Throwable {
		driver.findElement(By.id("btnContinue")).click();
	}

	@Then("^Se debe mostrar mensaje satisfactorio$")
	public void se_debe_mostrar_mensaje_satisfactorio() throws Throwable {
		
		
		WebElement msg = driver.findElement(By.id("tittle_message"));
		Assert.assertTrue(msg.getText().equalsIgnoreCase("Te contactaremos en las próximas"));
	
		driver.close();
		driver.quit();
		System.out.println("Prueba Satisfactoria");
	}

}
