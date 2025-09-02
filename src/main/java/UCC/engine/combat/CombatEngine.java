package UCC.engine.combat;

import UCC.core.enums.ActionType;
import UCC.core.enums.CommentType;
import UCC.core.enums.FightSituation;
import UCC.core.enums.FighterStance;
import UCC.core.model.Action;
import UCC.core.model.Fighter;
import UCC.engine.stamina.FadigueCalculator;
import UCC.ui.ConsolePrinter;
import UCC.ui.FightEventListener;

import java.util.Random;

public class CombatEngine {
    private Fighter challenging;
    private Fighter challenged;
    private Fighter winner;
    private CombatLog log;
    private CombatResult result;
    private FightEventListener eventListener;
    private ActionResolver actionResolver;

    public CombatEngine(Fighter challenging, Fighter challenger, FightEventListener eventListener) {
        this.setChallenging(challenging);
        this.setChallenged(challenger);
        this.setResult(new CombatResult());
        this.setEventListener(eventListener);
        this.setActionResolver(new ActionResolver(eventListener));
    }

    public CombatResult startCombat(int maxRounds){
        int round = 1;
        FightSituation situation = null;
        Fighter[] fighters = determineInitialOrder();

        while (situation == null){
            for(int i = 0; i < 2; i++){
                eventListener.onNewRound(round);
                runRound(fighters[0], fighters[1], round);
                situation = checkVictoryConditions(round, maxRounds);
                fighters = alternateTurn(fighters);
            }
            round++;
        }


        this.getResult().setLastRound(round);
        this.getResult().setSituation(situation);
        this.getResult().setWinner(this.getWinner());
        return this.getResult();
    }
    private void runRound(Fighter attacker, Fighter defender, int round){
        this.setLog(actionResolver.performAction(attacker, defender, round));


        this.getLog().registerAction();


        eventListener.showProgressBar("\n" + challenging.getName() + " current fatigue: ",  challenging.getFatigue(), challenging.getMaxFatigue(), 20);
        eventListener.showProgressBar(challenged.getName() + " current fatigue: ",  challenged.getFatigue(), challenged.getMaxFatigue(), 20);

        eventListener.onText();
    }
    public Fighter[] alternateTurn(Fighter[] fighters){
        Fighter backup;

        backup = fighters[0];
        fighters[0] = fighters[1];
        fighters[1] = backup;

        return fighters;
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



    private FightSituation checkVictoryConditions(int round, int maxRound){
        if (round == maxRound) {
            this.getChallenging().tieFight();
            this.getChallenged().tieFight();
            this.setWinner(null);
            declareTie();
            return FightSituation.TIE;
        }
        if(this.getChallenging().getFatigue() >= this.getChallenging().getMaxFatigue()){
            declareWinner(this.getChallenged().getName());
            this.getChallenged().winFight();
            this.setWinner(this.getChallenged());
            this.getChallenging().loseFight();
            return FightSituation.VICTORY;
        } else if (this.getChallenged().getFatigue() >= this.getChallenged().getMaxFatigue()) {
            declareWinner(this.getChallenging().getName());
            this.getChallenging().winFight();
            this.setWinner(this.getChallenging());
            this.getChallenged().loseFight();
            return FightSituation.VICTORY;
        }
        return null;
    }

    private void declareWinner(String winner){
        eventListener.onPrintWithDelay("\uD83C\uDFC6 " + winner + " wins the fight!", 200);
        eventListener.onComment(CommentType.VICTORY);
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

    public CombatResult getResult() {
        return result;
    }

    public void setResult(CombatResult result) {
        this.result = result;
    }

    public Fighter getWinner() {
        return winner;
    }

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    public ActionResolver getActionResolver() {
        return actionResolver;
    }

    public void setActionResolver(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }
}

class ActionResolver{
    private FightEventListener eventListener;

    public CombatLog performAction(Fighter caster, Fighter target, int round) {
        Random randomFactor = new Random();
        Action action = caster.getActions().get(randomFactor.nextInt(caster.getActions().size()));
        boolean hit = checkAccuracy(action);
        hitExecution(action, caster, target, hit);
        apllyFatigueConsumption(caster, action, hit);
        caster.setLastAction(action);
        FadigueCalculator.applyFadiguePenalties(caster);

        return new CombatLog(caster, target, round, action, hit);
    }

    public boolean checkAccuracy(Action action){
        Random randomFactor = new Random();
        int randomNumber = randomFactor.nextInt(100);
        if(randomNumber <= action.getBaseAccuracy()){
            return true;
        }else{
            return false;
        }
    }

    public ActionType hitExecution(Action action, Fighter caster, Fighter target, boolean hit){
        switch (action.getType()) {
            case STRIKE, GRAPPLE, COUNTER -> {
                caster.setStance(FighterStance.HITTING);
                if (hit && target.getStance() != FighterStance.BLOCKING) {
                    eventListener.onPrintWithDelay(caster.getName() + " performed " + action.getName() + " and hit", 1000);
                    eventListener.onComment(CommentType.HIT);
                    receiveHit(action, target);
                    caster.setStance(FighterStance.NEUTRAL);
                } else {
                    caster.setStance(FighterStance.NEUTRAL);
                    target.setStance(FighterStance.NEUTRAL);
                    eventListener.onPrintWithDelay(caster.getName() + " performed " + action.getName() + " but missed", 1000);
                    eventListener.onComment(CommentType.BLOCKED);
                }
            }
            case DEFENSE -> {
                caster.setStance(FighterStance.BLOCKING);
                eventListener.onPrintWithDelay(caster.getName() + " used " + action.getName(), 1000);
            }
        }
        return action.getType();
    }
    public boolean receiveHit(Action action, Fighter target){
        if(target.getLastAction().getType().equals(ActionType.DEFENSE)){
            eventListener.onText(target.getName() + " blocks the attack!");
            return false;
        } else {
            eventListener.onText(target.getName() + " was hit by " + action.getName());
            target.setFatigue(target.getFatigue() + action.getBaseFadigueConsumption() / 2);
            FadigueCalculator.applyFadiguePenalties(target);
            return true;
        }

    }


    public void apllyFatigueConsumption(Fighter caster, Action action, boolean hit){
        caster.setFatigue(caster.getFatigue() + FadigueCalculator.calculateFadigueConsumption(action, hit));
    }


    public ActionResolver(FightEventListener eventListener) {
        this.setEventListener(eventListener);
    }

    public void setEventListener(FightEventListener eventListener) {
        this.eventListener = eventListener;
    }
}