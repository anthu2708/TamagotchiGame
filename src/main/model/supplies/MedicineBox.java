package model.supplies;

import java.util.*;

/**
 * Represents a MedicineBox that stores a collection of pills and their quantities.
 * Provides functionality to add, use, and view pills in the box.
 */

public class MedicineBox {
    private Map<Pill, Integer> pills;

    public MedicineBox() {
        this.pills = new LinkedHashMap<>();
    }

    // EFFECTS: return true if is empty
    public boolean isEmpty() {
        return pills.isEmpty();
    }

    // MODIFIES: this
    // EFFECTS: add a pill to medicine box. 
    //          If the pill is already in the box, its quantity is incremented by 1.
    public void addPill(Pill pill) {
        pills.put(pill, pills.getOrDefault(pill, 0) + 1);
    }

    // REQUIRES: The medicine box is not empty
    // MODIFIES: this
    // EFFECTS: use a pill (if available) and decrease its count;
    // return true if can use that pill; else false
    public boolean usePill(Pill pill) {
        if (pills.containsKey(pill)) {
            pills.put(pill, pills.get(pill) - 1);
            if (pills.get(pill) == 0) {
                pills.remove(pill);
            }
            return true;
        }
        return false;
    }

    // REQUIRES: The medicine box is not empty
    // EFFECTS: return a list of pills in the medicine box with index and details
    public String viewPills() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Map.Entry<Pill, Integer> entry : pills.entrySet()) {
            Pill pill = entry.getKey();
            sb.append(index++)
                .append(". ")
                .append(pill.getName())
                .append(" (Quantity: ")
                .append(entry.getValue())
                .append(", Content: ")
                .append(pill.getContent())
                    .append(")\n");
        }
        return sb.toString();
    }

    // EFFECTS: returns a specific pill by index
    public Pill getPillByIndex(int index) {
        int i = 1;
        for (Pill pill : pills.keySet()) {
            if (i == index) {
                return pill;
            }
            i++;
        }
        return null;
    }

    // REQUIRES: the fridge is not empty
    // EFFECTS: returns a specific food item's quantity by index
    //          return -1 if fail (index not in the list)
    public int getQuantityByIndex(int index) {
        int i = 1;
        for (Map.Entry<Pill, Integer> entry : pills.entrySet()) {
            if (i == index) {
                return entry.getValue();
            }
            i++;
        }
        return -1;
    }

    // getter
    public Map<Pill, Integer> getPill() {
        return pills;
    }
}



