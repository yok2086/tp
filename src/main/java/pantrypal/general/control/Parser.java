package pantrypal.general.control;

import pantrypal.general.commands.Command;
import pantrypal.general.commands.HelpCommand;
import pantrypal.general.commands.ExitCommand;
import pantrypal.general.commands.NullCommand;
import pantrypal.general.commands.inventory.AddIngredientCommand;
import pantrypal.general.commands.inventory.IncreaseQuantityCommand;
import pantrypal.general.commands.inventory.DecreaseQuantityCommand;
import pantrypal.general.commands.inventory.SetAlertCommand;
import pantrypal.general.commands.inventory.CheckStockCommand;
import pantrypal.general.commands.inventory.ViewLowStockCommand;
import pantrypal.general.commands.inventory.DeleteIngredientCommand;
import pantrypal.general.commands.shoppinglist.AddShoppingItem;
import pantrypal.general.commands.shoppinglist.GenerateShoppingList;
import pantrypal.general.commands.shoppinglist.RemoveShoppingItem;
import pantrypal.general.commands.shoppinglist.ViewShoppingList;

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
            return new AddIngredientCommand(name, quantity, unit);
        case "increaseQuantity":
            name = inputParts[1];
            quantity = Double.parseDouble(inputParts[2]);
            unit = inputParts[3];
            return new IncreaseQuantityCommand(name, quantity, unit);
        case "decreaseQuantity":
            name = inputParts[1];
            quantity = Double.parseDouble(inputParts[2]);
            unit = inputParts[3];
            return new DecreaseQuantityCommand(name, quantity, unit);
        case "setAlert":
            name = inputParts[1];
            double threshold = Double.parseDouble(inputParts[2]);
            unit = inputParts[3];
            return new SetAlertCommand(name, threshold, unit);
        case "checkStock":
            return new CheckStockCommand();
        case "viewLowStock":
            return new ViewLowStockCommand();
        case "deleteIngredient":
            name = inputParts[1];
            return new DeleteIngredientCommand(name);
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
        default:
            return new NullCommand();
        }
    }
}
