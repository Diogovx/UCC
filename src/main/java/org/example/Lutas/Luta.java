package org.example.Lutas;

import java.util.Random;

public class Luta {
    private Lutador desafiado, desafiante;
    private int rounds;
    private boolean aprovada;

    public void marcarLuta(Lutador l1, Lutador l2){
        if(l1.getCategoria().equals(l2.getCategoria()) && l1 != l2){
            this.setAprovada(true);
            this.setDesafiado(l1);
            this.setDesafiante(l2);
        } else {
            this.setAprovada(false);
            this.setDesafiado(null);
            this.setDesafiante(null);
        }

    }



    public void lutar(){
        if(this.isAprovada()){
            System.out.println("##### DESAFIADO #####");
            this.getDesafiado().apresentar();
            System.out.println("##### DESAFIANTE #####");
            this.getDesafiante().apresentar();


            if(this.getDesafiante().getIndiceDePerformance() < this.getDesafiado().getIndiceDePerformance()){
                System.out.println("Vitória do " + this.getDesafiado().getNome());
                this.getDesafiado().ganharLuta();
                this.getDesafiante().perderLuta();
            } else if (getDesafiado().getIndiceDePerformance() < getDesafiante().getIndiceDePerformance()) {
                System.out.println("Vitória do " + this.getDesafiante().getNome());
                this.getDesafiante().ganharLuta();
                this.getDesafiado().perderLuta();
            } else{
                System.out.println("Empatou!");
                this.getDesafiado().empatarLutar();
                this.getDesafiante().empatarLutar();
            }


            /*switch (vencedor){
                case 0:
                    System.out.println("Empatou!");
                    this.getDesafiado().empatarLutar();
                    this.getDesafiante().empatarLutar();
                    break;
                case 1:
                    System.out.println("Vitória do " + this.getDesafiado().getNome());
                    this.getDesafiado().ganharLuta();
                    this.getDesafiante().perderLuta();
                    break;
                case 2:
                    System.out.println("Vitória do " + this.getDesafiante().getNome());
                    this.getDesafiado().perderLuta();
                    this.getDesafiante().ganharLuta();
                    break;
            }*/
        } else {
            System.out.println("A luta não pode acontecer!");
        }
    }

    public Lutador getDesafiado() {
        return desafiado;
    }

    public Lutador getDesafiante() {
        return desafiante;
    }

    public int getRounds() {
        return rounds;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void setDesafiado(Lutador desafiado) {
        this.desafiado = desafiado;
    }

    public void setDesafiante(Lutador desafiante) {
        this.desafiante = desafiante;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }
}
