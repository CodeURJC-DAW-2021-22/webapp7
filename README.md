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

| Entity | Description |
| ------------- | ------------- |
| Users | People that will be using our app you will find four kinds: **logged users** allowed to upload and save content, **unlogged users** which only will be allowed to explore recipes, and **admin users** which are allowed to upload, save and delete content. |
| Menus | Every user could save as many menus as wanted. Only one will be active, but you can save everyone you want according to your objetives: gain or lose weight|
| Recipes | Every meal from the menu will be composed by its recipe. Every weekly menu will have 7 lunches and 7 dinners, so it will have 14 recipes. Also, users can upload their own recipes to the web app where it will be stored, and available to include in other menus.|
| Shopping List | Every logged user will have an active menu. This mean that a shopping list can be generated from this. This also will be a component of the information stored from every user.|

 ## **Extra technology**

Using Food & U will not requiere a full-time connection to check your menu or recipes. You will find the easiest way to download your menu and recipes: just pressing a button! It is as simple as that. Additionally, it may be developed the way to generate a menu which fits better with your idea of gain or lose weight. We know is not easy to start doing this, and sometimes you need help because the lack of ideas. Food & U provides help with this kind of issues.

## **Web Navigation**

You can find in the following image a very first look about how we, the development team, think that you can travel around the web application from a point of view of a logged user:
![WebNavigation](webapp7\Imagenes README)


 ## **Algorithms**

Food & U will include an algorithm that recommend you recipes that is nutritionally similar to the ones you have chosed previously. We want to implement this to add variety to our users' menus. It will be based, of course, in the nutritional score of the previous meals used in the past week. In addition, we will be proud if the final version of the project is capable of making automatically a new diet based on the desires and goals of our users, but this would be included if time allows us to do so.

## **Graphics**

Food & U will provide to its users the possibility of compare their own menus with one which we consider appropiate and balanced. This will be shown as graphic as possible, trying to be clear and direct with the information. This information will be private for every user, because we can consider it as something personal.

