package rage.gameplay.flow;

import rage.core.model.Fighter;

public class Fight {
    private Fighter challenged;
    private Fighter challeging;
    private int rounds;
    private boolean approved;

    public void scheduleFight(Fighter l1, Fighter l2){
        if(l1.getCategory().equals(l2.getCategory()) && l1 != l2){
            this.setApproved(true);
            this.setChallenged(l1);
            this.setChalleging(l2);
        } else {
            this.setApproved(false);
            this.setChallenged(null);
            this.setChalleging(null);
        }

    }



    public void toFight(){
        if(this.isApproved()){
            System.out.println("##### CHALLENGER #####");
            this.getChallenged().present();
            System.out.println("##### CHALLENGED #####");
            this.getChalleging().present();


            if(this.getChalleging().getPerformanceIndex() < this.getChallenged().getPerformanceIndex()){
                System.out.println(this.getChallenged().getName() + "'s victory");
                this.getChallenged().winFight();
                this.getChalleging().loseFight();
            } else if (getChallenged().getPerformanceIndex() < getChalleging().getPerformanceIndex()) {
                System.out.println(this.getChalleging().getName() +  "'s victory");
                this.getChalleging().winFight();
                this.getChallenged().loseFight();
            } else{
                System.out.println("Tied!");
                this.getChallenged().tieFight();
                this.getChalleging().tieFight();
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
            System.out.println("The fight cannot happen!");
        }
    }

    public Fighter getChallenged() {
        return challenged;
    }

    public Fighter getChalleging() {
        return challeging;
    }

    public int getRounds() {
        return rounds;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setChallenged(Fighter challenged) {
        this.challenged = challenged;
    }

    public void setChalleging(Fighter challeging) {
        this.challeging = challeging;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
