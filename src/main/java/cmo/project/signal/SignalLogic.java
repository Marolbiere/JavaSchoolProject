package cmo.project.signal;

public abstract class SignalLogic {
    public abstract Boolean value();
    public abstract SignalLogic not();
    public abstract SignalLogic and(SignalLogic bool);
    public abstract SignalLogic or(SignalLogic bool);
    public abstract String toString();
}