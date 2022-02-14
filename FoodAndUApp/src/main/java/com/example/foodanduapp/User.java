package com.example.demo;

import java.util.Set;

public class User {
    String mail;
    String username;
    String password;
    Menu activeMenu;
    Set<Menu> storedMenus;

    public User(String mail, String username, String password) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.activeMenu = null;
        this.storedMenus = null;
    }

    public String getMail() {
        return mail;
    }

    public Menu getActiveMenu() {
        return activeMenu;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }

    public Set<Menu> getStoredMenus() {
        return storedMenus;
    }

    public void setStoredMenus(Set<Menu> storedMenus) {
        this.storedMenus = storedMenus;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
