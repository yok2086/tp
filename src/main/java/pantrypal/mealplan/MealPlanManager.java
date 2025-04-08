package pantrypal.mealplan;

import pantrypal.general.control.Ui;

import java.util.ArrayList;

public class MealPlanManager {
    private Plan[] weeklyPlans = new Plan[7]; //7 days in a week
    private ArrayList<Plan> planList = new ArrayList<>();


    public MealPlanManager() {}

    public void addPlan(String planName) {
        Plan plan = new Plan(planName);
        planList.add(plan);
    }

    public void removePlan(int planIndex) {
        Plan plan = planList.get(planIndex);
        planList.remove(plan);
        //Remove plan from Weekly Plan if it exists
        for (int i = 0; i < weeklyPlans.length; i++) {
            if (weeklyPlans[i] != null && weeklyPlans[i].equals(plan)) {
                weeklyPlans[i] = null;
            }
        }
    }

    public void addPlanToDay(int planIndex, Day day) {
        int dayIndex = day.ordinal();
        if (planIndex < 0 || planIndex >= planList.size()) {
            throw new IllegalArgumentException("Invalid plan index provided. Please enter a valid plan index.");
        }
        Plan plan = planList.get(planIndex);
        if (weeklyPlans[dayIndex] == null) {
            weeklyPlans[dayIndex] = plan;
        } else {
            throw new IllegalArgumentException("Plan already exists for this day.");
        }
    }

    public void removePlanFromDay(Day day) {
        int dayIndex = day.ordinal();
        if (weeklyPlans[dayIndex] != null) {
            weeklyPlans[dayIndex] = null;
            Ui.showMessage("Plan removed from " + day);
        } else {
            Ui.showMessage("There is no plan for " + day.name());
        }
    }

    public void viewPlanForDay(Day day) {
        int dayIndex = day.ordinal();
        if (weeklyPlans[dayIndex] != null) {
            Ui.showMessage("Plan for " + day.name() + ": " + weeklyPlans[dayIndex]);
        } else {
            Ui.showMessage("There is no plan for " + day.name());
        }
    }

    public void viewPlanForWeek() {
        Ui.printLine();
        for (int i = 0; i < weeklyPlans.length; i++) {
            if (weeklyPlans[i] != null) {
                System.out.println(Day.values()[i].name() + ": \n" + weeklyPlans[i].toString());
            } else {
                System.out.println("No plan for " + Day.values()[i].name());
            }
            Ui.printLine();
        }
    }

    public void viewPlanList() {
        if (planList.isEmpty()) {
            Ui.showMessage("No plans available.");
        } else {
            Ui.printLine();
            for (int i = 0; i < planList.size(); i++) {
                System.out.println((i + 1) + ": " + planList.get(i).getPlanName());
            }
            Ui.printLine();
        }
    }

    public void viewPlan(int planIndex) {
        if (planIndex < 0 || planIndex >= planList.size()) {
            Ui.showMessage("Invalid plan index.");
            return;
        }
        Plan plan = planList.get(planIndex);
        Ui.showMessage(plan.toString());
    }

    public boolean findPlan(String planName) {
        boolean found = false;
        for (Plan plan : planList) {
            if (plan.getPlanName().equalsIgnoreCase(planName)) {
                found = true;
                Ui.showMessage("Here are the plans that match your search:");
                Ui.showMessage((planList.indexOf(plan)+1) + //plus 1 for matching to user view
                        ": " + plan.getPlanName());
            }
        }
        return found;
    }

    public Plan[] getWeeklyPlans() {
        return weeklyPlans;
    }

    public ArrayList<Plan> getPlanList() {
        return planList;
    }

    public Plan getPlanForDay(Day day) {
        return weeklyPlans[day.ordinal()];
    }

    public Plan getPlan(int planIndex) {
        if (planIndex < 0 || planIndex >= planList.size()) {
            return null;
        }
        return planList.get(planIndex);
    }

    public Plan getPlanDetails(String planName) {
        for (Plan plan : planList) {
            if (plan.getPlanName().equalsIgnoreCase(planName)) {
                return plan;
            }
        }
        return null; // Plan not found
    }

    public int getPlanIndex(String planName) {
        for (int i = 0; i < planList.size(); i++) {
            if (planList.get(i).getPlanName().equalsIgnoreCase(planName)) {
                return i;
            }
        }
        return -1; // Plan not found
    }
}
