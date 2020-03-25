package com.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

public class UserRegistration extends SeleniumSettings {
    public String[] emailArray = {"bar","bar.com","getter[]setter[dot]com","","getter@setter.com", "hoba"};

    //рандомная строка до 10 символов
    protected String randomString() {
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();

        while (stringBuilder.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            stringBuilder.append(CHARS.charAt(index));
        }
        String randomStr = stringBuilder.toString();
        return randomStr;
    }

    public String email;

    //тест: регистрация юзера с обязательными полями
    @Test
    public void createAccount() throws InterruptedException {
        email = randomString() + "@google.com";  //рандомный емейл

        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).submit();
        Thread.sleep(3000);

        driver.findElement(By.id("customer_firstname")).sendKeys("firstname");
        driver.findElement(By.id("customer_lastname")).sendKeys("lastname");
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);
        driver.findElement(By.id("passwd")).sendKeys("pswrd");
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), "firstname");
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), "lastname");
        driver.findElement(By.id("address1")).sendKeys("addrs1");
        driver.findElement(By.id("city")).sendKeys("city");
        driver.findElement(By.id("postcode")).sendKeys("00000");
        Select state = new Select(driver.findElement(By.id("id_state")));
        driver.findElement(By.id("phone_mobile")).sendKeys("92838382811");
        state.selectByValue("1");

        driver.findElement(By.id("submitAccount")).click();
        Assert.assertTrue(driver.getTitle().equals("My account - My Store"), "Пользователь зарегистрирован");
    }

    //тест: проверка валидности вводимого email
    @Test
    public void wrongEmailTest() throws InterruptedException {
        ArrayList<String> incorrectEmail = new ArrayList<>();
        ArrayList<String> correctEmail = new ArrayList<>();

        for (int i = 0; i < emailArray.length; i++) {
            driver.findElement(By.id("email_create")).sendKeys(emailArray[i]);
            driver.findElement(By.id("SubmitCreate")).submit();
            Thread.sleep(1000);

            if (driver.findElement(By.id("create_account_error")).isDisplayed()) {
                //Assert.assertTrue(driver.findElement(By.id("create_account_error")).isDisplayed());
                incorrectEmail.add(emailArray[i]);
                //System.out.println("Email \"" + emailArray[i] + "\" incorrect.");
            } else {
                correctEmail.add(emailArray[i]);
                driver.get(url);
                Thread.sleep(3000);
                //System.out.println("Email \"" + emailArray[i] + "\" was correct. Registration");
            }

            driver.findElement(By.id("email_create")).clear();
        }

        System.out.print("Invalid emails: ");
        for (String wrong : incorrectEmail) {
            System.out.print("\"" + wrong + "\" ");
        }

        System.out.println();
        System.out.print("Valid emails: ");
        for (String succ : correctEmail) {
            System.out.print("\"" + succ + "\" ");
        }
    }
}

