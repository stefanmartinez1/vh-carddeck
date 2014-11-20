import Deck
import Card

def deck = new Deck()

def console = System.console()

Random gen = new Random()

println("How many games do you want to play?")

int totalNumGames = console.readLine()


for (int game = 0; game < totalNumGames; game++)  {
//1. Dealer will shuffle the deck.

deck.shuffle()

//2. You are dealt two cards. Dealer is dealt two cards.
int numPlayers = 2

    def hand1 = deck.deal(2)
    println("Player 1's Hand: " + hand1)
    def hand2 = deck.deal(2)
    println("Player 2's Hand: " + hand2)

//4. Your cards are added, Dealer's cards are added.

int player1Rank = hand1[0].rank + hand1[1].rank
int player2Rank = hand2[0].rank + hand2[1].rank

//5. You can choose to be hit, or stand.

println("Hit or Stand?")

    boolean player1Bust = false
    boolean player2Bust = false

while(player1Bust == false)
{
    
    String input = console.readLine()

    String check = "hit"

    int handNumber = 1

    String check2 = "stand"

    if ((input.toLowerCase()).equals(check))
    {   
        hand1 += deck.deal(1)
        player1Rank += hand1[1+handNumber].rank
        println("Player 1 now has: " + hand1)
        handNumber++
    }

    if (player1Rank > 21)
    {
        player1Bust = true
        println("Player 2 Wins! with: " + hand2)
        println("Player 1 Had: " + hand1)
        break
    }


    else if (input.toLowerCase().equals(check2))
    {
        break
    }

    else
    {
        println("You have to input \"Hit\" or \"Stand\" to continue")
    }

}


//6. Dealer will choose to be hit if he is under or at 14, but not over it.
int handNumber = 1
while (player2Rank <= 14 && player1Bust == false)
{
    hand2 += deck.deal(1)
    player2Rank += hand2[1+handNumber].rank
    handNumber++
    println("Dealer chose to hit, he now has: " + hand2)
}

// The Dealer can be super ballsy sometimes

while (player2Rank > 14 && player2Rank < 20)
{
    int ballsy = gen.nextInt(10)
    if (ballsy == 7)
    {
        hand2 += deck.deal(1)
        player2Rank += hand2[1+handNumber].rank
        handNumber++
        println("Dealer got ballsy and chose to hit, he now has: " + hand2)
    }

    else 
    {
        break
    }
}

//7. If you both choose to stand, then whoever has the most points wins. Can be tie.

// If player 2 reaches more than 21, he busts and player 1 wins
if (player2Rank > 21 && player1Bust == false)
{
    player2Bust = true
    println("Player 1 Wins! with: " + hand1)
    println("Player 2 Had: " + hand2)
}

int compare = player1Rank - player2Rank
// If player 1 gets to 21, and neither him nor player 2 have busted, he wins
if (player1Rank == 21 && player1Bust == false && player2Bust == false)
{
    println("Player 1 Wins! with: " + hand1)
    println("Player 2 Had: " + hand2)
}
// If player 2 gets to 21, and neither him nor player 1 have busted, he wins
else if (player2Rank == 21 && player1Bust == false && player2Bust == false)
{
    println("Player 2 Wins! with: " + hand2)
    println("Player 1 Had: " + hand1)
}
// If both players stand, and player 1 has the most points, he wins
else if (compare > 0 && player1Bust == false && player2Bust == false)
{
    println("Player 1 Wins! with: " + hand1)
    println("Player 2 Had: " + hand2)
}
// If both players stand, and player 2 has the most points, he wins
else if (compare < 0 && player1Bust == false && player2Bust == false)
{
    println("Player 2 Wins! with: " + hand2)
    println("Player 1 Had: " + hand1)
}
// If they tie, they tie
else if (compare == 0 && player1Bust == false && player2Bust == false)
{
    println("There is a Tie!\nPlayer 1 Has: " + hand1 + "\nPlayer 2 Has: " + hand2)
}

println("------------------------ \n New Game: \n")
//8. Person closest to or at 21 wins.


}