package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.PlanPresets;
import pantrypal.mealplan.WeeklySchedule;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class DecreaseQuantity extends InventoryCommand {
    private String name;
    private double quantity;

    public DecreaseQuantity(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public DecreaseQuantity() {
        super("decreaseQuantity <name> <quantity>",
                "Decrease quantity of ingredient");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, PlanPresets presets,
                        RecipeManager recipes, WeeklySchedule week, Scanner in) {
        inventory.decreaseQuantity(name, quantity);

    }
}
