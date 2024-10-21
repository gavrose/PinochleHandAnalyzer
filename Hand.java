
import java.util.*;

public class Hand {

    private int handSize;
    private boolean nines;
    private ArrayList<Card> hand;

    public Hand(int size, boolean nines) {
        this.handSize = size;
        this.nines = nines;
        this.hand = new ArrayList<>(size);
        this.inputHand();
    }

    public boolean addCard(String input) {
        String[] parts = input.split(" "); // split at the space
        String rankInput = parts[0]; // first input of parts
        String suitInput = parts[1]; // next input of parts
        if (!checkValidRank(rankInput) || !checkValidSuit(suitInput)) { // if the rank or suit is invalid is invalid
            System.out.println("No card was added.");
            return false;
        }
        Card card = new Card(rankInput, suitInput); //creates card if values are valid
        this.hand.add(card); // add the new Card if inputs are valid
        System.out.println(card + " added to hand.\n");
        return true;
    }

    public void removeCard(int idx) { // removes card based on index
        this.hand.remove(idx);
    }

//    public boolean removeCard(Card card){
//        this.hand.remove(card);
//
//    }

    public boolean removeCard(String input) {
        String[] parts = input.split(" "); // split at the space
        String rankInput = parts[0]; // first input of parts
        String suitInput = parts[1]; // next input of parts
        if (!checkValidRank(rankInput) || !checkValidSuit(suitInput)) { // if the rank or suit is invalid is invalid
            System.out.println("No card was removed.");
            return false;
        }
        Card card = new Card(rankInput, suitInput); //creates card if values are valid
        if (!this.hand.contains(card)){ // if the hand doesnt contain the card to remove
            System.out.println("That card is not in your hand. No card was removed.");
            return false;
        }
        this.hand.remove(card); // remove the new Card if inputs are valid
        System.out.println(card + " removed from hand.\n");
        return true;
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    public int getHandSize() {
        return handSize;
    }

    public boolean isNines() {
        return nines;
    }

    public void setHandSize(int size) {
        this.handSize = size;
    }

    public void setNines(boolean nines) {
        this.nines = nines;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public boolean checkValidRank(String rank){
        rank = rank.toUpperCase();
        ArrayList<String> validRanks = new ArrayList<>(Arrays.asList("9", "10", "J", "Q", "K", "A"));
        if (!isNines()) { //if nines not in the game --> remove 9 as a valid rank
            validRanks.remove("9");
        }
        if (!validRanks.contains(rank.toUpperCase())){ //if the rank input is not in the list of valid ranks --> False
            if (isNines()) {
                System.out.println("Error: Invalid rank. Valid ranks: (9, 10, J, Q, K, A).");
            } else {
                System.out.println("Error: Invalid rank. Valid ranks (10, J, Q, K, A).");
            }
            return false;
        }
        return true;
    }

    public boolean checkValidSuit(String suit){
        suit = suit.toUpperCase();
        ArrayList<String> validSuits = new ArrayList<>(Arrays.asList("H", "S", "D", "C"));
        if (!validSuits.contains(suit.toUpperCase())){ //if the suit input is not in the list of valid suits --> False
            System.out.println("Error: Invalid suit. Valid suit inputs: (H, C, S, D).");
            return false;
        }
        return true;
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }

    public void clearHand(){ //set hand to a new empty array list
        setHand(new ArrayList<>());
        setHandSize(handSize);
        System.out.println("Hand: " + getHand());
    }

    public void inputHand() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < handSize; i++) {
            System.out.println("\nCards Added: " + i+"/"+this.handSize);
            System.out.println("\n_____________________________________________________________\nInput Options:\n\n• Undo = 'undo' --> undo the last card entry\n• Clear = 'clear' --> removes all cards you have entered\n• Card = 'rank suit' --> enter the first letter of the rank and suit.\n     Ex.  Jack of Diamonds = 'j d'      10 of clubs = '10 c'\n_____________________________________________________________\n");
            System.out.println("Input Card: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("clear")){
                clearHand();
                i = -1; // reset i to -1 so will increment to 0 on next iteration
            } else if (input.equalsIgnoreCase("undo")){
                if (i > 0){
                    removeCard(i-1); //--> remove the previous card added (i-1)
                    i = i - 2;  //set i to the before the previous card was added
                } else {
                    System.out.println("There is no card to remove. Please make sure there are cards to undo.");
                    i = i - 1;
                    continue;
                }
            } else if (input.split(" ").length == 2){
                if (!addCard(input)){ //if the card is not added
                    i = i-1;
                    continue;
                }
            } else {
                System.out.println("\n\nInvalid input. Please enter a card (rank suit), 'undo', or 'clear.");
                i = i-1;
                continue;
            }

//                String[] parts = input.split(" "); // split at the space
//                if (parts.length == 2){
//                    String rankInput = parts[0]; // first input of parts
//                    String suitInput = parts[1]; // next input of parts
//                    if (!checkValidRank(rankInput) || !checkValidSuit(suitInput)){ // if the rank or suit is invalid is invalid
//                        i = i-1; // dont iterate i
//                        continue; // Skip to the next iteration
//                    }
//                    Card card = new Card(rankInput, suitInput);
//                    addCard(card); // add the new Card if inputs are valid
//                    System.out.println(card + " added to hand.\n");
            System.out.println("\nYour Hand: \n" + this);
        }
    }

    public String toString() {
        String str = "[";
        for (int i = 0;i < hand.size();i++){
            str += hand.get(i);
            if (i+1 == hand.size()){
                str += "]";
            } else if ((i+1)%5 == 0){ //go to a new line every 6 cards
                str +=",\n";
            } else {
                str += ", ";
            }
        }
        return str;
    }
}





