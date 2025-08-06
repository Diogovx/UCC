package UCC.app;
import UCC.core.model.Action;
import UCC.core.model.Fighter;
import UCC.core.model.PhysicalAttributes;
import UCC.gameplay.flow.Fight;

public class Main {
    public static void main(String[] args) {
        PhysicalAttributes pA[] = new PhysicalAttributes[2];
        pA[0] = new PhysicalAttributes(1.75, 68.9, 60, 60, 70, 1,100, 0.1, 0.5, 75, 65, 68);

        pA[1] = new PhysicalAttributes(1.68, 57.8, 60, 60, 70, 1,100, 0.1, 0.5, 75, 65, 68);
        Fighter l[] = new Fighter[2];

        l[0] = new Fighter("Pretty Boy", "France", 31, pA[0],
                 11,2,1, "Elegance and precision above all.");
        l[1] = new Fighter("Putscripts", "Brazil", 29, pA[1],
                14, 2,3, "I only compile after knocking out.");

        Action punch = new Action("Punch", 3, 70, Action.ActionType.STRIKE);
        Action block = new Action("Block", 1, 100, Action.ActionType.DEFENSE);
        l[0].addAction(punch);
        l[1].addAction(punch);
        l[0].addAction(block);
        l[1].addAction(block);


        Fight UEC01 = new Fight();

        UEC01.scheduleFight(l[0], l[1]);
        UEC01.startSimulation();
        System.out.println("\n");
        System.out.println("\n");
        //l[0].status();
        //l[1].status();




        /*l[0].performAction(l[0].getActions().get(0), l[1]);
        l[1].performAction(l[1].getActions().get(0), l[0]);
        System.out.println("\n" + l[0].getName() + " fadigue current fadigue: " + l[0].getFadigue());
        System.out.println(l[1].getName() + " fadigue current fadigue: " + l[1].getFadigue());

        l[0].performAction(l[0].getActions().get(1), l[0]);
        l[1].performAction(l[1].getActions().get(0), l[0]);

        System.out.println("\n" + l[0].getName() + " fadigue current fadigue: " + l[0].getFadigue());
        System.out.println(l[1].getName() + " fadigue current fadigue: " + l[1].getFadigue());

        l[0].performAction(l[0].getActions().get(0), l[1]);
        l[1].performAction(l[1].getActions().get(1), l[1]);

        */
    }
}
