package cmo.project;

public class NonChoiceException extends Exception{
    private final String message;
    public NonChoiceException(String msg) {
        this.message = msg;
    }

    public NonChoiceException() {
        this.message = "Veuillez rentrer 0 ou 1 ! (1 pour une état haut, 0 pour un état bas)";
    }

    @Override
    public String getMessage() {
        return message;
    }
}