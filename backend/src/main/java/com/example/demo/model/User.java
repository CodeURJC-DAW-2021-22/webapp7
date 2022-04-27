package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean admin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_Menu", referencedColumnName = "id")
    private Menu activeMenu;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Recipe> storedRecipes;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Diet> storedDiets;

    public User() {

    }
    public User(String mail, String username, String password, List<Recipe> recipes, Menu act, List<Diet> dietas, boolean admin) {
        this.mail = mail;
        this.name = username;
        this.password = password;
        this.storedRecipes = recipes;
        this.activeMenu = act;
        this.storedDiets = dietas;
        this.admin = admin;}


    public ShoppingList generateList(){
        if(activeMenu!=null)
            return new ShoppingList(this.activeMenu.getWeeklyPlan());
        return null;
    }

    public List<Recipe> getStoredRecipes() {
        return storedRecipes;
    }

    public void removeStoredRecipes(long id){
        int position = 0;
        int solution = -1;
        for(Recipe recipe: this.storedRecipes) {
            if(recipe.getId().equals(id)){
                solution = position;
            }
            position++;
        }
        this.storedRecipes.remove(solution);
    }

    public void addStoredRecipes(Recipe recipe){this.storedRecipes.add(recipe);}
    public void addStoredDiets(Diet diet){this.storedDiets.add(diet);}

    public void setStoredRecipes(List<Recipe> storedRecipes) {
        this.storedRecipes = storedRecipes;
    }

    public String getMail() {
        return mail;
    }

    public Menu getActiveMenu() {
        return activeMenu;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
    public List<String> getRoles(){
        List<String> toReturn = new ArrayList();
        toReturn.add("USER");
        if (this.admin)
            toReturn.add("ADMIN");
        return toReturn;
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
