package pantrypal.inventory;


public class Ingredient {
    String name;
    double quantity;
    String unit;

    Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}
