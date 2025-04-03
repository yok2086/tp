# Cai Jiali's Project Portfolio Page

## Project: PantryPal

PantryPal is an all-in-one application designed to help students manage their meals.
The application allows users to keep track of their ingredients, automatically generate shopping lists,
plan their meals, and store recipes. 

Given below are my contributions to the project.

## Code Contributed
- **Shopping List Feature**:  
  I contributed all code related to the shopping list functionality. This includes:
  - The `ShoppingList` class, which manages the collection of shopping list items and supports operations such as add, remove, update, edit (by index), and mark as purchased.
  - The `ShoppingListItem` class, which represents a single shopping list entry. This class ensures proper formatting (e.g., whole numbers without decimal places) and supports a purchased flag.
  - The `ShoppingListGenerator` class, which auto-generates the shopping list by comparing low-stock alerts from the Ingredient Inventory with current stock levels.
- [Link to my code on tP Code Dashboard](<https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=kemilii&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other>)

## Enhancements Implemented

### Shopping List Item Enhancements
- **What it does**:  
  Implements a robust `ShoppingListItem` class that encapsulates an ingredient's name, quantity, unit, and a flag indicating if the item is purchased.
- **Why it is implemented that way**:  
  To ensure each shopping list entry is self-contained, easily editable, and clearly displayed (with quantities formatted appropriately).
- **Highlights**:  
  The use of a copy constructor for deep copying and assert statements for data validation enhances code reliability.
  
### Shopping List Functionality Enhancements
- **What it does**:  
  The `ShoppingList` class supports operations to add, remove, update, and edit items, as well as mark items as purchased. This gives users flexible control over their shopping list.
- **Why it is implemented that way**:  
  The design follows the Single Responsibility Principle, separating item management from list-level operations. This makes the code modular and extensible.
- **Highlights**:  
  Integration of an index-based edit function allows users to easily modify items, and the "mark as purchased" feature visually tracks completed items.

### Auto-generation of Shopping List
- **What it does**:  
  The `ShoppingListGenerator` class automatically creates a shopping list by checking low-stock alerts from the Ingredient Inventory. It computes required quantities and updates or adds items accordingly.
- **Why it is implemented that way**:  
  Automating the shopping list generation reduces manual input, helping busy users quickly see what ingredients need replenishment.
- **Highlights**:  
  This feature required careful coordination with the Ingredient Inventory and a clear design to merge auto-generated items with manual list management.

## **Documentation**:
### User Guide:
  - Added documentation for shopping list features:
    - Adding items to shopping list
    - Updating shopping list items
    - Clearing shopping list
    - Automatic low-stock detection
  - Update command summary table with updated shopping list features

### Developer Guide:
- Completed the implementation of the shopping list item and shopping list features
- Created sequence diagrams for the shopping list item and shopping list features.
- Added detailed descriptions on the design and implementation of the shopping list item and shopping list features.

## **Project Management**:
- Set up Gradle build system
- Configured testing framework 
