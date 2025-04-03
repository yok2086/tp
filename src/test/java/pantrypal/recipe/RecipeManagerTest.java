package pantrypal.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Unit;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import static org.junit.jupiter.api.Assertions.*;

class RecipeManagerTest {

    private RecipeManager recipeManager;

    @BeforeEach
    void setUp() {
        recipeManager = new RecipeManager();
        assertNotNull(recipeManager, "Manager should not be null");
    }

    @Test
    void addRecipe() {
        recipeManager.addRecipe("fried_egg");
        recipeManager.addRecipe("cheesy_pizza");
        ArrayList<Recipe> recipes = recipeManager.getRecipeList();
        assertEquals(2, recipes.size(), "Expected 2 recipes in recipe list");
        assertEquals("fried_egg", recipes.get(0).getName(),
                "Expected first recipe to be fried_egg");
        assertEquals("cheesy_pizza", recipes.get(1).getName(),
                "Expected second recipe to be cheesy_pizza");
    }

    @Test
    void addRecipeIngredients() {
        recipeManager.addRecipe("fried_egg");
        Recipe fried_egg = recipeManager.searchRecipe("fried_egg");

        recipeManager.addRecipeIngredients(fried_egg, "eggs", 50, Unit.parseUnit("g"));
        assertEquals("eggs", fried_egg.getIngredients().get(0).getName(),
                "Expected fried egg to be eggs");

        fried_egg.removeIngredient("eggs");
        try {
            recipeManager.addRecipeIngredients(fried_egg, "eggs",
                    50, Unit.parseUnit("eggs"));
            fail("Method should throw an unsupported unit exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Method should throw an unsupported unit exception");
        } catch (Exception e){
            fail("Unexpected exception thrown");
        }

        fried_egg.removeIngredient("eggs");
        try {
            Method method = RecipeManager.class.getMethod("addRecipeIngredients",
                    Recipe.class, String.class, int.class, Unit.class);
            method.invoke(recipeManager, fried_egg, "milk", "eggs", Unit.parseUnit("g"));
            fail("Method should throw a number format exception");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof NumberFormatException) {
                assertNotNull(cause, "Method should throw a number format exception");
            } else {
                fail("Unexpected exception thrown: " + cause);
            }
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Method should throw an illegal argument exception");
        } catch (Exception e) {
            System.out.println("Unexpected exception thrown outside Invocation Target: " + e);
            fail("Unexpected exception thrown");
        }

        fried_egg.removeIngredient("eggs");
        try {
            recipeManager.addRecipeIngredients(fried_egg, "eggs", 0, Unit.parseUnit("g"));
            fail("Method should throw an arithmetic exception");
        } catch (ArithmeticException e){
            assertNotNull(e, "Method should throw an arithmetic exception");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        recipeManager.addRecipe("new_recipe");
        Recipe newRecipe = recipeManager.searchRecipe("new_recipe");
        recipeManager.addRecipeIngredients(newRecipe, "news", 50, Unit.parseUnit("g"));
        recipeManager.addRecipeIngredients(newRecipe, "news", 50, Unit.parseUnit("g"));
        assertEquals(1, newRecipe.getIngredients().size(),
                "Method should not accept duplicate ingredients");
    }

