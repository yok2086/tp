package pantrypal.mealplan;

import java.util.ArrayList;

public class Calendar {
    String userName;
    int selectedPlan;
    //ArrayList<MealPlan> plans;
    ArrayList<MealPlan> planPresets;

    public Calendar(PlanPresets planPresets, String userName) {
        this.planPresets = planPresets.plans;
        this.userName = userName;
    }

    /*
     *
     * @param planIndex refers to the plan slot in a preset
     */

    /*
    public void AddPlanToCalendar(int planIndex){
        plans.add(planPresets.get(planIndex));
    }
    */

    /*
     *
     * @param planIndex refers to the plan in this calendar
     */

    /*
    public void RemovePlanFromCalendar(int planIndex){
        plans.remove(planIndex);
    }
    */

    public void startPlan(int selectedPlan) {
        this.selectedPlan = selectedPlan-1; //pad for input-to-index disparity
    }

    public void stopPlan(int selectedPlan) {
        this.selectedPlan = -1;
    }

    public void showPlan(){
        try {
            System.out.println(planPresets.get(selectedPlan));

            if (selectedPlan == -1) {
                throw new NullPointerException();
            }
            System.out.println(planPresets.get(selectedPlan));
        } catch(IndexOutOfBoundsException e){
            System.out.println("Invalid index entered");
        } catch (NullPointerException e){
            System.out.println("No plans selected");
        }
    }

}
