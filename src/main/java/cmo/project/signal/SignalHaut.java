package cmo.project.signal;

public class SignalHaut extends SignalLogic{
    public Boolean value() {
        return true;
    }

    public SignalLogic not() {
        return new SignalBas();
    }

    public SignalLogic and(SignalLogic bool) {return bool;}

    public SignalLogic or(SignalLogic bool) {
        return this;
    }


    public String toString() {
        return Boolean.toString(value());
    }

}
