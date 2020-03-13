package automatic.fire.complex.ShellsSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ammunition {
    private Map<Cassette, Integer> stockOfCassettes = new HashMap<>();
    private List<Cassette> returnedList = new LinkedList<>();

    public void addCassette(Cassette cassette, int quantity) {
        if (stockOfCassettes.containsKey(cassette)){
            int currentQuantity = stockOfCassettes.get(cassette);
            int newQuantity = quantity + currentQuantity;
            stockOfCassettes.remove(cassette);
            stockOfCassettes.put(cassette, newQuantity);
        } else {
            stockOfCassettes.put(cassette, quantity);
        }
    }

    public Map<Cassette, Integer> getStockOfCassettes() {
        return stockOfCassettes;
    }

    public Cassette getCassette(Cassette typeOfShells) {
        int currentQuantity = stockOfCassettes.get(typeOfShells);
        currentQuantity--;
        stockOfCassettes.remove(typeOfShells);
        stockOfCassettes.put(typeOfShells, currentQuantity);
        System.out.println("cassette was return");
        return typeOfShells;
    }

    public void returnNotEmptyCassette(Cassette returnedCassette, int balance) {
        returnedList.add(returnedCassette);
    }
    
    public boolean hasNext(Cassette typeOfShells) {
        if (stockOfCassettes.containsKey(typeOfShells) && stockOfCassettes.get(typeOfShells) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Cassette> getReturnedList() {
        return returnedList;
    }

    public Cassette getCassetteFromReturned(Cassette typeOfCassette) {
        for (Cassette cassetteFromReturnedList : returnedList) {
            if (cassetteFromReturnedList.getValue().getClass() == typeOfCassette.getValue().getClass()) {
                return cassetteFromReturnedList;
            }
        }
        return null;
    }
}
