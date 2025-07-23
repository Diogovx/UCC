package rage.core.model;

import java.util.Random;

public class Action {
    private String name;
    private int baseFadigueConsumption;
    private int baseAccuracy;

    public boolean checkAccuracy(){
        Random randomFactor = new Random();
        int randomNumber = randomFactor.nextInt(10);
        if(randomNumber <= this.getBaseAccuracy()){
            return true;
        }else{
            return false;
        }
    }

    public Action(String name, int baseFadigueConsumption, int baseAccuracy) {
        this.setName(name);
        this.setBaseFadigueConsumption(baseFadigueConsumption);
        this.setBaseAccuracy(baseAccuracy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseAccuracy() {
        return baseAccuracy;
    }

    public int getBaseFadigueConsumption() {
        return baseFadigueConsumption;
    }

    public void setBaseFadigueConsumption(int baseFadigueConsumption) {
        this.baseFadigueConsumption = baseFadigueConsumption;
    }

    public void setBaseAccuracy(int baseAccuracy) {
        this.baseAccuracy = baseAccuracy;
    }
}
