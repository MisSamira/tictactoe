TicTacToe2.0
============

RUN GUIDE

mvn clean install
java -jar target/tictactoe-1.0.0-SNAPSHOT.jar configFile

for example : java -jar target/tictactoe-1.0.0-SNAPSHOT.jar config.properties


==================================


This project has five main packages :
1- config  => contains all the required config of the project
2- controller => contains the controller of the project
3- domain => all the domains of the project are here
4- exception => contains project exceptions
5- ui => the ui of the project

======================

< Config >

BoardConfig  :

    It's an interface so config could be expanded and modified so easily with new implementation of this interface
    it has two method getRow() and getColumn()

SquareBoardConfig :

    It's a square board with its limitation like the board size should be between 3 and 10

PlayerConfig :

    The interface of player config with main method getCharacter() ( it is the signature of every player in the game )
    the reason that i get it as an interface because of project flexibility for example in the next version
    player config can have name as its configuration

PlayerConfigImpl :

    Very simple implementation of player config with some constraints like every player symbol is just
    one character and it should be in the follow pattern : [a-zA-Z]

Configuration :

    It is where the configuration of project will create
    all the players config and board config will initiate in this class

======================

< Domain >

There is a "Board" that knows about the board for example if the board is full or not
a "Player" that play the game and make moves
a "Move" that contains the coordinate of the movement and its player signature


Board :

    It's a singleton class, why singleton? because every game need only one board.
    it has several methods :
    it containts several functions like createBoard, isWinner, isEmptyAt, getCharacterAt , ...

Player :

    The most important function of this Object is makemove
    for humanPlayer it will return empty because player will choose the move himself
    but for computer it will choose based on simple algorithm.
    Every Player should inherit from the Player abstract class so in this case we could have lots of implementation
    for every level of computer playing
    for example i implement dummy and simple ones, the default is simple
    (i did not implement the configuration for game lavel in my code because it was not one of the project requirement)
    but it is so easy, just need add one line in config files and apply few changes in PlayerFactroy and Configuration class

Move :

    It includes the coordinate and character of the movement

=================================

< UI >

UI :

    It's an interface for UI, so each implementation of UI should have main funcations like
    showGame, showStartGame, startGame

CommandLineUI :

    It's a singleton class because we need just one UI in the whole game.
    it's an implmentation of UI that use commandLine as a user interface,
    so everything is on the console, human player enter their movement in the cm
    and it has a refrence to Game, and it will use of the nextplayer to find out who's turn
    and after that player.makemove will make the move and it will continue until the move makes
    the game over

==============================

TicTacToe.java :

    Read the config file address from the commandLine and call ui.startGame
