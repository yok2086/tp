package pantrypal.mealplan;

import java.util.ArrayList;

public class DailySchedule {

    private ArrayList<MealPlan> plans;

    public DailySchedule() {

    } //attempt to load file

    public void showDay(){
        for (MealPlan plan : plans) {
            System.out.println(plan);
        }
    }

    public ArrayList<MealPlan> getPlans() {
        return plans;
    }
}