    @Test
    void addRecipeInstruction() {
        recipeManager.addRecipe("fried_egg");
        Recipe fried_egg = recipeManager.searchRecipe("fried_egg");

        recipeManager.addRecipeInstruction(fried_egg, 1, "crack eggs");
        recipeManager.addRecipeInstruction(fried_egg, 2, "serve eggs");
        assertEquals(2, fried_egg.getInstructions().size());
        assertEquals("crack eggs", fried_egg.getInstructions().get(0).getInstruction());
        assertEquals("serve eggs", fried_egg.getInstructions().get(1).getInstruction());

        recipeManager.addRecipe("recipe_2");
        Recipe recipe_2 = recipeManager.searchRecipe("recipe_2");

        try {
            recipeManager.addRecipeInstruction(recipe_2, 0, "serve eggs");
            fail("Method should throw an arithmetic exception");
        } catch (ArithmeticException e){
            assertNotNull(e, "Method should throw an arithmetic exception");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        recipeManager.addRecipeInstruction(recipe_2, 1, "serve eggs");
        recipeManager.addRecipeInstruction(recipe_2, 1, "cook eggs");
        assertEquals(1, recipe_2.getInstructions().size(),
                "Method should not accept duplicate step number");
    }

    @Test
    void removeRecipeInstruction() {
        recipeManager.addRecipe("fried_egg");
        Recipe fried_egg = recipeManager.searchRecipe("fried_egg");
        recipeManager.addRecipeInstruction(fried_egg, 1, "serve eggs");
        recipeManager.addRecipeInstruction(fried_egg, 2, "cook eggs");

        recipeManager.removeRecipeInstruction(fried_egg, "1");
        assertEquals(1, fried_egg.getInstructions().size());
        assertEquals("cook eggs", fried_egg.getInstructions().get(0).getInstruction());

        recipeManager.removeRecipeInstruction(fried_egg, "3");
        assertEquals(1, fried_egg.getInstructions().size());
        assertEquals("cook eggs", fried_egg.getInstructions().get(0).getInstruction());
    }

    @Test
    void removeRecipeIngredient() {
        recipeManager.addRecipe("fried_egg");
        Recipe fried_egg = recipeManager.searchRecipe("fried_egg");
        recipeManager.addRecipeIngredients(fried_egg, "eggs", 50, Unit.parseUnit("g"));
        recipeManager.addRecipeIngredients(fried_egg, "oil", 50, Unit.parseUnit("ml"));

        recipeManager.removeRecipeIngredient(fried_egg, "oil");
        assertEquals(1, fried_egg.getIngredients().size());
        assertEquals("eggs", fried_egg.getIngredients().get(0).getName());

        recipeManager.removeRecipeIngredient(fried_egg, "pizza");
        assertEquals(1, fried_egg.getIngredients().size());
        assertEquals("eggs", fried_egg.getIngredients().get(0).getName());
    }

    @Test
    void listRecipe() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            // Act: Call the method that prints output
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            recipeManager.listRecipe();

            // Assert: Check if the output is as expected
            String expectedOutput = "There are no recipes at the moment. You can add via addRecipe\r\n";
            assertEquals(expectedOutput, outContent.toString(),
                    "Printed output: " + outContent.toString() + " does not match expected output: "
                        + expectedOutput);


            recipeManager.addRecipe("fried_egg");
            recipeManager.addRecipe("milk");
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            recipeManager.listRecipe();

            expectedOutput = "1. fried_egg\r\n" +
                    "2. milk\r\n";
            assertEquals(expectedOutput, outContent.toString(),
                    "Printed output: " + outContent.toString() + " does not match expected output: "
                        + expectedOutput);
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }

    @Test
    void showRecipe() {
        recipeManager.addRecipe("fried_egg");
        recipeManager.addRecipe("milk");
        recipeManager.addRecipe("cheesy_pizza");
        recipeManager.addRecipe("fourth_repice");

        Recipe fried_egg = recipeManager.searchRecipe("fried_egg");
        recipeManager.addRecipeInstruction(fried_egg, 1, "serve eggs");
        recipeManager.addRecipeInstruction(fried_egg, 2, "cook eggs");
        recipeManager.addRecipeIngredients(fried_egg, "eggs", 50, Unit.parseUnit("g"));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Act: Call the method that prints output
            recipeManager.showRecipe("fried_egg");

            // Assert: Check if the output is as expected
            String expectedOutput = "fried_egg\n" +
                    "__________________________________________________\n" +
                    "Ingredients:\n" +
                    "1. eggs 50.0 g\n" +
                    "__________________________________________________\n" +
                    "Instructions:\n" +
                    "1. serve eggs\n" +
                    "2. cook eggs\n\n\r\n";
            assertEquals(expectedOutput, outContent.toString(),
                    "Printed output: " + outContent.toString() + " does not match expected output: "
                            + expectedOutput);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            recipeManager.showRecipe("recipe_that_does_not_exist");

            expectedOutput = "There is no recipe with name " +
                    "recipe_that_does_not_exist\r\n";
            assertEquals(expectedOutput, outContent.toString(),
                    "Printed output: " + outContent.toString() + " does not match expected output: "
                            + expectedOutput);
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }

    @Test
    void searchRecipe() {
        recipeManager.addRecipe("fried_egg");
        recipeManager.addRecipe("milk");
        recipeManager.addRecipe("cheesy_pizza");
        ArrayList<Recipe> recipes = recipeManager.getRecipeList();

        assertNull(recipeManager.searchRecipe("no_eggs"));
        assertEquals(recipes.get(0), recipeManager.searchRecipe("fried_egg"));
        assertEquals(recipes.get(1), recipeManager.searchRecipe("milk"));
        assertEquals(recipes.get(2), recipeManager.searchRecipe("cheesy_pizza"));

    }

    @Test
    void removeRecipe() {
        recipeManager.addRecipe("fried_egg");
        recipeManager.addRecipe("milk");
        recipeManager.addRecipe("cheesy_pizza");
        ArrayList<Recipe> recipes = recipeManager.getRecipeList();

        recipeManager.removeRecipe("fried_egg");
        assertEquals(recipes.get(0), recipeManager.searchRecipe("milk"));
        assertEquals(2, recipes.size());

        recipeManager.removeRecipe("no_egg");
        assertEquals(recipes.get(0), recipeManager.searchRecipe("milk"));
        assertEquals(2,recipes.size());
    }

    @Test
    void copyList() {
    }

    @Test
    void getRecipeList() {
    }

    @Test
    void initiallyEmptyRecipeList() {
        assertTrue(recipeManager.getRecipeList().isEmpty(), "Recipe list should be empty at the start");
    }
}