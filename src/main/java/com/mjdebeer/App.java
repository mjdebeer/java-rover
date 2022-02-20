package com.mjdebeer;

import java.io.IOException;

import com.mjdebeer.service.ExecutionService;
import com.mjdebeer.service.UserInterfaceService;

public class App 
{
    static boolean applicationRunning = true;

    public static void main(final String[] args) throws IOException {
        UserInterfaceService userInterfaceService = new UserInterfaceService(applicationRunning);
        ExecutionService executionService = new ExecutionService(userInterfaceService);

        executionService.executeApp();
    }
}
