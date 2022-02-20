package com.mjdebeer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInterfaceService {

    boolean appRunning;

    public UserInterfaceService(boolean applicationRunning) {
        appRunning = applicationRunning;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput(final String requestMessage, final List<String> acceptedCharacters) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        
        System.out.println(requestMessage);
        System.out.println("");

        String userInput = reader.readLine();

        assert acceptedCharacters != null;
        assert !acceptedCharacters.isEmpty();

        if (acceptedCharacters != null && !acceptedCharacters.isEmpty()) {
            if (acceptedCharacters.get(0).equals("init")) {
                return userInput;
            } else if (inputIsValid(userInput.toUpperCase(), acceptedCharacters)) {
                return userInput.toUpperCase();
            } 
        } else {
            printMessage("");
            getUserInput(requestMessage, acceptedCharacters);
        }
 
        return "";
    }

    private boolean inputIsValid(String input, List<String> acceptedCharacters) {
        List<String> inputList = new ArrayList<String>(Arrays.asList(input.split("")));

        for (int i = 0; i < inputList.size(); i++) {
            String character = inputList.get(i);
            if (!acceptedCharacters.contains((character))) {
                printMessage("The following character from your input is not currently allowed: " + character);
                return false;
            }
        }

        return true;
    }

}
