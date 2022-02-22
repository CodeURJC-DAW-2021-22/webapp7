package com.example.demo.model;

import org.hibernate.annotations.Cascade;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity @Table(name="userTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mail;
    private String name;
    private String password;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name="ID_Menu", referencedColumnName = "id")
    private Menu activeMenu;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Recipe> storedRecipes;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Diet> storedDiets;

    public User() {

    }
    public User(String mail, String username, String password, List<Recipe> recipes, Menu act, List<Diet> dietas) {
        this.mail = mail;
        this.name = username;
        this.password = password;
        this.storedRecipes = recipes;
        this.activeMenu = act;
        this.storedDiets = dietas;}


    public ShoppingList generateList(){
        if(activeMenu!=null)
            return new ShoppingList(this.activeMenu.getWeeklyPlan());
        return null;
    }

    public List<Recipe> getStoredRecipes() {
        return storedRecipes;
    }

    public void addStoredRecipes(Recipe recipe){this.storedRecipes.add(recipe);}

    public void setStoredRecipes(List<Recipe> storedRecipes) {
        this.storedRecipes = storedRecipes;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Diet> getStoredDiets() {
        return storedDiets;
    }

    public void setStoredDiets(List<Diet> storedDiets) {
        this.storedDiets = storedDiets;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
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
