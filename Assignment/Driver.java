import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Wall.Wall;
import Graph.ParadisMap;

public final class Driver {
    private static Scanner input = new Scanner(System.in);
    private static ParadisMap map = new ParadisMap();
    public static void main(String[] args) {
    }

    private static void scountingMissionInWall(){
        System.out.println("This page can help you to find a scounting path(Hamiltonian Cycle) started from any given point.");
        while(true){
            int start = getInput(0, 15, "Enter starting point: ");
            List<Integer> path = map.HamiltonianCycle(start);
            for(int i = 0; i < path.size()-1; i++)
                System.out.print(path.get(i) + "-->");
            System.out.println(path.get(path.size() - 1));

            System.out.print("Enter Y if you want to stay at this page: ");
            String line = input.nextLine();
            if(line.length() == 0 || line.charAt(0) != 'Y')
                return;
        }
    }

    private static void bestPathToKillTitan(){
        ArrayList<ArrayList<Integer>> lists;
        System.out.println("This page can help you to find a shortest path to kill titan.");
        while(true){
            System.out.println("1. Titan in constant location.\n2. Titan will move.\n3. Choose a character to kill titan\n4. Quit");
            switch(getInput(1, 4, "Enter your choice: ")){
                case 1:
                    killFixedTitan();
                    break;
                case 2:
                    killMovedTitan();
                    break;
                case 3:
                    //No complete!!!
                    break;
                case 4:
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
