package UCC.engine.combat;

import UCC.core.enums.FightSituation;
import UCC.core.model.Fighter;

public class CombatResult {
    private FightSituation situation;
    private Fighter winner;
    private int lastRound;

    public FightSituation getSituation() {
        return situation;
    }

    public void setSituation(FightSituation situation) {
        this.situation = situation;
    }

    public Fighter getWinner() {
        return winner;
    }

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    public int getLastRound() {
        return lastRound;
    }

    public void setLastRound(int lastRound) {
        this.lastRound = lastRound;
    }
}

