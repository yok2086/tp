package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.general.Help;
import pantrypal.general.commands.general.Exit;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.general.UnitList;
import pantrypal.general.commands.general.CategoryList;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.ConvertIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.SetAlert;
import pantrypal.general.commands.inventory.CheckStock;
import pantrypal.general.commands.inventory.ViewIngredientsByCategory;
import pantrypal.general.commands.inventory.ViewLowStock;
import pantrypal.general.commands.inventory.DeleteIngredient;
import pantrypal.general.commands.mealplan.AddPlan;
import pantrypal.general.commands.mealplan.AddPlanToDay;
import pantrypal.general.commands.mealplan.AddRecipeToPlan;
import pantrypal.general.commands.mealplan.RemovePlan;
import pantrypal.general.commands.mealplan.RemoveRecipeFromPlan;
import pantrypal.general.commands.mealplan.RemovePlanFromDay;
import pantrypal.general.commands.mealplan.ViewPlan;
import pantrypal.general.commands.mealplan.ViewPlanForDay;
import pantrypal.general.commands.mealplan.ViewPlanForWeek;
import pantrypal.general.commands.mealplan.ExecutePlanForDay;
import pantrypal.general.commands.mealplan.FindPlan;
import pantrypal.general.commands.mealplan.ViewPlanList;
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.general.commands.shoppinglist.RemoveShoppingItem;
import pantrypal.general.commands.shoppinglist.ViewShoppingList;
import pantrypal.general.commands.shoppinglist.EditShoppingItem;
import pantrypal.general.commands.shoppinglist.GenerateShoppingList;
import pantrypal.general.commands.shoppinglist.MarkShoppingItemAsPurchased;
import pantrypal.inventory.Category;
import pantrypal.inventory.Unit;


