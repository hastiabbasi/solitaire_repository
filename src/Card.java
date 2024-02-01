/*
 * Name: Hasti Abbasi Kenarsari
 * Date: 12/01/21
 * Period: 4 
 * 
 * Is this lab fully working? --> Yes
 * 
 */

/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card> {
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;
    
    /**
     * Creates an instance of a card
     * @param symbol represents the card symbol as a string
     * @param value
     */
    public Card(String symbol, int value) {
        // initializes the class attributes
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return symbol;
    }

    /**
     * Getter method to access this <code>Card</code>'s value. 
     *
     * @return this <code>Card</code>'s symbol.
     */ 
    public int getValue() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return value;
    }
    
    /**
     * Getter method to access whehter this <code>Card</code>'s isFaceUp atteribute is true/false.
     *
     * @return this <code>Card</code>'s isFaceUp value. 
     */ 
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Setter method that takes in a value for the <code>Card</code>'s setFaceUp attribute. 
     *
     * @param state a boolean representating whether the <code>Card</code> is face up/down
     */ 
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not the value of this <code>Card</code> is equal to the value of another card.
     *  
     *  @param other a Card object being compared to the current Card 
     *  @return a boolean signifying if the two cards have the same value
     */
    public boolean equals(Card other) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return (this.value == other.value);
    }
    
    /**
     * Returns whether or not this <code>Card</code> is less than, equal to, or greater than the Card object passed in
     *
     * @param c a <code>Card</code> object
     * @return a 0, negative integer, or positive integer depending on the comparison of the two card objects
     */   
    public int compareTo(Card c) {
        return this.value - c.value;
    }
    
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol or and X,
     * depending on whether the card is face up or down.
     */
    @Override
    public String toString() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        if(!this.isFaceUp) {
            return "X";
        } else {
            return this.symbol;
        }
    }
}

