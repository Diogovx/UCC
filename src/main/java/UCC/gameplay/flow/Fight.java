package UCC.gameplay.flow;

import UCC.core.model.Fighter;
import UCC.engine.combat.CombatEngine;
import UCC.ui.ConsolePrinter;

public class Fight {
    private Fighter challenged;
    private Fighter challenging;
    private boolean approved;
    private CombatEngine engine;

    public void scheduleFight(){
        if(this.getChallenged().getCategory().equals(this.getChallenging().getCategory()) && getChallenged() != getChallenging()){
            this.setApproved(true);
        } else {
            this.setApproved(false);
            this.setChallenged(null);
            this.setChallenging(null);
        }

    }

    public void startSimulation(){
        this.presentFighters();
        if(this.isApproved()){
            ConsolePrinter.printWithDelay("READY?", 1200);
            System.out.println("FIGHT!");
            System.out.println("\n");
            this.engine.startCombat(40);
        } else {
            System.out.println("The fight cannot happen!");
        }
    }

    private void presentFighters(){
        System.out.println("##### CHALLENGER #####");
        this.getChallenging().present();
        System.out.println("##### CHALLENGED #####");
        this.getChallenged().present();
    }

    public Fight(Fighter challenging, Fighter challenged) {
        this.setChallenging(challenging);
        this.setChallenged(challenged);
        this.setEngine(new CombatEngine(challenging, challenged));
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
}
