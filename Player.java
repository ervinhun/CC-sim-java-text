import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int maximumWeightToCarry;
    private Room currentRoom;
    private List<Item> inventory = new ArrayList<Item>();
    private List<Room> roomHistory = new ArrayList<Room>();
    private int level;
    private int money;
    private int numberOfSteps;
    private Level levelDetails;

    public Player(String name) {
        this.name = name;
        this.maximumWeightToCarry = 20;
        this.health = 100;
        this.level = 1;
        this.money = 1000;
        numberOfSteps = 30;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMaximumWeightToCarry() {
        return maximumWeightToCarry;
    }
    public void setMaximumWeightToCarry(int maximumWeightToCarry) {
        this.maximumWeightToCarry = maximumWeightToCarry;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom) {
        numberOfSteps--;
        if (numberOfSteps <= 0) {
            System.out.println("You are too tired to continue");

        }
        this.currentRoom = currentRoom;

    }

    public List<Room> getRoomHistory() {
        return roomHistory;
    }
    public void addToRoomHistory(Room room) {
        roomHistory.add(room);
    }
    public boolean removeFromRoomHistory(Room room) {
        return roomHistory.remove(room);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Item getAnItemFromInventory(String itemName) {
        boolean found = false;
        for (Item i : inventory) {
            if (i.getName().equals(itemName)) {
                found = true;
                return i;
            }
        }
        return null;
    }


    public String getInventoryString() {
        String returnString = money + "€\n";
        for (Item item : inventory) {
            returnString += item.getName() + " - " + item.getDescription() + " (" + item.getWeight() + "kg)" + "\n";
        }
        returnString += "You still can carry another " + maximumWeightToCarry  + "kg";

        return returnString;
    }

    public void setInventory(Item inventory) {
        this.inventory.add(inventory);
    }

    public boolean removeInventory(Item inventory) {
        return this.inventory.remove(inventory);
    }

    public int getLevel() {
        return level;
    }
    public void levelUp() {
        level++;
    }
    public int getMoney() {
        return money;
    }
    public void pay(int money) {
        this.money -= money;
    }
    public void getPaid(int money) {
        this.money += money;
    }
    public int getNumberOfSteps() {
        return numberOfSteps;
    }
    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps += numberOfSteps;
    }

}
