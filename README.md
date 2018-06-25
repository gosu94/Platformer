# Platformer

Platformer is a simple 2D game created with [LibGDX](https://libgdx.badlogicgames.com) framwork and Java as a support for engineering thesis called "Implementation of design patterns by the example of interactive graphical application".

![screenshot](https://github.com/gosu94/Platformer/raw/master/screenshot.png)

## Requirements

In order to play Platformer game you need to have Java Runtime Environment (ver >= 8) installed on your machine (system independent). - [Download](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

## Build and Run

The executable .jar file is already placed in `bin/` folder so you can play it straight away.

In order to build applcation by yourself you can use gradle wrapper included in package

`chmod +x gradlew && ./gradlew desktop:dist` on Linux and MacOS

`gradlew desktop:dist` on Windows

The .jar file will be build into `desktop/build/libs/` folder

## How to play

In order to win the game player has to find a clover item placed at the end of map. While doing that he needs to avoid (or kill) any potential obstacles.

Player can lose his life by running into enemy or falling off the platforms.

Player can regain lifes by collecting coins (1000 points = new life)

### Controls

Arrow keys (left, right) - movement

Space - jump

S - save game

L - load game

Esc - exit to main menu
