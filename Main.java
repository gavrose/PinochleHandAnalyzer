import java.sql.SQLOutput;
import java.util.*;
public class Main {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        //Collect user hand information//
        boolean tempNinesIn;
        while (true) {
            System.out.println("Are you playing with 9s in the game? (y/n)");
            String tempAnswer = sc.nextLine().trim();
            if (tempAnswer.equalsIgnoreCase("y")) {
                tempNinesIn = true;
                break;
            } else if (tempAnswer.equalsIgnoreCase("n")) {
                tempNinesIn = false;
                break;
            } else {
                System.out.println("\nEnter y or n.\n");
            }
        }
        int tempHandSize;
        while (true){
            System.out.println("\nHow many cards are in your hand?");
            if (sc.hasNextInt()){
                tempHandSize = sc.nextInt();
                if (tempHandSize <= 0){
                    System.out.println("Please enter of cards greater than 0.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number of cards greater than 0.");
                sc.next();
            }
        }

        Hand hand = new Hand(tempHandSize,tempNinesIn);
        HandAnalyzer analyzer = new HandAnalyzer(hand.getHand());
        System.out.println("\n---------------------------------------------------\nALL MELD COMBINATIONS -- THIS DOES NOT TAKE TRUMP RULES INTO ACCOUNT:\n");
        System.out.println(analyzer);
        System.out.println("---------------------------------------------------");
        if (!analyzer.toString().equals("Nothing.")){// if there is meld
            boolean validInput = false;
            while (!validInput){
                System.out.println("What is trump? (Enter C, D, H, or D");
                String trump = sc.next();
                if (hand.checkValidSuit(trump)){
                    validInput = true;
                    analyzer.setTrump(trump);
                    System.out.println("\n\n---------------------------------------------------\nMELD TAKING TRUMP INTO ACCOUNT:\n");
                    System.out.println(analyzer);
                    System.out.println("---------------------------------------------------");
                } else {
                    System.out.println("\nPlease enter the suit of trump (C, D, H, or D).");
                }
            }
        }

    }
}
