# java-rover
Java version of the mars rover challenge.

Welcome to my rover app! 

To run the application you can:

  Download the executable jar file at java-rover/target/ (It's called mars-rover-1.0-SNAPSHOT.jar)
  
  Run it from command line with ::
  
    java -jar mars-rover-1.0-SNAPSHOT.jar
  
  OR
  
  You can clone the repository to your machine and run:
  
    mvn clean install
    mvn exec:java
    
Usage:

The app takes a .txt file with movement instructions. 

The first line of instructions should be the expected grid size (x axis, y axis).

Example:

    10 10

The next two lines are to program the starting point and movements of the rover. Each consecutive set of 2 lines will spawn and move an additional rover. 

Example:

    1 2 N
    MMRMMLMLMMRRRM
    3 5 S
    RMMM



So a file to move 3 rovers on a 5 x 5 grid would look like this:

    5 5
    1 2 N
    MMRMMLMLMMRRRM
    3 5 S
    RMMM
    1 1 N 
    RMMMLLMMMMLMMRRM

Enjoy!
