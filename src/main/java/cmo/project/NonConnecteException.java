package cmo.project;

public class NonConnecteException extends Exception{
    private String message;
    public NonConnecteException(String msg) {
        this.message = msg;
    }

    public NonConnecteException() {
        this.message = "Entree non connecte au composant";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
