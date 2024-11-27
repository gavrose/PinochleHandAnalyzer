
import java.util.*;
import java.math.*;

public class HandAnalyzer{
    private HashMap<Card, Integer> cardFrequencyMap = new HashMap<>();  // how many there are of each card  <card,frequency>

    private HashMap<String, Integer> cardsPerSuitMap = new HashMap<>();  // how many cards are in each suit  <Suit,cards>
    private HashMap<String, Integer> suitsWithRun = new HashMap<>(); // <suit, # of runs>
    private HashMap<String, Integer> suitsWithMarriage = new HashMap<>(); // <suit, # of marriages>
    private int pinochles;
    private int roundOfAces;
    private int roundOfKings;
    private int roundOfQueens;
    private int roundOfJacks;

    private String trump;

    public String getTrump(){return this.trump;}

    public void setTrump(String trump){this.trump = trump;}

    public int getPinochles(){
        return this.pinochles;
    }

    public void setPinochles(int n){
        this.pinochles = n;
    }
    public void setRoundOfAces(int n){
        roundOfAces = n;
    }

    public int getRoundOfAces(){
        return this.roundOfAces;
    }
    public void setRoundOfKings(int n){
        roundOfKings = n;
    }
    public int getRoundOfKings(){
        return this.roundOfKings;
    }
    public void setRoundOfQueens(int n){
        roundOfQueens = n;
    }
    public int getRoundOfQueens(){
        return this.roundOfQueens;
    }
    public void setRoundOfJacks(int n){
        roundOfJacks = n;
    }
    public int getRoundOfJacks(){
        return this.roundOfJacks;
    }


    public HandAnalyzer(List<Card> hand){
        handAnalysis(hand);
        checkPinochles(hand);
        checkRounds(hand);
        checkRuns(hand);
        checkMarriages(hand);
    }
    public void handAnalysis(List<Card> hand){
        for (Card card:hand){  //for each card in the hand
            cardsPerSuitMap.put(card.getSuit(), cardsPerSuitMap.getOrDefault(card.getSuit(),0)+1); // add to the hash map <suit,Frequency + 1>
        }
    }

    public void checkPinochles(List<Card> hand){
        int jackDiamondsCount = 0;
        int queenSpadesCount = 0;

        if (!cardsPerSuitMap.containsKey("D") || !cardsPerSuitMap.containsKey("S")){ // if there are no diamonds or no spades return 0
            setPinochles(0);
        }
        for (Card card:hand){
            if (card.getRank().equalsIgnoreCase("J") && card.getSuit().equalsIgnoreCase("D")){ // if card is jack of diamonds --> increment jack of diamonds
                jackDiamondsCount++;
            }
            if (card.getRank().equalsIgnoreCase("Q") && card.getSuit().equalsIgnoreCase("S")){ // if card is queen of spades --> increment queen of spades
                queenSpadesCount++;
            }
        }
        int p = Math.min(jackDiamondsCount,queenSpadesCount); //find min of jacks of diamonds and queen fo spades
        setPinochles(p); // set pinochles to spades
    }

