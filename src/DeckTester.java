public class DeckTester {
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        String[] symbols = {"A", "2", "3"};
        int[] values = {1, 2, 3};
        
        Deck deck = new Deck();
        
        // builds a set of Cards and adds them to the deck
        for(int i = 0; i < symbols.length; i++) {
            
            deck.addCard(new Card(symbols[i], values[i]));
            
        }
        
        System.out.println("Testing shuffle -->");
        System.out.println("Before shuffle: " + deck.toString());
        deck.shuffle();
        System.out.println("After shuffle: " + deck.toString());
        
        System.out.println();
        
        System.out.println("Testing drawing a card -->");
        System.out.println("Card drawn: " + deck.drawCard().toString());
        System.out.println("After drawing a card: " + deck.toString());
        
        System.out.println("Testing adding a card -->");
        Card newCard = new Card("4", 4);
        deck.addCard(newCard);
        System.out.println("After adding a card: " + deck.toString());
        
        System.out.println("Testing method that returns the complete state of a Deck as a String -->");
        System.out.println(deck.returnState());
        
        Deck step7Test = new Deck("1dTdAu");
        /*
        Card newC = new Card("A", 1);
        newC.setFaceUp(true);
        step7Test.addCard(newC);
        */
        System.out.println(step7Test.toString());
        
        
    }
}
