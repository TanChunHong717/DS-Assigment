import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

import Character.CharacterList;
import Titan.AbnormalTitan;
import Titan.NineTitan;
import Titan.NormalTitan;
import Titan.Titan;
import Character.Character;

public class DriverHelpher {
    public static boolean wantToQuit(){
        System.out.print("Enter Y if you want to stay at this page: ");
        String line = Driver.input.nextLine();
        if(line.length() == 0 || line.charAt(0) != 'Y')
            return true;
        return false;
    }

    public static int getInput(int from, int to, String str){
        System.out.print(str);
        while(true){
            try{
                int res = Driver.input.nextInt();
                if(res >= from && res <= to){
                    Driver.input.nextLine();
                    return res;
                }
                else 
                    throw new InputMismatchException();
            }catch(Exception e){
                Driver.input.nextLine();
                if(from == 1 && to == Integer.MAX_VALUE)
                    System.out.println("Please enter an positive integer. ");
                else
                    System.out.println("Please enter integer between " + from + " to " + to + ". ");
                System.out.print(str);
            }
        }
    }

    public static int getInput(String str){
        return getInput(0, Integer.MAX_VALUE, str);
    }

    public static Character choseCharacter() throws FileNotFoundException{
        if(Driver.characterList == null)
            Driver.characterList = new CharacterList();

        while(true){
            System.out.print("Enter the character's name: ");
            Character character = Driver.characterList.getCharacter(Driver.input.nextLine().trim());
            if(character == null)
                System.out.println("Character not exist.");
            else
                return character;
        }
    }

    public static void sort(){
        System.out.println("1. Height\n2. Weight\n3. Strength\n4. Agility\n5. Intelligence\n6. Coordination\n7. Leadership");
        switch(getInput(1, 7, "Enter your choice:")){
            case 1:
                for(Character character : Driver.sortedHeight)
                    System.out.println(character.getName() + " " + character.getHeight());
                break;
            case 2:
                for(Character character : Driver.sortedWeight)
                    System.out.println(character.getName() + " " + character.getWeight());
                break;
            case 3:
                for(Character character : Driver.sortedStrength)
                    System.out.println(character.getName() + " " + character.getStrength());
                break;
            case 4:
                for(Character character : Driver.sortedAgility)
                    System.out.println(character.getName() + " " + character.getAgility());
                break;
            case 5:
                for(Character character : Driver.sortedIntelligence)
                    System.out.println(character.getName() + " " + character.getIntelligence());
                break;
            case 6:
                for(Character character : Driver.sortedCoordination)
                    System.out.println(character.getName() + " " + character.getCoordination());
                break;
            case 7:
                for(Character character : Driver.sortedLearderShip)
                    System.out.println(character.getName() + " " + character.getLeadership());
                break;
        }
    }

    public static void search(){
        System.out.println("1. Height\n2. Weight\n3. Strength\n4. Agility\n5. Intelligence\n6. Coordination\n7. Leadership");
        Character[] characters;
        int val;

        switch(getInput(1, 7, "Enter your choice: ")){
            case 1:
                val = getInput("Height: ");
                characters = CharacterList.binarySearchHeight(Driver.sortedHeight, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getHeight());
                else
                    System.out.println("No such a character exist.");
                break;
            case 2:
                val = getInput("Weight: ");
                characters = CharacterList.binarySearchStrength(Driver.sortedWeight, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getWeight());
                else
                    System.out.println("No such a character exist.");
                break;
            case 3:
                val = getInput(0, 12,"Strength: ");
                characters = CharacterList.binarySearchStrength(Driver.sortedStrength, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getStrength());
                else
                    System.out.println("No such a character exist.");
                break;
            case 4:
                val = getInput(0, 12, "Agility: ");
                characters = CharacterList.binarySearchAgility(Driver.sortedAgility, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getAgility());
                else
                    System.out.println("No such a character exist.");
                break;
            case 5:
                val = getInput(0, 12, "Intelligence: ");
                characters = CharacterList.binarySearchIntelligence(Driver.sortedIntelligence, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getIntelligence());
                else
                    System.out.println("No such a character exist.");
                break;
            case 6:
                val = getInput(0, 12, "Coordination: ");
                characters = CharacterList.binarySearchCoordination(Driver.sortedCoordination, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getCoordination());
                else
                    System.out.println("No such a character exist.");
                break;
            case 7:
                val = getInput(0, 12, "Leadership: ");
                characters = CharacterList.binarySearchLeaderShip(Driver.sortedLearderShip, val);
                if(characters != null)
                    for(Character character : characters)
                        System.out.println(character.getName() + " " + character.getLeadership());
                else
                    System.out.println("No such a character exist.");
                break;

            }
    }

    public static void killFixedTitan(){
        int position = getInput(0, 15, "Enter location of titan: ");
        ArrayList<ArrayList<Integer>> lists = Driver.map.BestPathToKillTitan(position);
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size()-1; j++)
                System.out.print(lists.get(i).get(j) + "-->");
            System.out.println(lists.get(i).get(lists.get(i).size() - 1));
        }
        System.out.println("Times to reach location: " + Driver.map.timeToReach(lists.get(0)));
    }

    public static void killMovedTitan(){
        int[] move = null;
        while(move == null){
            System.out.print("Enter the locations of titan(separated by white blank): ");
            try(Scanner scanner = new Scanner(Driver.input.nextLine());){    
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
        ArrayList<ArrayList<Integer>> lists = Driver.map.BestPathToKillTitan1(move);
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size()-1; j++)
                System.out.print(lists.get(i).get(j) + "-->");
            System.out.println(lists.get(i).get(lists.get(i).size() - 1));
        }
        System.out.println("Times to kill titan: " + Driver.map.timeToKillTitan(lists.get(0), move));
    }

    public static void killFixedTitan(Character character){
        int position = getInput(0, 15, "Enter location of titan: ");
        ArrayList<ArrayList<Integer>> lists = Driver.map.BestPathToKillTitan2(position, character.getCoordination(), character.getIntelligence(), character.getAgility());
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size()-1; j++)
                System.out.print(lists.get(i).get(j) + "-->");
            System.out.println(lists.get(i).get(lists.get(i).size() - 1));
        }
        System.out.println("Times to reach location: " + Driver.map.timeToReach(lists.get(0)));
    }

    public static void realBattle(Character character){
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
                titan = new NormalTitan();
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
            
            ArrayList<Integer> path = Driver.map.realBattle(prevPos, titan.position, character.getCoordination(), character.getIntelligence(), character.getAgility()).get(0);
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
}