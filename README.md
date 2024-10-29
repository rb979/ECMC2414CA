# ECM2414 – Software Development Continuous Assessment

## Overview
- **Module Leader**: Prof. Solomon Oyelere
- **Academic Year**: 2024/25
- **Set**: 21st October 2024
- **Submission deadline**: 10th December 2024
- **Contribution**: 40% of the total module mark

### Learning Outcomes
- Use a software design and development method incorporating both formal and informal techniques.
- Design and implement rigorous testing frameworks.
- Deploy advanced object-oriented language concepts and techniques.
- Develop multi-program software systems.
- Follow the phases of software development.
- Evaluate different development practices and judge their appropriateness.
- Analyze and break down problems, comparing and contrasting solutions.
- Follow the pair-programming development approach.

### Submission Requirements
- **Code Part**:
    - `cards.jar` (executable jar file with bytecode and source files).
    - `cardsTest.zip` (source files, test classes, test suites, and a README file).
- **Report Part**:
    - Design choices and reasons (max 2 pages).
    - Test design choices and reasons (max 3 pages).
    - Development log (max 1 page).

### Marking Criteria
- **Report**: 40%
- **Code**: 55%
- **README file**: 5%

### Additional Notes
- Avoid plagiarism and collusion.
- Use of GenAI tools is prohibited.
- Ensure the submission is correct and uploaded to the correct link.

## Project Specification

### Game Setup
- The game has `n` players and `n` decks of cards, both numbered `1 ... n`.
- There are `8n` cards in the pack, with both players and decks holding four each. Each card has a positive integer denomination.
- The decks and players form a ring topology, with players having a deck to their left and right. Player 1 has Deck 1 to their left, and Deck 2 to their right.
- A pack is generated from a pack file, consisting of `8n` lines each containing a single integer which is a card denomination.
- Cards are dealt in a round-robin fashion first between the players and then between the decks.
- To win the game, a player must hold four cards of the same denomination.

### Gameplay
- If a player has won, they should print `Player <n> wins`, notify the other threads, and exit.
  - The program does not need to handle multiple players winning at the same time.
- Otherwise, each player takes the card from the top of the deck to their left, and discards one to the bottom of the deck to their right.
- Players should implement the following strategy:
  - A player `n` will aim to collect four cards with denomination `n`.
  - It should not be possible for players to hold on to a non-preferred card indefinitely.
- Gameplay must be happening simultaneously, with all the players exchanging cards at the same time.

### Solution Development
- Implement an executable class `CardGame`, whose `main` method:
  - Requests a valid number of players.
  - The location of a pack file to load.
  - If the pack file is invalid, the user should be informed and another file requested.
  - Deal the cards to the players and decks.
- Each player should output their actions to a file named `player<n>_output.txt`.
  - Game actions should be printed as:
    - `player <n> draws a <x> from deck <y>`
    - `player <n> discards a <x> to deck <y>`
    - `player <n>'s current hand is <a b c d>`
  - At the end of the game, players should log:
    - `player <n> wins` or `player <m> has informed player <n> that they have won`
    - `player <n> exits`
    - `player <n>'s final hand is <a b c d>`
  - When a player wins, `player <m> wins` should be printed to the terminal.
- Each deck should output `deck<n> contains <a b c d>` to a file named `deck<n>_output.txt` at the end of the game.
- Each game should always result in exactly one winner, unless two players win at the same instant.
- The combination of a player's draw and discard should be atomic, and so all decks and players should always contain exactly four cards at every moment.

### Some Questions You May Have

1. **Which card should a player choose to discard?**
  - You can randomly choose one which is not the preferred denomination to discard.

2. **Can a player who is given four cards which are all the same value at the start of the game, but the value is not their preferred denomination, still win?**
  - Yes, it still can win the game.

3. **How many output files from the production code?**
  - 2n, where n is the number of players.

4. **Do I need to use Javadoc?**
  - No, you don’t need to.

5. **My code reads in a valid pack file which doesn’t contain the cards that allow a winning hand. What should I do?**
  - It’s normal. You don’t need to do anything. The code and pack are two separate things. You need to make sure your code is correct, which means if it takes in a valid pack that allows a winning hand, it can output a winning hand.

6. **Do I need to test private methods?**
  - Yes, you can consider using Java Reflection.
