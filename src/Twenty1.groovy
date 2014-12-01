import Deck
import Card

def deck = new Deck()

def console = System.console()

Random gen = new Random()

// ALL THE WINNING ASCII

String p1Wins = ("______ _                         __    _    _ _           _ _\n| ___ \\ |                       /  |  | |  | (_)         | | |\n| |_/ / | __ _ _   _  ___ _ __  `| |  | |  | |_ _ __  ___| | |\n|  __/| |/ _` | | | |/ _ \\ '__|  | |  | |/\\| | | '_ \\/ __| | |\n| |   | | (_| | |_| |  __/ |    _| |_ \\  /\\  / | | | \\__ \\_|_|\n\\_|   |_|\\__,_|\\__, |\\___|_|    \\___/  \\/  \\/|_|_| |_|___(_|_)\n                __/ |                                          \n               |___/                                           \n")

String p2Wins = ("______ _                         _____   _    _ _           _ _\n| ___ \\ |                       / __  \\ | |  | (_)         | | |\n| |_/ / | __ _ _   _  ___ _ __  `' / /' | |  | |_ _ __  ___| | |\n|  __/| |/ _` | | | |/ _ \\ '__|   / /   | |/\\| | | '_ \\/ __| | |\n| |   | | (_| | |_| |  __/ |    ./ /___ \\  /\\  / | | | \\__ \\_|_|\n\\_|   |_|\\__,_|\\__, |\\___|_|    \\_____/  \\/  \\/|_|_| |_|___(_|_)\n                __/ |                                            \n               |___/                                             \n")

String welcome = (" --------------------------\n|                          |\n|       This is 21.        |\n|  Welcome to the Future.  |\n|                          |\n|   By: Stefan Martinez    |\n --------------------------")

println("\n\n\n" + welcome)
println("|--------------------------|\n\n\n\nWhat is your name?\n")
String name = console.readLine()

println("\nWelcome, " + name + ".\nHow many games do you want to play? (Numbers only please)")
int totalNumGames = (console.readLine()).toInteger()

println("\nWith how risky of a Dealer would you like to play?\n(1) No-Risk-Randy\t(2) Somewhat-Safe\t(3) Risky\t(4) Super Ballsy")
int ballsyLevel = (console.readLine()).toInteger()
while (ballsyLevel < 1 || ballsyLevel > 4) 
{
    println("Please input a number between 1 and 4.")
    ballsyLevel = (console.readLine()).toInteger()
}

