package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SteamBot {
    private WebDriver driver;
    private WebDriverWait wait;
    
    public SteamBot() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    public void loginToAccount(SteamAccount account) {
        driver.get("https://store.steampowered.com/");
        
        // Add session cookies
        Cookie sessionIdCookie = new Cookie("sessionid", account.getSessionId(), 
            ".store.steampowered.com", "/", null, true);
        Cookie steamLoginCookie = new Cookie("steamLoginSecure", account.getSteamLoginSecure(), 
            ".store.steampowered.com", "/", null, true);
        
        driver.manage().addCookie(sessionIdCookie);
        driver.manage().addCookie(steamLoginCookie);
        
        // Refresh to apply cookies
        driver.navigate().refresh();
        
        System.out.println("Logged in as: " + account.getAccountName());
    }
    
    public boolean claimFreeGame(String gameUrl) {
        try {
            driver.get(gameUrl);
            
            // Find and click the "Add to Account" button
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#game_area_purchase_section_add_to_cart_1567605 > div.game_purchase_action > div > div.btn_addtocart > a")));
            addButton.click();
            
            System.out.println("Clicked add/play button.");
            
            // Wait for and click the confirmation button in modal
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".newmodal_content a.btn_green_steamui")));
            confirmButton.click();
            
            // Wait for success page
            wait.until(ExpectedConditions.urlContains("success"));
            
            System.out.println("Game claimed successfully!");
            return true;
            
        } catch (Exception e) {
            System.err.println("Error claiming game: " + e.getMessage());
            return false;
        }
    }
    
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
