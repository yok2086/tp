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
PantryPal's Shopping List feature helps you manage your grocery needs efficiently. You can manually add items, automatically generate a list based on low-stock alerts, edit existing items, and mark items as purchased. This ensures you never miss out on essential ingredients.

#### **addShoppingItem**
Command:
```
addShoppingItem <ingredient_name> <quantity> <unit>
```
Example:
```
addShoppingItem sugar 500 g
```
Description:
Manually adds an item to your shopping list. Use this command when you want to include items not
currently flagged by low-stock alerts or if you want to quickly update your list.

#### **generateShoppingList**
Command:
```
generateShoppingList
```
Example:
```
generateShoppingList
```
Description:
Automatically creates a shopping list by comparing your current ingredient stock with preset low-stock alerts from the 
inventory. It calculates the required quantity for each ingredient that is below its threshold and updates your shopping
list accordingly. Note that running this command clears the previous shopping list before generating an updated version.

#### **removeShoppingItem**
Command:
```
removeShoppingItem <ingredient_name>
```
Example:
```
removeShoppingItem sugar
```
Description:
Removes a specified item from your shopping list. Use this command if you decide you no longer need to purchase that ingredient.

#### **viewShoppingList**
Command:
```
viewShoppingList
```
Example:
```
viewShoppingList
```
Description:
Displays all items in your shopping list, including index numbers next to each item.
This makes it easy to reference items when you need to edit or mark them as purchased.

#### **editShoppingItem**
Command:
```
editShoppingItem <index> <new_ingredient_name> <new_quantity> <new_unit>
```
Example:
```
editShoppingItem 1 milk 250 ml
```
Description:
Allows you to edit an existing shopping list item by specifying its index (as shown in `viewShoppingList`).
Use this command to update the ingredient name, quantity, or unit if your shopping needs change.

#### **markItemAsPurchased**
Command:
```
markItemAsPurchased <ingredient_name>
```
Example:
```
markItemAsPurchased sugar
```
Description:
Marks an item in your shopping list as purchased. The item will be visually updated (e.g., with a “(Purchased)” tag)
so you can easily track which items have already been bought.

### Recipe Commands
#### Add New Recipe
Command:
Note that ```{enter}``` means clicking the enter button. Please enter your input line by line.
```
addRecipe {enter}
<recipe_name> (no whitespace allowed, use _ instead) {enter}
<ingredient_name/exit> (no whitespace allowed, use _ instead) {enter}
<ingredient_quantity> {enter}
<unit_of_measurement> {enter}
<instructions/exit> {enter}
```
Example:
```
addRecipe {enter}
fried_egg {enter}
egg {enter}
50 {enter}
g {enter}
exit {enter}
cook egg {enter}
serve egg {enter}
exit {enter}
```

Description:
Add a recipe to the recipe repository. You will be prompted to add the recipe name, the ingredients and their information, and the instructions.

#### View A Specific Recipe
Command:
```
viewRecipe <recipe_name>
```
Example:
```
viewRecipe fried_egg
```

Description:
View a specific recipe's full information (ingredients and instructions).

#### Remove A Recipe
Command:
```
removeRecipe <recipe_name>
```
Example:
```
removeRecipe fried_egg
```

Description:
Remove the specified recipe from the recipe repository.

#### List All Recipes
Command:
```
viewRecipeList
```

Description:
View the name of all the recipes available in the repository.


### Inventory Commands
#### Ingredient Inventory System

#### Add New Ingredient
Command:
```
addNewIngredient <ingredient_name> <quantity> <unit> <category>
```
Example:
```
addNewIngredient milk 200 ml DAIRY
```
Description: Adds a new ingredient with a specified quantity and unit to the inventory.

#### Increase Specific Quantity to Existing Ingredient
Command:
```
increaseQuantity <ingredient_name> <quantity>
```
Example:
```
increaseQuantity milk 200
```
Description: Increases the quantity of an existing ingredient by the specified amount.

#### Decrease Specific Quantity to Existing Ingredient
Command:
```
decreaseQuantity <ingredient_name> <quantity> 
```
Example:
```
decreaseQuantity milk 200 
```
Description: Decreases the quantity of an existing ingredient by the specified amount.

#### Set Low Stock Ingredient Alert
Command:
```
setAlert <ingredient_name> <threshold_quantity>
```
Example:
```
setAlert flour 10 
```
Description: Sets a threshold quantity to trigger a low-stock alert for an ingredient.

#### Check Ingredient Stock
Command:
```
viewStock
```
Description: Displays all current ingredients and their quantities in the inventory.

#### View Low Stock Ingredients
Command:
```
viewLowStock
```
Description: Displays all ingredients that are below their specified low-stock thresholds.

#### Delete Ingredient Entirely
Command:
```
deleteIngredient <ingredient_name>
```
Example:
```
deleteIngredient salt
```
Description: Removes an ingredient from the inventory.

#### Convert Ingredient Unit
Command:
```
convertIngredient <name> <targetUnit>
```
Example:
```
convertIngredient Sugar kg
```
Description: Converts the quantity of the specified ingredient to the target unit.

#### View Ingredients by Category
Command:
```
viewIngredientsByCategory <category>
```
Example:
```
viewIngredientsByCategory CONDIMENTS
```
Description: Displays all ingredients that belong to the specified category.



### Meal Plan Commands

`Coming Soon`




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
* **editShoppingItem**: Edit an existing item by its index
  * **Format**: `editShoppingItem <index> <new_name> <new_quantity> <new_unit>`
* **markItemAsPurchased**: Mark an item as purchased
  * **Format**: `markItemAsPurchased <name>`

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
