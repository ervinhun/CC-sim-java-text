import java.util.ArrayList;
import java.util.List;

public class Level {
    private int level;
    private String levelDescription;
    private Item itemForWin;
    private Room roomForWin;
    private int maximumNumberOfSteps;
    private List<Item> itemList;
    private List<Room> roomList;
    private List<String> descriptionList;

    public Level(int level, String levelDescription, Item itemForWin, Room roomForWin, int maximumNumberOfSteps) {
        this.level = level;
        this.levelDescription = levelDescription;
        this.itemForWin = itemForWin;
        this.roomForWin = roomForWin;
        this.maximumNumberOfSteps = maximumNumberOfSteps;
        itemList = new ArrayList<>();
        roomList = new ArrayList<>();
        descriptionList = new ArrayList<>();
        //setGoals();
    }



    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        this.levelDescription = descriptionList.get(level-1);
        this.roomForWin = roomList.get(level-1);
        this.itemForWin = itemList.get(level-1);
    }
    public String getLevelDescription() {
        return levelDescription;
    }
    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }
    public Item getItemForWin() {
        return itemForWin;
    }
    public void setItemForWin(Item itemForWin) {
        this.itemForWin = itemForWin;
    }
    public Room getRoomForWin() {
        return roomForWin;
    }
    public void setRoomForWin(Room roomForWin) {
        this.roomForWin = roomForWin;
    }
    public int getMaximumNumberOfSteps() {
        return maximumNumberOfSteps;
    }
    public void setMaximumNumberOfSteps(int maximumNumberOfSteps) {
        this.maximumNumberOfSteps = maximumNumberOfSteps;
    }

    @Override
    public String toString() {
        String text = "";
        text += "You need ";
        text += itemForWin.getName();
        text += " and you need to be at " + roomForWin;
        return text;
    }
}
