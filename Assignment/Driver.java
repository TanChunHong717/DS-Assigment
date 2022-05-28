import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.PatternSyntaxException;

import Character.CharacterList;
import Character.Character;
import Converter.Converter;
import Wall.Wall;
import Graph.ParadisMap;
import Titan.*;

public final class Driver {
    private static Scanner input = new Scanner(System.in);
    private static ParadisMap map = new ParadisMap();
    private static CharacterList characterList;
    private static Character[] sortedStrength;
    private static Character[] sortedAgility;
    private static Character[] sortedIntelligence;
    private static Character[] sortedCoordination;
    private static Character[] sortedLearderShip;
    private static boolean sorted = false;;

    public static void main(String[] args) {
        System.out.println("-----Attack Of Titan-----");

        while(true){
            System.out.println("1. Add charater in files\n2. Sort and find a character\n3. Evualate titan and determine the killing priority");
            System.out.println("4. Find a scounting path(Hamiltonian Cycle)\n5. Find a shortest path to kill titan\n6. Convert Marley sentence to English");
            System.out.println("7. Determine the weakest part of Wall of Maria\n8. Quit");
            switch(getInput(1, 8, "Enter your choice: ")){
                case 1:
                    addCharacter();
                    sorted = false;
                    break;
                case 2:
                    sortAndSearchCharacter();
                    break;
                case 3:
                    titanEvualateAndKillingPriority();
                    break;
                case 4:
                    scountingMissionInWall();
                    break;
                case 5:
                    bestPathToKillTitan();
                    break;
                case 6:
                    MarleyWordConverter();
                    break;
                case 7:
                    protectingWallOfMaria();
                    break;
                case 8:
                    System.out.println("Saving contents to the file...");
                    if(characterList != null)
                        characterList.close();
                    System.out.println("Program end.");
                    return;
            }
        }
    }

    private static void addCharacter(){
        System.out.println("This page allow you to add charater in files.");
        while(true){
            try{
                if(characterList == null)
                    characterList = new CharacterList();
                System.out.print("Enter name: ");    
                String name = input.nextLine();
                if(characterList.contains(name.trim())){
                    System.out.println("Character is already in list");
                    if(wantToQuit())
                        return;
                    else
                        continue;
                }
                System.out.print("Enter characteristic: ");
                String[] characteristic = input.nextLine().split(" ");
                Character character = new Character(name, Integer.parseInt(characteristic[0]), Integer.parseInt(characteristic[1]), 
                    Integer.parseInt(characteristic[2]), Integer.parseInt(characteristic[3]), Integer.parseInt(characteristic[4]), 
                    Integer.parseInt(characteristic[5]), Integer.parseInt(characteristic[6]));
                characterList.addCharacter(character);

                if(wantToQuit())
                    return;
            }catch(Exception e){
                if(e instanceof FileNotFoundException){
                    System.out.println("File not exist.");
                    return;
                }
                else if(e instanceof PatternSyntaxException || e instanceof IndexOutOfBoundsException)
                    System.out.println("Please enter only 7 integers seperate by blanks.");
            }
        }
    }

    private static void sortAndSearchCharacter(){
        System.out.println("This page can help you sort and find a character according to its characteristic.");
        while(true){
            System.out.println("1. Sorting\n2. Finding ability\n3. Quit");
            try{
                if(characterList == null)
                    characterList = new CharacterList();
                if(!sorted){
                    sortedStrength = characterList.sortStrength();
                    sortedAgility = characterList.sortAgility();
                    sortedIntelligence = characterList.sortIntelligence();
                    sortedLearderShip = characterList.sortLeadership();
                    sorted = true;
                }
                int temp = getInput(1, 2, "Enter your choice: ");
                switch(temp){
                    case 1:
                        sort();
                        break;
                    case 2:
                        search();
                        break;
                    case 3:
                        return;
                }

                if(wantToQuit())
                    return;
            }catch(FileNotFoundException e){
                System.out.println("File not exist");
                return;
            }
        }
    }

