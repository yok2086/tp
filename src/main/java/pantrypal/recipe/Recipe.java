package pantrypal.recipe;

import pantrypal.inventory.Ingredient;
import java.util.ArrayList;

public class Recipe {

    private static final String LINE = "_".repeat(50);

    private String name;
    private ArrayList<String> instructions;
    private ArrayList<String> ingredients;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInstructions(){
        String allInstructions = "";
        for (String instruction : instructions) {
            allInstructions = allInstructions + instruction + "\n";
        }
        return allInstructions;
    }

    public String getIngredients(){
        String allIngredients = "";
        for (String ingredient : ingredients) {
            allIngredients = allIngredients + ingredient + "\n";
        }
        return allIngredients;
    }

    public String getContent(){
        String allContents = "";
        allContents = allContents + name + "\n" + LINE + "\n" + "Instructions:" + "\n"
                + getInstructions() + "\n" + LINE + "\n" + "Ingredients:" + "\n";
        return allContents;
    }

    @Override
    public String toString(){
        return name;
    }
}
