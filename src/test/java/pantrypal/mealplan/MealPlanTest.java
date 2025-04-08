package pantrypal.mealplan;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pantrypal.inventory.IngredientInventory;
import pantrypal.recipe.RecipeManager;


public class MealPlanTest {

    private MealPlanManager plans;
    private RecipeManager recipes;
    private IngredientInventory inventory;

    @BeforeEach
    void setUp(){
        this.plans = new MealPlanManager();
        this.recipes = new RecipeManager();
        this.inventory = new IngredientInventory();
    }   

    @Test
    void testAddPlan() {
        assertNull(plans.getPlan(0), "No plan should have been created yet");

        plans.addPlan("");
        plans.addPlan("plan1");
        plans.addPlan("好おπㄴ");

        assertEquals("default", plans.getPlan(0).getPlanName(),
                "plan name not correctly converted");
        assertEquals("plan1", plans.getPlan(1).getPlanName(),
                "plan name not correctly parsed");
        assertEquals("好おπㄴ", plans.getPlan(2).getPlanName(),
                "plan name not correctly parsed");
    }

    @Test
    void testAddRecipeToPlan(){
        plans.addPlan("plan1");
        recipes.addRecipe("FriedEgg");
        recipes.addRecipe("NotFriedEgg");

        for (int i = 0; i < 3;) {
            assertNull(plans.getPlan(0).getPlanRecipes()[i++], "No recipe should have been added yet");
        }

        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.BREAKFAST);

        assertEquals(recipes.getRecipe(0), plans.getPlan(0).getPlanRecipes()[0],
                "Recipe should have been added");

        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.LUNCH);

        for (int i = 0; i < 3;) {
            if (i < 2) {
                assertEquals(recipes.getRecipe(0), plans.getPlan(0).getPlanRecipes()[i++],
                        "Recipe should have been added");
                continue;
            }
            assertNull(plans.getPlan(0).getPlanRecipes()[i++], "No recipe should have been added yet");
        }

        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.DINNER);

        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.LUNCH);

        assertNotEquals(recipes.getRecipe(1), plans.getPlan(0).getPlanRecipes()[1]);
    }

    @Test
    void testAddPlanToDay(){

        plans.addPlan("plan1");
        recipes.addRecipe("FriedEgg");
        recipes.addRecipe("NotFriedEgg");

        for (int i = 0; i < 3;) {
            assertNull(plans.getPlan(0).getPlanRecipes()[i++], "No recipe should have been added yet");
        }

        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.BREAKFAST);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(1), MealType.LUNCH);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.DINNER);

        plans.addPlanToDay(0, Day.MONDAY);
        assertEquals("plan1", plans.getWeeklyPlans()[Day.MONDAY.ordinal()].getPlanName(),
                "plan for associated should have been successfully added");

        assertNotEquals(recipes.getRecipe(1), plans.getWeeklyPlans()[0].getPlanRecipes()[0],
                "Wrong recipe should have been checked for");
        assertEquals(recipes.getRecipe(1), plans.getWeeklyPlans()[0].getPlanRecipes()[1],
                "Correct recipe should have been checked for");

        try {
            plans.addPlanToDay(1, null);
        } catch (NullPointerException e) {
            assertEquals(1, plans.getPlanList().size());
        }
    }

    @Test
    void testRemoveRecipeFromPlan(){

        plans.addPlan("plan1");
        recipes.addRecipe("FriedEgg");
        recipes.addRecipe("NotFriedEgg");
        plans.addPlanToDay(0, Day.MONDAY);

        try {
            plans.getWeeklyPlans()[0].removeRecipeFromPlan(MealType.BREAKFAST);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, plans.getPlanList().size());
        }

        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.BREAKFAST);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.LUNCH);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.DINNER);
        plans.addPlanToDay(0, Day.TUESDAY);


        plans.removePlanFromDay(Day.MONDAY);
        assertEquals(1, plans.getPlanList().size());

        for (int i = 0; i < 3; i++) {
            assertEquals(recipes.getRecipe(0), plans.getPlanForDay(Day.TUESDAY).getPlanRecipes()[i],
                    "Day should have been filled");
        }

        plans.getWeeklyPlans()[1].removeRecipeFromPlan(MealType.LUNCH);

        assertNull(plans.getWeeklyPlans()[1].getPlanRecipes()[1]);

        plans.getWeeklyPlans()[1].addRecipeToPlan(recipes.getRecipe(1), MealType.LUNCH);

        assertEquals(recipes.getRecipe(1), plans.getWeeklyPlans()[1].getPlanRecipes()[1]);
    }

    @Test
    void testRemovePlan(){

        assertEquals(0, plans.getPlanList().size());
        try {
            plans.removePlan(0);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, plans.getPlanList().size());
        }

        plans.addPlan("plan1");
        recipes.addRecipe("FriedEgg");
        recipes.addRecipe("NotFriedEgg");
        plans.addPlanToDay(0, Day.MONDAY);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.BREAKFAST);

        plans.removePlan(0);
        assertEquals(0, plans.getPlanList().size(), "Plan should have been removed");
        assertNull(plans.getWeeklyPlans()[0], "Weekly plan should have been removed");
    }

    @Test
    void testRemovePlanFromDay(){

        plans.addPlan("plan1");
        recipes.addRecipe("FriedEgg");
        recipes.addRecipe("NotFriedEgg");
        plans.addPlanToDay(0, Day.MONDAY);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.BREAKFAST);
        plans.getPlan(0).addRecipeToPlan(recipes.getRecipe(0), MealType.LUNCH);

        plans.removePlanFromDay(Day.MONDAY);
        assertNull(plans.getWeeklyPlans()[0], "Plan should have been removed");
        assertNotNull(plans.getPlan(0), "Plan should not have been removed from list");
    }

}
