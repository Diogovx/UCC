package rage.core.model;

import java.util.Random;

public class Action {
    private String name;
    private int baseFadigueConsumption;
    private int baseAccuracy;
    private ActionType type;

    public enum ActionType {
        STRIKE, GRAPPLE, DEFENSE
    }

    public boolean checkAccuracy(){
        Random randomFactor = new Random();
        int randomNumber = randomFactor.nextInt(100);
        if(randomNumber <= this.getBaseAccuracy()){
            return true;
        }else{
            return false;
        }
    }

    public Action(String name, int baseFadigueConsumption, int baseAccuracy, ActionType type) {
        this.setName(name);
        this.setBaseFadigueConsumption(baseFadigueConsumption);
        this.setBaseAccuracy(baseAccuracy);
        this.setType(type);
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

    public ActionType getType() {
        return type;
    }

    public void setBaseFadigueConsumption(int baseFadigueConsumption) {
        this.baseFadigueConsumption = baseFadigueConsumption;
    }

    public void setBaseAccuracy(int baseAccuracy) {
        this.baseAccuracy = baseAccuracy;
    }

    public void setType(ActionType type) {
        this.type = type;
    }
}
