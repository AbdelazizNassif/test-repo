package driverSettigns;

import filesReaders.PropertyFileReader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;



public class DriverFactory {
    WebDriver driver = null ;
    String runMode = null;
    String browser = null;
    final String PROPERTIES_FILE_NAME = "execution.properties" ;

    public WebDriver getDriver ()
    {    PropertyFileReader execution = new PropertyFileReader(PROPERTIES_FILE_NAME);

        runMode = execution.getPropertyByKey( "RUN_MODE");
        if (runMode.equalsIgnoreCase("local"))
        {
            browser = execution.getPropertyByKey( "BROWSER");
            if (browser.equalsIgnoreCase("chrome"))
            {
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--remote-allow-origins=*");

//                if (execution.getBooleanProperty( "HEADLESS") == true)
//                {
//                    options.setHeadless(true);
//                    options.addArguments("--window-size=1480,700");
//                }
                driver = new ChromeDriver(options);
            }
            else if (browser.equalsIgnoreCase("edge"))
            {
                driver = new EdgeDriver();
            }
            else if (browser.equalsIgnoreCase("firefox"))
            {
                driver = new FirefoxDriver();
            }
            else {
                System.out.println("Not supported browser");
            }
        }
        else if (runMode.equalsIgnoreCase("docker"))
        {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setBrowserName(execution.getPropertyByKey( "BROWSER"));
//            caps.setVersion("101");
            caps.setPlatform(Platform.LINUX);
            try {
                driver = new RemoteWebDriver(new URL(execution.getPropertyByKey( "DOCKER_ADDRESS")), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if (runMode.equalsIgnoreCase("localGrid"))
        {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setBrowserName(execution.getPropertyByKey( "BROWSER"));
//            caps.setVersion("101");
            caps.setPlatform(Platform.LINUX);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if (runMode.equalsIgnoreCase("browserstack"))
        {
            String URL = "https://" + execution.getPropertyByKey( "BROWSER_STACK_USERNAME") + ":" +
                    execution.getPropertyByKey( "BROWSER_STACK_ACCESS_KEY") + "@hub-cloud.browserstack.com/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "100");

        caps.setCapability("name", "First Test");
            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }


}
