# Food & U

## **Introduction**

This is a project where we are going to develop a new web application about the relationship beetween the food and our users. The main idea is that you will find recipes uploaded by other users, and you would save them to build your semanal food plan. In addition, you will have the opportunity to generate your own shopping list automatically, using the different elements that your daily food has. Let's take a deeper look about what are we planning to develop:


You will fin here a [video](https://www.youtube.com/watch?v=hfaoKZv3zUs) where we explain how our application works in the frontend part of the project.

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


![WebNavigation](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Navigation%20diagram.png)

Down Below you will find some of the HTML pages that you may use in the final version of this project:

**Index**

![Index](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/IndexLogged.png)

**About Us**

![AboutUs](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/AboutUs.PNG)

**User Main Page**

![User Main Page](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/User.PNG)

**Active Menu**

![Menu](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/YourMenuAdmin.png)

**User Diets**

![Stored Diets](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/StoredDiets.png)

**Sign Up & Log In**

![SignUp](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/SingUp.PNG)

![LogIn](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/LogIn.PNG)

**Recipes Browser**

![Recipes Browser](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Recipes.png)

**Recipe Page** ( The red button will depends on the type of the user, save for a registrated user, delete for admin and no button for not registered user)

![Recipe](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Recipe1.png)

**Your Recipes** 

![Your Recipes](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/YourRecipes%20user.png)

**Admin Profile**

![Admin Profile](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/AdminProfile.png)

**Menu All**

![Menu All](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/MenuFilter.png)

**Menu Maker**

![Menu Maker](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/MenuMaker.png)

## **Diagrams**

**Class Diagram**

![Class Diagram](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Class%20Diagram.png)

**DataBase Diagram**

![DataBase Diagram](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/DataBase%20Diagram.png)

**Fronted Class Diagram**

![Fronted Class Diagram](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/Fronted%20Class%20Diagram.png)

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

## **How to dockerize Food & U**

First of all, you must now about docker, [here](https://www.docker.com/blog/tag/docker-desktop-2/) you could find more information about what this technology provides you.

Once you have understood this technology, let's start talking about the pre-requirements to dockerize out app. We will show you how to do this in Windows:
 - [Java 17 installed](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html)
 - [Maven 3.8.5 downloaded](https://maven.apache.org/download.cgi)
 - [Set maven as a System Variable ](https://stackoverflow.com/questions/45119595/how-to-add-maven-to-the-path-variable)
 - [Set JAVA_HOME as a System Variable correctly with jdk 17.0.2](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html)
 - Set Path References correctly: Once you have setted JAVA_HOME and Maven as System Enviroment variables, you should add both paths to Path variable in the same screen by double-clicking it.
 - [Sign Up your Docker Hub account](https://hub.docker.com/signup)
 - [Download and install Docker Desktop to understand what are you doing](https://www.docker.com/products/docker-desktop/)
 - Download WSL 2 components to run proper command in Windows Powershell: Once you have installed Docker Desktop, by oppening it you will find the option to install these components automatically.
 - [OPTIONAL: Download Notepad++](https://notepad-plus-plus.org/downloads/)

Once everything is correctly done, let's start about generating the needed Docker images.
  - **1) PostgreSQL Image:** You must download the official PostgreSQL image. You can find how to it easily [here](https://hub.docker.com/_/postgres) or just running the command: " docker run --name some-postgres -e POSTGRES_PASSWORD=\<Your password> -d postgres ".
    You can find if you have correctly generated the image locally by using the command " docker ls " and you will see a image running with the name postgres. If you want, you can stop it to improve your PC performance.
  - **2) WAR generation and relocation:** Now you must have downloaded the project, and unzip it. We will follow the steps as if you have unzipped it in your Desktop, but if have decided unzip it into another folder, you only need to find the folder in the Windows Powershell. If you have the folder containing the code in the Desktop, write the next command in the Powershell: " cd Desktop/webapp7/webapp7/backend ". Now by using the command " ls ", you must see the pom.xml file. That's where you must be to run " mvn package -o " to generate the .war file. This file also can be used to execute the application locally. The process to generate the file must dure a bit less than one minute.

    Now that you have generated the webapp7urjc.war, you must find it at webapp7/webapp7/backend/target/webapp7urjc.war. You must move it to webapp7/webapp7/docker, in the same folder where docker-compose.yml and Dockerfile are located. Now there are many ways to generate the docker image of the application. But we are going to show you how to do it with IntelliJ Idea. First, load the project. Once it has been done, go to docker/DockerFile. You could see that you can Execute in the First line of the file where a button is present. But before doing it, you should configure your IDE to send the image to the correct Docker account. In IntelliJ IDEA, go to File>Setting>Build,Execution,Deployment>Docker and delete the existing connection config to generate a new one. The only thing you must to change is the Docker Registry, don't touch the address, but add your Docker Hub username and password in the fields behind it. Now you are ready to generate the docker image of the project! Just comeback to Dockerfile to execute it, and a image and a container will be created automatically. Once done, we recommend you to delete the new container, because IntelliJ Idea is going to try to execute it constantly, and is going to fail because is not in the same container with the PostgreSQL image generated previously.

  - **3) Docker-Compose Execution:** Now that both images are created you must modify the docker-compose.yml to connect them in the same container. To do it, you can use Notepad++ or IntelliJ. Opening docker-compose.yml, you must write the credentials used to open your database in the "db" service known as POSTGRES_USER (postgres superuser name), POSTGRES_PASSWORD, POSTGRES_DB (name of the Database you want to use to deploy the app). While installing PostgreSQL Server you must specify the ports that you want it to listen to by default. Change it in the second field of "ports" in db service if needed. Now you must specify again the password of the superuser in the field SPRING_DATASOURCE_PASSWORD. The last thing to do is to change the "image" field in web service. It contains the id of the web image generated in the previous step. So, go to Docker Desktop, and in Images you will find it, just copy and paste it in the docker-compose field.

    Now, everything is ready to execute. So go to Windows Powershell, and again, enter in Docker folder. if you have the application in the desktop, use the command "cd Desktop/webapp7/webapp7/docker" and now, using the command "ls" you must see that the folder contains docker-compose.yml. Now the only thing to do is run the command "docker-compose up" and wait till the process its over. If you have followed the steps correctly, you must see the container with two images inside in Docker Desktop. And in your favourite browser, typing [https://localhost:8443](https://localhost:8443) you will find Food & U app.

## **Creating Food & U docker image using the script provided**
Now we are going to provide you information about how to create a docker image with the script included in the project step by step:

**1) Download the project and unzip it in your Desktop:** Only do how it sounds. We are going to show you how to do this unzipping it in the Desktop, but if you prefer to do it in another folder, just check the folder in the " cd " command shown in the next step.

**2) Execute the script:** Once the project is unzipped, open it, and go to webapp7/docker. Then you will find a file named as "create_image.sh", just double click it and the image of the project will be created.

**3) Make sure it is created:** To understand that the image is correctly created, open windows powershell and run the next command: "docker images". You will find one named as < none >. That is the image of the webapp, and now you can relate it with your PostgreSQL Database.

## **How to deploy Food & U in Heroku**
**Pre-requeriments**:
  - [Have Docker Desktop installed](https://docs.docker.com/desktop/windows/install/)
  - [Sign up you Heroku account](https://signup.heroku.com)
  - [Download Heroku Client](https://devcenter.heroku.com/articles/heroku-cli)
  - [Add Heroku/bin folder to the path in Path variable](https://stackoverflow.com/questions/44301515/heroku-cli-installation-error-path-not-updated-original-length-1585-1024)
  - Try to run "heroku" in Windows Powershell to understand that you have succesfully installed heroku


**1) Create the docker image of the project:** Open the project with IntelliJ Idea, and once it is indexed, open docker file folder, and Dockerfile file. Execute it to create the image of the web part properly.

**2) Log In Heroku:** run the command "heroku login" in the Windows Powershell and follow the steps to log in with your account.

**3) Create a Heroku app:** run the command "heroku create < app_name >" to create a Heroku app. The name of your application must be unique between every Heroku app, so choose well.

**4) Add the docker tag to your Image:** you must run a command to add a tag to your image. First of all, copy the id of the image. You can find it in Docker Desktop, in the field of Images, or running the command "docker images". Once it is copied, you must run the next command in the Powershell: " docker tag < ID > registry.heroku.com/<app_name>/web". In Docker Desktop or running the command "docker images" you will find that the name of the image created previously has changed. This is the symtomph to understand you are doing it well.

**5) Upload the docker image in your application:** You must run two command in this step: " heroku container:login
" and " docker push registry.heroku.com/<heroku_app_name>/web ". These two command will push the web app to heroku.

**6) Config the PostgreSQL database in Heroku:** Run the command " heroku addons:create heroku-postgresql --app <heroku_app_name> ". This will allow your webapp to use PostgreSQL database. The project is already configured, so just doing this should work correctly.

**7) Config the SSL certifcations to false:** Run the next command: heroku config:set SERVER_SSL_ENABLED=false --app < heroku_app_name >. This command allows you to run the webapp without an official certification.

