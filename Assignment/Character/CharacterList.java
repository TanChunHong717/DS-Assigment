package Character;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class CharacterList {
    LinkedList<Character> character = new LinkedList<>();
    
    public CharacterList() throws FileNotFoundException {                   
        try (
            Scanner s = new Scanner (new FileReader("Character\\CharacterList.txt"))) {
            while(s.hasNextLine()) {
                String record = s.nextLine();
                if(record.length() == 0)
                    return;
                String[] field = record.split(" ");
                
                String name = field[0]+" "+field[1];
                int height = Integer.parseInt(field[2]);
                int weight = Integer.parseInt(field[3]);
                int strength = Integer.parseInt(field[4]);
                int agility = Integer.parseInt(field[5]);
                int intelligence = Integer.parseInt(field[6]);
                int coordination = Integer.parseInt(field[7]);
                int leadership = Integer.parseInt(field[8]);
                
                Character c = new Character (name, height, weight, strength, agility, intelligence, coordination, leadership);
                
                character.add(c);
            }
        }
    }
    
    
    //add new character into linked list
    public void addCharacter(Character o) {
        character.add(o);
    } 
    
    //remove character from linked list                              
    public Character removeCharacter(String name){
        //remove from linked list
        for(int i = 0; i < character.size(); i++)
            if(character.get(i).getName().equals(name))
                return character.remove(i);
        
        return null;
    }     

    //Check character in the list or not
    public boolean contains(String name){
        for(int i = 0; i < character.size(); i++)
            if(character.get(i).getName().equals(name))
                return true;
        
        return false;
    }

    public Character getCharacter(String name){
        for(int i = 0; i < character.size(); i++)
            if(character.get(i).getName().equals(name))
                return character.get(i);
        
        return null;
    }
    
    //get new Character and store in text file
    public void close (){
        try{
            FileWriter file = new FileWriter("Character\\CharacterList.txt", false);
            
            for(int i = 0; i < character.size(); i++){
                Character charac = character.get(i);
                file.write(charac.getName() + " ");
                file.write(charac.getHeight() + " ");
                file.write(charac.getWeight() + " ");
                file.write(charac.getStrength() + " ");
                file.write(charac.getAgility() + " ");
                file.write(charac.getIntelligence() + " ");
                file.write(charac.getCoordination() + " ");
                file.write(charac.getLeadership() + "\n");
            }
            file.close();
        }  catch (IOException e){
        }
    }
    
    
    
    //sort methods
    public Character[] sortWeight(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getWeight(), o2.getWeight());});
        return array;
    }
    
    public Character[] sortHeight(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getHeight(), o2.getHeight());});
        return array;
    }
    
    public Character[] sortStrength(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getStrength(), o2.getStrength());});
        return array;
    }
    
    public Character[] sortAgility(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getAgility(), o2.getAgility());});
        return array;
    }
    
    public Character[] sortIntelligence(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getIntelligence(), o2.getIntelligence());});
        return array;
    }
    
    public Character[] sortCoordination(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getCoordination(), o2.getCoordination());});
        return array;
    }
    
    public Character[] sortLeadership(){
        Character[] array = character.toArray(new Character[character.size()]);
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getLeadership(), o2.getLeadership());});
        return array;
    }
    
   //binary search methods
    public static Character[] binarySearchWeight(Character[] array, int weight){
        int low = leftbinarySearchWeight(array, weight)+1;
        int high = rightbinarySearchWeight(array, weight)+1;
        if(low == high && array[low].getWeight() != weight)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchWeight(Character[] array, int weight){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getWeight() > weight) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchWeight(Character[] array, int weight){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getWeight() < weight) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }
    
    public static Character[] binarySearchHeight(Character[] array, int height){
        int low = leftbinarySearchHeight(array, height)+1;
        int high = rightbinarySearchHeight(array, height)+1;
        if(low == high && array[low].getHeight() != height)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchHeight(Character[] array, int height){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getHeight() > height) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchHeight(Character[] array, int height){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getHeight() < height) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }
    
    public static Character[] binarySearchStrength(Character[] array, int strength){
        int low = leftbinarySearchStrength(array, strength)+1;
        int high = rightbinarySearchStrength(array, strength)+1;
        if(low == high && array[low].getStrength() != strength)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchStrength(Character[] array, int strength){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getStrength() > strength) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchStrength(Character[] array, int strength){

        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getStrength() < strength) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }

    public static Character[] binarySearchAgility(Character[] array, int agility){
        int low = leftbinarySearchAgility(array, agility)+1;
        int high = rightbinarySearchAgility(array, agility)+1;
        if(low == high && array[low].getAgility() != agility)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchAgility(Character[] array, int agility){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getAgility() > agility) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchAgility(Character[] array, int agility){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getAgility() < agility) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }

    public static Character[] binarySearchIntelligence(Character[] array, int intelligence){
        int low = leftbinarySearchIntelligence(array, intelligence)+1;
        int high = rightbinarySearchIntelligence(array, intelligence)+1;
        if(low == high && array[low].getIntelligence() != intelligence)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchIntelligence(Character[] array, int intelligence){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getIntelligence() > intelligence) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchIntelligence(Character[] array, int intelligence){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getIntelligence() < intelligence) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }

    public static Character[] binarySearchCoordination(Character[] array, int coordination){
        int low = leftbinarySearchCoordination(array, coordination)+1;
        int high = rightbinarySearchCoordination(array, coordination)+1;
        if(low == high && array[low].getCoordination() != coordination)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchCoordination(Character[] array, int coordination){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getCoordination() > coordination) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchCoordination(Character[] array, int coordination){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getCoordination() < coordination) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }
    
    public static Character[] binarySearchLeaderShip(Character[] array, int leadership){
        int low = leftbinarySearchLeaderShip(array, leadership)+1;
        int high = rightbinarySearchLeaderShip(array, leadership)+1;
        if(low == high && array[low].getLeadership() != leadership)
            return null;
        return Arrays.copyOfRange(array, low, high);
    }

    private static int rightbinarySearchLeaderShip(Character[] array, int leadership){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getLeadership() > leadership) 
			      high = mid - 1;
		        else
			      low = mid + 1;
	      }
	      return (low+high)/2;  
    }
    
    private static int leftbinarySearchLeaderShip(Character[] array, int leadership){
        int low = 0;
        int high = array.length -1;
        
	      while (low <= high) {
		        int mid = (low + high) / 2;

		        if (array[mid].getLeadership() < leadership) 
			      low = mid + 1;
		        else
			      high = mid - 1;
	      }
	      return (low+high)/2;  
    }
}