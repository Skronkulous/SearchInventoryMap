package com.pluralsight;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;

public class StoreApp {
    public static HashMap<Integer,Product> inventoryList = new HashMap<Integer, Product>();

    public static void main(String[] args) {
        loadInventory();
    }
    public static void loadInventory(){
        try{
            FileReader fr = new FileReader("src/main/resources/inventory.csv");
            BufferedReader br = new BufferedReader(fr);
            String tempInfo = "";
            while ((tempInfo = br.readLine()) != null) {
                String[] splitLine = tempInfo.split("\\|");
                int id = (Integer.parseInt(splitLine[0].trim()));
                String name = (splitLine[1]);
                double price = Double.parseDouble(splitLine[2]);
                Product newProduct = new Product(id, name, price);
                inventoryList.put(id, newProduct);
            }
            fr.close();
            br.close();
            menu();
        }
        catch(Exception e){
            System.out.println("There seems to be a problem with the current file path, please update and try again.");
            e.printStackTrace();
        }
    }
    public static void searchItem(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("List of available key/IDs: ");
        for(HashMap.Entry<Integer, Product> entry :inventoryList.entrySet()){
            System.out.println("Key/ID: " + entry.getKey());
        }
        System.out.print("Please enter the key/ID of the item you are interested in: ");
        int choice = userInput.nextInt();
        int temp = 0;
        for(HashMap.Entry<Integer, Product> entry :inventoryList.entrySet()){
            if(entry.getKey() == choice){
                System.out.println("\nKey/ID: " + entry.getKey() + " | Name: " + entry.getValue().getName() + " | Price: " + entry.getValue().getPrice());
                System.out.println();
                menu();
                break;
            }
            else if(temp == inventoryList.size()-1){
                System.out.println("\nWe could not find that Key/ID in our system, please try again!\n");
                searchItem();
            }
            else{
                temp++;
            }
        }
    }
    public static void menu(){
        Scanner menuInput = new Scanner(System.in);
        System.out.print("Welcome! Please choose an option (1 or 2).\n\t1)Search by Key/ID\n\t2)Exit Program\nUser Input: ");
        String choice = menuInput.nextLine();
        switch(choice){
            case ("1"):
                System.out.println();
                searchItem();
                break;
            case ("2"):
                System.out.println("\nSee you again soon!");
                break;
            default:
                System.out.println("\nPlease enter a valid option (1 or 2).\n");
                menu();
                break;
        }
    }
}