    public void checkRounds(List<Card> hand){
        if (cardsPerSuitMap.size() != 4){ //if hand does not have all 4 suits
            setRoundOfAces(0);
            setRoundOfKings(0);
            setRoundOfQueens(0);
            setRoundOfJacks(0);
        } else {
            //Hashmap<Rank,Hashmap<Suit, Frequency>>
            HashMap<String,HashMap<String, Integer>> ranks = new HashMap<>();  // hashmap = <suit, <ranks, frequency>>
            // creates hashmap for each different rank we are checking (A, K, Q, J) to define the inner hashmap
            for (Card card:hand){
                if (card.getRank().equalsIgnoreCase("A")){  // if the card is an Ace
                    HashMap<String,Integer> suits = ranks.getOrDefault("A",new HashMap<>()); // create new hashmap to store each suit and the frequency -- this hashmap will be the inner hashmap for each rank
                    suits.put(card.getSuit(), suits.getOrDefault(card.getSuit(),0)+1); // add <suit, frequency + 1>
                    ranks.put("A",suits); // add to hashmap <rank<suit,frequency>>
                }
                if (card.getRank().equalsIgnoreCase("K")){
                    HashMap<String,Integer> suits = ranks.getOrDefault("K",new HashMap<>());
                    suits.put(card.getSuit(), suits.getOrDefault(card.getSuit(),0)+1);
                    ranks.put("K",suits);
                }
                if (card.getRank().equalsIgnoreCase("Q")){
                    HashMap<String,Integer> suits = ranks.getOrDefault("Q",new HashMap<>());
                    suits.put(card.getSuit(), suits.getOrDefault(card.getSuit(),0)+1);
                    ranks.put("Q",suits);
                }
                if (card.getRank().equalsIgnoreCase("J")){
                    HashMap<String,Integer> suits = ranks.getOrDefault("J",new HashMap<>());
                    suits.put(card.getSuit(), suits.getOrDefault(card.getSuit(),0)+1);
                    ranks.put("J",suits);
                }
            }
            for (String rank: ranks.keySet()) { // for each rank in the keys of ranks (A,K,Q,J)
                HashMap<String, Integer> suitsOfRound = ranks.get(rank);  //gets the inner hashmap for each rank which = <Suit,Frequency>
                if (suitsOfRound.size() == 4) { // if the rank we are checking has all 4 suits
                    int min = 10; // arbitrary high value to compare to min
                    for (Integer frequency: suitsOfRound.values()) {  // for each value in suitsOfRound = {suit,frequency}
                        if (frequency < min) {
                            min = frequency;
                        }
                    }
                    switch(rank) {
                        case "A":
                            setRoundOfAces(min);
                            break;
                        case "K":
                            setRoundOfKings(min);
                            break;
                        case "Q":
                            setRoundOfQueens(min);
                            break;
                        case "J":
                            setRoundOfJacks(min);
                            break;
                    }
                }
            }
        }
    }

    public void checkRuns(List<Card> hand) { // if the a suit has (A,10,K,Q,J)
        ArrayList<String> suitsToCheck = new ArrayList<>(); // create empty array list 
        for (String suit : cardsPerSuitMap.keySet()) {
            if (cardsPerSuitMap.get(suit) >= 5) { // if the suit has more than 5 cards in it add it to the suits we need to check for a run
                suitsToCheck.add(suit);
            }
        }
        for (String suit : suitsToCheck) { //for each suit we need to check
            HashMap<String,Integer> partsOfRun = new HashMap<>(); //new HashMap for each part of the run within the suit <rank,frequency>
            for (Card card : hand) { // for each card in the hand
                if (!card.getSuit().equals(suit) || card.getRank().equals("9")){  // if the suit isn't one of the ones we need to check or is a 9--> continue to next card
                    continue;
                } else { // if it isnt a 9 and is in one of the suits we need to check --> add it to the partsOfRun frequency hashmap
                    partsOfRun.put(card.getRank().toUpperCase(), partsOfRun.getOrDefault(card.getRank().toUpperCase(),0)+1);  // add to hashmap <rank, 0 or value for that rank + 1>
                }
            }
            if (partsOfRun.containsKey("A") && partsOfRun.containsKey("10") && partsOfRun.containsKey("K") && partsOfRun.containsKey("Q") && partsOfRun.containsKey("J")){ //if the suit has all 5 components
                int min = 100; //arbitrary high value to use for comparison
                for (Integer runRankFrequency:partsOfRun.values()){ //finds the minimum frequency of the cards in the suit --> this determines how many complete runs there are
                    if (runRankFrequency<min){
                        min = runRankFrequency;
                    }
                }
                suitsWithRun.put(suit,min); // add the suit and how many runs there are
            }
        }
    }

