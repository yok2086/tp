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
import pantrypal.general.commands.recipe.AddRecipe;
import pantrypal.general.commands.recipe.ListRecipe;
import pantrypal.general.commands.recipe.RemoveRecipe;
import pantrypal.general.commands.recipe.ViewRecipe;
import pantrypal.general.commands.shoppinglist.*;
import pantrypal.inventory.Category;
import pantrypal.inventory.Unit;

public class Parser {

    public Command parse(String input) {
        String[] inputParts = input.split(" ");
        String command = inputParts[0];

        String name;
        double quantity;
        Unit unit;
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
                unit = Unit.parseUnit(inputParts[3]);
                category = Category.parseCategory(inputParts[4]);
                return new AddIngredient(name, quantity, unit, category);
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
            case "CategoryList":
                return new CategoryList();
            //From here on are commands for ShoppingList
            case "addShoppingItem":
                if (inputParts.length < 4) {
                    throw new IllegalArgumentException("Insufficient arguments for addShoppingItem command.");
                }
                name = inputParts[1].toUpperCase();
                quantity = Double.parseDouble(inputParts[2]);
                unit = Unit.parseUnit(inputParts[3]);
                category = Category.parseCategory(inputParts[4]);
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
                return new EditShoppingItem();
            case "markShoppingItemAsPurchased":
                return new MarkShoppingItemAsPurchased();
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
