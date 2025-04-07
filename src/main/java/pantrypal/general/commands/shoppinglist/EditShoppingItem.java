package pantrypal.general.commands.shoppinglist;

import pantrypal.general.control.Ui;
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
        // Check if the index is within valid bounds
        if (index < 0 || index >= list.getItems().size()) {
            ui.showMessage("Invalid index provided. No item updated.");
            return;
        }
        // If the index is valid, perform the edit operation.
        list.editItem(index, newIngredientName, newQuantity, newUnit);
        ui.showMessage("Item at index " + (index + 1) + " updated successfully.");
    }
}
