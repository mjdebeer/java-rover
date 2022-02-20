package com.mjdebeer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mjdebeer.entity.AreaMap;
import com.mjdebeer.entity.Rover;

public class ExecutionService {

    boolean running;
    UserInterfaceService uiService;
    RoverControlService roverControlService;

    public ExecutionService(final UserInterfaceService userInterfaceService) {
        running = userInterfaceService.appRunning;
        uiService = userInterfaceService;
        roverControlService = new RoverControlService(uiService);
    }

    public void executeApp() throws IOException {
        while(running != false) {
            uiService.printMessage("Welcome to the Mars Rover Control Interface.");
            readInstructions();
            running = false;
        }
    }

    private void readInstructions() throws IOException {
        List<String> acceptedCharacters = new ArrayList<>();
        acceptedCharacters.add("init");

        String userInput = uiService.getUserInput("Please specify the path to your input file or type X to exit.", acceptedCharacters);

        if (userInput.toUpperCase().equals("X")) {
            exitApp();
            return;
        }

        BufferedReader inputReader = new BufferedReader(new FileReader(userInput));
        int lineNumber = -1;
        LinkedList<String> instructions = new LinkedList<>();
        List<Rover> rovers = new LinkedList<>();
        AreaMap areaMap = null;
        String line;

        while ((line = inputReader.readLine()) != null) {
            if (lineNumber == -1) {
                String[] gridSize = line.split(" ");
                try {
                    areaMap = new AreaMap(Integer.parseInt(gridSize[0]), Integer.parseInt(gridSize[1]));
                } catch (Exception e) {
                    uiService.printMessage("");
                    uiService.printMessage("Area grid could not be initialized, please check line 0 of input file.");
                    exitApp();
                    break;
                }
                lineNumber = 0;
            } else {
                instructions.add(line);
                if (lineNumber == 0 || lineNumber % 2 == 0) {
                    rovers.add(new Rover(areaMap));
                }
                lineNumber ++;
            }
        };
        inputReader.close();

        roverControlService.deployAndMoveRovers(rovers, instructions);
    }

    public void exitApp() {
        uiService.printMessage("Exiting Mars Rover Control Service.");
        running = false;
    }

}