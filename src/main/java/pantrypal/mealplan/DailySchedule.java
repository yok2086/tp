package pantrypal.mealplan;

import java.util.ArrayList;

/**
 * collection on meal plans for a day
 */

public class DailySchedule {

    PlanPresets planPresets;
    private ArrayList<MealPlan> plans;

    public DailySchedule() {
    } //attempt to load file

    public void addPlan(int planIndex) {
        if (planPresets.plans.isEmpty()) {
            throw new NewPlanCreateException(); //prompt the user on adding a new plan to plan presets
        }
        plans.add(planPresets.plans.get(planIndex-1)); //pad for input-to-index disparity
    }

    public void removePlan(int removeIndex) {
        if (plans.isEmpty()) {
            throw new EmptyPlansException(); //indicate for no plans to remove
        }
        plans.remove(removeIndex-1);
    }

    public void showDay(){
        for (MealPlan plan : plans) {
            System.out.println(plan);
        }
    }

    public boolean isEmpty() {
        return plans.isEmpty();
    }
}
