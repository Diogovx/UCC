package UCC.engine.combat;

import UCC.core.model.Action;
import UCC.core.model.Fighter;
import UCC.engine.visual.CommentaryEngine;
import UCC.ui.ConsoleFightListener;
import UCC.ui.ConsolePrinter;
import UCC.ui.FightEventListener;

import java.util.Random;

public class CombatEngine {
    private Fighter challenging;
    private Fighter challenged;
    private CombatLog log;
    private FightEventListener eventListener;

    public CombatEngine(Fighter challenging, Fighter challenger) {
        this.setChallenging(challenging);
        this.setChallenged(challenger);
        this.setLog(new CombatLog());
        this.setEventListener(new ConsoleFightListener());
    }

    public void startCombat(int maxRounds){
        int round = 1;
        boolean hasWinner = false;
        boolean hasTied = false;
        Fighter attacker;
        Fighter defender;
        Fighter aux;
        Fighter[] order = determineInitialOrder();
        Action currentAction;
        attacker = order[0];
        defender = order[1];

        while (!hasWinner && !hasTied){
            eventListener.onNewRound(round);

            currentAction = attacker.performAction(defender);
            this.getLog().registerAction(attacker, defender, round, currentAction);

            eventListener.showProgressBar("\n" + challenging.getName() + " current fatigue: ",  challenging.getFatigue(), challenging.getMaxFatigue(), 20);
            eventListener.showProgressBar(challenged.getName() + " current fatigue: ",  challenged.getFatigue(), challenged.getMaxFatigue(), 20);

            hasWinner = checkVictoryConditions();
            hasTied = checkTieConditions(round, maxRounds);

            aux = attacker;
            attacker = defender;
            defender = aux;
            round++;
            eventListener.onText();

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
        eventListener.onPrintWithDelay("\uD83C\uDFC6 " + winner + " wins the fight!", 200);
        eventListener.onComment(CommentaryEngine.CommentType.VICTORY);
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

    public CombatLog getLog() {
        return log;
    }

    public void setLog(CombatLog log) {
        this.log = log;
    }

    public FightEventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(FightEventListener eventListener) {
        this.eventListener = eventListener;
    }
}
