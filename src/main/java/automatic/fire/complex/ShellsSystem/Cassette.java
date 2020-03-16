package automatic.fire.complex.ShellsSystem;

import java.util.ArrayList;
import java.util.List;

public class Cassette<T extends Shell> {
    private List<T> shells;
    private int balance;

    public Cassette(){}
    public Cassette(int balance) {
        this.balance = balance;
        shells = new ArrayList<>(balance);
    }

    public boolean add(T shell) {
        if (shells.size() <= balance) {
            shells.add(shell);
            return true;
        }else {
            return false;
        }
    }

    public boolean hasNext() {
        if (balance > 0) {
            return true;
        }else {
            return false;
        }
    }

    public T getShell() {
        return shells.get(balance);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
