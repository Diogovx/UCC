package org.example;
import org.example.Lutas.Lutador;
import org.example.Lutas.Luta;

public class Main {
    public static void main(String[] args) {
        Lutador l[] = new Lutador[6];

        l[0] = new Lutador("Pretty Boy", "França", 31, 1.75,
                68.9, 11,2,1);
        l[1] = new Lutador("Putscripts", "Brasil", 29, 1.68,
                57.8, 14,2,3);
        l[2] = new Lutador("Snapshadow", "EUA", 35, 1.65,
                80.9, 12,2,1);
        l[3] = new Lutador("Brad Code", "Austrália", 28, 1.93,
                81.6, 13,0,2);
        l[4] = new Lutador("UFOCobol", "Brasil", 37, 1.70,
                119.3, 5,4,3);
        l[5] = new Lutador("Nerdaart", "EUA", 30, 1.81,
                105.7, 12,2,4);


        Luta UEC01 = new Luta();
        UEC01.marcarLuta(l[0], l[1]);
        UEC01.lutar();
        System.out.println("\n");
        System.out.println("\n");

        Luta UEC02 = new Luta();
        UEC01.marcarLuta(l[2], l[3]);
        UEC01.lutar();
        System.out.println("\n");

        Luta UEC03 = new Luta();
        UEC01.marcarLuta(l[4], l[5]);
        UEC01.lutar();
    }
}