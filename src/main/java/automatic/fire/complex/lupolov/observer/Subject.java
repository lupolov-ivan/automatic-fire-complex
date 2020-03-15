package automatic.fire.complex.lupolov.observer;

public interface Subject {

    void register(automatic.fire.complex.lupolov.observer.Observer complex);
    void remove(automatic.fire.complex.lupolov.observer.Observer complex);
    void notifyAllAFC();

}
