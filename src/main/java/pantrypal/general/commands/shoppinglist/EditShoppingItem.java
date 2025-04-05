package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
import pantrypal.inventory.Category;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.shoppinglist.ShoppingListItem;
import pantrypal.inventory.Unit;
import java.util.Scanner;

public class EditShoppingItem extends ShoppingListCommand {
    private Scanner scanner;
    private ShoppingListItem shoppingListItem;
    private int index;
    private String newIngredientName;
    private double newQuantity;
    private Unit newUnit;

    public EditShoppingItem() {
        super("editShoppingItem <index> <newIngredientName> "
                + "<newQuantity> <newUnit>", "Edit a shopping list item");
    }

    public EditShoppingItem(int index, String newIngredientName, double newQuantity, Unit newUnit) {
        super("editShoppingItem <index> <newIngredientName> <newQuantity> <newUnit>",
                "Edit a shopping list item");
        this.index = index;
        this.newIngredientName = newIngredientName;
        this.newQuantity = newQuantity;
        this.newUnit = newUnit;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list,
                        RecipeManager recipes, MealPlanManager plans, Scanner in) {
        boolean success = list.editItem(index, newIngredientName, newQuantity, newUnit);
        if (success) {
            ui.showMessage("Item at index " + index + " updated successfully.");
        } else {
            ui.showMessage("Error: Invalid index provided. No item updated.");
        }
        Ui.printLine();
    }
}
