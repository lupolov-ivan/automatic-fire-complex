package automatic.fire.complex.ShellsSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cassette<T extends Shell> {
    private List<T> shells;
    private int startQuantity;
    private int balance;

    public Cassette(int startQuantity) {
        this.startQuantity = startQuantity;
        shells = new ArrayList<>(startQuantity);
    }

    public boolean add(T shell) {
        if (shells.size() < startQuantity) {
            shells.add(shell);
            balance = shells.size();
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
        shells.remove(shells.size()-1);
        balance = shells.size();
        return shells.get(0);
    }

    public int getBalance() {
        return balance;
    }
    public T getInstanceInnerElement() {
        return shells.get(0);
    }
}
