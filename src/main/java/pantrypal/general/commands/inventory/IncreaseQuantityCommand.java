package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

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
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList) {
        inventory.increaseQuantity(name, quantity, unit);
    }

}
