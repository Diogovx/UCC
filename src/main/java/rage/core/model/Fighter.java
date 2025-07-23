package rage.core.model;

import rage.engine.stamina.FadigueCalculator;

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
    private int fadigue;
    private int maxFadigue;
    private ArrayList<Action> strikes;

    public void present(){
        System.out.println("-------------------------------------");
        System.out.println("IT'S TIME! We present the fighter " + this.getName());
        System.out.println("Directly from " + this.getNationality());
        System.out.println("At " + this.getAge() + " years old and " + this.getPhysicalAttr().getHeight() + "m");
        System.out.println("Weighing " + this.getPhysicalAttr().getWeight() + "kg");
        System.out.println(this.getVictories() + " victories!");
        System.out.println(this.getTies() + " ties!");
        System.out.println(this.getDefeats() + " defeats!");
    }
    public void status(){
        System.out.println(this.getName() + " is in the " + this.getCategory() + " category");
        System.out.println("Won " + this.getVictories() + " times");
        System.out.println("Tied " + this.getTies() + " times");
        System.out.println("lost " + this.getDefeats() + " times");
        System.out.println("Max fadigue " + this.getMaxFadigue());
        System.out.println("\n");
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
        this.getStrikes().add(action);
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
        this.setMaxFadigue(FadigueCalculator.calculateMaxFadigue(this));
        this.strikes = new ArrayList<>();
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

    public int getFadigue() {
        return fadigue;
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

    public int getMaxFadigue() {
        return maxFadigue;
    }

    public ArrayList<Action> getStrikes() {
        return strikes;
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
            this.category = "Inválido";
        } else if(this.getPhysicalAttr().getWeight() <= 70.3){
            this.category = "Leve";
        } else if(this.getPhysicalAttr().getWeight() <= 83.9){
            this.category = "Médio";
        } else if(this.getPhysicalAttr().getWeight() <= 120.2){
            this.category = "Pesado";
        } else{
            this.category = "Inválido";
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

    public void setFadigue(int fadigue) {
        this.fadigue = fadigue;
    }

    public void setMaxFadigue(int maxFadigue) {
        this.maxFadigue = maxFadigue;
    }

    public void setStrikes(ArrayList<Action> strikes) {
        this.strikes = strikes;
    }
}
