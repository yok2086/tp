package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.inventory.Category;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;

import java.util.Scanner;

public class AddShoppingItem extends ShoppingListCommand {
    private ShoppingListItem shoppingListItem;
    private String name;

    public double getQuantity() {
        return shoppingListItem.getQuantity();
    }

    public Unit getUnit() {
        return shoppingListItem.getUnit();
    }

    public Category getCategory() {
        return shoppingListItem.getCategory();
    }

    public String getName() {
        return name;
    }

    public AddShoppingItem() {
        super("addShoppingItem <name> <quantity> <unit> <category>",
                "Add an item to the shopping list");
    }

    public AddShoppingItem(String name, double quantity, Unit unit, Category category) {
        super("AddShoppingItem <name> <quantity> <unit> <category>",
                "Add an item to the shopping list");
        shoppingListItem = new ShoppingListItem(name, quantity, unit, category);
        this.name = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        list.addItem(shoppingListItem);
        Ui.showMessage("Add '" + name + "' to the shopping list.");
    }
}
