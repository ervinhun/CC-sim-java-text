public enum CommandWord {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    LOOK("look"),
    BACK("back"),
    EAT("eat"),
    DRINK("drink"),
    TAKE("take"),
    DROP("drop"),
    SAVE("save"),
    LOAD("load"),
    INVENTORY("inventory"),
    MAP("map"),
    QUEST("quest");

    // The command string.
    private final String commandString;
    /**
     * Initialize with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
