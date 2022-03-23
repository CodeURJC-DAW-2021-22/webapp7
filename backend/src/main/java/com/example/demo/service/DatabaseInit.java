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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
    private PasswordEncoder passwordEncoder;


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


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(18/07/2021);

        Recipe recipe1 = new Recipe("Huevos revueltos", 5, "Fácil", date, "2 huevos", "Rodri_Chef", false, true, false, false,false, "A la sartén y remover");
        setRecipeImage(recipe1,"fotoscomida/huevosRevueltos.jpg");
        recipeRepository.save(recipe1);
        Recipe recipe2 = new Recipe("Ensalada de tomate", 7, "Fácil", date, "2 tomates, mozarella y especias al gusto", "Rodri_Chef", true, true, false, false,false, "Cortar los tomates y alinear al gusto junto con la mozarella");
        setRecipeImage(recipe2,"fotoscomida/ensaladaTomate.jpg");
        recipeRepository.save(recipe2);
        Recipe recipe3 = new Recipe("Pechuga a la plancha", 5, "Fácil", date, "Filete de pechuga de pollo", "Rodri_Chef", false, true, false, false,false, "Vuelta y vuelta en la plancha");
        setRecipeImage(recipe3,"fotoscomida/pechugaPlancha.jpg");
        recipeRepository.save(recipe3);
        Recipe recipe4 = new Recipe("Espaguetis sencillos", 10, "Fácil", date, "150g de pasta larga, especias provenzales y aceite aromatizado", "Rodri_Chef", false, false, true, false,false, "Cocer la pasta según el fabriante, servir con nuestro aceite aromatizado y las hierbas provenzales");
        setRecipeImage(recipe4,"fotoscomida/spaguettiSencillos.jpg");
        recipeRepository.save(recipe4);
        Recipe recipe5 = new Recipe("Arroz con pollo", 10, "Fácil", date, "100g de arroz, 1 pechuga de pollo", "Rodri_Chef", false, true, true, false,true, "Poner a cocer el arroz, cocinar el pollo en la sartén y añadir el arroz al final con toque de salsa de soja");
        setRecipeImage(recipe5,"fotoscomida/arrozConPollo.jpg");
        recipeRepository.save(recipe5);
        Recipe recipe6 = new Recipe("Ensalada de canónigos", 5, "Fácil", date, "150g de canónigos", "Rodri_Chef", true, false, false, false,false, "Alinear los canónigos al gusto");
        setRecipeImage(recipe6,"fotoscomida/ensaladaCanonigos.jpg");
        recipeRepository.save(recipe6);
        Recipe recipe7 = new Recipe("Sandwich mixto", 8, "Posibilidad de lesión", date, "Pan de molde, jamón york y queso al gusto", "Rodri_Chef", false, true, true, false,false, "Montar un sandwich puta madre y dale vuelta y vuelta en la sartén");
        setRecipeImage(recipe7,"fotoscomida/mixto.jpg");
        recipeRepository.save(recipe7);
        Recipe recipe8 = new Recipe("Papitas fritas", 15, "Intermedia", date, "4 patatas", "Rodri_Chef", false, false, true, true,true, "Cortar patatas, lavar en agua fría para eliminar almidón y freir en abundante aceite");
        setRecipeImage(recipe8,"fotoscomida/patatasFritas.jpg");
        recipeRepository.save(recipe8);
        Recipe recipe9 = new Recipe("Papitas fritas: Versión Airfryer", 15, "Fácil", date, "4 patatas y 1 airfryer", "Rodri_Chef", false, false, true, false,true, "Cortar patatas, lavar en agua fría para eliminar almidón y meter al airfryer");
        setRecipeImage(recipe9,"fotoscomida/patatasFritasAirfryer.jpg");
        recipeRepository.save(recipe9);
        Recipe recipe10 = new Recipe("Tomahawk a la brasa", 40, "Intermedia", date, "1 Tomahawk de ternera", "Rodri_Chef", false, true, false, false,true, "Poner a fuego indirecto durante 20 minutos, 10 a cada lado, y 4, 2 a cada lado, a fuego directo");
        setRecipeImage(recipe10,"fotoscomida/tomahawk.jpg");
        recipeRepository.save(recipe10);
        Recipe recipe11 = new Recipe("Cebolla caramelizada", 10, "Fácil", date, "1 cebolleta", "Rodri_Chef", true, false, false, true,false, "Cortar cebolleta el juliana, y a la sartén con una pizca de azúcar moreno y removiendo constantemente a fuego bajo");
        setRecipeImage(recipe11,"fotoscomida/cebollaCaramelizada.jpg");
        recipeRepository.save(recipe11);
        Recipe recipe12 = new Recipe("Ensalada mixta", 10, "Fácil", date, "Lechuga iceberg, atún, aceitunas negras, tomate", "Rodri_Chef", true, true, false, false,false, "Hacer una ensalada, no tiene mucho más");
        setRecipeImage(recipe12,"fotoscomida/ensaladaMixta.jpg");
        recipeRepository.save(recipe12);
        Recipe recipe13 = new Recipe("Tortilla de patatas", 30, "Intermedia", date, "5 patatas, 3 huevos y 1 cebolleta", "Hugo_Chef", false, true, true, false,true, "Cortar, batir, pochar y cuajar poco");
        setRecipeImage(recipe13,"fotoscomida/TortillaPatatas.jpg");
        recipeRepository.save(recipe13);
        Recipe recipe14 = new Recipe("Ensalada de garbanzos", 10, "Fácil", date, "100g de garbanzos, 1 tomate, 1 cebolla y aliño al gusto", "Hugo_chef", true, true, true, false, false, "Cocer los garbanzos y añadir el tomate y la cebolla cortada, por úlitmo añadir el aliño");
        setRecipeImage(recipe14,"fotoscomida/ensaladaGarbanzos.jpg");
        recipeRepository.save(recipe14);
        Recipe recipe15 = new Recipe("Esparragos trigueros a la plancha", 5, "Fácil", date, "Esparragos y aceite", "Hugo_chef", true, false, true, false, false, "Calentar la plancha y ehchar los esparragos hasta que se doren");
        setRecipeImage(recipe15,"fotoscomida/trigueros.jpg");
        recipeRepository.save(recipe15);
        Recipe recipe16 = new Recipe("Perrito caliente", 5, "Fácil", date, "Pan de perrito, 1 salchicha, ketchup y mostaza", "Hugo_chef", false, true, true, false, false, "Tostar el pan ligeramente y montar el perrito con la salchicha cocida previamente (según fabricante) y ketchup y mostaza al gusto");
        setRecipeImage(recipe16,"fotoscomida/perritosCalientes.jpg");
        recipeRepository.save(recipe16);
        Recipe recipe17 = new Recipe("Totopos con guacamole", 15, "Fácil", date, "Totopos, 2 agucates, 1 lima, media cebolla y cilantro", "Hugo_chef", true, true, true, true, false, "Machacar los aguacates, el jugo de una lima, media cebolla y añadir cilantro al gusto junto con un toque de sal y pimienta negra recién molida");
        setRecipeImage(recipe17,"fotoscomida/guacamole.jpg");
        recipeRepository.save(recipe17);
        Recipe recipe18 = new Recipe("Gambas al ajillo", 10, "Fácil", date, "Gambas, ajo y guindilla", "Hugo_chef", false, true, false, false, false, "Aromatizar aceite con ajo y guindilla y a los 3 minutos añadir las gambas");
        setRecipeImage(recipe18,"fotoscomida/gambasAlAjillo.jpg");
        recipeRepository.save(recipe18);
        Recipe recipe19 = new Recipe("Ternera con verduras", 15, "Intermedia", date, "150g de ternera, cebolla, zanahoria, salsa de soja y sésamo", "Hugo_chef", true, true, false, false, false, "Pochar las verduras a fuego medio-alto, añadir la carne, cuando está todo cocinado, añadir un chorrito de salsa de soja y dejar que caramelice y terminar con un puñadito de sésamo");
        setRecipeImage(recipe19,"fotoscomida/terneraConVerduras.jpg");
        recipeRepository.save(recipe19);
        Recipe recipe20 = new Recipe("Pollo asado", 45, "Fácil", date, "Pollo entero", "Hugo_chef", false, true, false, false, false, "Precalentar el horno a 200ºC, una vez alcanzada la temperatura, meter el pollo y regar con medio vaso  de brandy a la mitad");
        setRecipeImage(recipe20,"fotoscomida/polloAsado.jpg");
        recipeRepository.save(recipe20);
        Recipe recipe21 = new Recipe("Patatas alioli", 20, "Fácil", date, "4 patatas, 1 huevo y 1 ajo", "Hugo_chef", false, false, false, true, false, "Cocer las patatas durante 15 minutos, a la batidora un huevo, un diente de ajo, un chorro de aceite de girasol y una pizca de sal, una vez batido, emulsionar con aceite de oliva. Cortar las patatas al gusto y echar nuestro alioli");
        setRecipeImage(recipe21,"fotoscomida/patatasAlioli.jpg");
        recipeRepository.save(recipe21);
        Recipe recipe22 = new Recipe("Patatas bravas", 15, "Fácil", date, "4 patatas y salsa brava de nuestro gusto", "Hugo_chef", false, false, false, true, false, "Freir patatas hasta que estén a nuestro gusto y echar la salsa brava por encima");
        setRecipeImage(recipe22,"fotoscomida/patatasBravas.jpg");
        recipeRepository.save(recipe22);
        Recipe recipe23 = new Recipe("Berenjena rellena", 45, "Intermedia", date, "1 berenjena, 250g de carne picada y queso para gratinar", "Hugo_chef", true, true, false, false, false, "Cortar a la mitad la berenjena y hornear 20 minutos con un chorro de aceite y una pizca de sal. Mientras cocinamos la carne picada, vaciamos las berenjenas y añadimos el relleno a la sartén de la carne, y terminamos de cocinar. Volvemos a añadir a la carcasa de la berenjena la mezcla y gratinamos con queso a nuestro gusto durante otros 10 minutos");
        setRecipeImage(recipe23,"fotoscomida/berenjenasRellenas.jpg");
        recipeRepository.save(recipe23);
        Recipe recipe24 = new Recipe("Huevos fritos con patatas", 15, "Fácil", date, "2 huevos y 2 patatas", "Hugo_chef", false, true, false, true, false, "Freir huevos y patatas por separado y juntar para lograr tremendo GOD");
        setRecipeImage(recipe24,"fotoscomida/huevosFritosPatatas.jpg");
        recipeRepository.save(recipe24);


        List listRecipes1 = new ArrayList<Recipe>();
        listRecipes1.add(recipe1);
        listRecipes1.add(recipe2);
        listRecipes1.add(recipe3);
        listRecipes1.add(recipe4);
        listRecipes1.add(recipe5);
        listRecipes1.add(recipe6);
        listRecipes1.add(recipe7);
        listRecipes1.add(recipe8);
        listRecipes1.add(recipe9);
        listRecipes1.add(recipe10);
        listRecipes1.add(recipe11);
        listRecipes1.add(recipe12);
        listRecipes1.add(recipe13);
        listRecipes1.add(recipe14);
        Menu menu1 = new Menu("Menu 1", listRecipes1);
        menuRepository.save(menu1);

        List listRecipesHealthy1 = new ArrayList<Recipe>();
        listRecipesHealthy1.add(recipe5);
        listRecipesHealthy1.add(recipe12);
        listRecipesHealthy1.add(recipe4);
        listRecipesHealthy1.add(recipe3);
        listRecipesHealthy1.add(recipe10);
        listRecipesHealthy1.add(recipe6);
        listRecipesHealthy1.add(recipe7);
        listRecipesHealthy1.add(recipe1);
        listRecipesHealthy1.add(recipe5);
        listRecipesHealthy1.add(recipe2);
        listRecipesHealthy1.add(recipe3);
        listRecipesHealthy1.add(recipe12);
        listRecipesHealthy1.add(recipe4);
        listRecipesHealthy1.add(recipe2);
        Menu menuHealthy1 = new Menu("Healthy Menu low in fats (1)", listRecipesHealthy1);
        menuRepository.save(menuHealthy1);

        List listRecipesVegetarian = new ArrayList<Recipe>();
        listRecipesVegetarian.add(recipe8);
        listRecipesVegetarian.add(recipe2);
        listRecipesVegetarian.add(recipe1);
        listRecipesVegetarian.add(recipe11);
        listRecipesVegetarian.add(recipe4);
        listRecipesVegetarian.add(recipe6);
        listRecipesVegetarian.add(recipe9);
        listRecipesVegetarian.add(recipe2);
        listRecipesVegetarian.add(recipe13);
        listRecipesVegetarian.add(recipe6);
        listRecipesVegetarian.add(recipe13);
        listRecipesVegetarian.add(recipe11);
        listRecipesVegetarian.add(recipe4);
        listRecipesVegetarian.add(recipe2);
        Menu menuVegetarian = new Menu("Vegetarian Menu", listRecipesVegetarian);
        menuRepository.save(menuVegetarian);

        List listRecipesHighVolumeGym = new ArrayList<Recipe>();
        listRecipesHighVolumeGym.add(recipe4);
        listRecipesHighVolumeGym.add(recipe3);
        listRecipesHighVolumeGym.add(recipe5);
        listRecipesHighVolumeGym.add(recipe1);
        listRecipesHighVolumeGym.add(recipe13);
        listRecipesHighVolumeGym.add(recipe3);
        listRecipesHighVolumeGym.add(recipe13);
        listRecipesHighVolumeGym.add(recipe4);
        listRecipesHighVolumeGym.add(recipe5);
        listRecipesHighVolumeGym.add(recipe12);
        listRecipesHighVolumeGym.add(recipe4);
        listRecipesHighVolumeGym.add(recipe5);
        listRecipesHighVolumeGym.add(recipe3);
        listRecipesHighVolumeGym.add(recipe1);
        Menu menuHighVolumeGym = new Menu("Menu to grow up muscle volume", listRecipesHighVolumeGym);
        menuRepository.save(menuHighVolumeGym);

        List listRecipes2 = new ArrayList<Recipe>();
        listRecipes2.add(recipe13);
        listRecipes2.add(recipe7);
        listRecipes2.add(recipe9);
        listRecipes2.add(recipe3);
        listRecipes2.add(recipe5);
        listRecipes2.add(recipe12);
        listRecipes2.add(recipe10);
        listRecipes2.add(recipe8);
        listRecipes2.add(recipe4);
        listRecipes2.add(recipe1);
        listRecipes2.add(recipe7);
        listRecipes2.add(recipe9);
        listRecipes2.add(recipe5);
        listRecipes2.add(recipe1);
        Menu menu2 = new Menu("Menu 2", listRecipes2);
        menuRepository.save(menu2);

        List listRecipesVegan = new ArrayList<Recipe>();
        listRecipesVegan.add(recipe2);
        listRecipesVegan.add(recipe15);
        listRecipesVegan.add(recipe4);
        listRecipesVegan.add(recipe14);
        listRecipesVegan.add(recipe8);
        listRecipesVegan.add(recipe6);
        listRecipesVegan.add(recipe11);
        listRecipesVegan.add(recipe15);
        listRecipesVegan.add(recipe14);
        listRecipesVegan.add(recipe6);
        listRecipesVegan.add(recipe9);
        listRecipesVegan.add(recipe2);
        listRecipesVegan.add(recipe4);
        listRecipesVegan.add(recipe15);
        Menu menuVegan = new Menu("Vegan Menu", listRecipesVegan);
        menuRepository.save(menuVegan);

        List listRecipesCheapBuy = new ArrayList<Recipe>();
        listRecipesCheapBuy.add(recipe5);
        listRecipesCheapBuy.add(recipe6);
        listRecipesCheapBuy.add(recipe5);
        listRecipesCheapBuy.add(recipe1);
        listRecipesCheapBuy.add(recipe13);
        listRecipesCheapBuy.add(recipe3);
        listRecipesCheapBuy.add(recipe13);
        listRecipesCheapBuy.add(recipe11);
        listRecipesCheapBuy.add(recipe4);
        listRecipesCheapBuy.add(recipe3);
        listRecipesCheapBuy.add(recipe8);
        listRecipesCheapBuy.add(recipe5);
        listRecipesCheapBuy.add(recipe4);
        listRecipesCheapBuy.add(recipe9);
        Menu menuCheapBuy = new Menu("Cheap Menu to buy in the supermarket", listRecipesCheapBuy);
        menuRepository.save(menuCheapBuy);

        List listRecipesHealthy2 = new ArrayList<Recipe>();
        listRecipesHealthy2.add(recipe4);
        listRecipesHealthy2.add(recipe3);
        listRecipesHealthy2.add(recipe5);
        listRecipesHealthy2.add(recipe1);
        listRecipesHealthy2.add(recipe14);
        listRecipesHealthy2.add(recipe3);
        listRecipesHealthy2.add(recipe5);
        listRecipesHealthy2.add(recipe15);
        listRecipesHealthy2.add(recipe4);
        listRecipesHealthy2.add(recipe2);
        listRecipesHealthy2.add(recipe12);
        listRecipesHealthy2.add(recipe1);
        listRecipesHealthy2.add(recipe10);
        listRecipesHealthy2.add(recipe6);
        Menu menuHealthy2 = new Menu("Healthy Menu low in fats (2)", listRecipesHealthy2);
        menuRepository.save(menuHealthy2);

        List listRecipes3 = new ArrayList<Recipe>();
        listRecipes3.add(recipe9);
        listRecipes3.add(recipe3);
        listRecipes3.add(recipe10);
        listRecipes3.add(recipe2);
        listRecipes3.add(recipe5);
        listRecipes3.add(recipe7);
        listRecipes3.add(recipe13);
        listRecipes3.add(recipe3);
        listRecipes3.add(recipe13);
        listRecipes3.add(recipe15);
        listRecipes3.add(recipe4);
        listRecipes3.add(recipe2);
        listRecipes3.add(recipe14);
        listRecipes3.add(recipe3);
        Menu menu3 = new Menu("Menu 3", listRecipes3);
        menuRepository.save(menu3);

        List listMenuHealthy = new ArrayList<Menu>();
        listMenuHealthy.add(menuHealthy1);
        listMenuHealthy.add(menuHealthy2);
        Diet dietHealthy = new Diet("Healthy Diet", listMenuHealthy);
        dietRepository.save(dietHealthy);

        List listMenuVegetable = new ArrayList<Menu>();
        listMenuVegetable.add(menuVegan);
        listMenuVegetable.add(menuVegetarian);
        Diet dietVegetable = new Diet("Vegetable Diet", listMenuVegetable);
        dietRepository.save(dietVegetable);

        List listMenuGym = new ArrayList<Menu>();
        listMenuGym.add(menuHighVolumeGym);
        listMenuGym.add(menuVegetarian);
        Diet dietGym = new Diet("Gym Diet", listMenuGym);
        dietRepository.save(dietGym);

        List listCheapBuy = new ArrayList<Menu>();
        listCheapBuy.add(menuCheapBuy);
        Diet dietCheapBuy = new Diet("Cheap Diets for shop in the supermarket", listCheapBuy);
        dietRepository.save(dietCheapBuy);

        List listMenuStandard = new ArrayList<Menu>();
        listMenuStandard.add(menu1);
        listMenuStandard.add(menu2);
        listMenuStandard.add(menu3);
        Diet dietStandard = new Diet("Standard Diet", listMenuStandard);
        dietRepository.save(dietStandard);


        List<Diet> dietas = new ArrayList<Diet>();
        dietas.add(dietCheapBuy);

        List<Diet> dietas0 = new ArrayList<Diet>();
        dietas0.add(dietStandard);
        dietas0.add(dietVegetable);
        dietas0.add(dietGym);

        List<Diet> dietas1 = new ArrayList<Diet>();
        dietas1.add(dietGym);

        List<Diet> dietas2 = new ArrayList<Diet>();
        dietas2.add(dietVegetable);

        List<Diet> dietas3 = new ArrayList<Diet>();
        dietas3.add(dietHealthy);

        List<Diet> dietas4 = new ArrayList<Diet>();
        dietas4.add(dietCheapBuy);
        dietas4.add(dietStandard);
        dietas4.add(dietGym);

        List<Diet> dietasAdmin = new ArrayList<Diet>();
        dietasAdmin.add(dietCheapBuy);
        dietasAdmin.add(dietStandard);
        dietasAdmin.add(dietGym);
        dietasAdmin.add(dietHealthy);


        List<Recipe> recipesadd1 = new ArrayList<Recipe>();
        recipesadd1.add(recipe1);
        recipesadd1.add(recipe2);
        recipesadd1.add(recipe3);
        recipesadd1.add(recipe4);

        List<Recipe> recipesadd2 = new ArrayList<Recipe>();
        recipesadd2.add(recipe1);
        recipesadd2.add(recipe2);
        recipesadd2.add(recipe3);
        recipesadd2.add(recipe4);

        List<Recipe> recipesadd3 = new ArrayList<Recipe>();
        recipesadd3.add(recipe1);
        recipesadd3.add(recipe2);
        recipesadd3.add(recipe3);
        recipesadd3.add(recipe4);

        List<Recipe> recipesadd4 = new ArrayList<Recipe>();
        recipesadd4.add(recipe1);
        recipesadd4.add(recipe2);
        recipesadd4.add(recipe3);
        recipesadd4.add(recipe4);

        List<Recipe> recipesadd5 = new ArrayList<Recipe>();
        recipesadd5.add(recipe1);
        recipesadd5.add(recipe2);
        recipesadd5.add(recipe3);
        recipesadd5.add(recipe4);

        List<Recipe> recipesadd6 = new ArrayList<Recipe>();
        recipesadd6.add(recipe1);
        recipesadd6.add(recipe2);
        recipesadd6.add(recipe4);


        User user0 = new User("user0@gmail.com","user0", passwordEncoder.encode("123"),recipesadd1,menu1,dietas0,false);
        User user1 = new User("user1@gmail.com","user1",passwordEncoder.encode("123"),recipesadd2,menu2,dietas1,false);
        User user2 = new User("user2@gmail.com","user2",passwordEncoder.encode("123"),recipesadd3,menu3,dietas2,false);
        User user3 = new User("user3@gmail.com","user3",passwordEncoder.encode("123"),recipesadd4,menuVegan,dietas3,false);
        User user4 = new User("user4@gmail.com","user4",passwordEncoder.encode("123"),recipesadd5,menuVegetarian,dietas4,false);
        User admin = new User("admin@gmail.com","admin",passwordEncoder.encode("123"),recipesadd6,menuCheapBuy,dietasAdmin,true);

        userRepository.save(user0);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(admin);


    }

    public void setRecipeImage (Recipe newRecipe, String classpathResource) throws IOException {
        newRecipe.setHasPhoto(true);
        Resource image = new ClassPathResource(classpathResource);
        newRecipe.setRecipeImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
