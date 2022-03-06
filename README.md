# Food & U

## **Introduction**

This is a project where we are going to develop a new web application about the relationship beetween the food and our users. The main idea is that you will find recipes uploaded by other users, and you would save them to build your semanal food plan. In addition, you will have the opportunity to generate your own shopping list automatically, using the different elements that your daily food has. Let's take a deeper look about what are we planning to develop:


 ## **Food & U features**

Which features will Food & U have? Our development team has planned to include:

- Upload your own recipes which you think that other users could find interesting. These recipes must include images, which will mean a very first look for the interesed.
- Save the recipes that you want to include in your weekly menu.
- Every user will find in their data an active menu, filled by different meals that you have chosed.
- Every meal will contain its recipe and shopping list
- You could save as many menus as you want, but only one will be active.
- Every meal or recipe will include the principal nutritional information.
- Food & U will provide information about your weekly menu: you will see how balanced is your diet, and how could you improve it around our standards.

 ## **Entities**

Food & U will be working with four kind of entities, which they are:

| Entity  | Description                                                                                                                                                                                                                                                                                                                                   |
|---------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Users   | People that will be using our app you will find four kinds: **logged users** allowed to save content, and generate their own diets and menus **unlogged users** which only will be allowed to explore recipes, and **admin users** which are allowed to upload, save and delete content. They also will find some information about our users |
| Menus   | Every user could save as many menus as wanted. Only one will be active, but you can save everyone you want according to your objetives: gain or lose weight                                                                                                                                                                                   |
| Recipes | Every meal from the menu will be composed by its recipe. Every weekly menu will have 7 lunches and 7 dinners, so it will have 14 recipes. Also, users can upload their own recipes to the web app where it will be stored, and available to include in other menus.                                                                           |
| Diets   | Every menu will be stored in diferent diets. This will allow our users to store in better conditions their menus, also in a more structures form.                                                                                                                                                                                             |

 ## **Extra technology**

Using Food & U will not requiere a full-time connection to check your menu or recipes. You will find the easiest way to download your menu and recipes: just pressing a button! It is as simple as that. Additionally, it may be developed the way to generate a menu which fits better with your idea of gain or lose weight. We know is not easy to start doing this, and sometimes you need help because the lack of ideas. Food & U provides help with this kind of issues.

## **Web Navigation**

You can find in the following image a very first look about how we, the development team, think that you can travel around the web application from a point of view of a logged user:


![WebNavigation](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/First_Version.PNG)

Down Below you will find some of the HTML pages that you may use in the final version of this project:

**Index**

![Index 1](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Index%201.PNG)

![Index 2](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Index%202.PNG)

**About Us** (the text is just one example)

![AboutUs](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/AboutUs.PNG)

**User Main Page**

![User Main Page](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/User.PNG)

**Active Menu**

![Menu](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Menu%20semanal.PNG)

**User Diets**

![Stored Diets](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Stored%20Diets.PNG)

**Sign Up & Log In**

![SignUp](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/SingUp.PNG)

![LogIn](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/LogIn.PNG)

**Recipes Browser**

![Recipes Browser](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Recetas.PNG)

**Recipe Page**

![Recipe](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Recipe.PNG)




 ## **Algorithms**

Food & U will include an algorithm that recommend you recipes that is nutritionally similar to the ones you have chosed previously. We want to implement this to add variety to our users' menus. It will be based, of course, in the nutritional score of the previous meals used in the past week. In addition, we will be proud if the final version of the project is capable of making automatically a new diet based on the desires and goals of our users, but this would be included if time allows us to do so.

## **Graphics**

Food & U will provide to its users the possibility of compare their own menus with one which we consider appropiate and balanced. This will be shown as graphic as possible, trying to be clear and direct with the information. This information will be private for every user, because we can consider it as something personal.

## **How to start up Food & U app**

Pre-requeriments:
- PostGreSQL Server 14.2-1 version, you can download it [here](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads).
- Any IDE or framework to load the project that supports Spring Boot projects. We extremely recommend IntelliJ IDEA and you could find it [here](https://www.jetbrains.com/es-es/idea/download/#section=windows).
- You will also need JAVA 11, but once the IDE starts the project, it will notice and says that it can be downloaded and installed automatically with your authorization.
- You should know that we are developing with spring boot 2.6.3 version but no install is needed to feature it.

Once you started the project, that may take a couple of minutes while installing plugins and dependencies, you should make sure that everything is well installed. To know it, you should right-click pom.xml file, and follow > Maven > Reload Project. Once it is done, everything should be ready.

Then is time to start up your database. While installing PostGreSQL Server, you must establish a superuser to access to every DB made with your computer, its name will be 'postgres', but you can set up the password you want. We have used '1234' and it is represented in backend\src\main\resources\application.properties, in "spring.datasource.password" line. If you have setted another password, please, change the value to start with no problems here.

Now is time to create the DB. We will follow an example with IntelliJ. Once the project is loaded, you will find a view in the rightside bar called Database and we can create a new one clicking on +. Then, follow Data Source > PostGreSQL. You dont have to change nothing else than introducing as username "postgres" and the password you chosed for the superuser. Before starting it, check if the connection is properly, but following this tutorial step by step you wont have any problem. Also before starting, you must copy the URL, and paste it as the value of "spring.datasource.url" in the application.properties file.

Now, everything is ready to start. Find the .java file known as "BackendApplication" in backend\src\main\java\BackendApplication. Rightclick it and you could start it. The first run will take more less half a minute because it should be built first, so please be patient while it is loading.

Then, everything you must do is start your favourite navigator and paste this link in the browser [https://localhost:8443](https://localhost:8443) and the only thing to do now is enjoy using Food & U!

## **Developing Part by every member**

- **Rodrigo Marqués Buil**: I developed the most important part of the DB structure and its relations, the package of security was also part of the job I have done, and the more difficult methods of every class in the model. In the other hand, I helped my partners to work trying to solve their problems, knowing that I mostly designed the structure of the project. You will find some of my more important commits that I have done:
  - [Security Package added (not finished but mostly)](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/740276f31e60420935c3af79c2c396899e4e946f)
  - [Photos can be added and stored in the DB](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/5bbd0e40fe6098ff8cdf1b3e280e31c46d43dbd1)
  - [Functional Log In](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/571a2d4faf21f6626fde8961c4ec933b09ee5615)
  - [Setting Up DB](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/56dc0fbdcc84f7cbe08a322c924592ee902f1c33)
  - [Graphics added, Part 1](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/4d9603244e85f3c06389d51cfd1497acb5620b38)
  - [Grephics added, Part 2](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/36fa83a632c04306b57983183ff848dedb0b8722)
  
    And now I will add some links to explore some of the most edited files by me:
  - [Menu JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/Menu.java)
  - [User JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/User.java)
  - [Web Controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/WebController.java)
  - [Web Security Config](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/security/WebSecurityConfig.java)
  - [Database Initializer](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/service/DatabaseInit.java)
- **Hugo Coto González**
- **Carlos Rodríguez Gómez**
- **Carlos Alejandro Álvarez**
