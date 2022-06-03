import java.io.FileNotFoundException;
import java.util.*;

import Character.CharacterList;
import Character.Character;
import Converter.Converter;
import Wall.Wall;
import Graph.ParadisMap;
import Titan.*;

public final class Driver {
    public static Scanner input = new Scanner(System.in);
    public static ParadisMap map = new ParadisMap();
    public static CharacterList characterList;
    public static Character[] sortedStrength;
    public static Character[] sortedAgility;
    public static Character[] sortedIntelligence;
    public static Character[] sortedCoordination;
    public static Character[] sortedLearderShip;
    public static Character[] sortedHeight;
    public static Character[] sortedWeight;
    public static boolean sorted = false;;

    public static void main(String[] args) {
        System.out.println("-----Attack Of Titan-----");

        while(true){
            System.out.println("1. Add charater in files\n2. Sort and find a character\n3. Evualate titan and determine the killing priority");
            System.out.println("4. Find a scounting path(Hamiltonian Cycle)\n5. Find a shortest path to kill titan\n6. Convert Marley sentence to English");
            System.out.println("7. Determine the weakest part of Wall of Maria\n8. Quit");
            switch(DriverHelpher.getInput(1, 8, "Enter your choice: ")){
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
                    if(DriverHelpher.wantToQuit())
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

                System.out.println("Characters is sucessfully added.");
                if(DriverHelpher.wantToQuit())
                    return;
            }catch(Exception e){
                if(e instanceof FileNotFoundException){
                    System.out.println("File not exist.");
                    return;
                }
                else
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
                    sortedHeight = characterList.sortHeight();
                    sortedWeight = characterList.sortWeight();
                    sorted = true;
                }
                int temp = DriverHelpher.getInput(1, 2, "Enter your choice: ");
                switch(temp){
                    case 1:
                        DriverHelpher.sort();
                        break;
                    case 2:
                        DriverHelpher.search();
                        break;
                    case 3:
                        return;
                }

                if(DriverHelpher.wantToQuit())
                    return;
            }catch(FileNotFoundException e){
                System.out.println("File not exist");
                return;
            }
        }
    }

    private static void titanEvualateAndKillingPriority(){
        System.out.println("This page can help you to evualate titan and determine the killing priority.");
        System.out.println("1. Start with defualt\n2. Choose a character");
        try{
            Character character = (DriverHelpher.getInput(1,2, "Enter your choice :") == 2)? DriverHelpher.choseCharacter() :null;
            Random rand = new Random();
            PriorityQueue<Titan> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o2.getDangerRisk(), o1.getDangerRisk());});
            
            while(true){
                int numOfTitan = DriverHelpher.getInput(1, Integer.MAX_VALUE, "Number of titan: ");
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
                        break;
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
                if(DriverHelpher.wantToQuit())
                    return;
            }
        }catch(FileNotFoundException e){
            System.out.println("File not exist");
        }
    }

    private static void scountingMissionInWall(){
        System.out.println("This page can help you to find a scounting path(Hamiltonian Cycle) started from any given point.");
        while(true){
            int start = DriverHelpher.getInput(0, 15, "Enter starting point: ");
            List<Integer> path = map.HamiltonianCycle(start);
            for(int i = 0; i < path.size()-1; i++)
                System.out.print(path.get(i) + "-->");
            System.out.println(path.get(path.size() - 1));

            if(DriverHelpher.wantToQuit())
                return;
        }
    }

    private static void bestPathToKillTitan(){
        System.out.println("This page can help you to find a shortest path to kill titan.");
        while(true){
            System.out.println("1. Titan in constant location.\n2. Titan will move.\n3. Choose a character to kill titan\n4. Real Battle\n5. Quit");
            switch(DriverHelpher.getInput(1, 4, "Enter your choice: ")){
                case 1:
                    DriverHelpher.killFixedTitan();
                    break;
                case 2:
                    DriverHelpher.killMovedTitan();
                    break;
                case 3:
                    try{
                        Character character = DriverHelpher.choseCharacter();
                        DriverHelpher.killFixedTitan(character);
                        break;
                    }catch(FileNotFoundException e){
                        System.out.println("Charater files not exist. ");
                        break;
                    }
                case 4:
                    try{
                        Character character = DriverHelpher.choseCharacter();
                        DriverHelpher.realBattle(character);
                        break;
                    }catch(FileNotFoundException e){
                        System.out.println("Charater files not exist. ");
                        break;
                    }
                case 5:
                    return;
            }

            if(DriverHelpher.wantToQuit())
                return;
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
            if(DriverHelpher.wantToQuit())
                return;
        }
    }

    private static void protectingWallOfMaria(){
        System.out.println("This page can help you determine the weakest part of Wall of Maria.");
        while(true){
            List<List<Integer>> lists = new LinkedList<>();

            int num = DriverHelpher.getInput("Enter number of layers: "); 
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
            if(DriverHelpher.wantToQuit())
                return;
        }
    }
}