package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;

import java.util.Scanner;

public class AddShoppingItem extends ShoppingListCommand {
    private ShoppingListItem shoppingListItem;
    private String name;

    public AddShoppingItem(String name, double quantity, Unit unit) {
        super("AddShoppingItem <name> <quantity> <unit>",
                "Add an item to the shopping list");
        shoppingListItem = new ShoppingListItem(name, quantity, unit);
        this.name = name;
    }

    public AddShoppingItem() {
        super("addShoppingItem <name> <quantity> <unit>",
                "Add an item to the shopping list");
    }

    public double getQuantity() {
        return shoppingListItem.getQuantity();
    }

    public Unit getUnit() {
        return shoppingListItem.getUnit();
    }


    public String getName() {
        return name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        boolean success = list.addItem(shoppingListItem);
        if(!success){
            Ui.showMessage("Item '" + name + "' already exists. Please use editShoppingItem to update the item.");
        } else{
            Ui.showMessage("Add '" + name + "' to the shopping list.");
        }
    }
}
