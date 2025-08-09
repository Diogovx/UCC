package UCC.core.model;

import UCC.core.enums.FatiguePenaltyLevel;
import UCC.core.enums.FighterStance;
import UCC.engine.stamina.FadigueCalculator;
import UCC.ui.ConsoleFightListener;
import UCC.ui.FightEventListener;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

public class Fighter {
    private String name;
    private String nationality;
    private int age;
    private PhysicalAttributes physicalAttr;
    private String category;
    private int victories, defeats, ties;
    private int fatigue;
    private int maxFatigue;
    private ArrayList<Action> actions;
    private Action lastAction;
    private Set<FatiguePenaltyLevel> fatiguePenaltiesApplied = EnumSet.noneOf(FatiguePenaltyLevel.class);
    private String entryPhrase;
    private FighterStance stance;

    public void winFight(){
        this.setVictories(this.getVictories() + 1);
    }
    public void loseFight(){
        this.setDefeats(this.getDefeats() + 1);
    }
    public void tieFight(){
        this.setTies(this.getTies() + 1);
    }

    public void addAction(Action action){
        this.getActions().add(action);
    }

    public Fighter(String name, String nacionality, int age, PhysicalAttributes physicalAttr, int victories, int defeats, int ties, String entryPhrase) {
        this.setName(name);
        this.setNationality(nacionality);
        this.setAge(age);
        this.setPhysicalAttr(physicalAttr);
        this.setVictories(victories);
        this.setDefeats(defeats);
        this.setTies(ties);
        this.setMaxFatigue(FadigueCalculator.calculateMaxFadigue(this));
        this.actions = new ArrayList<>();
        this.setLastAction(Action.neutralAction());
        this.setEntryPhrase(entryPhrase);
        this.setStance(FighterStance.NEUTRAL);
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public PhysicalAttributes getPhysicalAttr() {
        return physicalAttr;
    }

    public String getCategory() {
        return category;
    }

    public int getVictories() {
        return victories;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getTies() {
        return ties;
    }

    public int getMaxFatigue() {
        return maxFatigue;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public Set<FatiguePenaltyLevel> getFatiguePenaltiesApplied() {
        return fatiguePenaltiesApplied;
    }

    public String getEntryPhrase() {
        return entryPhrase;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhysicalAttr(PhysicalAttributes physicalAttr) {
        this.physicalAttr = physicalAttr;
        setCategory();
    }

    private void setCategory() {
        if(this.getPhysicalAttr().getWeight() < 52.2){
            this.category = "Invalid";
        } else if(this.getPhysicalAttr().getWeight() <= 70.3){
            this.category = "Lightweight";
        } else if(this.getPhysicalAttr().getWeight() <= 83.9){
            this.category = "Middleweight";
        } else if(this.getPhysicalAttr().getWeight() <= 120.2){
            this.category = "Heavyweight";
        } else{
            this.category = "Invalid";
        }
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void setMaxFatigue(int maxFatigue) {
        this.maxFatigue = maxFatigue;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    public void setFatiguePenaltiesApplied(Set<FatiguePenaltyLevel> fatiguePenaltiesApplied) {
        this.fatiguePenaltiesApplied = fatiguePenaltiesApplied;
    }

    public void setEntryPhrase(String entryPhrase) {
        this.entryPhrase = entryPhrase;
    }

    public FighterStance getStance() {
        return stance;
    }

    public void setStance(FighterStance stance) {
        this.stance = stance;
    }
}
