package automatic.fire.complex.observer;

public interface Subject {

    void register(automatic.fire.complex.observer.Observer complex);
    void remove(automatic.fire.complex.observer.Observer complex);
    void notifyAllAFC();

}
