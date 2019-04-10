package ru.ifmo.CLI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//class implementing external command calls
public class ExternalCommand extends Command {
    public ExternalCommand(List<String> arguments) {
        super.arguments = arguments;
    }

    public ExternalCommand(IOData data) {
        this.data = data;
    }

    public IOData execute() {
        ProcessBuilder processBuilder = new ProcessBuilder(arguments);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader buffReader = new BufferedReader(streamReader);
            String line = buffReader.readLine();
            List<String> input = new ArrayList<String>();
            while (line != null) {
                input.add(line);
                line = buffReader.readLine();
            }
            return new IOData(input);
        } catch (IOException ex) {
            String message = arguments.get(0) + " failed";
            List<String> result = new ArrayList<>();
            result.add(message);
            return new IOData(result);
        }
    }
}
