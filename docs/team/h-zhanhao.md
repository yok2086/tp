# Zhan Hao's Project Portfolio Page

## Project: PantryPal

PantryPal is an all-in-one application designed to help students manage their meals.
The application allows users to keep track of their ingredients, automatically generate shopping lists,
plan their meals, and store recipes. 

#### tP Code Dashboard link: https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=h-zhanhao&breakdown=true

## Given below are my contributions to the project:
## **Command Classes**:          
  - Added command classes for most commands in the application. Which includes most Meal Plan Command, Recipe Command, 
    Shopping List Command, and Ingredient Inventory Command.
  - Implemented the execute method for each command class to perform the required action.
  - Separated the command classes into different packages for better organization and maintainability.
  - Separated the command classes into different subclasses for a more dynamic and flexible design of the help command
    
### Implemented the Help Command Class
  - Created the HelpCommand class to provide users with information about the available commands.
    
## **Parser Class**: 
   - Implemented the Parser class to parse user input and instantiate the appropriate command class.
   - Added error handling to manage invalid number of arguments and invalid command types.

## **User Interface Class**
  - Designed the user interface to be user-friendly and intuitive.
  - Implemented the UI class to handle the application's display 
    
## **Storage Class**
  - Implemented the Storage class to handle data storage and retrieval.
  - Added methods to save and load data from files.
  - Implemented anti corruption measures to ensure data integrity.

## **Main Class**
  - Created the Main class to serve as the entry point for the application.
  - Implemented the main method to initialize the application and handle user input.
  - Implemented the run method to start the application and handle user commands.
  - Implemented the exit logic to handle application termination.

## **MealPlanManager Class**
  - Aided in the implementation of the MealPlanManager class to manage meal plans.
  - Implemented methods to View Plan, View Plan List, and Remove Plans

## **JUnit Testing:**
  - Implemented test cases for the Parser class to verify the parsing logic.

## **Text-Ui-Test**
  - Created the TextUiTest class to test the user interface.
  - Added test cases for various user inputs and outputs.

## **Demo Video**
- Created a demo video showcasing the features and functionality of the application.

## **Documentation**:
- User Guide
    - Completed the FAQ section answering "How do I transfer my data to another computer?" and "What should I do if I encounter an error while using the application?"
    - Included the command summary table for all commands in the User Guide.
    - Added Quick Start Guide and Introduction to the application.
    - Added descriptions and format for General Commands.
    - Added descriptions and format for Meal Plan Commands.
- Developer Guide
    - Wrote a overview of the overall design and implementation of the application.
    - Made the class diagram for the overall architecture of the application.
    - Completed the implementation of the Parser, Storage and Command Class features
    - Created sequence diagrams for the Parser and Storage features.
    - Created class diagram for the Command Class feature.
    - Added detailed descriptions on the design and implementation of the Parser, Storage and Command Class features.

## **Project Management**:
- Created the project repository 
- Set up the project structure and initial files.
- Managed releases for `v.1.0`, `v2.0`, and `v2.1` of the application.
- Facilitated team collaboration and communication.
- Aided teammate in completing features.
  - Fixed bugs surfaced from issues for the Meal Plan Feature.
  - Completed missing commands for the Meal Plan Command. For example, View Plan, View Plan List, and Remove Plans commands.
- Facilitated team meetings and discussions.

    



