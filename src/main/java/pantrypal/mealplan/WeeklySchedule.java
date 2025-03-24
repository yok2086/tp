package pantrypal.mealplan;

import java.util.ArrayList;

/**
 * collection of daily schedules
 */

public class WeeklySchedule {

    PlanPresets planPresets;
    private final ArrayList<DailySchedule> days = new ArrayList<>(7);


    public WeeklySchedule(PlanPresets planPresets) {
        this.planPresets = planPresets;
    }


    public void addPlan(int planIndex, int dayIndex) {
        if (planPresets.plans.isEmpty()) {
            throw new NewPlanCreateException(); //prompt the user on adding a new plan to plan presets
        }
        days.get(dayIndex-1).addPlan(planIndex); //pad for input-to-index disparity
    }

    public void removePlan(int removeIndex, int dayIndex) {
        if (days.get(dayIndex-1).isEmpty()) {
            throw new EmptyPlansException(); //indicate for no plans to remove
        }
        days.get(dayIndex-1).removePlan(removeIndex);
    }

    public void showWeek() {
        boolean isCompletelyEmpty = true;
        for (DailySchedule day : days) {
            if (day.isEmpty()) {
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
