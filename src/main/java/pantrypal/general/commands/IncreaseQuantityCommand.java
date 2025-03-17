package pantrypal.general.commands;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class IncreaseQuantityCommand extends Command {
    private String name;
    private double quantity;
    private String unit;

    public IncreaseQuantityCommand() {
        super("increaseQuantity <name> <quantity> <unit>","Increase quantity of ingredient");
    }

    public IncreaseQuantityCommand(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        inventory.increaseQuantity(name, quantity, unit);
    }

}
