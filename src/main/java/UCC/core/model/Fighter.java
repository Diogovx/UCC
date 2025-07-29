package UCC.core.model;

import UCC.engine.stamina.FadigueCalculator;
import UCC.ui.ConsolePrinter;

import java.util.ArrayList;
import java.util.Random;

public class Fighter {
    private String name;
    private String nationality;
    private int age;
    private PhysicalAttributes physicalAttr;
    private double performanceIndex;
    private String category;
    private int victories, defeats, ties;
    private int fatigue;
    private int maxFatigue;
    private ArrayList<Action> actions;
    private Action lastAction;

    public void present(){
        ConsolePrinter.divider();
        ConsolePrinter.typeEffect("IT'S TIME! We present the fighter " + this.getName(), 50);
        ConsolePrinter.printWithDelay("Directly from " + this.getNationality(), 1000);
        ConsolePrinter.printWithDelay("At " + this.getAge() + " years old and " + this.getPhysicalAttr().getHeight() + "m", 1000);
        ConsolePrinter.printWithDelay("Weighing " + this.getPhysicalAttr().getWeight() + "kg", 1000);
        ConsolePrinter.printWithDelay(this.getVictories() + " victories!", 1000);
        ConsolePrinter.printWithDelay(this.getTies() + " ties!", 1000);
        ConsolePrinter.printWithDelay(this.getDefeats() + " defeats!", 1000);
    }
    public void status(){
        System.out.println();
        ConsolePrinter.divider();
        System.out.println(this.getName() + " is in the " + this.getCategory() + " category");
        System.out.println("Won " + this.getVictories() + " times");
        System.out.println("Tied " + this.getTies() + " times");
        System.out.println("lost " + this.getDefeats() + " times");
        System.out.println("Max fadigue " + this.getMaxFatigue());
    }
    public void winFight(){
        this.setVictories(this.getVictories() + 1);
    }
    public void loseFight(){
        this.setDefeats(this.getDefeats() + 1);
    }
    public void tieFight(){
        this.setTies(this.getTies() + 1);
    }

    public void addAction(Action action){
        this.getActions().add(action);
    }

    public void performAction(Action action, Fighter target) {
        boolean hit = action.checkAccuracy();
        this.setFatigue(this.getFatigue() + FadigueCalculator.calculateFadigueConsumption(action, hit));
        switch (action.getType()) {
            case STRIKE, GRAPPLE, COUNTER -> {
                if (hit && target.getLastAction().getType() != Action.ActionType.DEFENSE) {
                    ConsolePrinter.printWithDelay(this.getName() + " performed " + action.getName() + " and hit", 1000);
                    target.receiveHit(action);

                } else {
                    ConsolePrinter.printWithDelay(this.getName() + " performed " + action.getName() + " but missed", 1000);
                }
            }
            case DEFENSE -> {
                ConsolePrinter.printWithDelay(this.getName() + " used " + action.getName(), 1000);
            }

        }
        this.setLastAction(action);
    }

    public void receiveHit(Action action){
        if(this.lastAction.getType().equals(Action.ActionType.DEFENSE)){
            System.out.println(this.getName() + " blocks the attack!");
        } else {
            System.out.println(this.getName() + " was hit by " + action.getName());
            this.setFatigue(this.getFatigue() + action.getBaseFadigueConsumption() / 2);
        }

    }

    public Fighter(String name, String nacionality, int age, PhysicalAttributes physicalAttr, int victories, int defeats, int ties) {
        this.setName(name);
        this.setNationality(nacionality);
        this.setAge(age);
        this.setPhysicalAttr(physicalAttr);
        this.setVictories(victories);
        this.setDefeats(defeats);
        this.setTies(ties);
        this.setPerformanceIndex();
        this.setMaxFatigue(FadigueCalculator.calculateMaxFadigue(this));
        this.actions = new ArrayList<>();
        this.setLastAction(Action.neutralAction());
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public PhysicalAttributes getPhysicalAttr() {
        return physicalAttr;
    }

    public String getCategory() {
        return category;
    }

    public int getVictories() {
        return victories;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getTies() {
        return ties;
    }

    public double getPerformanceIndex() {
        return performanceIndex;
    }

    public int getMaxFatigue() {
        return maxFatigue;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhysicalAttr(PhysicalAttributes physicalAttr) {
        this.physicalAttr = physicalAttr;
        setCategory();
    }

    private void setCategory() {
        if(this.getPhysicalAttr().getWeight() < 52.2){
            this.category = "Invalid";
        } else if(this.getPhysicalAttr().getWeight() <= 70.3){
            this.category = "Lightweight";
        } else if(this.getPhysicalAttr().getWeight() <= 83.9){
            this.category = "Middleweight";
        } else if(this.getPhysicalAttr().getWeight() <= 120.2){
            this.category = "Heavyweight";
        } else{
            this.category = "Invalid";
        }
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    private void setPerformanceIndex() {
        double PI = (this.getPhysicalAttr().getHeight() * 10) + (this.getPhysicalAttr().getWeight() * 0.3) + (this.getVictories() * 5) - (this.getDefeats() * 2) - (Math.abs(this.getAge() - 28) * 1.5);
        Random randomFactor = new Random();
        this.performanceIndex = PI + (randomFactor.nextInt(50) - 15);
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void setMaxFatigue(int maxFatigue) {
        this.maxFatigue = maxFatigue;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }
}
