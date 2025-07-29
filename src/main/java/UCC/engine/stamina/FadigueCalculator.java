package UCC.engine.stamina;

import UCC.core.model.Action;
import UCC.core.model.Fighter;
import UCC.core.model.PhysicalAttributes;

public class FadigueCalculator {

    public static int calculateMaxFadigue(Fighter fighter){
        PhysicalAttributes attr = fighter.getPhysicalAttr();
        int idealAge = 28;
        int age = fighter.getAge();
        double baseStamina =
                attr.getCardiovascularEndurance() * 0.4
                + attr.getAerobicEndurance() * 0.3
                + attr.getBalance() * 0.1
                + attr.getAgility() * 0.1
                + attr.getSpeed() * 0.1;
        double agePenalty = Math.max(0, Math.abs(age - idealAge) * 0.01);

        double weight = attr.getWeight();
        double height = attr.getHeight();
        double weightPenalty = Math.max(0, (weight - (height * 22)) * 0.005);

        double fadigue = baseStamina * (1.0 - agePenalty) * (1.0 - weightPenalty);
        return (int) Math.max(fadigue, 10);
    }
    public static int calculateFadigueConsumption(Action action, boolean hit){
        int baseConsumption = action.getBaseFadigueConsumption();
        if(hit){
            return baseConsumption;
        } else{
            return baseConsumption * 2;
        }
    }
}
