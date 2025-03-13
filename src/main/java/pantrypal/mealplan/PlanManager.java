package pantrypal.mealplan;

import java.util.ArrayList;

/**
 * Overarching handler for created meal plans and month-based assignment of meal plans
 * selected meal plan is for individual, non-calendar based view
 * calendar added meal plan is for added structure pertaining to the associated month
 */


public class PlanManager {
    private final static int PRESET_SIZE = 11; //padding element + 10 elements
    private final static int NUMBER_OF_MONTHS = 12;
    ArrayList<Calendar> calendar;
    ArrayList<MealPlan> planPresets;
    int selectedPlan = 0; //default null value

    public PlanManager() {
        calendar = new ArrayList<>();
        planPresets = new ArrayList<>();
        calendar.add(null);
        planPresets.add(null);
    }

    public void addToCalendar(String month, int startDay, MealPlan mealPlan) {
        calendar.add(new Calendar(month, startDay, mealPlan.getDuration(), mealPlan));
    }

    public void removeFromCalendar(int month){
        calendar.remove(month);
    }

    public void showCalendar(){
        for (Calendar calendar : calendar) {
            System.out.println(calendar);
        }
    }

    public void startPlan(int selectedPlan){
        this.selectedPlan = selectedPlan;
    }

    public void stopPlan(){
        selectedPlan = 0;
    }

    public void showPlan(){
        try {
            if (selectedPlan == 0) {
                throw new NullPointerException();
            }
            System.out.println(planPresets.get(selectedPlan));
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Invalid index entered");
        }
        catch (NullPointerException e){
            System.out.println("No plans selected");
        }
    }

    public void addPlan(int duration){
        planPresets.add(new MealPlan(duration));
    }

    public void viewPlans(){
        for (MealPlan plan : planPresets.subList(1, planPresets.size())) {
            System.out.println(plan);
        }
    }
}
