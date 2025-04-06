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
import pantrypal.general.commands.mealplan.RemoveRecipeFromPlan;
import pantrypal.general.commands.mealplan.RemovePlanFromDay;
import pantrypal.general.commands.mealplan.ViewPlanForDay;
import pantrypal.general.commands.mealplan.ViewPlanForWeek;
import pantrypal.general.commands.mealplan.ExecutePlanForDay;
import pantrypal.general.commands.mealplan.FindForPlans;
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

public class Parser {

    public Command parse(String input) {
        String[] inputParts = input.trim().split(" ");
        String command = inputParts[0];

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
            case "addNewIngredient":
                if (inputParts.length < 5) {
                    throw new IllegalArgumentException("Insufficient arguments for addNewIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Negative quantity is not allowed for " +
                            "addNewIngredient command.");
                }
                unit = Unit.parseUnit(inputParts[3]);
                category = Category.parseCategory(inputParts[4]);
                return new AddIngredient(name, quantity, unit, category);
            case "increaseQuantity":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for increaseQuantity command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Negative quantity is not allowed for " +
                            "increaseQuantity command.");
                }
                return new IncreaseQuantity(name, quantity);
            case "decreaseQuantity":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for decreaseQuantity command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Negative quantity is not allowed for " +
                            "decreaseQuantity command.");
                }
                return new DecreaseQuantity(name, quantity);
            case "setAlert":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for setAlert command.");
                }
                name = inputParts[1].toUpperCase();
                double threshold = Double.parseDouble(inputParts[2]);
                if (threshold < 0) {
                    throw new IllegalArgumentException("Negative threshold is not allowed for setAlert command.");
                }
                return new SetAlert(name, threshold);
            case "viewStock":
                return new CheckStock();
            case "viewLowStock":
                return new ViewLowStock();
            case "deleteIngredient":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for deleteIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                return new DeleteIngredient(name);
            case "convertIngredient":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for convertIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                unit = Unit.parseUnit(inputParts[2]);
                return new ConvertIngredient(name, unit);
            case "viewIngredientsByCategory":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewIngredientsByCategory command.");
                }
                Category categoryName = Category.parseCategory(inputParts[1]);
                return new ViewIngredientsByCategory(categoryName);
            case "unitList":
                return new UnitList();
            case "categoryList":
                return new CategoryList();
            //From here on are commands for ShoppingList
            case "addShoppingItem":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addShoppingItem command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                unit = Unit.parseUnit(inputParts[3]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Negative quantity is not allowed for addShoppingItem command.");
                }
                return new AddShoppingItem(name, quantity, unit);
            case "generateShoppingList":
                return new GenerateShoppingList();
            case "removeShoppingItem":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removeShoppingItem command.");
                }
                name = inputParts[1].toUpperCase();
                return new RemoveShoppingItem(name);
            case "viewShoppingList":
                return new ViewShoppingList();
            case "editShoppingItem":
                if (inputParts.length < 5) {
                    throw new IllegalArgumentException("Insufficient arguments for editShoppingItem command.");
                }
                index = Integer.parseInt(inputParts[1]);
                if (index < 0) {
                    throw new IllegalArgumentException("Negative index is not allowed for editShoppingItem command.");
                }
                name = inputParts[2].toUpperCase();
                quantity = Double.parseDouble(inputParts[3]);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Negative quantity is not allowed for " +
                            "editShoppingItem command.");
                }
                unit = Unit.parseUnit(inputParts[4]);
                return new EditShoppingItem(index, name, quantity, unit);
            case "markShoppingItemAsPurchased":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for markShoppingItemAsPurchased" +
                            "command.");
                }
                name = inputParts[1].toUpperCase();
                return new MarkShoppingItemAsPurchased(name);
            //From here on are commands for Recipe
            case "addRecipe":
                return new AddRecipe();
            case "viewRecipe":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewRecipe command.");
                }
                name = inputParts[1].toUpperCase();
                return new ViewRecipe(name);
            case "removeRecipe":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removeRecipe command.");
                }
                name = inputParts[1].toUpperCase();
                return new RemoveRecipe(name);
            case "viewRecipeList":
                return new ListRecipe();
            //From here on are commands for MealPlan
            case "addPlan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for addPlan command.");
                }
                String planName = inputParts[1];
                return new AddPlan(planName);
            case "addPlanToDay":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for addPlanToDay command.");
                }
                int addToWeekIndex = Integer.parseInt(inputParts[1]);
                if (addToWeekIndex < 0) {
                    throw new IllegalArgumentException("Negative index is not allowed for addPlanToDay command.");
                }
                String addDayName = inputParts[2];
                return new AddPlanToDay(addToWeekIndex, addDayName);
            case "addRecipeToPlan":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addPlanToDay command.");
                }
                int addRecipePlanIndex = Integer.parseInt(inputParts[1]);
                if (addRecipePlanIndex < 0) {
                    throw new IllegalArgumentException("Negative index is not allowed for addRecipeToPlan command.");
                }
                int addRecipeRecipeIndex = Integer.parseInt(inputParts[2]);
                if (addRecipeRecipeIndex < 0) {
                    throw new IllegalArgumentException("Negative index is not allowed for addRecipeToPlan command.");
                }
                String addRecipeMealName = inputParts[3];
                return new AddRecipeToPlan(addRecipePlanIndex, addRecipeRecipeIndex, addRecipeMealName);
            case "removeRecipeFromPlan":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for removeRecipeFromPlan command.");
                }
                int deleteRecipePlanIndex = Integer.parseInt(inputParts[1]);
                if (deleteRecipePlanIndex < 0) {
                    throw new IllegalArgumentException("Negative index is not allowed for " +
                            "removeRecipeFromPlan command.");
                }
                String deleteRecipeMealName = inputParts[2];
                return new RemoveRecipeFromPlan(deleteRecipePlanIndex, deleteRecipeMealName);
            case "removePlanFromDay":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removeRecipeFromWeek command.");
                }
                String deleteFromWeekDayName = inputParts[1];
                return new RemovePlanFromDay(deleteFromWeekDayName);
            case "viewPlanForDay":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewPlanForDay command.");
                }
                String viewDayName = inputParts[1];
                return new ViewPlanForDay(viewDayName);
            case "viewPlanForWeek":
                return new ViewPlanForWeek();
            case "execute":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for executePlanForDay command.");
                }
                String executeDayName = inputParts[1];
                return new ExecutePlanForDay(executeDayName);
            case "findForPlans":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for findForPlans command.");
                }
                String findSearchKey = inputParts[1];
                return new FindForPlans(findSearchKey);
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

