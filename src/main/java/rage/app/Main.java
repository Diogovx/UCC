package rage.app;
import rage.core.model.Fighter;
import rage.gameplay.flow.Fight;

public class Main {
    public static void main(String[] args) {
        Fighter l[] = new Fighter[6];

        l[0] = new Fighter("Pretty Boy", "França", 31, 1.75,
                68.9, 11,2,1);
        l[1] = new Fighter("Putscripts", "Brasil", 29, 1.68,
                57.8, 14,2,3);
        l[2] = new Fighter("Snapshadow", "EUA", 35, 1.65,
                80.9, 12,2,1);
        l[3] = new Fighter("Brad Code", "Austrália", 28, 1.93,
                81.6, 13,0,2);
        l[4] = new Fighter("UFOCobol", "Brasil", 37, 1.70,
                119.3, 5,4,3);
        l[5] = new Fighter("Nerdaart", "EUA", 30, 1.81,
                105.7, 12,2,4);


        Fight UEC01 = new Fight();
        UEC01.scheduleFight(l[0], l[1]);
        UEC01.toFight();
        System.out.println("\n");
        System.out.println("\n");

        Fight UEC02 = new Fight();
        UEC02.scheduleFight(l[2], l[3]);
        UEC02.toFight();
        System.out.println("\n");

        Fight UEC03 = new Fight();
        UEC03.scheduleFight(l[4], l[5]);
        UEC03.toFight();
    }
}