package domiksad.heroTournament.entities;

import domiksad.heroTournament.items.Item;

import java.util.ArrayList;

public class Player extends AbstractCharacter<Player>{
    private ArrayList<Item> inventory = new ArrayList<>();
    private int gold = 100;

    public int getGold() {
        return gold;
    }

    public Player setGold(int gold) {
        this.gold = gold;
        return this;
    }

    public void takeGold(int amount){
        gold -= amount;
    }

    public void giveGold(int amount){
        gold += amount;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}
