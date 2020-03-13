package automatic.fire.complex.ShellsSystem;

public class Cassette<T extends Shell> {
    private T val;
    private int maxSizeForCurrentShells;
    private int balance;

    public Cassette(){}

    public Cassette(T arg) {
        val = arg;
        maxSizeForCurrentShells = val.getMaxSizeOfCassette();
        balance = maxSizeForCurrentShells;
    }

    public Cassette(T arg, int balance) {
        val = arg;
        this.balance = balance;
    }

    public T getValue() {
        return val;
    }
    public String toString() {
        return "{" + val + "}";
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
