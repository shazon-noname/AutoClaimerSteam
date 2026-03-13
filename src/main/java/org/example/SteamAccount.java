package org.example;

public class SteamAccount {
    private String sessionId;
    private String steamLoginSecure;
    private String accountName;
    
    public SteamAccount(String sessionId, String steamLoginSecure, String accountName) {
        this.sessionId = sessionId;
        this.steamLoginSecure = steamLoginSecure;
        this.accountName = accountName;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getSteamLoginSecure() {
        return steamLoginSecure;
    }
    
    public void setSteamLoginSecure(String steamLoginSecure) {
        this.steamLoginSecure = steamLoginSecure;
    }
    
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    @Override
    public String toString() {
        return accountName + " (" + sessionId + ")";
    }
}
