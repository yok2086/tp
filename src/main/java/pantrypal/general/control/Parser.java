package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.general.Help;
import pantrypal.general.commands.general.Exit;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.general.UnitList;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.SetAlert;
import pantrypal.general.commands.inventory.CheckStock;
import pantrypal.general.commands.inventory.ViewLowStock;
import pantrypal.general.commands.inventory.DeleteIngredient;
import pantrypal.general.commands.mealplan.AddNewDay;
import pantrypal.general.commands.mealplan.RemoveDay;
import pantrypal.general.commands.mealplan.ViewDay;
import pantrypal.general.commands.mealplan.AddNewPlan;
import pantrypal.general.commands.mealplan.RemovePlan;
import pantrypal.general.commands.mealplan.ExecuteDay;
import pantrypal.general.commands.mealplan.ViewPresets;
import pantrypal.general.commands.mealplan.ViewWeek;
import pantrypal.general.commands.mealplan.RemoveRecipeFromPlan;
import pantrypal.general.commands.mealplan.AddRecipeToPlan;
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.general.commands.shoppinglist.GenerateShoppingList;
import pantrypal.general.commands.shoppinglist.RemoveShoppingItem;
import pantrypal.general.commands.shoppinglist.ViewShoppingList;
import pantrypal.inventory.Unit;

public class Parser {

    public Command parse(String input) {
        String[] inputParts = input.split(" ");
        String command = inputParts[0];

        String name;
        double quantity;
        Unit unit;

        try {
            switch (command) {
            case "help":
                return new Help();
            case "exit":
                return new Exit();
            case "addNewIngredient":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addNewIngredient command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                unit = Unit.parseUnit(inputParts[3]);
                return new AddIngredient(name, quantity, unit);
            case "increaseQuantity":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for increaseQuantity command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                return new IncreaseQuantity(name, quantity);
            case "decreaseQuantity":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for decreaseQuantity command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                return new DecreaseQuantity(name, quantity);
            case "setAlert":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for setAlert command.");
                }
                name = inputParts[1].toUpperCase();
                double threshold = Double.parseDouble(inputParts[2]);
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
            case "addShoppingItem":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addShoppingItem command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                unit = Unit.parseUnit(inputParts[3]);
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
            case "addNewPlan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for addNewPlan command.");
                }
                String planName = inputParts[1];
                return new AddNewPlan(planName);
            case "removePlan":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for removePlan command.");
                }
                int index = Integer.parseInt(inputParts[1]) - 1;
                return new RemovePlan(index);
            case "addRecipeToPlan":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addRecipeToPlan command.");
                }
                int recipeIndex = Integer.parseInt(inputParts[1]) - 1;
                int planIndex = Integer.parseInt(inputParts[2]) - 1;
                String mealName = inputParts[3];
                return new AddRecipeToPlan(recipeIndex, planIndex, mealName);
            case "removeRecipeFromPlan":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for removeRecipeFromPlan command.");
                }
                int deleteRecipeIndex = Integer.parseInt(inputParts[1]) - 1;
                String deleteMealName = inputParts[2];
                return new RemoveRecipeFromPlan(deleteRecipeIndex, deleteMealName);
            case "addNewDay":
                if (inputParts.length < 3) {
                    throw new IllegalArgumentException("Insufficient arguments for addNewDay command.");
                }
                int dayPlanIndex = Integer.parseInt(inputParts[1]) - 1;
                int addDayIndex = Integer.parseInt(inputParts[2]) - 1;
                return new AddNewDay(dayPlanIndex, addDayIndex);
            case "removeDay":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for removeDay command.");
                }
                int deleteDayIndex = Integer.parseInt(inputParts[1]) - 1;
                return new RemoveDay(deleteDayIndex);
            case "viewDay":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for viewDay command.");
                }
                int viewDayIndex = Integer.parseInt(inputParts[1]) - 1;
                return new ViewDay(viewDayIndex);
            case "executeDay":
                if (inputParts.length < 2) {
                    throw new IllegalArgumentException("Insufficient arguments for executeDay command.");
                }
                int executeDayIndex = Integer.parseInt(inputParts[1]) - 1;
                return new ExecuteDay(executeDayIndex);
            case "viewPresets":
                return new ViewPresets();
            case "viewWeek":
                return new ViewWeek();

            case "unitList":
                return new UnitList();
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
