package com.automationpractice;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class SeleniumSettings {
    //перечень настроек драйвера
    public String url = "http://automationpractice.com/index.php?controller=authentication&back=my-account";  //URL
    public WebDriver driver;

    //запуск драйвера
    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Anastasia/IdeaProjects/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    //завершение работы драйвера
    @AfterTest
    public void closeBrowser() {
        driver.close();
    }


}