**8) Check everything is correct before deploying the app:** Access to Heroku in your browser, and log in with your account. In the dashboard tab you will find the heroku app you have created, so click on it to navigate to the manage page of it. Then, in the resources tab, you will must find the next configuration:

![Heroku Config](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/heroku_well_config.png)

**9) Deploy the app:** Run the next command: " heroku container:release web --app < heroku_app_name > " and the app should be deployed. If you want to see the logs while it is running in the PowerShell (extremely recommended but it is not necessary) you must run the following command: "  heroku logs --tail --app < heroku_app_name > "

**10) Access to the page deployed:** Back to the Heroku dashboard, and select the app that has been deployed. Then, in the right part of the page, you should see a button named "Open App". So clicking on it you will open the app:

![Heroku Open App](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Imagenes_README/openapp_button.png)

Right now you should understand the next problem: Heroku close the app if finds an inactivity period of 30 minutes, so if you find this situation, back to the step 9. But at this point, everything is done! The only thing left is to enjoy Food & U app.

## **API REST Documentation**

You can check the API REST documentation in the next links: 
  - [HTML format](https://raw.githack.com/CodeURJC-DAW-2021-22/webapp7/main/backend/api-docs/api-docs.html)

  - [YAML format](https://raw.githack.com/CodeURJC-DAW-2021-22/webapp7/main/backend/api-docs/api-docs.yaml)

## **How to transfer Food & U to a SPA**
**Pre-requeriments**:
 - [Install Angular CLI](https://angular.io/cli). In this case we will have to open the terminal and type the following code on the keyboard, this will depend on the operating system you have, you can check it in the previous link: npm install -g @angular/cli
 - [Download node](https://nodejs.org/es/download/)
 - We highly recommend using two IDE or framework so that we can run the application locally in one of them, while in the other we will take care of the frontend. As for example [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/#section=mac) for local and [Visual Studio Code](https://code.visualstudio.com/download) for fronted.

After fulfilling the following requirements, we will dedicate ourselves to installing plugins in our IDE or framework. For the use of Visaul Studio Code, for example, it will be necessary to install the [Language Service](https://marketplace.visualstudio.com/items?itemName=Angular.ng-template) and we also recommend other extensions such as this [Pack](https://marketplace.visualstudio.com/items?itemName=loiane.angular-extension-pack). Once we have configured our IDE or framework we can start to mount the fronted.

The next step will be to do npm install so that the node modules folder is installed, which has the necessary dependencies for our SPA application to work correctly. We will have to do this command in our frontend folder, which we will have to create before.

Next, we will create a folder called proxy.conf.json, which will allow access to our backend from the frontend to get the data. We must configure it properly with the IP address and the port that is configured in the backend.

Finally, as we previously recommended, we will run the application in the backend with one of our IDE or framework, and then we will launch the ng serve command from the terminal of the other IDE or framework. With this we would have our application running in the fronted and backend at the same time.

## **Developing Part by every member**


| Name    | Mail | Github User    |
|---------|--------|----------------|
| Rodrigo Marqués Buil|r.marques.2018@alumnos.urjc.es| Larrivey |
| Hugo Coto González|h.coto.2018@alumnos.urjc.es| hugocg6 |
| Carlos Rodríguez Gómez|c.roriguezgo.2018@alumnos.urjc.es| carlitosrogo |
| Carlos Alejandro Álvarez|c.alejandro.2019@alumnos.urjc.es| CalejandroURJC |

**Phase 2:**

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
  - [DataBaseInit](backend/src/main/java/com/example/demo/service/DatabaseInit.java)
  - [Most of the templates](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/templates)

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


  **Phase 3:**

- **Rodrigo Marqués Buil**: During this phase, I worked in the docker and Heroku engine until it worked correctly. It was hard to make everything run, because it is a technology that I never used before, but totally enjoyable. Also, I worked in README to help users to understand how to deploy the app in the way they prefer. Now you will find my principal five commits:
  - [Database Config for Heroku](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/4c62167e12c66d376f7c18a02a9bad52f7804d13)
  - [First preparation for Dockerize the app](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/6decabc269c11b07812e6a7cfaed07ae7dc4e080)
  - [Dockerfile prepared, pom updated to generate correctly the war file](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/fa30ca53a01946fa2a4436950df226a91246fc6e)
  - [Dockerfile ready to use and docker-compose advanced](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/7cca94bf5ef3180691c3065cdd109b5cd538b789)
  - [Everything ready to dockerize](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/2c37eade4e8c2d8db679a90c080e0bb1a2125bb7)
  
  And now you will find the 5 file that I consider I worked in the most part of my time in this phase:
  - [Dockerfile](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/Dockerfile)
  - [Docker-compose](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/docker-compose.yml)
  - [pom.xml](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/pom.xml)
  - [README.md](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/README.md)
  - [Database Config](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/DatabaseConfig.java)


- **Hugo Coto González**: In this phase of the implementation, I mostly spent all my efforts with Rodrigo developing the docker and Heroku parts and making it work. It was new and something unseen for us, but the teamwork there made it successful. Also, I took care of the API REST documentation and the Postman requests. Down below you can check my principal commits:
  - [Api Docs](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/616a175e9c37afb00f988a2a261fda497a088fc7)
  - [Postman Collections](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/e31e43d76771193a8ffa73b03874cd80d00a089d)
  - [Docker image creator script](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/1e63da2df8d799b1c3aedcd2c2c5843c06d04465)
  - [First Heroku deploy](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/d65ea254cd789b5f5a9f7a7a8d808d844ef41b77)
  - [First docker image creation](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/2558c802cab273a80ab4a3fd8385738b02edd738)
  
  The links where I consider I worked the most on:
  - [Application properties](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/application.properties)
  - [create_image.sh](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/create_image.sh)
  - [pom.xml](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/pom.xml)
  - [Postman Collections](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/Postman)
  - [Dockerfile](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/Dockerfile)
  

- **Carlos Alejandro Álvarez**: In this phase I worked in the Api Rest and in the functions we missed in the last phase. I also helped my team in all the things I could. In this phase one important aspect was the communication, and I think we really accomplished in that aspect. You will find some of my more important commits that I have done:
    - [Part of Api Rest](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/e91d79f5a3a11a23e966238c5b67aebf18d9ab9f)
    - [Rest controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/2dbd87c85e1dc28209b083a9d9be982a656c40be)
    - [Rest Controller Init, / y bdd](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/bbcf8ff2cd2568b9c2c0e21cb44af4a8405f7340)
    - [RestController JWT](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/88e1832b37d58197d9c5157f96c8cbb2ba1e9562)
    - [UpdateRecipe and rest](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/3b83117e3684d120028d675507a842be1130a677)
    
  The 5 file that I consider I worked in the most part of my time in this phase:
    - [RestController](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/RestController.java)
    - [Web Controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/WebController.java)
    - [RecipeUpdater](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/templates/RecipeUpdater.html)
    - [recipeLoaded](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/resources/templates/recipeLoaded.html)
    - [MenuService](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/service/MenuService.java)

- **Carlos Rodríguez Gómez**: Throughout this phase I have been working on the development of the API REST controllers, I have also updated the class diagrams, below I leave the 5 most important commits:

    - [Some methods of Api Rest](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/0f7a73e87b9d3f5116df439c9f1bddbecb012ca4)
    - [More methods Api Rest](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/4b029f4dc07b5789de7a5c659d3ad494b9803004)
    - [User method on RestController](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/3f89564e641303c92e93dab04ddd5afa8925e780)
    - [Update of Class Diagram](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/52be3e6b415f096c67029fc984dd04266327f9b0)
    - [First thoughts of Api Rest](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/8c20285b950e7cb47f96b957975dffcaf2822d93)

  Now attached the files I mostly work in:

    - [RestController](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/RestController.java)
    - [Web Controller](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/backend/src/main/java/com/example/demo/controller/WebController.java)
    - [Image_README](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/Imagenes_README)

  **Phase 4:**


- **Rodrigo Marqués Buil**: During this phase I tried to develop each part as I could of frontend, because the volumen of components must be sorted by every developer. Also, I co-worked redesigning the api rest to fit better with the frontend, and finished dockerizing the ultimate version of itself. Now you can see the most important commits I did during this part of the develop:

  - [New version of Docker File and Docker Compose](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/2692c9c96f95499f7305bf109e5d617fe029f169)
  - [Data persistence in local after refreshing](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/71bcee24e4138c2ba97e81567ef403771de3c7bb)
  - [Change to relative paths that works in Heroku](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/9e2213a468cdc5db46cd62202a87ed77e8280a35)
  - [Control of access to determinated pages depending of the kind of user trying to do so](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/9d0a55bfc1195b53345ab01acddcdb3e52461ad2)
  - [Active Menu page finished](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/43802546ee41e5d762546837082acb5497903366)

  And now you can check the five most representativas files where I worked:
  - [Login Service](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/services/Login/login.service.ts)
  - [User Service](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/services/Users/users.service.ts)
  - [Home Component](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/components/Home/home/home.component.ts)
  - [Dockerfile](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/Dockerfile)
  - [Header](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/components/Header/header/header.component.html)


- **Hugo Coto González**: During this phase my work has consisted in the structuration of the frontend and adding and modifying all the parameters required for itS correct performance. Also I develop some methods and components using Angular. Most of my work has been dockerize the whole app and automate that proccess by writhing a bash script that does it without any previous installation beyond node.
Down below you can check my most important commits during this last phase:
  - [Image creation script](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/e6723c52fec1a915401d4eb2209edf55570b1bad)
  - [Logout](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/ac995dcf96f217347f4e9186ba8f31203e32d5ae)
  - [Recipes page](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/d3abd0cedc0c7d4f29ada798c27d960a8abd950e)
  - [Load more recipes](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/680cddbd5c2da7c18a8044fdf570f1b2e66cfed5)
  - [Services and classes](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/9fb6e657e289c9e8c810a2f1f243ac98233bf306)
  
  This are the files I mostly worked on:
  - [Script](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/create_image_phase4.sh)
  - [Dockerfile](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/docker/Dockerfile)
  - [Recipe Service](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/services/Recipes/recipes.service.ts)
  - [RecipeAll Component](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/components/Recipe/recipeall/recipeall.component.ts)
  - [User service](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/services/Users/users.service.ts)


- **Carlos Alejandro Álvarez**: During this phase I tried to develop as much as possible of the components I worked in. I focus my time in algorithms and connecting components with the backend api rest. Now you could find my most important five commits I made:
  
  - [Chart created](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/c1ec63c4c0ce14bb4b8dba32fbd61c0acd6db62d)
  - [Receipt to download](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/efbec72d8af89dd82eac40d4c81745f678992a1b)
  - [Recipe Updater Page](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/6af6815b9fc0458b9e8ed8dfc9af4057094d65c2)
  - [Saving recipes](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/5e800ac1c44abda483bfcb938cac9e002e8c05fa)
  - [Paginated searchs](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/fe3d254929b2109ddfab7716b0fea420bbacb8f9)
  
  And now, a list with the five files where I most worked in:
  - [Specific recipe](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/frontend/src/app/components/Recipe/recipespecific)
  - [Menu Active](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/components/Menu/menuactive/menuactive.component.ts)
  - [Diet Maker](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/components/Diet/dietmaker/dietmaker/dietmaker.component.ts)
  - [Recipe Service](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/services/Recipes/recipes.service.ts)
  - [Menu Service](https://github.com/CodeURJC-DAW-2021-22/webapp7/blob/main/frontend/src/app/services/Menu/menu.service.ts)


- **Carlos Rodríguez Gómez**: During this phase I have been working mainly on the creation and implementation of fronted components. My biggest contributions are in the header, login and image uploads. In addition, I have helped with the creation of services and other small contributions to the rest of the components. Then I leave my 5 most important commits:
    - [Register created](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/faca37734dad01f0b1df7e61ffe2b5162acb28bd)
    - [Update Image created](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/02cb55084b23df8b845fe7b15b99c0e41fc7ff91)
    - [Login contributions and Login service](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/15c7d3c58a7ab8b8424392a0b9815ab5ee2b1df5)
    - [Header and some Components](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/9f4915be3676029254849d87361ee421b23d2dbb)
    - [Fronted CLass Diagram](https://github.com/CodeURJC-DAW-2021-22/webapp7/commit/c20e2d1fc5db5b34fa731d085efe9f4388942fa3)
    
  The 5 file that I consider I worked in the most part of my time in this phase:
    - [Login Component](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/frontend/src/app/components/Login/login)
    - [Service in general](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/frontend/src/app/services)
    - [RecipeUpdater Component](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/frontend/src/app/components/Recipe/recipeupdater)
    - [Header Component](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/frontend/src/app/components/Header/header)
    - [RecipeMaker Component](https://github.com/CodeURJC-DAW-2021-22/webapp7/tree/main/frontend/src/app/components/Recipe/recipemaker)

  
