package pantrypal.mealplan;

public class Calendar {
    String month;
    int startDate;
    int endDate;
    MealPlan mealPlan;

    public Calendar(String month, int startDate, int duration, MealPlan mealPlan) {
        this.month = month;
        this.startDate = startDate;
        this.endDate = startDate+duration;
        this.mealPlan = mealPlan;
    }

}