    public static void sort(){
        System.out.print("Sorting atribute: ");
        switch(input.nextLine().trim()){
            case "Strength":
                for(Character character : sortedStrength)
                    System.out.println(character.getName() + " " + character.getStrength());
                break;
            case "Agility":
                for(Character character : sortedAgility)
                    System.out.println(character.getName() + " " + character.getAgility());
                break;
            case "Intelligence":
                for(Character character : sortedIntelligence)
                    System.out.println(character.getName() + " " + character.getIntelligence());
                break;
            case "Coordination":
                for(Character character : sortedCoordination)
                    System.out.println(character.getName() + " " + character.getCoordination());
                break;
            case "Leadership":
                for(Character character : sortedLearderShip)
                    System.out.println(character.getName() + " " + character.getLeadership());
                break;
            default:
                System.out.println("Please enter only : Strength | Agility | Intelligence | Coordinatiion | Leadership");
        }
    }

    public static void search(){
        System.out.print("Finding ablity: ");
        String ability = input.nextLine().trim();
        int val = getInput("val: ");
        Character[] characters;

        switch(ability){
            case "Strength":
                characters = CharacterList.binarySearchStrength(sortedStrength, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getStrength());
                else
                    System.out.println("No such a character exist.");
                break;
            case "Agility":
                characters = CharacterList.binarySearchAgility(sortedAgility, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getAgility());
                else
                    System.out.println("No such a character exist.");
                break;
            case "Intelligence":
                characters = CharacterList.binarySearchIntelligence(sortedIntelligence, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getIntelligence());
                else
                    System.out.println("No such a character exist.");
                break;
            case "Coordination":
                characters = CharacterList.binarySearchCoordination(sortedCoordination, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getCoordination());
                else
                    System.out.println("No such a character exist.");
                break;
            case "Leadership":
                characters = CharacterList.binarySearchLeaderShip(sortedLearderShip, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getLeadership());
                else
                    System.out.println("No such a character exist.");
                break;
            default:
                System.out.println("Please enter only : Strength | Agility | Intelligence | Coordinatiion | Leadership");
            }
    }

