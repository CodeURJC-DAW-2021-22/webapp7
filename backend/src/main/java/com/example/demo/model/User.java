package com.example.demo.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    String mail;
    String username;
    String password;

    @OneToOne
    Menu activeMenu;

    @OneToMany
    List<Menu> storedMenus;

    @OneToOne
    ShoppingList shoppingList;

    public User(String mail, String username, String password) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.activeMenu = null;
        this.storedMenus = null;
        this.shoppingList = null; // esto habria que generarlo una vez la base de datos devuelva un menu activo
    }

    public User() {

    }

    public ShoppingList generateList(){
        if(activeMenu!=null)
            return new ShoppingList(this.activeMenu.getWeeklyPlan());
        return null;
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

    public List<Menu> getStoredMenus() {
        return storedMenus;
    }

    public void setStoredMenus(List<Menu> storedMenus) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
