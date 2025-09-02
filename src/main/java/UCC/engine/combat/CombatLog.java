package UCC.engine.combat;

import UCC.core.model.Action;
import UCC.core.model.Fighter;
import ch.qos.logback.core.joran.sanity.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CombatLog {
    private Fighter attacker;
    private Fighter defender;
    private int currentTurn;
    private Action action;
    private boolean result;

    public void registerAction(){
        log.info("Attacker: " + this.getAttacker().getName() + " Defender: " + this.getDefender().getName() + " Current turn: " + this.getCurrentTurn() + " Action: " + this.getAction().getName() + " Hit?: " + this.isResult());
    }

    public CombatLog(Fighter attacker, Fighter defender, int currentTurn, Action action, boolean result) {
        this.attacker = attacker;
        this.defender = defender;
        this.currentTurn = currentTurn;
        this.action = action;
        this.result = result;
    }

    public Fighter getAttacker() {
        return attacker;
    }

    public void setAttacker(Fighter attacker) {
        this.attacker = attacker;
    }

    public Fighter getDefender() {
        return defender;
    }

    public void setDefender(Fighter defender) {
        this.defender = defender;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
