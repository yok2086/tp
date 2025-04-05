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
                "LITER(L)" + "\n" +
                "OUNCE(oz)" + "\n" +
                "POUND(lb)" + "\n" +
                "MILLIGRAM(mg)" + "\n" +
                "EGG(egg)" + "\n" +
                "EGG WHITE(egg white)" + "\n" +
                "EGG YOLK(egg yolk)" + "\n" +
                "PINCH(pinch)" + "\n" +
                "SPLASH(splash)" + "\n" +
                "BUNCH(bunch)" + "\n" +
                "SLICES(slices)" + "\n" +
                "CLOVE(clove)" + "\n" +
                "PINT(pt)" + "\n" +
                "QUART(qt)" + "\n" +
                "GALLON(gal)" + "\n" +
                "FLUID OUNCE(fl oz)" + "\n" +
                "CUBIC CENTIMETER(cc)" + "\n" +
                "CUBIC INCH(in³)" + "\n" +
                "CUBIC FOOT(ft³)" + "\n" +
                "SQUARE INCH(in²)" + "\n" +
                "SQUARE FOOT(ft²)" + "\n" +
                "SQUARE METER(m²)" + "\n" +
                "SQUARE CENTIMETER(cm²)" + "\n" +
                "LITER PER MINUTE(L/min)" + "\n" +
                "KILOCALORIE(kcal)" + "\n" +
                "CALORIE(cal)");
    }
}
