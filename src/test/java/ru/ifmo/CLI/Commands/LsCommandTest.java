package ru.ifmo.CLI.Commands;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.ifmo.CLI.InterpreterEnvironment;
import ru.ifmo.CLI.Utils.IOData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LsCommandTest {

    static String pathTest = "src/test/resources/test/lscommand";

    @Test
    void testWithArg() {
        Command command = new LsCommand();
        command.addArguments(Collections.singletonList(pathTest));
        IOData output = command.execute();
        String result = output.getData().get(0);
        assertEquals(result, "a");
    }

    @Test
    void testWithoutArg() {
        makeCdCommand(pathTest).execute();
        Command command = new LsCommand();
        IOData output = command.execute();
        String result = output.getData().get(0);
        assertEquals(result, "a");
    }

    @AfterEach
    void trueCurrentDirectory() {
        InterpreterEnvironment.currentDirectoryName = System.getProperty("user.dir");
    }

    private CdCommand makeCdCommand(String destination) {
        var command = new CdCommand();
        List<String> arguments = new ArrayList<>();
        arguments.add(destination);
        command.addArguments(arguments);
        return command;
    }




}
