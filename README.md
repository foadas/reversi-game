# Reversi Game

This is a **Java and JavaFX-based** implementation of the classic board game **Reversi** (also known as *Othello*). The game involves two players taking turns to place discs on a board, with the goal of capturing the opponent's discs by surrounding them with their own.

## Features

- **Interactive Game Board**: The game board is an 8x8 grid where players can place their discs. The grid automatically updates to reflect the current state of the game.
- **Player Turns**: Players alternate turns. Each player must place a disc in a valid position that captures at least one of the opponent's discs.
- **Valid Move Indication**: The game highlights valid moves for the current player, ensuring that the player only places discs in positions that result in a capture.
- **Disc Flipping Mechanism**: When a player surrounds the opponent’s discs in any direction (horizontal, vertical, or diagonal), those discs are flipped to match the player's color.
- **Score Tracking**: The game keeps track of the score, showing the current count of discs for each player.
- **End Game Condition**: The game ends when there are no valid moves left for either player, and the player with the most discs on the board wins.
