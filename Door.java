public class Door {
    private String doorName;
    private boolean locked;
    private Item keyType;
    private String position;
    public Door(String doorName, boolean locked, Item keyType, String position) {
        this.doorName = doorName;
        this.locked = locked;
        this.keyType = keyType;
        this.position = position;
    }
    public String getDoorName() {
        return doorName;
    }
    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public void unlock() {
        locked = false;
    }
    public Item getKeyType() {
        return keyType;
    }
    public boolean keyOpensThisDoor(Item i) {
        return i == keyType;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
