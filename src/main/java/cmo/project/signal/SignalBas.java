package cmo.project.signal;

public class SignalBas extends SignalLogic{
    public Boolean value() {
        return false;
    }

    public SignalLogic not() {
        return new SignalHaut();
    }
    public SignalLogic and(SignalLogic bool) {return this;}

    public SignalLogic or(SignalLogic bool) {
        return bool;
    }

    public String toString() {
        // System.out.println(value());
        return Boolean.toString(value());
    }
}
