# Feng Yuan's Project Portfolio Page

## Project: PantryPal

PantryPal is an all-in-one application designed to help students manage their meals.
The application allows users to keep track of their ingredients, automatically generate shopping lists,
plan their meals, and store recipes.

## Given below are my contributions to the project:

### **MealPlanManager Class**

- Developed a robust manager class to handle the core functionality of meal plan creation and assignment to days of the week, and detailed/summarized views.
- Introduced day-based plan assignment using an enum (Day) to represent each day of the week, aligning with real-world scheduling needs.
- Implemented plan removal logic that ensures any assigned plan is removed from both the central list and any associated weekly day slots.
- Created user feedback mechanisms through Ui.showMessage() to guide the user through actions such as adding/removing plans or handling edge cases like duplicates or invalid indexes.

### **Plan Class**

- Designed the Plan class to represent a single meal plan with support for up to three recipes per day, categorized by MealType (e.g., breakfast, lunch, dinner).
- Implemented recipe addition and removal logic with clear error handling when attempting to overwrite or delete non-existent entries.
- Developed a custom toString() method to display plan contents in a user-friendly format, showing both the plan name and associated meal recipes.

### **Added search and indexing features:**

- Wrote utility methods to locate plans by name (findPlan) and return corresponding objects.
- Ensured the search was case-insensitive, improving the user experience when dealing with various input formats.

### **Emphasized modularity and scalability:**
- Ensured clean separation of concerns by decoupling plan logic from UI handling and leveraging enums to simplify index mapping.
- Wrote extensible code that can easily be expanded to accommodate features like custom meal types or multi-week scheduling in the future.
- This module significantly improved the app's functionality by enabling structured meal organization and promoting better user planning habits. My work ensures that the system remains maintainable, user-friendly, and ready for future enhancements.

### **JUnit Testing:**
- Implemented test cases for the MealPlan and MealPlanManager classes to verify the parsing logic.

### **Documentation**
- Wrote the Developer's guide for the following functions for the MealPlan and MealPlanManager classes::
    - addRecipeToPlan
    - removeRecipeFromPlan 
    - viewPlan
    - viewPlanList
    - viewDayPlan
    - viewWeekPlans
<br>
<br>
- and further created sequence diagrams for these functions below:
    - addPlan
    - addPlanToDay
    - removePlan
    - removePlanFromDay
    - executePlan
    - findPlan


### **Project Management**
- Provided clear indications and clarifications for GitHub usage with regards to commit, push, merge
- Assisted with debugging and implementation of collaborators' code
