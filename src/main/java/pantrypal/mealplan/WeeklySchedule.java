package pantrypal.mealplan;

/**
 * week view of daily schedules
 */

public class WeeklySchedule {
    private static final int NUMBER_OF_DAYS = 7;
    private DailySchedule[] weeklySchedule;

    public WeeklySchedule(PlanPresets planPresets) {
        this.weeklySchedule = new DailySchedule[NUMBER_OF_DAYS];
    }

    public void addDay(PlanPresets presets, int planIndex, int dayIndex){
        weeklySchedule[dayIndex].setPlan(presets, planIndex);
    }

    public void removePlan(int index){
        weeklySchedule[index].removePlan();
    }

    public void showWeek() {
        boolean isCompletelyEmpty = true;
        for (DailySchedule day : weeklySchedule) {
            if (day.planIsUsed()) {
                day.showDay();
                isCompletelyEmpty = false;
            }
        }
        if (isCompletelyEmpty) {
            throw new EmptyPlansException(); //indicate that no plans can be shown
        }
    }



}
