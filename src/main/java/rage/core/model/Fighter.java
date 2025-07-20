package rage.core.model;

import java.util.Random;

public class Fighter {
    private String name;
    private String nacionality;
    private int age;
    private double height;
    private double weight;
    private double performanceIndex;
    private String category;
    private int victories, defeats, ties;

    public void present(){
        System.out.println("-------------------------------------");
        System.out.println("IT'S TIME! We present the fighter " + this.getName());
        System.out.println("Directly from " + this.getNacionality());
        System.out.println("At " + this.getAge() + " years old and " + this.getHeight() + "m");
        System.out.println("Weighing " + this.getWeight() + "kg");
        System.out.println(this.getVictories() + " victories!");
        System.out.println(this.getTies() + " ties!");
        System.out.println(this.getDefeats() + " defeats!");
    }
    public void status(){
        System.out.println(this.getName() + " is in the " + this.getcategory() + " category");
        System.out.println("Won " + this.getVictories() + " times");
        System.out.println("Tied " + this.getTies() + " times");
        System.out.println("lost " + this.getDefeats() + " times");
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


    public Fighter(String name, String nacionality, int age, double height, double weight, int victories, int defeats, int ties) {
        this.setName(name);
        this.setNacionality(nacionality);
        this.setAge(age);
        this.setHeight(height);
        this.setWeight(weight);
        this.setVictories(victories);
        this.setDefeats(defeats);
        this.setTies(ties);
        this.setPerformanceIndex();
    }

    public String getName() {
        return name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getcategory() {
        return category;
    }

    public int getVictories() {
        return victories;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.setCategory();
    }

    private void setCategory() {
        if(this.getWeight() < 52.2){
            this.category = "Inválido";
        } else if(this.getWeight() <= 70.3){
            this.category = "Leve";
        } else if(this.getWeight() <= 83.9){
            this.category = "Médio";
        } else if(this.getWeight() <= 120.2){
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
        double PI = (this.getHeight() * 10) + (this.getWeight() * 0.3) + (this.getVictories() * 5) - (this.getDefeats() * 2) - (Math.abs(this.getAge() - 28) * 1.5);
        Random randomFactor = new Random();
        double finalResult = PI + (randomFactor.nextInt(50) - 15);
        this.performanceIndex = finalResult;
    }
}
