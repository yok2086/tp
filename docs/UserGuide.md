# PantryPal User Guide

## Introduction

PantryPal is an all-in-one application designed to help students manage their meals. 
The application allows users to keep track of their ingredients, automatically generate shopping lists, 
plan their meals, and store recipes. 

## Quick Start

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `PantryPal` from [here](https://github.com/AY2425S2-CS2113-F11-3/tp/releases).
2. Copy the file to the folder you want to use as the home folder for PantryPal.
3. Open a command line terminal and navigate to the folder where you saved the file.
4. Run the command `java -jar PantryPal.jar` to start the application.

## Features 

### Shopping List Commands

### Recipe Commands

### Inventory Commands

### Meal Plan Commands

### General Commands

#### List all available commands: `help`
* **Format**: `help`
* **Description**: Displays a list of all available commands and their descriptions.

#### List all valid units: `unitList`
* **Format**: `unitList`
* **Description**: Displays a list of all valid units for ingredients.

#### Exit the program: `exit`
* **Format**: `exit`
* **Description**: Exits the program.



## FAQ

**Q**: How do I transfer my data to another computer?

**A**: To transfer your data to another computer, follow these steps:
1. Locate the `data.txt` file in the application's directory. The file should be located in a folder named data.
2. Copy the `data.txt` file to a portable storage device or use a file transfer service.
3. On the new computer, run the application once and let the program create a new data folder and a new `data.txt` file.
4. Replace the `data.txt` with the one you copied from the old computer. Make sure to keep the same file name and location.
4. Launch the application on the new computer, and your data should be loaded automatically.

**Q**: What should I do if I encounter an error while using the application?

**A**: If you encounter an error, try the following steps:
1. Ensure that you are using the correct version of Java (Java 17 or above)
2. Check the command format and make sure you are entering the correct parameters.
3. Refer to the `help` command to see the list of available commands and their formats.


## Command Summary

### General Commands
* **help**: List all available commands
    * **Format**: `help`
* **exit**: Exit the program
    * **Format**: `exit`
* **unitList**: List all valid units for ingredients
    * **Format**: `unitList`

### Inventory Commands
* **addNewIngredient**: Add a new ingredient to the inventory
    * **Format**: `addNewIngredient <name> <quantity> <unit>`
* **increaseQuantity**: Increase the quantity of an ingredient
    * **Format**: `increaseQuantity <name> <quantity>`
* **decreaseQuantity**: Decrease the quantity of an ingredient
    * **Format**: `decreaseQuantity <name> <quantity>`
* **setAlert**: Set an alert for an ingredient
    * **Format**: `setAlert <name> <threshold>`
* **viewStock**: Check the stock of all ingredients
    * **Format**: `viewStock`
* **viewLowStock**: View ingredients with low stock
    * **Format**: `viewLowStock`
* **deleteIngredient**: Delete an ingredient from the inventory
    * **Format**: `deleteIngredient <name>`

### Shopping List Commands
* **addShoppingItem**: Add an item to the shopping list
    * **Format**: `addShoppingItem <name> <quantity> <unit>`
* **generateShoppingList**: Generate a shopping list
    * **Format**: `generateShoppingList`
* **removeShoppingItem**: Remove an item from the shopping list
    * **Format**: `removeShoppingItem <name>`
* **viewShoppingList**: View the shopping list
    * **Format**: `viewShoppingList`

### Meal Plan Commands
* **viewPlan**: View meal plan presets
    * **Format**: `viewPlan`
* **addPlan**: Add a new meal plan
    * **Format**: `addPlan <planName>`
* **removePlan**: Remove a meal plan
    * **Format**: `removePlan <index>`
* **addRecipeToPlan**: Add a recipe to a meal plan
    * **Format**: `addRecipeToPlan <recipeIndex> <planIndex> <mealIndex>`
* **removeRecipeFromPlan**: Remove a recipe from a meal plan
    * **Format**: `removeRecipeFromPlan <recipeIndex> <planIndex>`

### Recipe Commands
* **addRecipe**: Add a new recipe
    * **Format**: `addRecipe`
* **viewRecipe**: View a recipe
    * **Format**: `viewRecipe <name>`
* **removeRecipe**: Remove a recipe
    * **Format**: `removeRecipe <name>`
* **listRecipe**: List all recipes
    * **Format**: `listRecipe`
