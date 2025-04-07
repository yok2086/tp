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
    private Unit unit;
    private Category category;

    public AddIngredient(String name, double quantity, Unit unit, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
    }

    public AddIngredient() {
        super("addNewIngredient <name> <quantity> <unit> <category>",
                "Adding new ingredient");
    }


    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }
    public Category getCategory() {
        return category;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        try {
            inventory.addNewIngredient(name, quantity, unit, category);
            System.out.println("Added ingredient " + name + " with quantity " + quantity + " and unit " + unit);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

