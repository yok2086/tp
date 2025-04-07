# Bui Gia Nhat Minh's Project Portfolio Page

## Project: PantryPal

PantryPal is an all-in-one application designed to help students manage their meals. The application allows users to keep track of their ingredients, automatically generate shopping lists, plan their meals, and store recipes. 

Given below are my contributions to the project.

## Code Contributed
- **Recipe Management Feature**:  
  I contributed to all the codes related to Recipe Manangement feature and all its JUnit tests. This includes:
  - The ``Instruction`` class represents all the instructions in the Recipe. Each instruction instance encapsulates essential attributes such as step number and instruction content. This modular desgin ensures that instructions and instruction list can be easily created, modified, and retrieved when needed.
  - The ``Recipe`` class represents individual recipes in the system. Each recipe instance encapsulates essential attributes such as name, instructions, and ingredients. This modular design ensures that recipes can be easily created, modified, and retrieved when needed.
  - The ``RecipeManagemer`` class is responsible for managing a collection of recipes, tracking their name, ingredients and instructions. This feature enables users to add recipes, update recipe's ingredients and instructions, and remove recipes.
  - Keep the code coherence and work smoothly with other classes in the project.
  - The `RecipeManagerTest` JUnit test, which works as the test for all the method related to recipe management.
  - Manage the parser and command class with other team members to ensure all the functions are represented, all the inputs and error handling are correctly integrated with each other. 
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
- Created and managed GitHub issues
- Collaborated with other team members to ensure the coherence of all the classes in the project
- Collaborated with other team members to ensure new feautures does not cause conflict
- Developed JUnit tests for core functionalities.
- Contributed to feature integration by adjusting logic in the command line interface.
- Tested other team member's code
- Find and fix the bugs and issue in the team's code and documentations
