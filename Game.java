import java.util.Scanner;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player1;
    private Texts texts;
    private Level level;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        texts = new Texts();
        player1 = new Player(getPlayerName());
        createRooms();
        parser = new Parser();
    }



    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, arrivals, departures, bar, restaurant, lobby, checkinDesk, crewWaiting, idOffice,
                crewSecurity, security, dutyFree, gateArea, gates1, gates2, visaCheck, ac, acExtra;
        Item busTicket, sun, coffee, croissant, lunch, salad, wineKey, sculptures, map, id, boardingPass,
            whiskey, parfum, cigarette, visa, extraBoardingPass, crew, gift;
        Door wineDoor, mainEntrance, securityDoor, paxSecurity, gate2, crewBoarding, euBoarding, nonEuBoarding;

      
        // create the rooms
        outside = new Room(texts.getTexts("outsideN"), texts.getTexts("intro1"));
        arrivals = new Room(texts.getTexts("arrivalN"), texts.getTexts("arrival1"));
        departures = new Room(texts.getTexts("departuresN"), texts.getTexts("departures1"));
        bar = new Room(texts.getTexts("barN"), texts.getTexts("bar1"));
        restaurant = new Room(texts.getTexts("restaurantN"), texts.getTexts("restaurant1"));
        lobby = new Room(texts.getTexts("lobbyN"), texts.getTexts("lobby1"));
        checkinDesk = new Room(texts.getTexts("checkindeskN"), texts.getTexts("checkindesk1"));
        crewWaiting = new Room(texts.getTexts("crewwaitingN"), texts.getTexts("crewwaiting1"));
        idOffice = new Room(texts.getTexts("idofficeN"), texts.getTexts("idoffice1"));
        crewSecurity = new Room(texts.getTexts("crewsecurityN"), texts.getTexts("crewsecurity1"));
        gateArea = new Room(texts.getTexts("gateareaN"), texts.getTexts("gatearea1"));
        security = new Room(texts.getTexts("securityN"), texts.getTexts("security1"));
        dutyFree = new Room(texts.getTexts("dutyC"), texts.getTexts("duty"));
        ac = new Room(texts.getTexts("acC"), texts.getTexts("ac"));
        gates1 = new Room(texts.getTexts("g1C"), texts.getTexts("g1"));
        gates2 = new Room(texts.getTexts("g2C"), texts.getTexts("g2"));
        visaCheck = new Room(texts.getTexts("visaC"), texts.getTexts("visa"));


        // create the items
        sun = new Item(texts.getTexts("sunC"), texts.getTexts("sun"), 1000, 0, false, false, true);
        busTicket = new Item(texts.getTexts("bustTicketC"), texts.getTexts("busTicket1"), 1, 100,  false, false, true);
        coffee = new Item(texts.getTexts("coffeeC"), texts.getTexts("coffee"), 1, 2,  false, true, true);
        croissant = new Item(texts.getTexts("croissantC"), texts.getTexts("croissant"), 1, 3,  false, true, true);
        lunch = new Item(texts.getTexts("lunchC"), texts.getTexts("lunch"), 1, 15, false, true, true);
        salad = new Item(texts.getTexts("saladC"), texts.getTexts("salad"), 1, 8, false, true, true);
        map = new Item(texts.getTexts("mapC"), texts.getTexts("map"), 1, 0, false, false, true);
        wineKey = new Item("key", "Key for the wine cellar", 1, 0, false, false, true);
        sculptures = new Item("sculpture", "Sculpture of a dolphin ", 500, 0, false, false, true);
        id = new Item(texts.getTexts("idC"), texts.getTexts("id"), 1, 50, true, false, true);
        boardingPass = new Item(texts.getTexts("boardingC"), texts.getTexts("boarding"), 1, 0, false, false, true);
        extraBoardingPass = new Item(texts.getTexts("boardingC"), texts.getTexts("boarding"), 1, 0, false, false, true);
        whiskey = new Item(texts.getTexts("whiskeyC"), texts.getTexts("whiskey"), 1, 80, false, true, true);
        parfum = new Item(texts.getTexts("parfumC"), texts.getTexts("parfum"), 1, 150, false, true, true);
        cigarette = new Item(texts.getTexts("cigiC"), texts.getTexts("cigi"), 1, 100, false, true, true);
        visa = new Item(texts.getTexts("visaiC"), texts.getTexts("visai"), 1, 0, false, false, true);
        crew = new Item(texts.getTexts("crewC"), texts.getTexts("crew"), 0, 0, false, false, true);
        gift = new Item(texts.getTexts("giftC"), texts.getTexts("gift"), 2, 0, false, false, true);

        //Create the doors
        mainEntrance = new Door("Main gate", false, null, "east");
        wineDoor = new Door("Wine door", true, wineKey, "down");
        securityDoor = new Door("Security", true, id, "forward");
        paxSecurity = new Door("Passenger security", true, boardingPass, "forward");
        gate2 = new Door("non Schengen", true, visa, "left");
        crewBoarding = new Door("Crew entrance", true, id, "crewexit");
        euBoarding = new Door("Boarding", true, boardingPass, "forward");
        nonEuBoarding = new Door("Oversea boarding", true, extraBoardingPass, "forward");
        
        // initialise room exits
        outside.setExit("left", arrivals);
        outside.setExit("right", departures);
        outside.setItem(sun, sculptures);
        outside.setDoors(mainEntrance);

        arrivals.setExit("right", departures);
        arrivals.setExit("forward", bar);
        arrivals.setItem(busTicket);

        bar.setExit("backward", arrivals);
        bar.setExit("forward", restaurant);
        bar.setItem(coffee, croissant);
        if(player1.getLevel() == 3)
            bar.setItem(crew);
        if(player1.getLevel() == 6)
            bar.setItem(gift);

        restaurant.setExit("backward", bar);
        restaurant.setItem(lunch, salad);

        departures.setExit("left", outside);
        departures.setExit("forward", lobby);
        departures.setItem(map);


        lobby.setExit("backward", departures);
        lobby.setExit("forward", crewWaiting);
        lobby.setExit("right", checkinDesk);

        checkinDesk.setExit("left", lobby);
        if (player1.getLevel() == 5)
            checkinDesk.setItem(extraBoardingPass);

        crewWaiting.setExit("right", idOffice);
        crewWaiting.setExit("backward", lobby);
        crewWaiting.setExit("forward", crewSecurity);

        idOffice.setExit("left", crewWaiting);
        if (player1.getLevel() == 1)
            idOffice.setItem(id);

        crewSecurity.setExit("backward", crewWaiting);
        crewSecurity.setExit("forward", gateArea);
        crewSecurity.setDoors(securityDoor);

        security.setExit("left", lobby);
        security.setExit("forward", dutyFree);
        security.setDoors(paxSecurity);

        dutyFree.setExit("forward", gateArea);
        dutyFree.setItem(whiskey, parfum, cigarette);

        if (player1.getLevel() != 2 || player1.getLevel() != 4 || player1.getLevel() != 5)
            gateArea.setExit("crewexit", ac);
        gateArea.setExit("left", gates2);
        gateArea.setExit("right", gates1);
        gateArea.setExit("forward", visaCheck);
        gateArea.setExit("backward", dutyFree);
        gateArea.setDoors(gate2, crewBoarding);

        gates1.setExit("left", gateArea);
        gates1.setExit("forward", ac);
        gates1.setDoors();


        ac.setExit("levelUp", outside);
        ac.setExit("backward", gateArea);



        player1.setCurrentRoom(outside);
        player1.setMaximumWeightToCarry(20);
        int levelNumber = player1.getLevel();
        switch (levelNumber) {
            case 2:
                level = new Level(player1.getLevel(), texts.getTexts("level1Description"), id, ac, 30);
                break;
            case 3:
                level = new Level(player1.getLevel(), texts.getTexts("level2Description"), boardingPass, ac, 30);
                break;
            case 4:
                level = new Level(player1.getLevel(), texts.getTexts("level3Description"), crew, bar, 30);
                break;
            case 5:
                level = new Level(player1.getLevel(), texts.getTexts("level4Description"), extraBoardingPass, ac, 30);
                break;
            default:
                level = new Level(player1.getLevel(), texts.getTexts("level5Description"), gift, ac, 30);
        }
        System.out.println(level);
        // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println(texts.getTexts("welcome") + " " + player1.getName() + " " + texts.getTexts("welcome2"));
        System.out.println(getValidCommands() + "\n");
        printLocationInfo();
    }

    private String getPlayerName() {
        System.out.print("Please enter your name: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        //String commandWord = command.getCommandWord();
        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
            printHelp();
            break;
            case GO:
                goRoom(command);
                break;
            case LOOK:
                look();
                break;
            case BACK:
                goBack();
                break;
            case EAT:
                eat(command, false);
                break;
            case DRINK:
                eat(command, true);
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case SAVE:
                save(command);
                break;
            case LOAD:
                load(command);
                break;
            case INVENTORY:
                System.out.println(player1.getInventoryString());
                break;
            case MAP:
                DrawMap map = new DrawMap();
            /**case MAGYAR:
                System.out.println("Itt majd lesz valami a nyelvvaltashoz");*/
            case QUIT:
                wantToQuit = quit(command);
            default:
                System.out.println();
        }
        return wantToQuit;
    }

    private void save(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Saving...");
            FileHandler file = new FileHandler();
            String data = "name: " + player1.getName() + "#currentRoom: " + player1.getCurrentRoom().getDescription() + "#" + "health: 100#" + "inventory: ";
            if(!player1.getInventory().isEmpty())
                for( Item i : player1.getInventory())
                    data += i.getName() + ";";
            file.CreateFile(command.getSecondWord(),data);
        }
        else {
            System.out.println("Add a file name to save. eg.: \"save alive\"");
        }
    }

    private void load (Command command) {
        System.out.println("Loading something - I don't know yet what, but it is loading. Just wait for it");
        //TODO: implement load method
    }

    private void take(Command command) {

        //TODO: move this and the drop() methods to the Player class
        boolean isExists = false;
        if (command.hasSecondWord()) {
            for (Item item : player1.getCurrentRoom().getItem()) {
                if (item.getName().equalsIgnoreCase(command.getSecondWord())) {
                    if(item.getWeight() > player1.getMaximumWeightToCarry()) {
                        System.out.println("Item is too heavy to carry");
                        isExists = true;
                        break;
                    }
                    if(item.getCost() > player1.getMoney()) {
                        System.out.println("It is too expensive. You have only " + player1.getMoney() + "€");
                        isExists = true;
                        break;
                    }
                    player1.pay(item.getCost());
                    System.out.println("You paid " + item.getCost() + "€ for "
                            + item.getDescription() + ". You have " + player1.getMoney() + "€ left.");
                    player1.setInventory(item);
                    player1.setMaximumWeightToCarry(player1.getMaximumWeightToCarry()-item.getWeight());
                    if (!item.isCanEat())
                        player1.getCurrentRoom().removeItem(item);
                    isExists = true;
                    System.out.println(item.getDescription() + " has been taken. Now you have it in your inventory");
                    break;
                }

            }
            if (!isExists) {
                    System.out.println(command.getSecondWord() + " does not exist in this room");
            }
        }
        else {
            System.out.println("What do you want to take?");
        }
    }

    private void drop(Command command) {
        boolean isExtists = false;
        if (command.hasSecondWord()) {
            for (Item item : player1.getInventory()) {
                if (item.getName().equalsIgnoreCase(command.getSecondWord())) {
                    isExtists = true;
                    player1.removeInventory(item);
                    player1.setMaximumWeightToCarry(player1.getMaximumWeightToCarry() + item.getWeight());
                    player1.getCurrentRoom().setItem(item);
                    System.out.println(item.getDescription() + " has been dropped.");
                    break;
                }

                }
            if (!isExtists) {
                    System.out.println(command.getSecondWord() + " does not exist in your inventory");
            }
        }
        else {
            System.out.println("What do you want to drop?");
        }
    }

    private void eat(Command command, boolean drink) {
        if (command.hasSecondWord() && player1.getAnItemFromInventory(command.getSecondWord())!=null
            && player1.getAnItemFromInventory(command.getSecondWord()).isCanEat()) {
            if(!drink)
                System.out.println(command.getSecondWord() + " has been eaten. Now you are full");
            if(drink) {
                System.out.println("You drank the " + command.getSecondWord());
                if (command.getSecondWord().equals(texts.getTexts("coffeeC"))) {
                    player1.setNumberOfSteps(5);
                    System.out.println(player1.getNumberOfSteps());
                }
            }

            player1.removeInventory(player1.getAnItemFromInventory(command.getSecondWord()));
            player1.setMaximumWeightToCarry(2000);
        }
        else {
            System.out.println("What would you like to eat?");
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(getValidCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?\n" + player1.getCurrentRoom().getExitString());
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;

        nextRoom = player1.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            if (player1.getCurrentRoom().hasDoors(direction)){
                if(player1.getInventory().contains(player1.getCurrentRoom().getDoor(direction).getKeyType())) {
                    player1.getCurrentRoom().getDoor(direction).unlock();
                    System.out.println("Door has been unlocked");
                    goRoom(command);
                }
                else {
                    System.out.println("First you need to find the key to pass here");
                }
            }
            else
                System.out.println("There is no door!");
        }
        else {
            player1.addToRoomHistory(player1.getCurrentRoom());
            player1.setCurrentRoom(nextRoom);
            printLocationInfo();
            checkForWin();
        }
    }

    private void checkForWin() {
        if (player1.getInventory().contains(level.getItemForWin()) && player1.getCurrentRoom() == level.getRoomForWin()) {
            //TODO: fix why it is not winning
            System.out.println("You win! Level " + player1.getLevel() + " has been completed");
            player1.levelUp();
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            //goRoom(new Command(CommandWord.GO, "levelUp"));
            processCommand(new Command(CommandWord.GO, "levelUp"));
            level.setLevel(player1.getLevel());
            createRooms();
            System.out.println(level);
        }
        else if (player1.getNumberOfSteps() <= 0) {
            System.out.println("You lost - did not manage to reach the goal of the level\n" +
                    "Would you like to restart? Y/N");
            Scanner sc = new Scanner(System.in);
            if (sc.nextLine().equalsIgnoreCase("y") || sc.nextLine().equalsIgnoreCase("yes")) {
                String name = player1.getName();
                player1 = new Player(name);
                createRooms();
            }
            else
                processCommand(new Command(CommandWord.QUIT, null));
        }
    }


    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * "Look" was entered. Check the rest of the command to see
     * whether we really just wan to look around the room.
     * @return true, if this command quits the game, false otherwise.
     */
    private void look()
    {
        DrawMap map = new DrawMap();
        System.out.println("OK, then again...");
            System.out.println(player1.getCurrentRoom().getLongDescription());
    }

    private void goBack() {
        if (!player1.getRoomHistory().isEmpty()) {
            Room tempRoom = player1.getRoomHistory().getLast();
            System.out.println("Returning to previous room");
            player1.setCurrentRoom(player1.getRoomHistory().getLast());
            player1.removeFromRoomHistory(tempRoom);
            printLocationInfo();
        }
        else {
            System.out.println("Can not go back at this point");
        }
    }


    /**
     * Get the info on current location
     */
    private void printLocationInfo()
    {
        System.out.println(player1.getCurrentRoom().getLongDescription());
    }

    private String getValidCommands() {
        String commands = "\t";
        for (String c : parser.getValidCommands()) {
            commands += c + " ";
        }
        return commands;
    }
}
