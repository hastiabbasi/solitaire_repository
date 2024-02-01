import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.lang.reflect.InvocationTargetException;

import java.util.StringTokenizer;

public class Board {   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    ArrayList<Deck> stacks; 
    // cannot do ArrayList<Card>. has to be a deck
    //ArrayList<Card> drawPile;
    Deck drawPile;
    
    int stackNum;
    int deckNum;
    
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  Here are examples:
     *  
     *  # numDecks     #cards in overall Deck
     *      1          13 (all same suit)
     *      2          26 (all same suit)
     *      3          39 (all same suit)
     *      4          52 (all same suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.
     */    
    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        
        stackNum = numStacks;
        deckNum = numDecks;
        
        Deck overall = new Deck();
        
        int numCards = numDecks * 13;
        int count = 1;
        
        for(int i = 1; i <= numCards; i++) {
            
            Card currentCard;
            
            if(count == 1) {
                
                currentCard = new Card("A", 1);
                
            } else if(count == 10) {
                
                currentCard = new Card("T", 10);
                
            } else if(count == 11) {
                
                currentCard = new Card("J", 11);
                
            } else if(count == 12) {
                
                currentCard = new Card("Q", 12);
                
            } else if(count == 13) {
                
                currentCard = new Card("K", 13);
                count = 0;
                
            } else {
                
                currentCard = new Card(count + "", count);
                
            }
            
            overall.addCard(currentCard);
            
            count++;
        }
        
        overall.shuffle();
        
        stacks = new ArrayList<Deck>();
        
        for(int i = 0; i < numStacks; i++) {
            stacks.add(new Deck());
        }
        
        int cardCount = overall.getSize() - 1;
        
        // keeps adding to stacks until half have been added 
        while(cardCount > numCards / 2) {
            
            for(int j = 0; j < stacks.size(); j++) {
                stacks.get(j).addCard(overall.getCard(cardCount));
                // removes each card that is added to stacks from overall arr
                overall.removeCard(cardCount);
                cardCount--;
            }
            
        }
        
        // sets drawPile to the remaining cards
        drawPile = new Deck();
        drawPile = overall;
        
    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.
     */
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        
        Deck sourceD = stacks.get(src);
        int sourceDLength = sourceD.getSize();
        
        Deck destD = stacks.get(dest);
        int destDLength = destD.getSize();
        
        int symIndex = 0;
        
        Deck runCards = new Deck();
        int numRunCards = 0;
    
        for(int i = sourceDLength - 1; i >= 0; i--) {
            
            Card current = sourceD.getCard(i);
            
            if(current.getSymbol().equals(symbol)) {
                symIndex = i;
                break;
            }
            
        }
        
        numRunCards = sourceDLength - symIndex;
        
        // stores the runCards in a new deck
        runCards = sourceD.takeTopCards(numRunCards);
        
        // run only gets transfered to dest & removed from src if the move is valid
        if(isValidMove(destD, runCards)) {
            // transfers the new runCards deck to the dest deck
            Deck.transferCards(destD, runCards);
            // removes the runCards from the src deck
            sourceD.removeTopCards(numRunCards);
            // flips the next card face up in the src deck
            sourceD.setTopCardFaceUp();
        } else {
            System.out.println("Please enter a valid move.");
        }
        
    }
    
    // try-catch utilized instead 
    public boolean validMoveParams(String sym, int source, int destination) {
        
        if(!sym.equals("K") || !sym.equals("Q") || !sym.equals("J") 
            || !sym.equals("T") || !sym.equals("9") || !sym.equals("8")
            || !sym.equals("7") || !sym.equals("6") || !sym.equals("5") 
            || !sym.equals("4") || !sym.equals("3") || !sym.equals("2")
            || !sym.equals("A")) {
                
            System.out.println("Invalid information. Please enter a valid symbol.");
            return false;
        }
        
        if(!(source > 0 && source <= stackNum)) {
            System.out.println("Invalid information. Please enter a valid source stack.");
            return false;
        }
        
        if(!(destination > 0 && destination <= stackNum)) {
            System.out.println("Invalid information. Please enter a valid destination stack.");
            return false;
        }
        
        return true;
    }
    
    public boolean isValidMove(Deck destinationD, Deck runCardD) {
        
        int destDLength = destinationD.getSize();
        int runCardDLength = runCardD.getSize();
        
        // checks to see if the run cards are valid (in order)
        for(int i = 0; i < runCardDLength - 1; i++) {
            
            Card current = runCardD.getCard(i);
            Card next = runCardD.getCard(i + 1);
            
            if(current.getValue() - 1 != next.getValue()) {
                return false;
            } 
            
        }
        
        Card topFromDest = destinationD.getCard(destDLength - 1);
        Card bottomFromRun = runCardD.getCard(0);
        
        // checks to see if the transition from the dest deck to the run cards is valid
        if(topFromDest.getValue() - 1 != bottomFromRun.getValue()) {
            return false;
        }
        
        // checks to see if all the cards in the run are face up
        for(int i = 0; i < runCardDLength; i++) {
            
            Card current = runCardD.getCard(i);
            
            if(!current.isFaceUp()) {
                // error message printed
                System.out.println("Symbol card not found.");
                return false;
            }
            
        }
        
        // true is returned if move is valid
        return true; 
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        
        // cards are only drawn if there are no empty stacks
        if(noEmptyStacks()) {
            
            for(int i = 0; i < stackNum; i++) {
                
                if(drawPile.getSize() > 0) {
                    Card topFromDrawPile = drawPile.getCard(drawPile.getSize() - 1);
                    topFromDrawPile.setFaceUp(true);
                    stacks.get(i).addCard(topFromDrawPile);
                    drawPile.removeCard(drawPile.getSize() - 1);
                } else {
                    
                    System.out.println("Draw pile is empty.");
                    break;
                    
                }
                
            }
            
        } else {
            
            System.out.println("Empty stack(s) present. Please ensure that all stacks have at least one card.");
            
        }
        
    }
    