    public void checkMarriages(List<Card> hand){ //if a suit has (K,Q)
        ArrayList<String> suitsToCheck = new ArrayList<>();
        for (String suit: cardsPerSuitMap.keySet()){ //for each suit we have cards in
            if (cardsPerSuitMap.get(suit) >= 2) { // if the suit has more than 2 cards in it, add it to the suits we need to check for a run
                suitsToCheck.add(suit);
            }
        }
        for (String suit:suitsToCheck) { // for each suit we need to check
            HashMap<String, Integer> partsOfMarriage = new HashMap<>(); // <card (K/Q), frequency>
            for (Card card : hand) {
                if (!card.getSuit().equals(suit)) { // if card not in the suit we are looking for
                    continue;
                } else { // if the card is in the suit we are looking for
                    if (card.getRank().equalsIgnoreCase("K") || card.getRank().equalsIgnoreCase("Q")) { // if the card is a king or queen
                        partsOfMarriage.put(card.getRank().toUpperCase(), partsOfMarriage.getOrDefault(card.getRank().toUpperCase(), 0) + 1); // add to hashmap <rank, 0 or value for that rank + 1>
                    }
                }
            }
            if (partsOfMarriage.containsKey("K") && partsOfMarriage.containsKey("Q")) { // if all parts of the marriage are present
                int min = 100; //arbitrary high value for comparison
                for (Integer marriageRankFrequency : partsOfMarriage.values()) {  // for each freqeuncy if the values in the marriage ranks
                    if (marriageRankFrequency < min){
                        min = marriageRankFrequency;
                    }  
                }
                suitsWithMarriage.put(suit,min);  // add the suit and number of marriages to hashmap
            }

        }
    }

    public String switchToFull(String suit){
        String suitName = "";
        switch (suit.toUpperCase()) {
            case "H":
                suitName = "Hearts";
                break; // Break to prevent fall-through
            case "D":
                suitName = "Diamonds";
                break; // Break to prevent fall-through
            case "C":
                suitName = "Clubs";
                break; // Break to prevent fall-through
            case "S":
                suitName = "Spades";
                break; // Break to prevent fall-through
        }
        return suitName;
    }

    public String toString() {
        String str = "";
        if (getPinochles() > 0) { //only show if there are 1 or more
            str += "\nPinochle(s): " + getPinochles();
        }
        if (getRoundOfAces() > 0) { //only show if there are 1 or more
            str += "\nRound(s) of Aces: " + getRoundOfAces();
        }
        if (getRoundOfKings() > 0) { //only show if there are 1 or more
            str += "\nRound(s) of Kings: " + getRoundOfKings();
        }
        if (getRoundOfQueens() > 0) { //only show if there are 1 or more
            str += "\nRound(s) of Queens: " + getRoundOfQueens();
        }
        if (getRoundOfJacks() > 0) { //only show if there are 1 or more
            str += "\nRound(s) of Jacks: " + getRoundOfJacks();
        }
        if (!suitsWithRun.isEmpty()) { //if suitsWithRun is not empty
            for (String suit : suitsWithRun.keySet()) { //for each suit that has a run
                String fullSuit = switchToFull(suit);
                if (this.getTrump() != null){ // if trump has been entered
                    if (switchToFull(this.getTrump()).equals(fullSuit)){ //if the suit is in trump add it to the toString - if it is not in trmp it wontbe added
                        str += "\n" + "Run(s) in " + fullSuit + ": " + suitsWithRun.get(suit);
                    }
                } else { //trump has not been entered
                    str += "\n" + "Run(s) in " + fullSuit + ": " + suitsWithRun.get(suit);
                }
            }
        }

        if (!suitsWithMarriage.isEmpty()) {
            for (String suit : suitsWithMarriage.keySet()) { //for each suit that has a marriage
                String fullSuit = switchToFull(suit);
                if (this.getTrump() != null) { // if trump has been entered
                    if (suitsWithRun.containsKey(suit) && switchToFull(this.getTrump()).equals(fullSuit)) { //if the suit has a run that is in trump
                        if (suitsWithRun.get(suit) > 0 && suitsWithMarriage.get(suit) > 0) {
                            str += "\n" + "Marriage(s) in " + fullSuit + ": " + (suitsWithMarriage.get(suit) - suitsWithRun.get(suit)); //subtract the number of runs from the number of marriages to ensure no overlap
                        }
                    } else {
                        str += "\n" + "Marriage(s) in " + fullSuit + ": " + suitsWithMarriage.get(suit);
                    }
                } else { // if trump has not been entered
                    str += "\n" + "Marriage(s) in " + fullSuit + ": " + suitsWithMarriage.get(suit);
                }
            }
        }

        if (str.equals("")){ //if there is no meld
            str += "Nothing.";
        }
        return str;
    }
}
