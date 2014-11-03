import Deck

def deck = new Deck()

deck.shuffle()
println deck

println "Re-Shuffle"
deck.shuffle()
println deck

println "Re-Shuffle and Deal All Cards"
deck.shuffle()
while(!deck.isEmpty())
{
    Card aCard = deck.deal()
    println(aCard)
}

println "Re-Shuffle and Deal 7 cards to 5 players"
int numPlayers = 5
deck.reset()
deck.shuffle()
for(int player=0; player<numPlayers; player++)
{
    Card[] hand = deck.deal(7)
    println(hand)
}