    private static void titanEvualateAndKillingPriority(){
        System.out.println("This page can help you to evualate titan and determine the killing priority.");
        System.out.println("1. Start with defualt\n2. Choose a character");
        try{
            Character character = (getInput(1,2, "Enter your choice :") == 2)? choseCharacter() :null;
            Random rand = new Random();
            PriorityQueue<Titan> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o2.getDangerRisk(), o1.getDangerRisk());});
            out:
            while(true){
                int numOfTitan = getInput(1, Integer.MAX_VALUE, "Number of titan: ");
                if(numOfTitan == 1)
                    System.out.println("\nGenerating 1 titan.");
                else
                    System.out.println("\nGenerating " + numOfTitan + " titans.");
                
                int i;
                for(i = 1; i <= numOfTitan; i++){
                    int c = rand.nextInt(10);
                    Titan titan;
                    if(c < 7)
                        titan = new NormalTitan();
                    else if(c < 9)
                        titan = new AbnormalTitan();
                    else
                        titan = new NineTitan();
                    System.out.println("Titan " + i + ": " + titan.toString());
                    titan.index = i;
                    pq.add(titan);
                }
    
                int totalDistance = 0;
                int prevPos = 0;
                System.out.println("\nSequence to be killed: ");
                while(!pq.isEmpty()){
                    Titan titan = pq.poll();
                    if(!pq.isEmpty() && titan.getDangerRisk() == pq.peek().getDangerRisk()){
                        PriorityQueue<Titan> container = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o2.getDangerRisk(), o1.getDangerRisk());});
                        while(!pq.isEmpty() && pq.peek().getDangerRisk() == titan.getDangerRisk()){
                            if(Math.abs(pq.peek().index - prevPos) < Math.abs(titan.index - prevPos)){
                                container.add(titan);
                                titan = pq.poll();
                            }else
                                container.add(pq.poll());
                        }
                        while(!container.isEmpty())
                            pq.add(container.poll());
                    }
    
                    if(character != null && character.getStrength() + character.getAgility() < titan.getDangerRisk()){
                        System.out.println("Titan " + titan.index + " is too danger." + character.getName() + " run.");
                        if(wantToQuit())
                            return;
                        else
                            break out;
                    }
                    System.out.print("Titan " + titan.index);
                    if(!pq.isEmpty())
                        System.out.print(" --> ");
                    totalDistance += Math.abs(prevPos - titan.index);
                    prevPos = titan.index;

                    if(rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean()){
                        System.out.println("\nA new titan appear!!!");
                        int c = rand.nextInt(10);
                        if(c < 7)
                            titan = new NormalTitan();
                        else if(c < 9)
                            titan = new AbnormalTitan();
                        else
                            titan = new NineTitan();
                        System.out.println("Titan " + i + ": " + titan.toString());
                        titan.index = i;
                        pq.add(titan);
                        i++;
                    }
                }
                System.out.println("\nTotal distance moved: " + totalDistance);
                if(wantToQuit())
                    return;
            }
        }catch(FileNotFoundException e){
            System.out.println("File not exist");
        }
    }

    private static void scountingMissionInWall(){
        System.out.println("This page can help you to find a scounting path(Hamiltonian Cycle) started from any given point.");
        while(true){
            int start = getInput(0, 15, "Enter starting point: ");
            List<Integer> path = map.HamiltonianCycle(start);
            for(int i = 0; i < path.size()-1; i++)
                System.out.print(path.get(i) + "-->");
            System.out.println(path.get(path.size() - 1));

            if(wantToQuit())
                return;
        }
    }

    private static void bestPathToKillTitan(){
        System.out.println("This page can help you to find a shortest path to kill titan.");
        while(true){
            System.out.println("1. Titan in constant location.\n2. Titan will move.\n3. Choose a character to kill titan\n4. Real Battle\n5. Quit");
            switch(getInput(1, 4, "Enter your choice: ")){
                case 1:
                    killFixedTitan();
                    break;
                case 2:
                    killMovedTitan();
                    break;
                case 3:
                    try{
                        Character character = choseCharacter();
                        killFixedTitan(character);
                        break;
                    }catch(FileNotFoundException e){
                        System.out.println("Charater files not exist. ");
                        break;
                    }
                case 4:
                    try{
                        Character character = choseCharacter();
                        realBattle(character);
                        break;
                    }catch(FileNotFoundException e){
                        System.out.println("Charater files not exist. ");
                        break;
                    }
                case 5:
                    return;
            }

            if(wantToQuit())
                return;
        }
    }

    private static void killFixedTitan(){
        int position = getInput(0, 15, "Enter location of titan: ");
        ArrayList<ArrayList<Integer>> lists = map.BestPathToKillTitan(position);
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size()-1; j++)
                System.out.print(lists.get(i).get(j) + "-->");
            System.out.println(lists.get(i).get(lists.get(i).size() - 1));
        }
        System.out.println("Times to reach location: " + map.timeToReach(lists.get(0)));
    }

    private static void killMovedTitan(){
        int[] move = null;
        while(move == null){
            System.out.print("Enter the locations of titan(separated by white black): ");
            try(Scanner scanner = new Scanner(input.nextLine());){    
                List<Integer> list = new LinkedList<>();
                while(scanner.hasNext()){
                    int temp = scanner.nextInt();
                    if(temp < 0 || temp > 15)
                        throw new InputMismatchException();
                    list.add(temp);
                }
                move = list.stream().mapToInt(i -> i).toArray();
            }catch(Exception e){
                System.out.println("Please enter integers between 0 to 15 that separates with white blank only. ");
            }
        }
        ArrayList<ArrayList<Integer>> lists = map.BestPathToKillTitan1(move);
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size()-1; j++)
                System.out.print(lists.get(i).get(j) + "-->");
            System.out.println(lists.get(i).get(lists.get(i).size() - 1));
        }
        System.out.println("Times to kill titan: " + map.timeToKillTitan(lists.get(0), move));
    }

    private static Character choseCharacter() throws FileNotFoundException{
        if(characterList == null)
            characterList = new CharacterList();

        while(true){
            System.out.print("Enter the character's name: ");
            Character character = characterList.getCharacter(input.nextLine().trim());
            if(character == null)
                System.out.println("Character not exist.");
            else
                return character;
        }
    }

    private static void killFixedTitan(Character character){
        int position = getInput(0, 15, "Enter location of titan: ");
        ArrayList<ArrayList<Integer>> lists = map.BestPathToKillTitan2(position, character.getCoordination(), character.getIntelligence(), character.getAgility());
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size()-1; j++)
                System.out.print(lists.get(i).get(j) + "-->");
            System.out.println(lists.get(i).get(lists.get(i).size() - 1));
        }
        System.out.println("Times to reach location: " + map.timeToReach(lists.get(0)));
    }

    private static void realBattle(Character character){
        Random rand = new Random();
        PriorityQueue<Titan> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o2.getDangerRisk(), o1.getDangerRisk());});
        
        int numOfTitan = getInput(1, Integer.MAX_VALUE, "Number of titan: ");
        if(numOfTitan == 1)
            System.out.println("\nGenerating 1 titan.");
        else
            System.out.println("\nGenerating " + numOfTitan + " titans.");
        
        int i;
        for(i = 1; i <= numOfTitan; i++){
            int c = rand.nextInt(10);
            Titan titan;
            if(c < 7)
                titan = new NormalTitan();
            else if(c < 9)
                titan = new AbnormalTitan();
            else
                titan = new NineTitan();
            titan.index = i;
            titan.position = rand.nextInt(15) + 1;
            System.out.println("Titan " + i + ": " + titan.toString() + " at " + titan.position);
            pq.add(titan);
        }
        System.out.println();

        int prevPos = 0;
        while(!pq.isEmpty()){
            Titan titan = pq.poll();
            if(character.getStrength() + character.getAgility() < titan.getDangerRisk()){
                System.out.println("Titan " + titan.index + " is too danger." + character.getName() + " run.");
                return;
            }
            System.out.println("Killing titan " + titan.index);
            if(prevPos == titan.position)
                continue;
            
            ArrayList<Integer> path = map.realBattle(prevPos, titan.position, character.getCoordination(), character.getIntelligence(), character.getAgility()).get(0);
            for(int j = 0; j < path.size(); j++){
                if(j == path.size()-1)
                    System.out.println(path.get(j));
                else
                    System.out.print(path.get(j) + " --> ");
                
                prevPos = path.get(j);
                if(rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean()){
                    if(prevPos != titan.position)
                        pq.add(titan);
                
                    System.out.println("\nA new titan appear!!!");
                    int c = rand.nextInt(10);
                    if(c < 7)
                        titan = new NormalTitan();
                    else if(c < 9)
                        titan = new AbnormalTitan();
                    else
                        titan = new NineTitan();
                    titan.index = i;
                    titan.position = rand.nextInt(15) + 1;
                    System.out.println("Titan " + i + ": " + titan.toString() + " at " + titan.position);
                    pq.add(titan);
                    i++;
                    break;
                }
            }
        }        
    }

    private static void MarleyWordConverter(){
        System.out.println("This page can help you to convert Marley sentence to English. ");
        Converter converter = new Converter();

        //    oh(ldchc$ebdccd$rl)
        //    rsgc(qqd^i$tkz)$ko$^udzhd,(rld$sgk^z$)$^gpssld
        //    ^ukgc$rd(vsq$gh$zshrqkg$gwkzsml)h$dbeszudl
        while (true) {
            System.out.print("Enter Marley sentence: ");
            String from = input.nextLine();
            String to = converter.convert(from);
            System.out.println(to);
            if(wantToQuit())
                return;
        }
    }

    private static void protectingWallOfMaria(){
        System.out.println("This page can help you determine the weakest part of Wall of Maria.");
        while(true){
            List<List<Integer>> lists = new LinkedList<>();

            int num = getInput("Enter number of layers: "); 
            while(num-- > 0){
                System.out.print("Enter bricks of layers: ");
                try(Scanner scanner = new Scanner(input.nextLine());){
                    List<Integer> list = new LinkedList<>();
                    while(scanner.hasNext()){
                        int temp = scanner.nextInt();
                        if(temp < 0)
                            throw new InputMismatchException();
                        list.add(temp);
                    }
                    lists.add(list);
                }catch(Exception e){
                    System.out.println("Please enter positive integer that separates with white blank only. ");
                    num++;
                }
            }

            System.out.println("\nWeakest part of the wall is at position " + Wall.weakPoint(lists));
            if(wantToQuit())
                return;
        }
    }

    private static boolean wantToQuit(){
        System.out.print("Enter Y if you want to stay at this page: ");
        String line = input.nextLine();
        if(line.length() == 0 || line.charAt(0) != 'Y')
            return true;
        return false;
    }

    private static int getInput(int from, int to, String str){
        System.out.print(str);
        while(true){
            try{
                int res = input.nextInt();
                if(res >= from && res <= to){
                    input.nextLine();
                    return res;
                }
                else 
                    throw new InputMismatchException();
            }catch(Exception e){
                input.nextLine();
                if(from == 1 && to == Integer.MAX_VALUE)
                    System.out.println("Please enter an positive integer. ");
                else
                    System.out.println("Please enter integer between " + from + " to " + to + ". ");
                System.out.print(str);
            }
        }
    }

    private static int getInput(String str){
        return getInput(0, Integer.MAX_VALUE, str);
    }
}