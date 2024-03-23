# Connect Four Game in JavaFX

## Overview

This project is a GUI-based implementation of the classic Connect Four game, using JavaFX. Players take turns dropping colored discs into a seven-column, six-row vertically suspended grid. The first player to form a horizontal, vertical, or diagonal line of four of their own discs wins the game.

## Features

- **Two Player Game:** Supports two players, Red and Yellow, taking turns to play.
- **GUI Interface:** Fully graphical interface for engaging gameplay.
- **Win Detection:** Automatically detects and announces a win condition for both horizontal, vertical, and diagonal sequences.
- **New Game Option:** Allows players to start a new game once the current game ends or the board is full.
- **Color Flashing:** Winning sequence flashes to highlight the winning discs.

## How to Run

Ensure you have Java and JavaFX installed on your system. Compile and run `ConnectFour.java` using your IDE of choice or the command line. Make sure JavaFX libraries are correctly linked.

Example command line compilation and execution (adjust paths as necessary):

```bash
javac -classpath path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml ConnectFour.java
java -classpath .;path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml ConnectFour
