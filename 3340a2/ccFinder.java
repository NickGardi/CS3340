//Nicholas Gardi - 250868721

import java.io.*;
import java.util.*;

public class ccFinder {

	static int rows = 71;
    static int columns = 71;
    static char[][]matrix=new char[rows][columns]; //71x71 matrix
    static uandf uandf = new uandf(7071);  //union-find for the girl image

    
    
    
    public static void main(String[] args) throws FileNotFoundException {
        //PART 1 - read in and print out the input binary image
    	System.out.println("PART 1 - the input binary image \n");
        File girlFile = new File("girl.img.txt");
    	try {
            Scanner scanner = new Scanner(girlFile);
            for (int row = 0; scanner.hasNextLine() && row < rows; row++) {
                char[] chars = scanner.nextLine().toCharArray();
                for (int column = 0; column < columns && column < chars.length; column++) {
                    matrix[row][column] = chars[column];
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
        for (int i = 0; i < matrix.length; i++){
            System.out.println(matrix[i]);
        }
        
        
        
        
        
        //PART 2 - the connected component image where each component is labelled with a 
        //unique character (a-z)
        //use union-find data structure to make 'forests' for all positions in the graph that = "+"
    	System.out.println("\n PART 2 - the connected component image where each component is labelled with a unique character (a-z) \n");
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if (matrix[row][column] == "+".charAt(0)) { 
                	//if needed, pad ints with a 0 to make coordinates of form RRCC ex: 0250
                    String rowFormatted = String.format("%02d", row);
                    String columnFormatted = String.format("%02d", column);
                    String coordinateFormatted = rowFormatted+columnFormatted;
                    int coordinateInt = Integer.parseInt(coordinateFormatted);
                    //System.out.println(coordinateInt);
                    uandf.make_set(coordinateInt);
                }
            }
        }
        
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if (matrix[row][column] == "+".charAt(0)){
                    try {
                    	unionUp(row,column);
                    	unionDown(row,column);                    	
                    	unionLeft(row,column); 
                    	unionRight(row,column);
                    }catch (Exception e){
                        //System.out.println("Exception");
                    }
                }
            }
        }
        //printing
        //start at "A"
        int asciiCode = 65;   
        char[] list = new char[7071]; 
        int [] part3Array = new int[7071];
        int counter = 0;
        for (int row=0; row < 71; row++){
            for (int column = 0; column < 71; column++){
                if (matrix[row][column] == "+".charAt(0)) {
                	//format coordinate...**make function for this
                	String rowFormatted = String.format("%02d", row);
                    String columnFormatted = String.format("%02d", column);
                    String coordinateFormatted = rowFormatted+columnFormatted;
                    int coordinateInt = Integer.parseInt(coordinateFormatted);
                    int listItem = uandf.find_set_element(coordinateInt); 

                    //if the list item is not in the list then add it and convert to the next ascii char
                    if (list[listItem] == 0) {      
                        list[listItem] = (char) asciiCode;
                        //increment ascii code 
                        asciiCode += 1;
                    }
                    System.out.print(list[listItem]);
                    char x = list[listItem];
                    int ascii = (int) x;
                    part3Array[counter] = ascii;
                    counter++;                 
                }
                else{
                    System.out.printf(" ");
                }
            }
            System.out.printf("%n");
        } 	       
        //PART 3 -  a list sorted by component size, where each line of the list contains the size and the label of a component
        //hasmap that contains the ascii values and how often they occur in the second image
        Map<Integer,Integer> hmap = new HashMap();
        for(int x:part3Array){

            if(!hmap.containsKey(x)){
            	hmap.put(x,1);
            }else{
            	hmap.put(x, hmap.get(x)+1);
            }
        }
        //sorting the hmap and formatting the printing
        Map<Integer, String> map = sortByValues(hmap); 
        System.out.println("\n PART 3 - a list sorted by component size, where each line of the list contains the size and the label of a component \n");
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
             Map.Entry me2 = (Map.Entry)iterator2.next();
             int ascii = (int) me2.getKey();
             System.out.print((char)ascii + ": ");
             System.out.println(me2.getValue());
        }
    }

    
    //PART 4 -  same as part 2 with the connected component whose size equals to one or two removed
    
    
    
    
    
    //sort low to high, used for part 3
    public static HashMap sortByValues(Map<Integer, Integer> hmap) { 
        List list = new LinkedList(hmap.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                   .compareTo(((Map.Entry) (o2)).getValue());
             }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        return sortedHashMap;
   }
    
    //returns how many unique values are in an array, used in part 3
    public static int diffValues(int[] numArray){
        int numOfDifferentVals = 0;

        ArrayList<Integer> diffNum = new ArrayList<>();

        for(int i=0; i<numArray.length; i++){
            if(!diffNum.contains(numArray[i])){
                diffNum.add(numArray[i]);
            }
        }

        if(diffNum.size()==1){
                numOfDifferentVals = 0;
        }
        else{
              numOfDifferentVals = diffNum.size();
            } 

       return numOfDifferentVals;
    }

    //used to 
    
    //next 4 functions: union the char in a specific direction 
    public static void unionUp(int row, int column) {
    	if (matrix[row-1][column] == "+".charAt(0)) {
    		//first coordinate in union_set
    		String row1Formatted = String.format("%02d", row);
            String column1Formatted = String.format("%02d", column);
            String coordinate1Formatted = row1Formatted+column1Formatted;
            int coordinate1Int = Integer.parseInt(coordinate1Formatted);
            //second coordinate in union_set
            String row2Formatted = String.format("%02d", row - 1);
            String column2Formatted = String.format("%02d", column);
            String coordinate2Formatted = row2Formatted+column2Formatted;
            int coordinate2Int = Integer.parseInt(coordinate2Formatted);
            //union
            uandf.union_sets(coordinate1Int, coordinate2Int);
        }
    }
    public static void unionDown(int row, int column) {
    	if (matrix[row + 1][column] == "+".charAt(0)) {
    		//first coordinate in union_set
    		String row1Formatted = String.format("%02d", row);
            String column1Formatted = String.format("%02d", column);
            String coordinate1Formatted = row1Formatted+column1Formatted;
            int coordinate1Int = Integer.parseInt(coordinate1Formatted);
            //second coordinate in union_set
            String row2Formatted = String.format("%02d", row + 1);
            String column2Formatted = String.format("%02d", column);
            String coordinate2Formatted = row2Formatted+column2Formatted;
            int coordinate2Int = Integer.parseInt(coordinate2Formatted);
            //union
            uandf.union_sets(coordinate1Int, coordinate2Int);
        }
    }
    public static void unionRight(int row, int column) {
    	if (matrix[row][column + 1] == "+".charAt(0)) {
    		//first coordinate in union_set
    		String row1Formatted = String.format("%02d", row);
            String column1Formatted = String.format("%02d", column);
            String coordinate1Formatted = row1Formatted+column1Formatted;
            int coordinate1Int = Integer.parseInt(coordinate1Formatted);
            //second coordinate in union_set
            String row2Formatted = String.format("%02d", row);
            String column2Formatted = String.format("%02d", column + 1);
            String coordinate2Formatted = row2Formatted+column2Formatted;
            int coordinate2Int = Integer.parseInt(coordinate2Formatted);
            //union
            uandf.union_sets(coordinate1Int, coordinate2Int);
        }
    }
    public static void unionLeft(int row, int column) {
    	if (matrix[row][column - 1] == "+".charAt(0)) {
    		//first coordinate in union_set
    		String row1Formatted = String.format("%02d", row);
            String column1Formatted = String.format("%02d", column);
            String coordinate1Formatted = row1Formatted+column1Formatted;
            int coordinate1Int = Integer.parseInt(coordinate1Formatted);
            //second coordinate in union_set
            String row2Formatted = String.format("%02d", row);
            String column2Formatted = String.format("%02d", column - 1);
            String coordinate2Formatted = row2Formatted+column2Formatted;
            int coordinate2Int = Integer.parseInt(coordinate2Formatted);
            //union
            uandf.union_sets(coordinate1Int, coordinate2Int);
        }
    }
    
}