for (int game = 1; game <= totalNumGames; game++)  {
println("\n-------------|Game: |" + game + " of " + totalNumGames + "|----------------")

//1. Dealer will shuffle the deck.
println()
deck.shuffle()

//2. You are dealt two cards. Dealer is dealt two cards.
int numPlayers = 2

    def hand1 = deck.deal(2)
    println(name + "'s Hand: " + hand1)
    def hand2 = deck.deal(2)
    println("Dealer's Hand: " + hand2 + "\n")

//3. Your cards are added, Dealer's cards are added.

int player1Rank = hand1[0].rank + hand1[1].rank
int player2Rank = hand2[0].rank + hand2[1].rank

boolean player1Bust = false
boolean player2Bust = false

if (player1Rank > 21)
{
    player1Bust = true
}
if (player2Rank > 21)
{
    player2Bust = true
}

// If player 1 gets to 21, and neither him nor player 2 have busted, he wins
if (player1Rank == 21 && player1Bust == false && player2Bust == false)
{
    player2Bust = true
    println(p1Wins)
    println(name + " Wins! with: " + hand1)
    println("Dealer Had: " + hand2 + "\n")
}
// If player 2 gets to 21, and neither him nor player 1 have busted, he wins
else if (player2Rank == 21 && player1Bust == false && player2Bust == false)
{
    player1Bust = true
    println(p2Wins)
    println("Dealer Wins! with: " + hand2)
    println(name + " Had: " + hand1 + "\n")
}

//4. You can choose to be hit, or stand.
if (player1Bust == false && player2Bust == false) {

    int handP1Number = 1

    int handP2Number = 1

while(player1Bust == false && player2Bust == false)
{
    println("Do you want to Hit or Stand?")

    String input = console.readLine()

    int player1Continue = 2
    int player1Stop = 0
    int player2Stop = 0

    String check = "hit"
    String check2 = "stand"
    String check3 = "h"
    String check4 = "s"

    if (((input.toLowerCase()).equals(check) || input.toLowerCase().equals(check3)) && player1Rank <= 21)
    {   
        hand1 += deck.deal(1)
        player1Rank += hand1[1+handP1Number].rank
        println(name + " now has: " + hand1 + "\n")
        handP1Number++
        player1Continue = 1

        if ((player1Rank > 21 && player2Bust == false) || player2Rank == 21)
        {
            player1Bust = true
            break
        }
    }
        
    else if (input.toLowerCase().equals(check2) || input.toLowerCase().equals(check4))
    {
        player1Stop = 1
    }

    else
    {
        player1Continue = 0
        println("You have to input \"Hit\" or \"Stand\" to continue.")
    }

    // Dealer will choose to be hit if he is under or at 14, but not over it.
    if (player2Rank <= 14 && player1Bust == false && (player1Continue == 1 || player1Continue == 2))
    {
        hand2 += deck.deal(1)
        player2Rank += hand2[1+handP2Number].rank
        handP2Number++
        println("Dealer chose to hit, he now has: " + hand2 + "\n")
    }
    else {player2Stop = 1}

    // The Dealer can be super ballsy sometimes..
    if (ballsyLevel > 1 && player2Rank > 14 && player2Rank < 20 && (player1Continue == 1 || player1Continue == 2))
    {
        int ballsy = gen.nextInt(10)
        if ((ballsyLevel == 2 && (ballsy == 7 || ballsy == 6)) || (ballsyLevel == 3 && (ballsy >= 4 && ballsy <= 8)) || (ballsyLevel == 4 && (ballsy >= 1)))
        {
            hand2 += deck.deal(1)
            player2Rank += hand2[1+handP2Number].rank
            handP2Number++
            println("Dealer got ballsy and chose to hit, he now has: " + hand2 + "\n")
        }
    }
    else {player2Stop = 1}

    if ((player2Rank > 21 && player1Bust == false) || player1Rank == 21)
    {
            player2Bust = true
            break
    }

    if (player1Stop == 1 && player2Stop == 1)
    {
        break
    }

}// WHILE LOOP

// If player 1 reaches more than 21, he busts and player 2 wins
if (player1Rank > 21 && player2Bust == false)
{
    player1Bust = true
    println(p2Wins)
    println("Dealer Wins! with: " + hand2)
    println(name + " Had: " + hand1 + "\n")
}

// If player 2 reaches more than 21, he busts and player 1 wins
if (player2Rank > 21 && player1Bust == false)
{
    player2Bust = true
    println(p1Wins)
    println(name + " Wins! with: " + hand1)
    println("Dealer Had: " + hand2 + "\n")
}

//5. If you both choose to stand, then whoever has the most points wins. Can be tie.

int compare = player1Rank - player2Rank
// If player 1 gets to 21, and neither him nor player 2 have busted, he wins
if (player1Rank == 21 && player1Bust == false && player2Bust == false)
{
    println(p1Wins)
    println(name + " Wins! with: " + hand1)
    println("Dealer Had: " + hand2 + "\n")
}
// If player 2 gets to 21, and neither him nor player 1 have busted, he wins
else if (player2Rank == 21 && player1Bust == false && player2Bust == false)
{
    println(p2Wins)
    println("Dealer Wins! with: " + hand2)
    println(name + " Had: " + hand1 + "\n")
}
// If both players stand, and player 1 has the most points, he wins
else if (compare > 0 && player1Bust == false && player2Bust == false)
{
    println(p1Wins)
    println(name + " Wins! with: " + hand1)
    println("Dealer Had: " + hand2 + "\n")
}
// If both players stand, and player 2 has the most points, he wins
else if (compare < 0 && player1Bust == false && player2Bust == false)
{
    println(p2Wins)
    println("Dealer Wins! with: " + hand2)
    println(name + " Had: " + hand1 + "\n")
}
// If they tie, they tie
else if (compare == 0 && player1Bust == false && player2Bust == false)
{
    println("There is a Tie!\n" + name + " Has: " + hand1 + "\nDealer Has: " + hand2)
}

}// if loop

if (game != totalNumGames)
sleep(4000)

if (totalNumGames > 1 && game != totalNumGames)
{
    println("------------------------------------------- \n\n New Game: ")//\n\n------------------------")
}
//6. Person closest to or at 21 wins.


} // for loop
