package pantrypal.general.commands.shoppinglist;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;

public class AddShoppingItem extends Command {
    private ShoppingListItem shoppingListItem;
    private String name;
    private ShoppingList shoppingList;

    public AddShoppingItem() {
        super("AddShoppingItem <name> <quantity> <unit>", "Add an item to the shopping list");
    }

    public AddShoppingItem(String name, double quantity, String unit, ShoppingList shoppingList) {
        super("AddShoppingItem <name> <quantity> <unit>", "Add an item to the shopping list");
        shoppingListItem = new ShoppingListItem(name, quantity, unit);
        this.name = name;
        this.shoppingList = shoppingList;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory) {

        shoppingList.addItem(shoppingListItem);
        ui.showMessage("Add '" + name + "' to the shopping list.");
    }
}
