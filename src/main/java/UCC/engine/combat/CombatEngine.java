package UCC.engine.combat;

import UCC.core.model.Action;
import UCC.core.model.Fighter;
import UCC.engine.visual.CommentaryEngine;
import UCC.ui.ConsolePrinter;

import java.util.Random;

public class CombatEngine {
    private Random ramdomAction = new Random();
    private Fighter challenging;
    private Fighter challenged;
    private CombatLog combatLog;

    public CombatEngine(Fighter challenging, Fighter challenger) {
        this.setChallenging(challenging);
        this.setChallenged(challenger);
        this.setCombatLog(new CombatLog());
    }

    public void toFight(Fighter challenging, Fighter challenged, int maxRounds){
        int round = 1;
        boolean hasWinner = false;
        boolean hasTied = false;
        Fighter attacker, defender, aux;
        Fighter[] order = determineInitialOrder();
        Action currentAction;
        attacker = order[0];
        defender = order[1];

        while (!hasWinner && !hasTied){
            ConsolePrinter.roundBanner(round);

            currentAction = attacker.performAction(defender);
            this.getCombatLog().registerAction(attacker, defender, round, currentAction);

            System.out.println("\n" + challenging.getName() + " current fatigue: " + ConsolePrinter.progressBar(challenging.getFatigue(), challenging.getMaxFatigue(), 20));
            System.out.println(challenged.getName() + " current fatigue: " + ConsolePrinter.progressBar(challenged.getFatigue(), challenged.getMaxFatigue(), 20));

            hasWinner = checkVictoryConditions();
            hasTied = checkTieConditions(round, maxRounds);

            aux = attacker;
            attacker = defender;
            defender = aux;
            round++;
            System.out.println();

        }
    }

    private Fighter[] determineInitialOrder() {
        if(this.getChallenged().getPhysicalAttr().getSpeed() > this.getChallenging().getPhysicalAttr().getSpeed()){
            return new Fighter[] {this.getChallenged(), this.getChallenging()};
        } else if(this.getChallenged().getPhysicalAttr().getSpeed() < this.getChallenging().getPhysicalAttr().getSpeed()){
            return new Fighter[] {this.getChallenging(), this.getChallenged()};
        } else {
            return new Random().nextBoolean() ? new Fighter[]{this.getChallenging(), this.getChallenged()} : new Fighter[]{this.getChallenged(), this.getChallenging()};
        }
    }

    private boolean checkVictoryConditions(){
        if(this.getChallenging().getFatigue() >= this.getChallenging().getMaxFatigue()){
            declareWinner(this.getChallenged().getName());
            this.getChallenged().winFight();
            this.getChallenging().loseFight();
            return true;
        } else if (this.getChallenged().getFatigue() >= this.getChallenged().getMaxFatigue()) {
            declareWinner(this.getChallenging().getName());
            this.getChallenging().winFight();
            this.getChallenged().loseFight();
            return true;
        } else {
            return false;
        }
    }
    private boolean checkTieConditions(int round, int maxRounds){
        if (round == maxRounds) {
            this.getChallenging().tieFight();
            this.getChallenged().tieFight();
            declareTie();
            return true;
        } else{
            return false;
        }
    }

    private void declareWinner(String winner){
        ConsolePrinter.typeEffect("\uD83C\uDFC6 " + winner + " wins the fight!", 200);
        ConsolePrinter.printWithDelay(CommentaryEngine.getComment(CommentaryEngine.CommentType.VICTORY), 800);
    }
    private void declareTie(){
        ConsolePrinter.typeEffect("Tied", 250);
    }

    public Fighter getChallenging() {
        return challenging;
    }

    public void setChallenging(Fighter challenging) {
        this.challenging = challenging;
    }

    public Fighter getChallenged() {
        return challenged;
    }

    public void setChallenged(Fighter challenged) {
        this.challenged = challenged;
    }

    public CombatLog getCombatLog() {
        return combatLog;
    }

    public void setCombatLog(CombatLog combatLog) {
        this.combatLog = combatLog;
    }
}
