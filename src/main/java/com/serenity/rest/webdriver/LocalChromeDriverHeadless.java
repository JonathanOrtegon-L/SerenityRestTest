package com.serenity.rest.webdriver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//drivers.windows.webdriver.chrome.driver = ./src/test/resources/drivers/windows/chromedriver.exe
//drivers.mac.webdriver.chrome.driver = ./src/test/resources/drivers/mac/chromedriver
//drivers.linux.webdriver.chrome.driver = ./src/test/resources/drivers/linux/chromedriver

public class LocalChromeDriverHeadless {

    private static final Logger logger = LoggerFactory.getLogger(LocalChromeDriverHeadless.class);
    private static ChromeDriver chromeDriver;
    private static final String WEB_DRIVER = "webdriver.chrome.driver";

    public static LocalChromeDriverHeadless web() {

        String pathDriver;
        pathDriver = "./src/test/resources/drivers/mac/chromedriver";

        System.setProperty(WEB_DRIVER, pathDriver);
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.addArguments("--headless", "--plugins.always_open_pdf_externally=true", "--test-type", "--disable-extensions", "--disable-gpu",
                "--window-size=1620,800", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage", "--allowed-ips",
                "--disable-popup-blocking", "--ignore-certificate-errors", "--always-authorize-plugins", "--incognito");

        chromeDriver = new ChromeDriver(options);
        return new LocalChromeDriverHeadless();
    }

    public WebDriver inToUrl(String url) {
        chromeDriver.get(url);
        logger.info("Abre la pagina web {}", url);
        return chromeDriver;
    }

    public static void closeProcess() {
        chromeDriver.quit();
    }

    public static WebDriver getDriver() {
        return chromeDriver;
    }

}
