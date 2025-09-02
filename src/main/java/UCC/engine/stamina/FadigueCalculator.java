package UCC.engine.stamina;

import UCC.core.enums.FatiguePenaltyLevel;
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
    public static void applyFadiguePenalties(Fighter fighter){
        PhysicalAttributes attr = fighter.getPhysicalAttr();
        int max = fighter.getMaxFatigue();
        int current = fighter.getFatigue();
        if(!fighter.getFatiguePenaltiesApplied().contains(FatiguePenaltyLevel.HEAVY_90) && current >= max * 0.9){
            fighter.getFatiguePenaltiesApplied().add(FatiguePenaltyLevel.HEAVY_90);
            attr.setBalance(attr.getBalance() * 0.6);
            attr.setExplosivePower(attr.getExplosivePower() * 0.6);
            attr.setAgility(attr.getAgility() * 0.6);
            System.out.println("\uD83D\uDD25 " + fighter.getName() + " is severely fatigued! Stats reduced.");
        } else if (!fighter.getFatiguePenaltiesApplied().contains(FatiguePenaltyLevel.MILD_60) && current >= max * 0.6) {
            fighter.getFatiguePenaltiesApplied().add(FatiguePenaltyLevel.MILD_60);
            attr.setBalance(attr.getBalance() * 0.9);
            attr.setExplosivePower(attr.getExplosivePower() * 0.9);
            attr.setAgility(attr.getAgility() * 0.9);
            System.out.println("⚠\uFE0F " + fighter.getName() + " is getting tired. Slight penalty applied.");
        }
    }
}
