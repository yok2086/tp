package pantrypal.mealplan;

import pantrypal.inventory.Ingredient;
import pantrypal.recipe.*; //missing class from recipe package
import java.util.ArrayList;
import java.util.Scanner;

public class MealPlan {
    private final int duration;
    private ArrayList<String> ingredients = new ArrayList<>(); //required ingredients
    //private ArrayList <Recipe> recipes; //recipes that are part of this meal plan

    public MealPlan(int duration) {
        this.duration = duration;
        //to add while loop for ingredients listing
    }

    /* constructor requires allocation of scanner
    public MealPlan(Scanner inpScan, int duration){
        String inpLine = inputScan.nextLine();
        while(inpLine != "exit"){
            addIngredient(inpLine);
        }

    }
     */

    public int getDuration() {
        return duration;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Duration: ");
        res.append(getDuration());
        res.append("\n");
        for (String i : ingredients) {
            res.append(i);
        }

        return res.toString();
    }
}
