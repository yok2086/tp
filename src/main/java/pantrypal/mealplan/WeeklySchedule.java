package pantrypal.mealplan;

import java.util.ArrayList;

public class WeeklySchedule {

    private final ArrayList<ArrayList<MealPlan>> days = new ArrayList<>(7);
    ArrayList<MealPlan> planPresets;

    public WeeklySchedule(PlanPresets planPresets) {
        this.planPresets = planPresets.plans;

    }

    private String loadPlans(String filepath){
        StringBuilder str = new StringBuilder();

        return str.toString();
    }

    public void addPlan(int planIndex, int dayIndex) {
        if (planPresets.isEmpty()) {
            throw new NewPlanCreateException(); //prompt the user on adding a new plan to plan presets
        }
        days.get(dayIndex-1).add(planPresets.get(planIndex-1)); //pad for input-to-index disparity
    }

    public void removePlan(int removeIndex, int dayIndex) {
        if (days.get(dayIndex-1).isEmpty()) {
            throw new EmptyPlansException(); //indicate for no plans to remove
        }
        days.get(dayIndex-1).remove(removeIndex-1);
    }

    public void showWeek() {
        boolean isCompletelyEmpty = true;
        for (ArrayList<MealPlan> day : days) {
            if (day.isEmpty()) {
                continue;
            }
            for (MealPlan plan : day) {
                isCompletelyEmpty = false;
                System.out.println(plan);
            }
        }
        if (isCompletelyEmpty) {
            throw new EmptyPlansException(); //indicate that no plans can be shown
        }
    }

    public void showDay(int dayIndex) {
        if (days.get(dayIndex-1).isEmpty()) {
            throw new EmptyPlansException();
        }
        for (MealPlan plan : days.get(dayIndex-1)) {
            System.out.println(plan);
        }
    }



}
