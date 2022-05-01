package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Menu> menus;


    public Diet() {
        this.menus=new ArrayList<Menu>();
    }
    public Diet(String name, List<Menu> listaMenus){
        this.menus=listaMenus;
        this.name=name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public void addtoDiet(Menu newMenu){
        menus.add(newMenu);
    }

    public void removeMenuFromDiet(Menu m){
        menus.remove(m);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
