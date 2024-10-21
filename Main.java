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
        System.out.println("\n---------------------------------------------------\nALL MELD COMBINATIONS:\n");
        System.out.println(analyzer);
        System.out.println("---------------------------------------------------");

        System.out.println("\n\n\nNOTE:\n\tTHIS PROGRAM FINDS ALL POSSIBLE MELD COMBINATIONS.\n\t- IT DOES NOT CALCULATE THE POINTS OF THE GIVEN HAND.\n\t- IT DOES NOT ACCOUNT FOR TRUMP OR RULES THAT WOULD OTHERWISE NEGATE CERTAIN MELD (EX. MARRIAGE WITHIN A RUN).\n\tBE CAREFUL WHEN CALCULATING YOUR SCORE.");
    }
}
