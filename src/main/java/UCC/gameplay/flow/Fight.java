package UCC.gameplay.flow;

import UCC.core.model.Fighter;
import UCC.engine.visual.CommentaryEngine;
import UCC.ui.ConsolePrinter;

import java.util.Random;

public class Fight {
    private Fighter challenged;
    private Fighter challeging;
    private int rounds;
    private boolean approved;

    public void scheduleFight(Fighter l1, Fighter l2){
        if(l1.getCategory().equals(l2.getCategory()) && l1 != l2){
            this.setApproved(true);
            this.setChallenged(l1);
            this.setChalleging(l2);
        } else {
            this.setApproved(false);
            this.setChallenged(null);
            this.setChalleging(null);
        }

    }

    public void startSimulation(){
        System.out.println("##### CHALLENGER #####");
        this.getChalleging().present();
        System.out.println("##### CHALLENGED #####");
        this.getChallenged().present();

        if(this.isApproved()){
            ConsolePrinter.printWithDelay("READY?", 1200);
            System.out.println("FIGHT!");
            System.out.println("\n");
            this.toFight();
        } else {
            System.out.println("The fight cannot happen!");
        }
    }

    public void toFight(){

        int round = 1;
        boolean hasWinner = false;
        boolean hasTied = false;
        Fighter attacker, defender, aux;
        Random ramdomAction = new Random();
        if(this.getChallenged().getPhysicalAttr().getSpeed() > this.getChalleging().getPhysicalAttr().getSpeed()){
            attacker = this.getChallenged();
            defender = this.getChalleging();
        } else {
            attacker = this.getChalleging();
            defender = this.getChallenged();
        }
        while (!hasWinner && !hasTied){
            ConsolePrinter.roundBanner(round);

            attacker.performAction(attacker.getActions().get(ramdomAction.nextInt(attacker.getActions().size())), defender);

            System.out.println("\n" + this.getChalleging().getName() + " current fatigue: " + this.getChalleging().getFatigue());
            System.out.println(this.getChallenged().getName() + " current fatigue: " + this.getChallenged().getFatigue());

            if(attacker.getFatigue() >= attacker.getMaxFatigue()){
                hasWinner = true;
                this.declareWinner(defender.getName());
                defender.winFight();
                attacker.loseFight();
                attacker.status();
                defender.status();
            } else if (defender.getFatigue() >= defender.getMaxFatigue()) {
                hasWinner = true;
                this.declareWinner(attacker.getName());
                attacker.winFight();
                defender.loseFight();
                attacker.status();
                defender.status();
            }
            if (round == 40) {
                hasTied = true;
                attacker.tieFight();
                defender.tieFight();
                this.declareTie();
                attacker.status();
                defender.status();
            }
            aux = attacker;
            attacker = defender;
            defender = aux;
            round++;
            System.out.println();

        }
    }

    public void declareWinner(String winner){
        ConsolePrinter.typeEffect("\uD83C\uDFC6 " + winner + " wins the fight!", 200);
        ConsolePrinter.printWithDelay(CommentaryEngine.getComment(CommentaryEngine.CommentType.VICTORY), 800);
    }
    public void declareTie(){
        ConsolePrinter.typeEffect("Tied", 250);
    }

    public Fighter getChallenged() {
        return challenged;
    }

    public Fighter getChalleging() {
        return challeging;
    }

    public int getRounds() {
        return rounds;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setChallenged(Fighter challenged) {
        this.challenged = challenged;
    }

    public void setChalleging(Fighter challeging) {
        this.challeging = challeging;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
