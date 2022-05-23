package Character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class CharacterList  {

    LinkedList<String> character = new LinkedList<>();
    
    public CharacterList() throws FileNotFoundException {
        try (
            Scanner s = new Scanner (new FileReader("CharacterList.txt"))) {
            while(s.hasNextLine()) {
                String theList = s.nextLine();
                character.add(theList); //Add theList to the LinkedList "character"
            }
        }
    }
    
    
    //print new character from input into CharacterList txt file
    public void addCharacter() {
        try(FileWriter fw = new FileWriter ("CharacterList.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);){
            
                out.println();      
                out.close();
                }  catch (IOException e){
            }
           
    } 
    
    
    
    
    //remove character from text file and linked list
    public void removeCharacter() throws FileNotFoundException, IOException{
        Scanner in = new Scanner(System.in);
        File inputFile = new File("CharacterList.txt");
        File tempFile = new File("myTemp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            
            String currentLine;
            
            //remove the whole line based on name from user input
            String lineToRemove;
            System.out.println("Enter the name you wish to delete/update: ");
            lineToRemove = in.next();
            
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.startsWith(lineToRemove)) continue;
                System.out.println(trimmedLine);
                writer.write((currentLine) + System.getProperty("line.separator"));
            }
        }
        boolean successful;
        Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        successful = true;   
            
    }       

    
    
    
    
    public Character[] sortWeight(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getWeight(), o2.getWeight());});
        return array;
    }
    
    public Character[] sortHeight(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getHeight(), o2.getHeight());});
        return array;
    }
    
    public Character[] sortStrength(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getStrength(), o2.getStrength());});
        return array;
    }
    
    public Character[] sortAgility(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getAgility(), o2.getAgility());});
        return array;
    }
    
    public Character[] sortIntelligence(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getIntelligence(), o2.getIntelligence());});
        return array;
    }
    
    public Character[] sortCoordination(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getCoordination(), o2.getCoordination());});
        return array;
    }
    
    public Character[] sortLeadership(){
        Character[] array = (Character[])character.toArray();
        Arrays.sort(array, (o1, o2)->{return Integer.compare(o1.getLeadership(), o2.getLeadership());});
        return array;
    }
    
    public static int binarySearchWeight(Character[] array, int weight){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getWeight()==weight) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getWeight() > weight) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getWeight() < weight) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
    public static int binarySearchHeight(Character[] array, int height){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getHeight()==height) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getHeight() > height) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getHeight() < height) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
    public static int binarySearchStrength(Character[] array, int strength){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getStrength()==strength) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getStrength() > strength) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getStrength() < strength) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
    public static int binarySearchAgility(Character[] array, int agility){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getAgility()==agility) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getAgility() > agility) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getAgility() < agility) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
    public static int binarySearchIntelligence(Character[] array, int intelligence){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getIntelligence()==intelligence) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getIntelligence() > intelligence) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getIntelligence() < intelligence) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
    public static int binarySearchCoordination(Character[] array, int coordination){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getCoordination()==coordination) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getCoordination() > coordination) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getCoordination() < coordination) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
    public static int binarySearchLeadership(Character[] array, int leadership){
        int low = 0;
        int high = array.length -1;
        
	while (low <= high) {

		// Finding the mid using floor division
		int mid = low + (high - low) / 2;

		// Target value is present at the middle of the array
		if (array[mid].getLeadership()==leadership) 
			return mid;
		
		// Target value is present in the low subarray
		else if (array[mid].getLeadership() > leadership) {
			high = mid - 1;
		}

		// Target value is present in the high subarray
		else if (array[mid].getLeadership() < leadership) {
			low = mid + 1;
		}
	}
	// Target value is not present in the array
	return -1;  
    }
    
  
    

}
