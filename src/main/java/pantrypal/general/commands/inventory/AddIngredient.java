package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Category;
import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Unit;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddIngredient extends InventoryCommand {
    private String name;
    private double quantity;
    private String unit;
    private String category;

    public AddIngredient(String name, double quantity, Unit unit, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.unit = String.valueOf(unit);
        this.category = String.valueOf(category);
    }

    public AddIngredient() {
        super("addNewIngredient <name> <quantity> <unit> <category>",
                "Adding new ingredient");
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        if (!inventory.getInventory().containsKey(name)) {
            inventory.addNewIngredient(name, quantity, Unit.parseUnit(unit), Category.parseCategory(category));
            Ui.printAddIngredientMessage(name, quantity, unit, category);
        } else {
            Ui.printIngredientExists(name);
        }
    }
}