/**
 * The Parser class is responsible for parsing user input and returning the appropriate Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input the user input as a String
     * @return the Command object corresponding to the user input
     */
    public Command parse(String input) {
        String[] inputParts = input.trim().split(" ");
        String command = inputParts[0].toLowerCase();

        String name;
        double quantity;
        Unit unit;
        int index;
        Category category;

        try {
            switch (command) {
            //general commands
            case "help":
                return new Help();
            case "exit":
                return new Exit();
            //From here on are commands for Ingredient
            case "addnewingredient":
                if (inputParts.length < 5) {
                    throw new IllegalArgumentException("Insufficient arguments for addNewIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";

                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0");
                }
                assert quantity >= 0 : "Quantity must be greater than 0";

                unit = Unit.parseUnit(inputParts[3]);
                if (unit == null) {
                    throw new IllegalArgumentException("Unit cannot be null");
                }
                assert unit != null : "Unit cannot be null";

                category = Category.parseCategory(inputParts[4]);
                if (category == null) {
                    throw new IllegalArgumentException("Category cannot be null");
                }
                assert category != null : "Category cannot be null";
                return new AddIngredient(name, quantity, unit, category);
            case "increasequantity":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for increaseQuantity command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";

                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0");
                }
                assert quantity >= 0 : "Quantity must be greater than 0";
                return new IncreaseQuantity(name, quantity);
            case "decreasequantity":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for decreaseQuantity command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";

                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0");
                }
                assert quantity >= 0 : "Quantity must be greater than 0";
                return new DecreaseQuantity(name, quantity);
            case "setalert":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for setAlert command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";

                double threshold = Double.parseDouble(inputParts[2]);
                if (threshold < 0) {
                    throw new IllegalArgumentException("Threshold must be greater than 0");
                }
                assert threshold >= 0 : "Threshold must be greater than 0";
                return new SetAlert(name, threshold);
            case "viewstock":
                return new CheckStock();
            case "viewlowstock":
                return new ViewLowStock();
            case "deleteingredient":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for deleteIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";
                return new DeleteIngredient(name);
            case "convertingredient":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for convertIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Ingredient name cannot be null or empty";

                unit = Unit.parseUnit(inputParts[2]);
                if (unit == null) {
                    throw new IllegalArgumentException("Unit cannot be null");
                }
                assert unit != null : "Unit cannot be null";
                return new ConvertIngredient(name, unit);
            case "viewingredientsbycategory":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewIngredientsByCategory command.");
                }
                Category categoryName = Category.parseCategory(inputParts[1]);
                if (categoryName == null) {
                    throw new IllegalArgumentException("Category cannot be null");
                }
                assert categoryName != null : "Category cannot be null";
                return new ViewIngredientsByCategory(categoryName);
            case "unitlist":
                return new UnitList();
            case "categorylist":
                return new CategoryList();
            //From here on are commands for ShoppingList
            case "addshoppingitem":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addShoppingItem command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Item name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Item name cannot be null or empty";

                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0");
                }
                assert quantity >= 0 : "Quantity must be greater than 0";

                unit = Unit.parseUnit(inputParts[3]);
                if (unit == null) {
                    throw new IllegalArgumentException("Unit cannot be null");
                }
                assert unit != null : "Unit cannot be null";
                return new AddShoppingItem(name, quantity, unit);
            case "generateshoppinglist":
                return new GenerateShoppingList();
            case "removeshoppingitem":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removeShoppingItem command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Item name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Item name cannot be null or empty";
                return new RemoveShoppingItem(name);
            case "viewshoppinglist":
                return new ViewShoppingList();
            case "editshoppingitem":
                if (inputParts.length < 5) {
                    throw new IllegalArgumentException("Insufficient arguments for editShoppingItem command.");
                }
                index = Integer.parseInt(inputParts[1]) - 1;
                if (index < 0) {
                    throw new IllegalArgumentException("Index must be greater than 0");
                }
                assert index >= 0 : "Index must be greater than 0";

                name = inputParts[2].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Item name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Item name cannot be null or empty";

                quantity = Double.parseDouble(inputParts[3]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0");
                }
                assert quantity >= 0 : "Quantity must be greater than 0";

                unit = Unit.parseUnit(inputParts[4]);
                if (unit == null) {
                    throw new IllegalArgumentException("Unit cannot be null");
                }
                assert unit != null : "Unit cannot be null";
                return new EditShoppingItem(index, name, quantity, unit);
            case "markshoppingitemaspurchased":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for markShoppingItemAsPurchased" +
                            "command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Item name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Item name cannot be null or empty";
                return new MarkShoppingItemAsPurchased(name);
            //From here on are commands for Recipe
            case "addrecipe":
                return new AddRecipe();
            case "viewrecipe":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewRecipe command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Recipe name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Recipe name cannot be null or empty";
                return new ViewRecipe(name);
            case "removerecipe":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removeRecipe command.");
                }
                name = inputParts[1].toUpperCase();
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Recipe name cannot be null or empty");
                }
                assert name != null && !name.isEmpty() : "Recipe name cannot be null or empty";
                return new RemoveRecipe(name);
            case "viewrecipelist":
                return new ListRecipe();
            //From here on are commands for MealPlan
            case "addplan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for addPlan command.");
                }
                String planName = inputParts[1];
                if (planName == null || planName.isEmpty()) {
                    throw new IllegalArgumentException("Plan name cannot be null or empty");
                }
                assert planName != null && !planName.isEmpty() : "Plan name cannot be null or empty";
                return new AddPlan(planName);
            case "addplantoday":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for addPlanToDay command.");
                }
                int addToWeekIndex = Integer.parseInt(inputParts[1]) - 1;
                if (addToWeekIndex < 0) {
                    throw new IllegalArgumentException("Week index must be greater than 0");
                }
                assert addToWeekIndex >= 0 : "Week index must be non-negative";

                String addDayName = inputParts[2];
                if (addDayName == null || addDayName.isEmpty()) {
                    throw new IllegalArgumentException("Day name cannot be null or empty");
                }
                assert addDayName != null && !addDayName.isEmpty() : "Day name cannot be null or empty";
                return new AddPlanToDay(addToWeekIndex, addDayName);
            case "addrecipetoplan":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addRecipeToPlan command.");
                }
                int addRecipePlanIndex = Integer.parseInt(inputParts[1]) - 1;
                if (addRecipePlanIndex < 0) {
                    throw new IllegalArgumentException("Plan index must be greater than 0");
                }
                assert addRecipePlanIndex >= 0 : "Plan index must be greater than 0";

                int addRecipeRecipeIndex = Integer.parseInt(inputParts[2]) - 1;
                if (addRecipeRecipeIndex < 0) {
                    throw new IllegalArgumentException("Recipe index must be greater than 0");
                }
                assert addRecipeRecipeIndex >= 0 : "Recipe index must be greater than 0";

                String addRecipeMealName = inputParts[3];
                if (addRecipeMealName == null || addRecipeMealName.isEmpty()) {
                    throw new IllegalArgumentException("Meal name cannot be null or empty");
                }
                assert addRecipeMealName != null && !addRecipeMealName.isEmpty() : "Meal name cannot be null or empty";
                return new AddRecipeToPlan(addRecipePlanIndex, addRecipeRecipeIndex, addRecipeMealName);
            case "removerecipefromplan":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for removeRecipeFromPlan command.");
                }
                int deleteRecipePlanIndex = Integer.parseInt(inputParts[1]) - 1;
                if (deleteRecipePlanIndex < 0) {
                    throw new IllegalArgumentException("Plan index must be greater than 0");
                }
                assert deleteRecipePlanIndex >= 0 : "Plan index must be greater than 0";

                String deleteRecipeMealName = inputParts[2];
                if (deleteRecipeMealName == null || deleteRecipeMealName.isEmpty()) {
                    throw new IllegalArgumentException("Meal name cannot be null or empty");
                }
                assert deleteRecipeMealName != null && !deleteRecipeMealName.isEmpty() : "Meal name cannot " +
                        "be null or empty";
                return new RemoveRecipeFromPlan(deleteRecipePlanIndex, deleteRecipeMealName);
            case "removeplanfromday":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removePlanFromDay command.");
                }
                String deleteFromWeekDayName = inputParts[1];
                if (deleteFromWeekDayName == null || deleteFromWeekDayName.isEmpty()) {
                    throw new IllegalArgumentException("Day name cannot be null or empty");
                }
                assert deleteFromWeekDayName != null && !deleteFromWeekDayName.isEmpty() : "Day name cannot " +
                        "be null or empty";
                return new RemovePlanFromDay(deleteFromWeekDayName);
            case "viewplanforday":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewPlanForDay command.");
                }
                String viewDayName = inputParts[1];
                if (viewDayName == null || viewDayName.isEmpty()) {
                    throw new IllegalArgumentException("Day name cannot be null or empty");
                }
                assert viewDayName != null && !viewDayName.isEmpty() : "Day name cannot be null or empty";
                return new ViewPlanForDay(viewDayName);
            case "viewplanforweek":
                return new ViewPlanForWeek();
            case "execute":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for executePlanForDay command.");
                }
                String executeDayName = inputParts[1];
                if (executeDayName == null || executeDayName.isEmpty()) {
                    throw new IllegalArgumentException("Day name cannot be null or empty");
                }
                assert executeDayName != null && !executeDayName.isEmpty() : "Day name cannot be null or empty";
                return new ExecutePlanForDay(executeDayName);
            case "findplan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for findPlan command.");
                }
                String findSearchKey = inputParts[1];
                if (findSearchKey == null || findSearchKey.isEmpty()) {
                    throw new IllegalArgumentException("Search key cannot be null or empty");
                }
                assert findSearchKey != null && !findSearchKey.isEmpty() : "Search key cannot be null or empty";
                return new FindPlan(findSearchKey);
            case "viewplanlist":
                return new ViewPlanList();
            case "viewplan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewPlan command.");
                }
                int viewPlanIndex = Integer.parseInt(inputParts[1]) - 1;
                if (viewPlanIndex < 0) {
                    throw new IllegalArgumentException("Plan index must be greater than 0");
                }
                assert viewPlanIndex >= 0 : "Plan index must be greater than 0";
                return new ViewPlan(viewPlanIndex);
            case "removeplan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removePlan command.");
                }
                int removePlanIndex = Integer.parseInt(inputParts[1]) - 1;
                if (removePlanIndex < 0) {
                    throw new IllegalArgumentException("Plan index must be greater than 0.");
                }
                assert removePlanIndex >= 0 : "Plan index must be greater than 0.";
                return new RemovePlan(removePlanIndex);
            default:
                return new NullCommand("Invalid Command! ");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.showMessage(e.getMessage());
            return new NullCommand("Invalid Number! ");
        } catch (IllegalArgumentException e) {
            Ui.showMessage(e.getMessage());
            return new NullCommand("");
        }
    }
}
