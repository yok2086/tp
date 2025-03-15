package pantrypal.recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Recipe {

    private static final String LINE = "_".repeat(50);

    private String name;
    private ArrayList<Instruction> instructions;
    private ArrayList<String> ingredients;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public boolean addInstruction(Instruction instruction) {
        if (instructions.contains(instruction)) {
            System.out.println("Error: Instruction at step " + instruction.getStep() + " already exists.");
            return false; // Duplicate item not added
        }
        instructions.add(instruction);
        instructions.sort(Comparator.comparingInt(Instruction::getStep));
        return true; // Successfully added
    }

    public Instruction getInstruction(int index) {
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getStep() == index).toList();

        if (filtered.isEmpty()) {
            return null;
        }

        return filtered.get(0);
    }

    public void removeInstruction(int index) {
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getStep() == index).toList();
        if (filtered.isEmpty()) {
            System.out.println("Error: Instruction at step " + index + " does not exist.");
            return;
        }
        instructions.remove(index);
    }

    public String getAllInstructions(){
        StringBuilder allInstructions = new StringBuilder();
        for (Instruction instruction : instructions) {
            allInstructions.append(instruction.toString()).append("\n");
        }
        return allInstructions.toString();
    }

    public void addIngredient(String ingredient) {
        if (ingredients.contains(ingredient)) {
            System.out.println("Error: Ingredient " + ingredient + " already exists.");
        }
        ingredients.add(ingredient);
    }

    public void editIngredient(String ingredient, String newIngredient) {
        if (!ingredients.contains(ingredient)) {
            System.out.println("Error: Ingredient " + ingredient + " does not exist.");
        }
        ingredients.set(ingredients.indexOf(ingredient), newIngredient);
    }

    public void removeIngredient(String ingredient) {
        if (!ingredients.contains(ingredient)) {
            System.out.println("Error: Ingredient " + ingredient + " does not exist.");
        }
        ingredients.remove(ingredient);
    }

    public String getAllIngredients(){
        StringBuilder allIngredients = new StringBuilder();
        for (String ingredient : ingredients) {
            allIngredients.append(ingredient).append("\n");
        }
        return allIngredients.toString();
    }

    public String getContent(){
        String allContents = "";
        allContents = allContents + name + "\n" + LINE + "\n" + "Instructions:" + "\n"
                + getAllInstructions() + "\n" + LINE + "\n" + "Ingredients:" + "\n" + getAllIngredients() + "\n";
        return allContents;
    }

    @Override
    public String toString(){
        return name;
    }
}
