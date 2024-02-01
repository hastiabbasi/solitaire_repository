public class CardTester {
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        
        Card card = new Card("Ace", 10);
        
        System.out.println("card's symbol: " + card.getSymbol());
        System.out.println("card's value: " + card.getValue());
        System.out.println("card is face up: " + card.isFaceUp());
        card.setFaceUp(true);
        System.out.println("card is face up (after setter is utilized to change it): " + card.isFaceUp());
        
        Card card2 = new Card("Ace", 200);
        System.out.println("card is equal to card2: " + card.equals(card2));
        System.out.println("card compared to card2: " + card.compareTo(card2));
        
        System.out.println("toString() called on card: " + card.toString());
        System.out.println("toString() called on card2: " + card2.toString());
        
    }
}
