import Deck
import Card

def deck = new Deck()


int totalNumGames = 2


for (int game = 1; game <= totalNumGames; game++)  {
//1. Dealer will shuffle the deck.

deck.shuffle()

//2. You are dealt two cards. Dealer is dealt two cards.
int numPlayers = 2

    Card[] hand1 = deck.deal(2)
    println("Player 1's Hand: " + hand1)
    Card[] hand2 = deck.deal(2)
    println("Player 2's Hand: " + hand2)

//4. Your cards are added, Dealer's cards are added.

int player1Rank = hand1[0].rank + hand1[1].rank
int player2Rank = hand2[0].rank + hand2[1].rank

//5. You can choose to be hit, or stand.

println("Hit or Stand?")

boolean player1Bust = false
boolean player2Bust = false

while(player1Bust = false) {
	String input = readLine()

if (input.equals("Hit"))
{
	hand1 = deck.deal(1)
	player1Rank += hand1[2].rank
}

if (player1Rank > 21)
{
	player1Bust = true
	println("Player 2 Wins!")	
	break
}


else if (input.equals("Stand"))
{
	break
}

else 
{
	println("You have to input \"Hit\" or \"Stand\" to continue")
}

}


//6. Dealer will choose to be hit if he is under 13, but not over it.
int handNumber = 1
while (player2Rank <= 13 && player1Bust == false)
{
	hand2 = deck.deal(1)
	player2Rank += hand2[1+handNumber].rank
	handNumber++
}

//7. If you both choose to stand, then whoever has the most points wins. Can be tie.

// If player 2 reaches more than 21, he busts and player 1 wins
if (player2Rank > 21 && player1Bust == false)
{
	player2Bust = true
	println("Player 1 Wins!")
}

int compare = player1Rank.compareRank(player2Rank)
// If player 1 gets to 21, and neither him nor player 2 have busted, he wins
if (player1Rank == 21 && player1Bust == false && player2Bust == false)
{
	println("Player 1 Wins!")
}
// If player 2 gets to 21, and neither him nor player 1 have busted, he wins
else if (player2Rank == 21 && player1Bust == false && player2Bust == false)
{
	println("Player 2 Wins!")
}
// If both players stand, and player 1 has the most points, he wins
else if (compare > 0 && player1Bust == false && player2Bust == false)
{
	println("Player 1 Wins!")
}
// If both players stand, and player 2 has the most points, he wins
else if (compare < 0 && player1Bust == false && player2Bust == false)
{
	println("Player 2 Wins!")
}
// If they tie, they tie
else
{
	println("There is a Tie!")
}

//8. Person closest to or at 21 wins.


}