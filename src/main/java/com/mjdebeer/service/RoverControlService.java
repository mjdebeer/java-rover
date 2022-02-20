package com.mjdebeer.service;

import java.util.LinkedList;
import java.util.List;

import com.mjdebeer.entity.Rover;

public class RoverControlService {
    UserInterfaceService uiService;

    public RoverControlService(UserInterfaceService userInterfaceService) {
        uiService = userInterfaceService;
    }

    public void deployAndMoveRovers(List<Rover> roversList, LinkedList<String> instructionsList) {
        roversList.forEach(rover -> {
            String[] deploymentInstructions = instructionsList.get(0).split(" ");
            instructionsList.removeFirst();
            String[] movementInstructions = instructionsList.get(0).split("");
            instructionsList.removeFirst();

            try {
                rover.xAxisPosition = Integer.parseInt(deploymentInstructions[0]);
                rover.yAxisPosition = Integer.parseInt(deploymentInstructions[1]);
                rover.currentOrientation = Rover.Orientation.valueOf(deploymentInstructions[2]);

                executeMovementInstructions(movementInstructions, rover);

                uiService.printMessage(String.format("%s %s %s", 
                String.valueOf(rover.xAxisPosition), 
                String.valueOf(rover.yAxisPosition), 
                rover.currentOrientation.name()));
            } catch (Exception e) {
                uiService.printMessage("Error executing movement instructions. Please ensure file is correctly formatted.");
                e.printStackTrace();
            }
        });
    };

    public void executeMovementInstructions(final String[] instructions, Rover rover) {
        for (int i = 0; i < instructions.length; i ++) {
            switch(instructions[i].toUpperCase()) {
                case "L":
                    rover.turn("L");
                    break;
                case "R":
                    rover.turn("R");
                    break;
                case "M":
                    switch(rover.currentOrientation) {
                        case N:
                            rover.moveNorth();
                        break;
                        case E:
                            rover.moveEast();
                        break;
                        case S:
                            rover.moveSouth();
                        break;
                        case W:
                            rover.moveWest();
                        break;
                    }
                    break;
                default:
                    uiService.printMessage("Invalid character in movement instructions ::: " + instructions[i]);
            }
        };
    }

}
