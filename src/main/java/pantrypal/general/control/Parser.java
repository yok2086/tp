package pantrypal.general.control;

import pantrypal.general.commands.*;

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

        default:
            return new NullCommand();
        }
    }


}
