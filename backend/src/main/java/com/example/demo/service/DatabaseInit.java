package com.example.demo.service;


import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.DietRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DietRepository dietRepository;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        //sample users
        Menu menuVoid= new Menu();
        menuRepository.save(menuVoid);
        List<Menu> menuList = new ArrayList<Menu>();
        List<Diet> dietas = new ArrayList<Diet>();
        User user0 = new User("user0@gmail.com","user0","123",menuVoid,dietas);
        User user1 = new User("user1@gmail.com","user1","123",menuVoid,dietas);
        User user2 = new User("user2@gmail.com","user2","123",menuVoid,dietas);
        User user3 = new User("user3@gmail.com","user3","123",menuVoid,dietas);
        User user4 = new User("user4@gmail.com","user4","123",menuVoid,dietas);
        User admin = new User("admin@gmail.com","admin","123",menuVoid,dietas);

        userRepository.save(user0);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(admin);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(18/07/2021);

        Recipe recipe0 = new Recipe("Tortilla de patatas", 30, "Intermedia", formatter.format(date), "5 patatas, 3 huevos y 1 cebolleta", "Rodri_Chef", false, true, true, false, "Cortar, batir, pochar y cuajar poco");
        Recipe recipe1 = new Recipe("Huevos revueltos", 5, "Fácil", formatter.format(date), "2 huevos", "Rodri_Chef", false, true, false, false, "A la sartén y remover");
        Recipe recipe2 = new Recipe("Ensalada de tomate", 7, "Fácil", formatter.format(date), "2 tomates, mozarella y especias al gusto", "Rodri_Chef", true, true, false, false, "Cortar los tomates y alinear al gusto junto con la mozarella");
        Recipe recipe3 = new Recipe("Pechuga a la plancha", 5, "Fácil", formatter.format(date), "Filete de pechuga de pollo", "Rodri_Chef", false, true, false, false, "Vuelta y vuelta en la plancha");
        Recipe recipe4 = new Recipe("Espaguetis sencillos", 10, "Fácil", formatter.format(date), "150g de pasta larga, especias provenzales y aceite aromatizado", "Rodri_Chef", false, false, true, false, "Cocer la pasta según el fabriante, servir con nuestro aceite aromatizado y las hierbas provenzales");
        Recipe recipe5 = new Recipe("Arroz con pollo", 10, "Fácil", formatter.format(date), "100g de arroz, 1 pechuga de pollo", "Rodri_Chef", false, true, true, false, "Poner a cocer el arroz, cocinar el pollo en la sartén y añadir el arroz al final con toque de salsa de soja");
        Recipe recipe6 = new Recipe("Ensalada de canónigos", 5, "Fácil", formatter.format(date), "150g de canónigos", "Rodri_Chef", true, false, false, false, "Alinear los canónigos al gusto");
        Recipe recipe7 = new Recipe("Sandwich mixto", 8, "Posibilidad de lesión", formatter.format(date), "Pan de molde, jamón york y queso al gusto", "Rodri_Chef", false, true, true, false, "Montar un sandwich puta madre y dale vuelta y vuelta en la sartén");
        Recipe recipe8 = new Recipe("Papitas fritas", 15, "Intermedia", formatter.format(date), "4 patatas", "Rodri_Chef", false, false, true, true, "Cortar patatas, lavar en agua fría para eliminar almidón y freir en abundante aceite");
        Recipe recipe9 = new Recipe("Papitas fritas: Versión Airfryer", 15, "Fácil", formatter.format(date), "4 patatas y 1 airfryer", "Rodri_Chef", false, false, true, false, "Cortar patatas, lavar en agua fría para eliminar almidón y meter al airfryer");
        Recipe recipe10 = new Recipe("Tomahawk a la brasa", 40, "Intermedia", formatter.format(date), "1 Tomahawk de ternera", "Rodri_Chef", false, true, false, false, "Poner a fuego indirecto durante 20 minutos, 10 a cada lado, y 4, 2 a cada lado, a fuego directo");
        Recipe recipe11 = new Recipe("Cebolla caramelizada", 10 mins, "Fácil", formatter.format(date), "1 cebolleta", "Rodri_Chef", true, false, false, true, "Cortar cebolleta el juliana, y a la sartén con una pizca de azúcar moreno y removiendo constantemente a fuego bajo");
        Recipe recipe12 = new Recipe("Ensalada mixta", 10, "Fácil", formatter.format(date), "Lechuga iceberg, atún, aceitunas negras, tomate", "Rodri_Chef", true, true, false, false, "Hacer una ensalada, no tiene mucho más");
    }
    public void setRecipeImage (Recipe newRecipe, String classpathResource) throws IOException {
        newRecipe.setHasPhoto(true);
        Resource image = new ClassPathResource(classpathResource);
        newRecipe.setRecipeImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
