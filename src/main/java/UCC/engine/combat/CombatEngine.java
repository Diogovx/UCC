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

            eventListener.onNewRound(round);
            runRound(fighters[0], fighters[1], round);
            situation = checkVictoryConditions(round, maxRounds);
            fighters = alternateTurn(fighters);

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
    private Random randomFactor = new Random();

    public Action rollAction(Fighter attacker){
        Action action;
        do{
            action = attacker.getActions().get(randomFactor.nextInt(attacker.getActions().size()));
        } while (action.getType() == ActionType.COUNTER);
        return action;
    }

    public CombatLog performAction(Fighter attacker, Fighter target, int round) {
        Action action = rollAction(attacker);

        boolean hit = checkAccuracy(action);

        resolveHit(action, attacker, target, hit);

        resolveReaction(attacker, target, hit);

        apllyFatigueConsumption(attacker, action, hit);

        attacker.setLastAction(action);

        FadigueCalculator.applyFadiguePenalties(attacker);

        return new CombatLog(attacker, target, round, action, hit);
    }

    public void resolveHit(Action action, Fighter attacker , Fighter target, boolean hit){
        if(action.getType() == ActionType.DEFENSE){
            attacker.setStance(FighterStance.BLOCKING);
            eventListener.onPrintWithDelay(attacker.getName() + " used " + action.getName(), 1000);
            return;
        }


        if(action.getType() == ActionType.STRIKE){
            attacker.setStance(FighterStance.HITTING);
            if (hit && target.getStance() != FighterStance.BLOCKING) {
                eventListener.onPrintWithDelay(attacker.getName() + " performed " + action.getName() + " and hit", 1000);
                eventListener.onComment(CommentType.HIT);
                receiveHit(action, target);
            }
            else{
                eventListener.onPrintWithDelay(attacker.getName() + " performed " + action.getName() + " but missed", 1000);
                eventListener.onComment(CommentType.BLOCKED);
            }
        } else if (action.getType() == ActionType.COUNTER) {
            eventListener.onPrintWithDelay(target.getName() + " counter the attack", 1000);
            eventListener.onComment(CommentType.SURPRISE);
            receiveHit(action, target);
        }
    }

    public void resolveReaction(Fighter attacker , Fighter target, boolean hit){
        if((hit) || (hit && target.getStance() == FighterStance.BLOCKING)){
            return;
        }
        if(!willCounter(target)){
            return;
        }

        target.setStance(FighterStance.CONTERATTACKING);

        target.getActions().stream()
                .filter(a -> a.getType() == ActionType.COUNTER)
                .findFirst()
                .ifPresent(counter -> {
                    resolveHit(counter, attacker , target, true);
                });
        target.setStance(FighterStance.NEUTRAL);
    }


    public boolean checkAccuracy(Action action){
        int randomNumber = randomFactor.nextInt(100);
        if(randomNumber <= action.getBaseAccuracy()){
            return true;
        }else{
            return false;
        }
    }


    public boolean receiveHit(Action action, Fighter target){
        if(target.getLastAction().getType().equals(ActionType.DEFENSE) && target.getStance() != FighterStance.CONTERATTACKING){
            eventListener.onText(target.getName() + " blocks the attack!");
            return false;
        } else {
            eventListener.onText(target.getName() + " was hit by " + action.getName());
            target.setFatigue(target.getFatigue() + action.getBaseFadigueConsumption() / 2);
            FadigueCalculator.applyFadiguePenalties(target);
            return true;
        }

    }

    public boolean willCounter(Fighter target){
        int randomNumber = randomFactor.nextInt(100);
        if(randomNumber >= 90){
            return true;
        } else{
            return false;
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