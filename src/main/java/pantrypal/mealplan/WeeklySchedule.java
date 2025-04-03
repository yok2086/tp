package pantrypal.mealplan;

import pantrypal.inventory.IngredientInventory;

import java.util.Scanner;

/**
 * week view of daily schedules
 */

public class WeeklySchedule {
    private static final int NUMBER_OF_DAYS = 7;
    private DailySchedule[] days;
    private boolean[] usedDays = new boolean[7];

    public WeeklySchedule(PlanPresets planPresets) {
        this.days = new DailySchedule[NUMBER_OF_DAYS];
    }

    public void addDay(PlanPresets presets, int planIndex, int dayIndex){
        days[dayIndex].setPlan(presets, planIndex);
        usedDays[dayIndex] = true;
    }

    public void removeDay(int dayIndex){
        usedDays[dayIndex] = false;
        days[dayIndex].removePlan();
    }

    public void executeDay(IngredientInventory inventory, int dayIndex){
        DailySchedule day = days[dayIndex];
        if (!day.planIsExecuted() && day.planIsUsed()) {
            day.executePlan(inventory);
        } else {
            System.out.println("Day " + dayIndex + " already executed");
        }
    }

    public void editDay(int dayIndex, Scanner in) {
        days[dayIndex].editPlan(in);
    }

    public void viewDay(int dayIndex) {
        days[dayIndex].showDay();
    }

    public void showWeek() {
        int counter = 1;
        boolean isCompletelyEmpty = true;
        for (DailySchedule day : days) {
            if (day.planIsUsed()) {
                System.out.println(counter + ": " + day.getSelectedPlanName());
                isCompletelyEmpty = false;
            }
            counter++;
        }
        if (isCompletelyEmpty) {
            throw new EmptyPlansException(); //indicate that no plans can be shown
        }
    }

    public boolean[] getUsedDays() {
        return usedDays;
    }



}
