package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
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

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        try {
            inventory.decreaseQuantity(name, quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
