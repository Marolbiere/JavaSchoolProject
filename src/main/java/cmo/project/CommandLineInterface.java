package cmo.project;

import java.util.Scanner;

public class CommandLineInterface {
    private final Scanner scanner;
    public CommandLineInterface() {
        this.scanner = new Scanner(System.in);
    }
    public String scanCommand() {
        return scanner.nextLine();
    }
}
