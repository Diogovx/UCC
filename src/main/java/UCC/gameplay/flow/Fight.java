package UCC.gameplay.flow;

import UCC.core.enums.FightSituation;
import UCC.core.model.Fighter;
import UCC.engine.combat.CombatEngine;
import UCC.engine.combat.CombatResult;
import UCC.ui.ConsoleFightListener;
import UCC.ui.FightEventListener;

public class Fight {
    private Fighter challenged;
    private Fighter challenging;
    private boolean approved;
    private CombatEngine engine;
    private FightEventListener eventListener;
    private CombatResult combatResult;


    public void scheduleFight(){
        if(this.getChallenged().getCategory().equals(this.getChallenging().getCategory()) && getChallenged() != getChallenging()){
            this.setApproved(true);
        } else {
            this.setApproved(false);
            this.setChallenged(null);
            this.setChallenging(null);
        }

    }

    public boolean startSimulation(){
        this.presentFighters();
        if(this.isApproved()){
            eventListener.onPrintWithDelay("READY?", 1200);
            eventListener.onText("FIGHT!");
            eventListener.onText("\n");
            this.setCombatResult(this.engine.startCombat(40));
            return true;
        } else {
            eventListener.onText("The fight cannot happen!");
            return false;
        }
    }

    private void presentFighters(){
        eventListener.onText("##### CHALLENGER #####");
        eventListener.showDivider();
        this.present(this.getChallenging());
        eventListener.onText("##### CHALLENGED #####");
        eventListener.showDivider();
        this.present(this.getChallenged());
    }
    public void exportResults(){}

    public void present(Fighter fighter){
        eventListener.onTypeEffect("IT'S TIME! We present the fighter " + fighter.getName(), 50);
        eventListener.onPrintWithDelay("Directly from " + fighter.getNationality(), 1000);
        eventListener.onPrintWithDelay("At " + fighter.getAge() + " years old and " + fighter.getPhysicalAttr().getHeight() + "m", 1000);
        eventListener.onPrintWithDelay("Weighing " + fighter.getPhysicalAttr().getWeight() + "kg", 1000);
        eventListener.onPrintWithDelay(fighter.getVictories() + " victories!", 1000);
        eventListener.onPrintWithDelay(fighter.getTies() + " ties!", 1000);
        eventListener.onPrintWithDelay(fighter.getDefeats() + " defeats!", 1000);
        eventListener.onTypeEffect(fighter.getEntryPhrase(), 100);
    }
    public void status(Fighter fighter){
        eventListener.onText();
        eventListener.showDivider();
        eventListener.onText(fighter.getName() + " is in the " + fighter.getCategory() + " category");
        eventListener.onText("Won " + fighter.getVictories() + " times");
        eventListener.onText("Tied " + fighter.getTies() + " times");
        eventListener.onText("lost " + fighter.getDefeats() + " times");
        eventListener.onText("Max fadigue " + fighter.getMaxFatigue());
    }

    public Fight(Fighter challenging, Fighter challenged) {
        this.setChallenging(challenging);
        this.setChallenged(challenged);
        this.setEngine(new CombatEngine(challenging, challenged));
        this.setEventListener(new ConsoleFightListener());
        this.setCombatResult(new CombatResult());
    }

    public Fighter getChallenged() {
        return challenged;
    }

    public Fighter getChallenging() {
        return challenging;
    }

    public boolean isApproved() {
        return approved;
    }

    public CombatEngine getEngine() {
        return engine;
    }

    public void setChallenged(Fighter challenged) {
        this.challenged = challenged;
    }

    public void setChallenging(Fighter challenging) {
        this.challenging = challenging;
    }


    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setEngine(CombatEngine engine) {
        this.engine = engine;
    }

    public FightEventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(FightEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public CombatResult getCombatResult() {
        return combatResult;
    }

    public void setCombatResult(CombatResult combatResult) {
        this.combatResult = combatResult;
    }
}
