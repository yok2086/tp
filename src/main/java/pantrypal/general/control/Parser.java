package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.general.HelpCommand;
import pantrypal.general.commands.general.ExitCommand;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.inventory.AddIngredient;
import pantrypal.general.commands.inventory.IncreaseQuantity;
import pantrypal.general.commands.inventory.DecreaseQuantity;
import pantrypal.general.commands.inventory.SetAlert;
import pantrypal.general.commands.inventory.CheckStock;
import pantrypal.general.commands.inventory.ViewLowStock;
import pantrypal.general.commands.inventory.DeleteIngredient;
import pantrypal.general.commands.mealplan.AddNewPlan;
import pantrypal.general.commands.mealplan.RemovePlan;
import pantrypal.general.commands.mealplan.RemoveRecipeFromPlan;
import pantrypal.general.commands.mealplan.ViewPlan;
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.general.commands.shoppinglist.GenerateShoppingList;
import pantrypal.general.commands.shoppinglist.RemoveShoppingItem;
import pantrypal.general.commands.shoppinglist.ViewShoppingList;
import pantrypal.general.commands.mealplan.AddRecipeToPlan;

public class Parser {

    public Command parse(String input) {
        String[] inputParts = input.split(" ");
        String command = inputParts[0];

        String name;
        double quantity;
        String unit;

        switch (command) {
        case "help":
            return new HelpCommand();
        case "exit":
            return new ExitCommand();
        case "addNewIngredient":
            name = inputParts[1];
            quantity = Double.parseDouble(inputParts[2]);
            unit = inputParts[3];
            return new AddIngredient(name, quantity, unit);
        case "increaseQuantity":
            name = inputParts[1];
            quantity = Double.parseDouble(inputParts[2]);
            return new IncreaseQuantity(name, quantity);
        case "decreaseQuantity":
            name = inputParts[1];
            quantity = Double.parseDouble(inputParts[2]);
            return new DecreaseQuantity(name, quantity);
        case "setAlert":
            name = inputParts[1];
            double threshold = Double.parseDouble(inputParts[2]);
            unit = inputParts[3];
            return new SetAlert(name, threshold, unit);
        case "checkStock":
            return new CheckStock();
        case "viewLowStock":
            return new ViewLowStock();
        case "deleteIngredient":
            name = inputParts[1];
            return new DeleteIngredient(name);
        case "addShoppingItem":
            name = inputParts[1];
            quantity = Double.parseDouble(inputParts[2]);
            unit = inputParts[3];
            return new AddShoppingItem(name, quantity, unit);
        case "generateShoppingList":
            return new GenerateShoppingList();
        case "removeShoppingItem":
            name = inputParts[1];
            return new RemoveShoppingItem(name);
        case "view":
            return new ViewShoppingList();
        case "viewPlan":
            return new ViewPlan();
        case "addPlan":
            int duration = Integer.parseInt(inputParts[1]);
            return new AddNewPlan(duration);
        case "removePlan":
            int index = Integer.parseInt(inputParts[1]) - 1;
            return new RemovePlan(index);
        case "addRecipe":
            return new AddRecipe();
        case "viewRecipe": //Add error handling for empty recipe field
            name = inputParts[1];
            return new ViewRecipe(name);
        case "removeRecipe":
            name = inputParts[1];
            return new RemoveRecipe(name);
        case "listRecipes":
            return new ListRecipe();
        case "addRecipeToPlan":
            int recipeIndex = Integer.parseInt(inputParts[1]) - 1;
            int planIndex = Integer.parseInt(inputParts[2]) - 1;
            return new AddRecipeToPlan(recipeIndex, planIndex);
        case "removeRecipeFromPlan":
            int deleteRecipeIndex = Integer.parseInt(inputParts[1]) - 1;
            int deletePlanIndex = Integer.parseInt(inputParts[2]) - 1;
            return new RemoveRecipeFromPlan(deleteRecipeIndex, deletePlanIndex);
        default:
            return new NullCommand();
        }
    }
}