    public boolean noEmptyStacks() {
        
        for(int i = 0; i < stacks.size(); i++) {
            if(stacks.get(i).getSize() == 0) {
                return false;
            }
        }
        
        return true;
    }


    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        
        boolean drawPileEmpty = false; 
        boolean stacksEmpty = false; 
        
        if(drawPile.getSize() != 0) {
            return false;
        }
        
        for(int i = 0; i < stacks.size(); i++) {
            if(stacks.get(i).getSize() != 0) {
                return false;
            }
        }
        
        return true;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        
        try{
            Deck stack = stacks.get(sourceStack);
        
            boolean correctOrder = true; 
            
            int count = 1;
            
            // sourceStack --> the stack they want to check 
            if(stack.getSize() < 13) {
                System.out.println("Invalid move. Stack #" + (sourceStack + 1) + " is not a run of A through K.");
            } else { 
                
                for(int i = stack.getSize() - 1; i >= stack.getSize() - 14; i--) {
                    
                    Card current = stack.getCard(i);
                    
                    if(count != current.getValue()) {
                        correctOrder = false;
                        // breaks to avoid unnecessary checking
                        break;
                    }
                    
                }
                
                if(correctOrder) {
                    stacks.get(sourceStack).removeTopCards(13);
                }
                
            }
            
        } catch(IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid source stack.");
        } 
        
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        System.out.println("Stacks:");
        
        for(int i = 0; i < stacks.size(); i++) {
            int num = i + 1;
            
            for(int j = 0; j < stacks.get(i).getSize(); j++) {
                
                /*
                if(j < stacks.get(i).getSize() - 1) {
                    stacks.get(i).getCard(j).setFaceUp(false);
                    
                } else {
                    stacks.get(i).getCard(j).setFaceUp(true);
                }
                */
                
                if(j == stacks.get(i).getSize() - 1) {
                    stacks.get(i).getCard(j).setFaceUp(true);
                }
                
            }
            
            System.out.println(num + stacks.get(i).toString());
        }
        
        System.out.println("Draw Pile:");
        
        for(int i = 0; i < drawPile.getSize(); i++) {
            drawPile.getCard(i).setFaceUp(false);
        }
        
        System.out.println(drawPile.toString());
        
    }
    
    public void save() {
        
        try {
            EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        
                        JFileChooser chooser = new JFileChooser(".");
                        chooser.showSaveDialog(null);
                        File userFile;
                        
                        try{
                            userFile = chooser.getSelectedFile();
                            
                            FileWriter savedData;
                        
                            try{
                                savedData = new FileWriter(userFile);
                                
                                try{
                                    String numStacksString = stackNum + "\n";
                                    
                                    // writes the # of stacks in the first line
                                    savedData.write(numStacksString, 0, numStacksString.length());
                                    
                                    // writes the String rep of each stack in the second line (space separates each stack)
                                    for(int i = 0; i < stackNum; i++) {
                                        
                                        String sString;
                                        
                                        if(i == stackNum - 1) {
                                            sString = stacks.get(i).returnState() + "\n";
                                        } else {
                                            sString = stacks.get(i).returnState() + " ";
                                        }
                                        savedData.write(sString, 0, sString.length()); 
                                        
                                    }
                                    
                                    // writes the String rep of the drawPile in the third line
                                    String drawPileString = drawPile.returnState();
                                    savedData.write(drawPileString, 0, drawPileString.length());
                                    
                                    savedData.close();
                                }catch(IOException i) {
                                     System.out.println("Error: " + i.getMessage());
                                }
                                
                            }catch(IOException i) {
                                System.out.println("Error: " + i.getMessage());
                            }
                            
                        }catch(NullPointerException i) {
                            System.out.println("Error: " + i.getMessage());
                        }  
            
                    }
                });
        }
        catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    
    public void restore() {
        
        // add try catch if user presses cancel while choosing a file
        JFileChooser chooser = new JFileChooser(".");
        chooser.showSaveDialog(null);  
        
        File userFile;
        
        Scanner in;
                        
        try{
            userFile = chooser.getSelectedFile();
        
            // initalizes Scanner that reads the selected file & breaks if successful
            while(true) {
                
                try {
                    in = new Scanner(userFile);
                    break;
                }catch(IOException i) {
                    System.out.println("Error: " + i.getMessage());
                }
                
            }
            
            for(int i = 0; i < 3; i++) {
                // current line stored in String var
                String line = in.nextLine();
                
                if(i == 0) {
                    
                    StringTokenizer lineST = new StringTokenizer(line);
                    
                    while(lineST.hasMoreTokens()) {
                        stackNum = Integer.parseInt(lineST.nextToken());
                        break;
                    }
                    
                } else if(i == 1) {
                    
                    stacks = new ArrayList<Deck>();
                    
                    StringTokenizer lineST = new StringTokenizer(line);
                    
                    while(lineST.hasMoreTokens()) {
                        
                        String currentDeckString = lineST.nextToken();
                        stacks.add(new Deck(currentDeckString));
                    }
                    
                } else {
                    
                    StringTokenizer lineST = new StringTokenizer(line);
                    
                    while(lineST.hasMoreTokens()) {
                        
                        String drawPileString = lineST.nextToken();
                        drawPile = new Deck(drawPileString);
                        
                    }
                }
            }
            
            in.close();
            
        } catch(NullPointerException i) {
            System.out.println("Error: " + i.getMessage());
        }    
    
    }
    
}
