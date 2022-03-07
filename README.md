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


| Name    | Mail | Github User    |
|---------|--------|----------------|
| Rodrigo Marqués Buil|r.marques.2018@alumnos.urjc.es| Larrivey       |
| Hugo Coto González|h.coto.2018@alumnos.urjc.es| hugocg6        |
| Carlos Rodríguez Gómez|c.roriguezgo.2018@alumnos.urjc.es| carlitosrogo   |
| Carlos Alejandro Álvarez|c.alejandro.2019@alumnos.urjc.es| CalejandroURJC |


- **Rodrigo Marqués Buil**: I developed the most important part of the DB structure and its relations, the package of security was also part of the job I have done, and the more difficult methods of every class in the model. In the other hand, I helped my partners to work trying to solve their problems, knowing that I mostly designed the structure of the project. You will find some of my more important commits that I have done:
  - [Security Package added (not finished but mostly)](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/740276f31e60420935c3af79c2c396899e4e946f)
  - [Photos can be added and stored in the DB](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/5bbd0e40fe6098ff8cdf1b3e280e31c46d43dbd1)
  - [Functional Log In](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/571a2d4faf21f6626fde8961c4ec933b09ee5615)
  - [Setting Up DB](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/56dc0fbdcc84f7cbe08a322c924592ee902f1c33)
  - [Graphics added, Part 1](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/4d9603244e85f3c06389d51cfd1497acb5620b38)
  - [Graphics added, Part 2](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/36fa83a632c04306b57983183ff848dedb0b8722)
  
    And now I will add some links to explore some of the most edited files by me:
  - [Menu JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/Menu.java)
  - [User JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/User.java)
  - [Web Controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/WebController.java)
  - [Web Security Config](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/security/WebSecurityConfig.java)
  - [Database Initializer](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/service/DatabaseInit.java)

- **Hugo Coto González**: My work has consisted in the development of many of the controllers that were related with getting information from the DB along with the correct display in the frontend set through the use of Mustache (the complete fill of the index and recipes pages). Also, I implemented the whole structure to page objects, so my teammates could replicate it without any extremely complex change and save time on their own implementations that required this method. Moreover, the toughest task I had to deal with, without any doubt, was the AJAX script in addition to the correct display of the paginated objects. The most important commits I've made are shown below: 
  - [Functional AJAX button](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/1ca6cbedfce9b57e85d9bfe6f29be29e99f90ded)
  - [AJAX Related, Part 1](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/c5a07863c4f81fb8bc219ea34e31fb34313b90c2)
  - [AJAX Related, Part 2](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/77431b4afebbfcd4e93c7022dd37df4c65ae86d2)
  - [Index loaded from DB](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/4fb87287d80986a605bf15ee1c49a47f93dfa10e)
  - [Load paginated objects method (/Recipes)](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/d9af423294269fc8ff398c44784da4c283b15910)
  - [Header + Footer with Mustache](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/8a95ff2b4af4d9693455f1c3e49f1116344b1b72)

    You can check below the files where I mostly took part on:
  - [Recipe JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/Recipe.java)
  - [Script](backend/src/main/resources/static/js/script.js)
  - [Web Controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/WebController.java)
  - [Web Security Config](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/security/WebSecurityConfig.java)
  - [Mostly of the templates](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/templates)

- **Carlos Rodríguez Gómez**:Certainly in this phase my colleagues have done a greater workload during it, so my work has been less. Even so, they have been able to update me on all the details so that I do not lose the thread. Regarding the tasks that I have been in charge of are: some templates, data entry in the database and the creation of the diagrams for the documentation. In the next phase I will be more active and I will try to balance the work that I have not done during this phase. My most important commits are shown below::
  - [Menu DataBase](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/87a43cc34e7fbec99475ad37eac1e5aa2bb7bd35)
  - [Diets DataBase](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/87a43cc34e7fbec99475ad37eac1e5aa2bb7bd35)
  - [Creation of Class and DataBase Diagrams](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/c6709920dea7dfcc70cea286530f3604df1c1a26)
  - [Creation of Menu](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/6745a61304543aa63e4c267b3432d0b3604251e1)
  - [Creation of DropDown](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/ae5dee3aec17b27a1f6d399cf3d9cacd713fd6fa)

    Here are the links of the most used files:
  - [Database Initializer](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/service/DatabaseInit.java)
  - [Js button](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Food%26U_Templates/js/download.js)
  - [Image_README](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/Imagenes_README)
  - [Some templates](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/templates)

- **Carlos Alejandro Álvarez**: I developed the most part of the DB item creations and applications, I helped with the class in the model. I also helped my partners by giving them ideas for the project. Finally, the most problematic tasks I had to deal with was get the recipe maker to save the image in the DB and the creation of the PDF file.  You will find some of my more important commits that I have done:
  - [Dowload the pdf for the ingredients from the active menu](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/df577f4428fbf761504f5a67186e2fa31b63d742)
  - [User register to the DB and recipe maker](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/6cfd079d8ca7354c57d45303e18167f5db3fcce1)
  - [Menu maker, users can see all the menus and the content of the menu](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/7d24a6f05db42f628ecd4d3efc1e5efce492a7c2)
  - [User can save recipes and Admin can remove them](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/2ba1463bddcc9513be372d95213d232610034c40)
  - [Admin can make recipes with image](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/7f0d746db5da43a77fa7025220809397624a9fd7)
  
     And now I will add some links to explore some of the most edited files by me:
  - [Recipe JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/Recipe.java)
  - [Web Controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/WebController.java)
  - [User JAVA class](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/model/User.java)
  - [ExportPdfService](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/service/ExportPdfService.java)
  - [Mostly of the templates](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/templates)

  