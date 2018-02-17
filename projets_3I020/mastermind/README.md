# mastermind

A small project from 3I020. First game named mastermind (was very famous). We tried to reproduce the same game as explain here :
https://en.wikipedia.org/wiki/Mastermind_(board_game)

## Installation

http://project.com/3I020 (Doesn't work though)

## Usage

Use lein if you want to launch the project.
There are two ways to launch it.

First way :
In a terminal, enter the repository of the game (~/projets_3I020/mastermind).
Then use this command : lein repl.
Afterwards, this line will appear on the terminal :
mastermind.core=>
To launch the game, enter (-main) after the line above.
You should have this : mastermind.core=> (-main).
Finally enjoy the game !

Second way :
In a terminal, enter the repository of the file core.clj (~/projets/mastermind/src/mastermind).
Then enter this command : lein run.
Finally enjoy the game !


## Options

Does not have any options.

## Examples

Here an example for you :
$ lein run
Welcome to the MatserMind ! Do you want to start a new game ?
1 : yes,why not?
2 : WTH! not at all
1
Perfect! Will you be the next mastermind?
How many tries do you want ?
1 : 12 (hmmm still a newbie ?)
2 : 10 (A good debute hmm
3 : 8 (Oh you are very confident)
4 : 6 (Wouah, You really like challenge !)
1
The Boss has chosen 4 colors from red,blue,green,yellow,black and white so let's start !
You have 12 tries left
green
blue
yellow
black
[green blue yellow black]
[:bad :bad :color :bad]
Too bad ! let's retry
You have 11 tries left
white 
black
red
yellow
[white black red yellow]
[:bad :bad :good :color]
Too bad ! let's retry
You have 10 tries left
yellow
red 
red
red
[yellow red red red]
[:good :bad :good :good]
Too bad ! let's retry
You have 9 tries left
yellow
yellow
red
re
Wrong arguments ! Please enter only one argument with the correct format : example red
red
[yellow yellow red red]
[:good :good :good :good]
OMG ! Congratulations! I can't believe it ! You won ! Another game ? 
1:it was fun, so yes
2:I need a break, so no thanks
2
Too bad, it was fun ! See you later !

### Bugs

Still does not have any bugs ! 
But if you have spotted a problem, please signal us :) !

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2018 3I020_MasterMind

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
