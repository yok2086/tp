package pantrypal.mealplan;


import static org.junit.jupiter.api.Assertions.assertEquals;
/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
import pantrypal.inventory.IngredientInventory;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
*/
public class MealPlanTest {

    private MealPlanManager plans;

    @BeforeEach
    void setUp(){
        this.plans = new MealPlanManager();
    }   

    @Test
    void testAddPlan() {
        plans.addPlanToList("");
        plans.addPlanToList("plan1");
        plans.addPlanToList("好おπㄴ");

        assertEquals("default", plans.getPlanDetails(0).getPlanName(),
                "plan name not correctly converted");
        assertEquals("plan1", plans.getPlanDetails(1).getPlanName(),
                "plan name not correctly parsed");
        assertEquals("好おπㄴ", plans.getPlanDetails(2).getPlanName(),
                "plan name not correctly parsed");
    }

    @Test
    void testAddPlanToDay(){
        plans.addPlanToList("plan1");
        plans.addPlanToDay(0, Day.MONDAY);
        assertEquals("plan1", plans.getWeeklyPlans()[Day.MONDAY.ordinal()].getPlanName());

        try {
            plans.addPlanToDay(1, null);
        } catch (NullPointerException e) {
            assertEquals(1, plans.getPlanList().length);
        }
    }





}
