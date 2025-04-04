# Bui Gia Nhat Minh's Project Portfolio Page

## Project: PantryPal

PantryPal is an all-in-one application designed to help students manage their meals. The application allows users to keep track of their ingredients, automatically generate shopping lists, plan their meals, and store recipes. 

Given below are my contributions to the project.

## Code Contributed
- **Recipe Management Feature**:  
  I contributed to all the codes related to Recipe Manangement feature and all its JUnit tests. This includes:
  - The `Instruction` class, which represents a step in the recipe's instruction. This class ensures modularity for the recipe class, and make the recipe overall easier to edit.
  - The `Recipe` class, which represents a single recipe. This class holds all the information (name, instructions, ingredients). These informations are easily added, edited, and removed through this class. The attributes are also used by other class.
  - The `RecipeManager` class, which works as the collection of Recipes. This class manages all the activities related to Recipe Management such as add, remove, view, search, edit recipes and its information.
  - Keep the code coherence and work smoothly with other classes in the project.
  - The `RecipeManagerTest` JUnit test, which works as the test for all the method related to recipe management.
- [Link to my code on tP Code Dashboard](<>)

## Enhancements Implemented

### Implementation of Instructions and Integration of Ingredient Class
- **What it does**:  
  Implements a modular `Instructions` class and integrate `Ingredient` class from `Inventory package` instead of using String more a more robust `Recipe` class.
- **Why it is implemented that way**:  
  To ensure the `Recipe`'s content is easily editable by swapping, adding, removing the corresponding classes. This design also follows the Single Responsibility Principle, sharing the responsibility to lower-level class.
  
### Error Handling inside the Class
- **What it does**:  
  The `RecipeManagement` class integrates error checking (when removing from empty list, getting item from empty list, etc) by using `assert` calls. 
- **Why it is implemented that way**:  
  This ensures that the data format is reliable, reducing unpredicted behavior that might happen from the application

## **Documentation**:
### User Guide:
  - Added documentation for recipe management features:
    - Adding recipes to recipe list
    - Removing recipes from recipe list
    - Viewing recipe content
    - Showing the list of recipes

### Developer Guide:
- Completed the implementation of instruction, recipe, and recipe management features
- Created sequence diagrams for the instruction, recipe, and recipe management features.
- Added detailed descriptions on the design and implementation of the instruction, recipe, and recipe management features.

## **Project Management**:
- hi
