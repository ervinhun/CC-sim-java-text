public class Quest {
    private int level;
    private String name;
    private String description;
    private int points;
    private Room finalRoom;
    private Item itemGoal;
    public Quest(int level, String name, String description, Room finalRoom, Item itemGoal) {
        this.level = level;
        this.name = name;
        this.description = description;
        this.finalRoom = finalRoom;
        this.itemGoal = itemGoal;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Room getFinalRoom() {
        return finalRoom;
    }
    public Item getItemGoal() {
        return itemGoal;
    }
}
