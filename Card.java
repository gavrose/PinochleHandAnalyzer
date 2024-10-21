import java.util.Objects;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }


    private String convertRank(String rank){
        rank = rank.toUpperCase();
        switch (rank){
            case "9":
                return "9";
            case "10":
                return "10";
            case "J":
                return "Jack";
            case "Q":
                return "Queen";
            case "K":
                return "King";
            case "A":
                return "Ace";
            default:
                throw new IllegalArgumentException("Invalid card rank character: " + rank);
        }
    }
    private String convertSuit(String suit){
        suit = suit.toUpperCase();
        switch (suit){
            case "H":
                return "Hearts";
            case "D":
                return "Diamonds";
            case "C":
                return "Clubs";
            case "S":
                return "Spades";
            default:
                throw new IllegalArgumentException("Invalid suit character: " + suit);
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public String toString(){
        try {
            convertRank(rank);
            convertSuit(suit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return convertRank(rank) + " of " + convertSuit(suit);
    }

    public static void main(String[] args){

    }
}
