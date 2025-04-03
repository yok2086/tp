package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;

import java.util.Scanner;

public class AddShoppingItem extends ShoppingListCommand {
    private ShoppingListItem shoppingListItem;
    private String name;

    public AddShoppingItem() {
        super("addShoppingItem <name> <quantity> <unit>", "Add an item to the shopping list");
    }

    public AddShoppingItem(String name, double quantity, Unit unit) {
        super("AddShoppingItem <name> <quantity> <unit>", "Add an item to the shopping list");
        shoppingListItem = new ShoppingListItem(name, quantity, unit);
        this.name = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        list.addItem(shoppingListItem);
        Ui.showMessage("Add '" + name + "' to the shopping list.");
    }
}
