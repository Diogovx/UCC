package rage.gameplay.flow;

import rage.core.model.Fighter;

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
            System.out.println("READY?");
            System.out.println("FIGHT!");
            System.out.println("\n");
            this.toFight();
        } else {
            System.out.println("The fight cannot happen!");
        }
    }

    public void toFight(){

        int round = 0;
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
            System.out.println(">> Round " + round);

            attacker.performAction(attacker.getActions().get(ramdomAction.nextInt(attacker.getActions().size())), defender);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\n" + attacker.getName() + " fadigue current fadigue: " + attacker.getFadigue());
            System.out.println(defender.getName() + " fadigue current fadigue: " + defender.getFadigue());

            if(attacker.getFadigue() >= attacker.getMaxFadigue()){
                hasWinner = true;
                System.out.println(defender.getName() + " wins!");
                defender.winFight();
                attacker.loseFight();
            } else if (defender.getFadigue() >= defender.getMaxFadigue()) {
                hasWinner = true;
                System.out.println(attacker.getName() + " wins!");
                attacker.winFight();
                defender.loseFight();
            }
            if (round == 30) {
                hasTied = true;
                System.out.println("Tied");
                attacker.tieFight();
                defender.tieFight();
            }
            aux = attacker;
            attacker = defender;
            defender = aux;
            round++;
        }
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
