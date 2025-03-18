package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;

public class DecreaseQuantityCommand extends Command {
    private String name;
    private double quantity;
    private String unit;

    public DecreaseQuantityCommand(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public DecreaseQuantityCommand() {
        super("decreaseQuantity <name> <quantity> <unit>", "Decrease quantity of ingredient");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {
        inventory.decreaseQuantity(name, quantity, unit);

    }
}
