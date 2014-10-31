class Deck
{
    public static final int MAX_SIZE = 52
    private def cards = []

    public Deck()
    {
        reset()
    }
    
    private void reset()
    {
        cards = []
        addSuit(Suit.SPADES)
        addSuit(Suit.HEARTS)
        addSuit(Suit.DIAMONDS)
        addSuit(Suit.CLUBS)  
    }
        
    private void addSuit(Suit suit)
    {
        for (int i = 2; i <= Card.ACE; i++)
        {
            Card newCard = new Card(i, suit)
            cards.add(newCard)
        }
    }

    public boolean isEmpty()
    {
        return cards.isEmpty()
    }

    //methods

    //add - puts a Card at the end ("bottom") of the pile.  It just uses the ArrayList method
    public void add(Card aCard)
    {
        cards.add(aCard)
    }

    public void clear()
    {
        cards.clear()
    }

    //getTopCard - removes and returns the "top" card of the pile.  It just uses the ArrayList method
    public Card getTopCard()
    {
        return cards.remove(0)
    }

    public int size()
    {
        return cards.size()
    }

    public Card deal()
    {
        if(isEmpty())
        {
            return null
        }
        else
        {
            return cards.remove(cards.size() -1)
        }
    }

    public Card[] deal(int number)
    {
        if (number > cards.size())
        {
            return null
        }
        else
        {
            Card[] hand = new Card[number]
            for(int i = 0; i < hand.length; i++)
            {
                hand[i] = deal()
            }
            return hand
        }
    }
    
    public void shuffle()
    {
        if (cards.size() < MAX_SIZE)
        {
            println "Couldn't shuffle. There are only " + cards.size() + " cards!"
            return
        }
        Random gen = new Random()
        Card[] array = new Card[MAX_SIZE]
        while (cards.size() > 0)
        {
            Card card = cards.remove(cards.size() - 1)
            int i = gen.nextInt(MAX_SIZE)
            while (array[i] != null)
            {
                i = gen.nextInt(MAX_SIZE)
            }                
            array[i] = card
        }
        for (Card card : array)
        {
            cards.add(card)
        }
    }

    public String toString()
    {
        String result = ""
        for(Card card : cards)
        {
            result += card.toString() + "\n"
        }
        return result
    }
}
