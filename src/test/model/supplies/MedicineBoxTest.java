package model.supplies;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicineBoxTest {
    private MedicineBox medicineBox;
    private Pill vitaminC;
    private Pill painRelief;

    @BeforeEach
    void setUp() {
        medicineBox = new MedicineBox();
        vitaminC = new Pill("Vitamin C", 10, 5, 0, 0);
        painRelief = new Pill("Pain Relief", 20, 2, 5, 10);
    }

    @Test
    void testConstructor() {
        assertTrue(medicineBox.isEmpty());
    }

    @Test
    void testAddPill() {
        medicineBox.addPill(vitaminC);
        Map<Pill, Integer> answer = new LinkedHashMap<>();
        answer.put(vitaminC,1);
        assertEquals(answer, medicineBox.getPill());
    }

    @Test
    void testAddPillMultipleTimes() {
        // Act
        medicineBox.addPill(vitaminC);
        medicineBox.addPill(painRelief);
        medicineBox.addPill(vitaminC); 
        Map<Pill, Integer> answer = new LinkedHashMap<>();
        answer.put(vitaminC,2);
        answer.put(painRelief,1);
        assertEquals(answer, medicineBox.getPill());
        
    }

    @Test
    void testUsePill() {
        medicineBox.addPill(vitaminC);
        medicineBox.addPill(painRelief);
        medicineBox.usePill(painRelief);
        Map<Pill, Integer> answer = new LinkedHashMap<>();
        answer.put(vitaminC,1);
        assertEquals(answer, medicineBox.getPill());
    }

    @Test
    void testUseAllPill() {
        medicineBox.addPill(vitaminC);
        medicineBox.addPill(vitaminC);
        medicineBox.usePill(vitaminC);
        medicineBox.usePill(vitaminC);
        Map<Pill, Integer> answer = new LinkedHashMap<>();
        assertEquals(answer, medicineBox.getPill());
    }

    @Test
    void testUsePillNoPill() {
        medicineBox.addPill(painRelief);
        medicineBox.usePill(vitaminC);
        Map<Pill, Integer> answer = new LinkedHashMap<>();
        answer.put(painRelief,1);
        assertEquals(answer, medicineBox.getPill());
    }

    @Test
    void testViewPills() {
        // Arrange
        medicineBox.addPill(vitaminC);
        medicineBox.addPill(painRelief);

        // Act & Assert
        assertEquals("1. Vitamin C (Quantity: 1, Content: Nutrition Value - 5, "
                + "Health Value - 0, Happiness Value - 0)\n" 
                + "2. Pain Relief (Quantity: 1, Content: Nutrition Value - 2, "
                +  "Health Value - 5, Happiness Value - 10)\n",
                     medicineBox.viewPills());
    }

    @Test
    void testGetPillByIndex() {
        // Arrange
        medicineBox.addPill(vitaminC);
        medicineBox.addPill(painRelief);

        // Act & Assert
        assertEquals(vitaminC, medicineBox.getPillByIndex(1));
        assertEquals(painRelief, medicineBox.getPillByIndex(2));
        assertEquals(null, medicineBox.getPillByIndex(3));
    }

    @Test
    void testGetQuantityByIndex() {
        medicineBox.addPill(vitaminC);
        medicineBox.addPill(painRelief);
        medicineBox.addPill(painRelief);
        assertEquals(1, medicineBox.getQuantityByIndex(1));
        assertEquals(2, medicineBox.getQuantityByIndex(2));
        assertEquals(-1, medicineBox.getQuantityByIndex(3));
    }

    @Test
    void testToJson() {
        Pill painKiller = new Pill("Painkiller", 50, 20, 10, 5);
        Pill vitamin = new Pill("Vitamin", 30, 15, 5, 10);
        medicineBox.addPill(painKiller);
        medicineBox.addPill(painKiller);
        medicineBox.addPill(vitamin);

        JSONObject expectedJson = initExpectedJson();
        

        JSONObject actualJson = medicineBox.toJson();
        assertEquals(expectedJson.toString(), actualJson.toString());
    }

    // helper
    JSONObject initExpectedJson() {
        JSONObject expectedJson = new JSONObject();
        JSONArray pillsArray = new JSONArray();
        pillsArray.put(new JSONObject()
                .put("name", "Painkiller")
                .put("cost", 50)
                .put("nutritionValue", 20)
                .put("healthValue", 10)
                .put("happinessValue", 5)
                .put("quantity", 2));
        pillsArray.put(new JSONObject()
                .put("name", "Vitamin")
                .put("cost", 30)
                .put("nutritionValue", 15)
                .put("healthValue", 5)
                .put("happinessValue", 10)
                .put("quantity", 1));
        expectedJson.put("pills", pillsArray);
        return expectedJson;
    }
}
