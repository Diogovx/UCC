package rage.app;
import rage.core.model.Fighter;
import rage.core.model.PhysicalAttributes;
import rage.gameplay.flow.Fight;

public class Main {
    public static void main(String[] args) {
        PhysicalAttributes pA[] = new PhysicalAttributes[2];
        pA[0] = new PhysicalAttributes(1.75, 68.9, 60, 60, 70, 1,100, 0.1, 0.5, 75, 65, 68);

        pA[1] = new PhysicalAttributes(1.68, 57.8, 60, 60, 70, 1,100, 0.1, 0.5, 75, 65, 68);
        Fighter l[] = new Fighter[2];

        l[0] = new Fighter("Pretty Boy", "França", 31, pA[0],
                 11,2,1);
        l[1] = new Fighter("Putscripts", "Brasil", 29, pA[1],
                14, 2,3);


        Fight UEC01 = new Fight();
        UEC01.scheduleFight(l[0], l[1]);
        UEC01.toFight();
        System.out.println("\n");
        System.out.println("\n");
        l[0].status();
        l[1].status();
    }
}