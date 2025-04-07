package pantrypal.general.commands.general;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class UnitList extends GeneralCommand {

    public UnitList() {
        super("unitList", "List all valid units");
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {
        Ui.showMessage("All valid units: " + "\n" +
                "GRAM(g)" + "\n" +
                "KILOGRAM(kg)" + "\n" +
                "MILLILITER(mL)" + "\n" +
                "CUP(cup)" + "\n" +
                "LITER(L)" + "\n" +
                "OUNCE(oz)" + "\n" +
                "POUND(lb)" + "\n" +
                "MILLIGRAM(mg)" + "\n" +
                "PIECE(pc)" + "\n" +
                "EGG_WHITE(egg white)" + "\n" +
                "EGG_YOLK(egg yolk)" + "\n" +
                "PINCH(pinch)" + "\n" +
                "SPLASH(splash)" + "\n" +
                "BUNCH(bunch)" + "\n" +
                "SLICES(slices)" + "\n" +
                "CLOVE(clove)" + "\n" +
                "PINT(pt)" + "\n" +
                "QUART(qt)" + "\n" +
                "GALLON(gal)" + "\n" +
                "FLUID_OUNCE(fl oz)" + "\n" +
                "CUBIC_CENTIMETER(cc)" + "\n" +
                "CUBIC_INCH(in³)" + "\n" +
                "CUBIC_FOOT(ft³)" + "\n" +
                "SQUARE_INCH(in²)" + "\n" +
                "SQUARE_FOOT(ft²)" + "\n" +
                "SQUARE_METER(m²)" + "\n" +
                "SQUARE_CENTIMETER(cm²)" + "\n" +
                "LITER_PER_MINUTE(L/min)" + "\n" +
                "KILOCALORIE(kcal)" + "\n" +
                "CALORIE(cal)");
    }
}
