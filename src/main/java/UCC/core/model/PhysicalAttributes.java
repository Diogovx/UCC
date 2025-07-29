package UCC.core.model;

public class PhysicalAttributes {
    private double height;
    private double weight;
    private double agility;
    private int cardiovascularEndurance;
    private int aerobicEndurance;
    private double speed;
    private double explosivePower;
    private double reactionTime;
    private double rangeOfMotion;
    private double balance;
    private double armsReach;
    private double legsReach;

    public PhysicalAttributes(double height, double weight, double agility, int cardiovascularEndurance, int aerobicEndurance, double speed, double explosivePower, double reactionTime, double rangeOfMotion, double balance, double armsReach, double legsReach) {
        this.height = height;
        this.weight = weight;
        this.agility = agility;
        this.cardiovascularEndurance = cardiovascularEndurance;
        this.aerobicEndurance = aerobicEndurance;
        this.speed = speed;
        this.explosivePower = explosivePower;
        this.reactionTime = reactionTime;
        this.rangeOfMotion = rangeOfMotion;
        this.balance = balance;
        this.armsReach = armsReach;
        this.legsReach = legsReach;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setCardiovascularEndurance(int cardiovascularEndurance) {
        this.cardiovascularEndurance = cardiovascularEndurance;
    }

    public void setAerobicEndurance(int aerobicEndurance) {
        this.aerobicEndurance = aerobicEndurance;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setExplosivePower(double explosivePower) {
        this.explosivePower = explosivePower;
    }

    public void setReactionTime(double reactionTime) {
        this.reactionTime = reactionTime;
    }

    public void setRangeOfMotion(double rangeOfMotion) {
        this.rangeOfMotion = rangeOfMotion;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setArmsReach(double armsReach) {
        this.armsReach = armsReach;
    }

    public void setLegsReach(double legsReach) {
        this.legsReach = legsReach;
    }


    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getAgility() {
        return agility;
    }

    public int getCardiovascularEndurance() {
        return cardiovascularEndurance;
    }

    public int getAerobicEndurance() {
        return aerobicEndurance;
    }

    public double getSpeed() {
        return speed;
    }

    public double getExplosivePower() {
        return explosivePower;
    }

    public double getReactionTime() {
        return reactionTime;
    }

    public double getRangeOfMotion() {
        return rangeOfMotion;
    }

    public double getBalance() {
        return balance;
    }

    public double getArmsReach() {
        return armsReach;
    }

    public double getLegsReach() {
        return legsReach;
    }

}
