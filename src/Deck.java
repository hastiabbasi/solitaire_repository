import java.util.ArrayList;
import java.util.Random;

public class Deck {
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
    
    ArrayList<Card> d;
    
    public Deck() {
        // initializes the deck to an empty state 
        d = new ArrayList<Card>();
    }
    
    public Deck(String state) {
        d = new ArrayList<Card>();
        
        int stringL = state.length();
        
        for(int i = 0; i < stringL - 1; i++) {
            
            Card c;
            
            char current = state.charAt(i);
            
            if(current == 1) {
                
                c = new Card("A", 1);
                
            } else if(current == 10) {
                
                c = new Card("T", 10);
                
            } else if(current == 11) {
                
                c = new Card("J", 11);
                
            } else if(current == 12) {
                
                c = new Card("Q", 12);
                
            } else if(current == 13) {
                
                c = new Card("K", 13);
                
            } else {
                
                c = new Card(current + "", current);
                
            }
            
            char next = state.charAt(i + 1);
            
            if(next == 'u') {
                c.setFaceUp(true);
            } else {
                c.setFaceUp(false);
            }
            
            addCard(c);
            i++;
        }
    }
    
    public ArrayList<Card> returnDeckArray() {
        return d;
    }
    
    // shuffle method
    public void shuffle() {
        
        for(int i = 0; i < d.size(); i++) {
            
            Card current = d.get(i);
            
            int randPos = new Random().nextInt(d.size());
            Card randCard = d.get(randPos);
            
            // swaps the current card @ position i with a random card
            d.set(i, randCard);
            d.set(randPos, current);
            
        }
        
    }
    
    // overriden toString() method 
    @Override
    public String toString() {
        
        String deckString = "";
        
        int count = 0;
        deckString += "[";
        
        for(Card i : d) {
            
            if(count != d.size() - 1) {
                deckString += i.toString() + " ";
            } else {
                deckString += i.toString();
            }
            
            count++;
    
        }
    
        deckString += "]";
        
        // returns the cards in an ArrayList format (cards are placed in order in brackets)
        return deckString;
    }
    
    // draw method 
    public Card drawCard() {
        
        // returns the last (top) card in the deck & removes it 
        Card last = d.get(d.size() - 1); 
        d.remove(d.size() - 1);
        return last;
        
    }
    
    public Card getCard(int index) {
        return d.get(index);
    }
    
    public void removeCard(int index) {
        d.remove(index);
    }
    
    // adds card to the end of the deck (so it is on top)
    public void addCard(Card c) {
        d.add(c);
    }
    
    // adds card to the given index & slides all other cards over 
    public void addCardAtIndex(Card c, int index) {
        
        Card top = d.get(d.size() - 1);
        
        for(int i = index + 1; i < d.size() - 1; i++) {
            
            d.set(i, d.get(i - 1));
            
        }
        
        d.add(top);
        
    }
    
    // hides the top card (card @ last index in deck array) by setting faceUp to false
    public void hideTopCard() {
        
        Card top = d.get(d.size() - 1);
        top.setFaceUp(false);
        
    }
    
    // hides the top card (card @ last index in deck array) by setting faceUp to true
    public void showTopCard() {
        
        Card top = d.get(d.size() - 1);
        top.setFaceUp(true);
        
    }
    
    public void showAll() {
        
        for(int i = 0; i < d.size(); i++) {
            
            d.get(i).setFaceUp(true);
            
        }
        
    }
    
    // returns the # of cards in the deck array
    public int getSize() {
        
        return d.size();
        
    }
    
    // sets the top card to its opposite state (face up --> face down & vice versa)
    public void flipTopCard() {
        
        Card top = d.get(d.size() - 1);
        
        if(top.isFaceUp() == true) {
            top.setFaceUp(false);
        } else {
             top.setFaceUp(true);
        }
        
    }
    
    public void setTopCardFaceUp() {
        
        Card top = d.get(d.size() - 1);
        top.setFaceUp(true);
        
    }
    
    // takes the top cards from a deck & returns a new deck containing them 
    public Deck takeTopCards(int numCards) {
        
        Deck topCardDeck = new Deck();
        
        for(int i = d.size() - numCards; i < d.size(); i++) {
            
            topCardDeck.addCard(d.get(i));
            
        }
        
        return topCardDeck;
        
    }
    
    // removes the top cards from a deck
    public void removeTopCards(int numCards) {
        
        for(int i = d.size() - numCards; i < d.size(); i++) {
            
            d.remove(i);
            
        }
        
    }
    
    // adds the cards from d2 to the end of d1
    public static void transferCards(Deck d1, Deck d2) {
        
        for(int i = 0; i < d2.getSize(); i++) {
            
            d1.addCard(d2.getCard(i));
            
        }
        
    }

    
    //add card(index), add card that adds to the end), hideTopCard (set it to face down)
    // show all (sets all to face up), size, flipTopCard (sets it to opposite state)
    // return a String that reps the state of this deck, viewCard(returns it but doesn't remove)
    // takeTopCards(int numCards) takes the top cards, returns a new deck containing them
    // transfer method that transfers cards from one deck to the next 
    
    // returns the state of the deck as a String by adding each card's symbol & whether it is face up/down
    public String returnState() {
        
        String state = "";
        
        for(int i = 0; i < d.size(); i++) {
            
            Card current = d.get(i);
            
            state += current.getSymbol();
            
            if(current.isFaceUp()) {
                state += "u";
            } else {
                state += "d";
            }
            
        }
        
        return state;
    }
}

