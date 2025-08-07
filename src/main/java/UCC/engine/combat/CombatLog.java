package UCC.engine.combat;

import UCC.core.model.Action;
import UCC.core.model.Fighter;

public class CombatLog {
    private Fighter attacker;
    private Fighter defender;
    private int currentTurn;
    private Action action;

    public void registerAction(Fighter attacker, Fighter defender, int currentTurn, Action action){
        this.setAttacker(attacker);
        this.setDefender(defender);
        this.setCurrentTurn(currentTurn);
        this.setAction(action);
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
}
