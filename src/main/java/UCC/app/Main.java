package UCC.app;
import UCC.core.enums.ActionType;
import UCC.core.model.Action;
import UCC.core.model.Fighter;
import UCC.core.model.PhysicalAttributes;
import UCC.gameplay.flow.Fight;
import UCC.ui.ConsoleFightListener;
import UCC.ui.FightEventListener;

public class Main {
    private static FightEventListener eventListener = new ConsoleFightListener();
    public static void main(String[] args) {
        PhysicalAttributes pA[] = new PhysicalAttributes[2];
        pA[0] = new PhysicalAttributes(1.75, 68.9, 60, 60, 70, 1,100, 0.1, 0.5, 75, 65, 68);

        pA[1] = new PhysicalAttributes(1.68, 57.8, 60, 60, 70, 1,100, 0.1, 0.5, 75, 65, 68);
        Fighter l[] = new Fighter[2];

        l[0] = new Fighter("Pretty Boy", "France", 31, pA[0],
                 11,2,1, "Elegance and precision above all.");
        l[1] = new Fighter("Putscripts", "Brazil", 29, pA[1],
                14, 2,3, "I only compile after knocking out.");

        Action punch = new Action("Punch", 3, 70, ActionType.STRIKE);
        Action block = new Action("Block", 1, 100, ActionType.DEFENSE);
        l[0].addAction(punch);
        l[1].addAction(punch);
        l[0].addAction(block);
        l[1].addAction(block);


        Fight UEC01 = new Fight(l[0],l[1], eventListener);

        UEC01.scheduleFight();
        UEC01.startSimulation();
        System.out.println("\n");
        System.out.println("\n");

    }
}
