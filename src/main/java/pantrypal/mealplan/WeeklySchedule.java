package pantrypal.mealplan;

import java.util.ArrayList;

public class WeeklySchedule {

    private final ArrayList<DailySchedule> days = new ArrayList<>(7);
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
        days.get(dayIndex-1).getPlans().add(planPresets.get(planIndex-1)); //pad for input-to-index disparity
    }

    public void removePlan(int removeIndex, int dayIndex) {
        if (days.get(dayIndex-1).getPlans().isEmpty()) {
            throw new EmptyPlansException(); //indicate for no plans to remove
        }
        days.get(dayIndex-1).getPlans().remove(removeIndex-1);
    }

    public void showWeek() {
        boolean isCompletelyEmpty = true;
        for (DailySchedule day : days) {
            if (day.getPlans().isEmpty()) {
                continue;
            }
            day.showDay();
            isCompletelyEmpty = false;
        }
        if (isCompletelyEmpty) {
            throw new EmptyPlansException(); //indicate that no plans can be shown
        }
    }



}
