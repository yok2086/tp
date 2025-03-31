package pantrypal.mealplan;

/**
 *  shows a selected meal plan for a day
 */

public class DailySchedule {

    private MealPlan selectedMealPlan = null;
    private boolean used = false;

    public DailySchedule() {
    }

    public void setPlan(PlanPresets planPresets, int planIndex){
        selectedMealPlan = planPresets.getPlans().get(planIndex);
        this.used = true;
    }

    public void showDay(){
        if (!used){
            System.out.println("No plan selected");
        } else {
            System.out.println(selectedMealPlan.toString());
        }
    }

    public boolean planIsUsed(){
        return used;
    }
}
