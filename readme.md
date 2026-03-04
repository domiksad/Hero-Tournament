# Heroes Tournament

Heroes Tournament is a terminal-based adventure game where you play as a hero fighting monsters, leveling up, and collecting treasures.

## Controls

Keyboard only
Navigate menus and make choices by pressing the corresponding keys.

## Prerequisites

To run this game, you need:

1. Maven – to build and run the project.

2. PostgreSQL – with the following setup:

* Database name: `heros`

* User: `heros`

* Password: `heros`

* Port: `5432 (localhost)`

Make sure the database is running before starting the game.

## How to run

1. Clone the repository:

    `git clone <repo_url>`

2. Build the project with Maven:

    `mvn clean package`

3. Run the game:

    `java -jar target/heroes-tournament.jar`