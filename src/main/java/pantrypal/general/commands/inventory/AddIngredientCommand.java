package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class AddIngredientCommand extends Command {
    private String name;
    private double quantity;
    private String unit;

    public AddIngredientCommand(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public AddIngredientCommand() {
        super("addNewIngredient <name> <quantity> <unit>", "Adding new ingredient");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        inventory.addNewIngredient(name, quantity, unit);

    }
}
