package br.faccat.clinica_selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AgendamentoSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // Altere aqui
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void deveAgendarConsultaViaInterface() {
        driver.get("http://localhost:8080/agendar.html");

        driver.findElement(By.id("paciente")).sendKeys("Laura");
        driver.findElement(By.id("cpf")).sendKeys("12345678900");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('datahora').value = '2025-07-01T14:00';");

        driver.findElement(By.tagName("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));

        assertEquals("Consulta agendada para Laura (CPF: 12345678900) em 2025-07-01T14:00", mensagem.getText());
    }

}
