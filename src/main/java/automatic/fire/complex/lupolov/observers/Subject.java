package automatic.fire.complex.lupolov.observers;

public interface Subject {

    void register(automatic.fire.complex.lupolov.observers.Observer complex);
    void remove(automatic.fire.complex.lupolov.observers.Observer complex);
    void notifyAllAFC();

}
