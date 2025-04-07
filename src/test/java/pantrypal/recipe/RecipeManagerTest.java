package pantrypal.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.Category;
import pantrypal.inventory.Unit;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        Recipe friedEgg = recipeManager.searchRecipe("fried_egg");

        recipeManager.addRecipeIngredients(friedEgg, "eggs", 50, Unit.parseUnit("g"),
                Category.parseCategory("DAIRY"));
        assertEquals("eggs", friedEgg.getIngredients().get(0).getName(),
                "Expected fried egg to be eggs");

        friedEgg.removeIngredient("eggs");
        /* Removed for handled case
        try {
            recipeManager.addRecipeIngredients(friedEgg, "eggs",
                    50, Unit.parseUnit("eggs"), Category.parseCategory("DAIRY"));
            fail("Method should throw an unsupported unit exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Method should throw an unsupported unit exception");
        } catch (Exception e){
            fail("Unexpected exception thrown");
        }
        */
        try {
            recipeManager.addRecipeIngredients(friedEgg, "eggs",
                    50, Unit.parseUnit("g"), Category.parseCategory("HEHE"));
            fail("Method should throw an unsupported unit exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Method should throw an unsupported category exception");
        } catch (Exception e){
            fail("Unexpected exception thrown");
        }

        friedEgg.removeIngredient("eggs");
        try {
            Method method = RecipeManager.class.getMethod("addRecipeIngredients",
                    Recipe.class, String.class, int.class, Unit.class, Category.class);
            method.invoke(recipeManager, friedEgg, "milk", "eggs", Unit.parseUnit("g"),
                    Category.parseCategory("DAIRY"));
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

        friedEgg.removeIngredient("eggs");
        try {
            recipeManager.addRecipeIngredients(friedEgg, "eggs", 0, Unit.parseUnit("g"),
                    Category.parseCategory("DAIRY"));
            fail("Method should throw an assertion exception");
        } catch (AssertionError e){
            assertNotNull(e, "Method should throw an assertion exception");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        recipeManager.addRecipe("new_recipe");
        Recipe newRecipe = recipeManager.searchRecipe("new_recipe");
        recipeManager.addRecipeIngredients(newRecipe, "news", 50, Unit.parseUnit("g"),
                Category.parseCategory("FRUIT"));
        recipeManager.addRecipeIngredients(newRecipe, "news", 50, Unit.parseUnit("g"),
                Category.parseCategory("FRUIT"));
        assertEquals(1, newRecipe.getIngredients().size(),
                "Method should not accept duplicate ingredients");
    }

    @Test
    void addRecipeInstruction() {
        recipeManager.addRecipe("fried_egg");
        Recipe friedEgg = recipeManager.searchRecipe("fried_egg");

        recipeManager.addRecipeInstruction(friedEgg, 1, "crack eggs");
        recipeManager.addRecipeInstruction(friedEgg, 2, "serve eggs");
        assertEquals(2, friedEgg.getInstructions().size());
        assertEquals("crack eggs", friedEgg.getInstructions().get(0).getInstruction());
        assertEquals("serve eggs", friedEgg.getInstructions().get(1).getInstruction());

        recipeManager.addRecipe("recipe_2");
        Recipe recipeTwo = recipeManager.searchRecipe("recipe_2");

        try {
            recipeManager.addRecipeInstruction(recipeTwo, 0, "serve eggs");
            fail("Method should throw an assertion exception");
        } catch (AssertionError e){
            assertNotNull(e, "Method should throw an assertion exception");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

        recipeManager.addRecipeInstruction(recipeTwo, 1, "serve eggs");
        recipeManager.addRecipeInstruction(recipeTwo, 1, "cook eggs");
        assertEquals(1, recipeTwo.getInstructions().size(),
                "Method should not accept duplicate step number");
    }

    @Test
    void removeRecipeInstruction() {
        recipeManager.addRecipe("fried_egg");
        Recipe friedEgg = recipeManager.searchRecipe("fried_egg");
        recipeManager.addRecipeInstruction(friedEgg, 1, "serve eggs");
        recipeManager.addRecipeInstruction(friedEgg, 2, "cook eggs");

        recipeManager.removeRecipeInstruction(friedEgg, "1");
        assertEquals(1, friedEgg.getInstructions().size(), "serve eggs should be removed");
        assertEquals("cook eggs", friedEgg.getInstructions().get(0).getInstruction(),
                "serve eggs should be removed");

        recipeManager.removeRecipeInstruction(friedEgg, "3");
        assertEquals(1, friedEgg.getInstructions().size(),
                "nothing should be removed");
        assertEquals("cook eggs", friedEgg.getInstructions().get(0).getInstruction(),
                "nothing should be removed");
    }

    @Test
    void removeRecipeIngredient() {
        recipeManager.addRecipe("fried_egg");
        Recipe friedEgg = recipeManager.searchRecipe("fried_egg");
        recipeManager.addRecipeIngredients(friedEgg, "eggs", 50, Unit.parseUnit("g"),
                Category.parseCategory("DAIRY"));
        recipeManager.addRecipeIngredients(friedEgg, "oil", 50, Unit.parseUnit("ml"),
                Category.parseCategory("CONDIMENT"));

        recipeManager.removeRecipeIngredient(friedEgg, "oil");
        assertEquals(1, friedEgg.getIngredients().size(),
                "oil should be removed");
        assertEquals("eggs", friedEgg.getIngredients().get(0).getName(),
                "oil should be removed");

        recipeManager.removeRecipeIngredient(friedEgg, "pizza");
        assertEquals(1, friedEgg.getIngredients().size(),
                "nothing should be removed");
        assertEquals("eggs", friedEgg.getIngredients().get(0).getName(),
                "eggs should be removed");
    }

    @Test
    void listRecipe() {
        ByteArrayOutputStream outContent;
        PrintStream originalOut = System.out;

        try {
            // Act: Call the method that prints output
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            recipeManager.listRecipe();

            // Assert: Check if the output is as expected
            String expectedOutput = "There are no recipes at the moment. You can add via addRecipe\n";
            assertEquals(normalizeString(expectedOutput), normalizeString(outContent.toString()),
                    "Printed output: " + outContent + " does not match expected output: "
                        + expectedOutput);


            recipeManager.addRecipe("fried_egg");
            recipeManager.addRecipe("milk");
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            recipeManager.listRecipe();

            expectedOutput = """
                    1. fried_egg
                    2. milk
                    """;
            assertEquals(normalizeString(expectedOutput), normalizeString(outContent.toString()),
                    "Printed output: " + outContent + " does not match expected output: "
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

        Recipe friedEgg = recipeManager.searchRecipe("fried_egg");
        recipeManager.addRecipeInstruction(friedEgg, 1, "serve eggs");
        recipeManager.addRecipeInstruction(friedEgg, 2, "cook eggs");
        recipeManager.addRecipeIngredients(friedEgg, "eggs", 50, Unit.parseUnit("g"),
                Category.parseCategory("DAIRY"));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Act: Call the method that prints output
            recipeManager.showRecipe("fried_egg");

            // Assert: Check if the output is as expected
            String expectedOutput = """
                    ____________________________________________________________
                    fried_egg
                    ____________________________________________________________
                    Ingredients:
                    1. eggs 50.0 g Dairy
                    ____________________________________________________________
                    Instructions:
                    1. serve eggs
                    2. cook eggs
                    
                    
                    ____________________________________________________________
                    """;
            assertEquals(normalizeString(expectedOutput), normalizeString(outContent.toString()),
                    "Printed output: " + outContent + " does not match expected output: "
                            + expectedOutput);

            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            recipeManager.showRecipe("recipe_that_does_not_exist");

            expectedOutput = "There is no recipe with name " +
                    "recipe_that_does_not_exist\n";
            assertEquals(normalizeString(expectedOutput), normalizeString(outContent.toString()),
                    "Printed output: " + outContent + " does not match expected output: "
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
        assertEquals(recipes.get(0), recipeManager.searchRecipe("fried_egg"),
                "recipe returned from searchRecipe should be the same object");
        assertEquals(recipes.get(1), recipeManager.searchRecipe("milk"),
                "recipe returned from searchRecipe should be the same object");
        assertEquals(recipes.get(2), recipeManager.searchRecipe("cheesy_pizza"),
                "recipe returned from searchRecipe should be the same object");

    }

    @Test
    void removeRecipe() {
        recipeManager.addRecipe("fried_egg");
        recipeManager.addRecipe("milk");
        recipeManager.addRecipe("cheesy_pizza");
        ArrayList<Recipe> recipes = recipeManager.getRecipeList();

        recipeManager.removeRecipe("fried_egg");
        assertEquals(recipes.get(0), recipeManager.searchRecipe("milk"),
                "fried_egg should be removed");
        assertEquals(2, recipes.size(),
                "fried_egg should be removed");

        recipeManager.removeRecipe("no_egg");
        assertEquals(recipes.get(0), recipeManager.searchRecipe("milk"),
                "nothing should be removed");
        assertEquals(2,recipes.size(),
                "nothing should be removed");
    }

    @Test
    void getRecipeList() {
        assertEquals(ArrayList.class, recipeManager.getRecipeList().getClass(),
                "getRecipeList should return ArrayList");
        assertEquals(0, recipeManager.getRecipeList().size(),
                "recipe list should be null at the start");
        recipeManager.addRecipe("fried_egg");
        assertEquals(Recipe.class, recipeManager.getRecipeList().get(0).getClass(),
                "recipe list should contain object of type Recipe");
        assertEquals("fried_egg", recipeManager.getRecipeList().get(0).getName(),
                "fried_egg is expected as the first object");
        assertEquals(1, recipeManager.getRecipeList().size(),
                "fried_egg should be added");
    }

    @Test
    void initiallyEmptyRecipeList() {
        assertTrue(recipeManager.getRecipeList().isEmpty(), "Recipe list should be empty at the start");
    }

    private static String normalizeString(String input) {
        return input
                .replaceAll("\\r\\n?", "\n")  // Normalize Windows `\r\n` to Unix `\n`
                .replaceAll("\\s+", " ")       // Replace multiple spaces/tabs/newlines with a single space
                .trim();                        // Trim leading/trailing spaces
    }
}

