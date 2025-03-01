package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

class CoinManagerTest {
    private CoinManager coinManager;

    @BeforeEach
    void setUp() {
        coinManager = new CoinManager(100);
    }

    @Test
    void testConstructor() {
        assertEquals(100, coinManager.getValue());
    }

    @Test
    void testAdd() {
        coinManager.add(50); 
        assertEquals(150, coinManager.getValue());
    }

    @Test
    void testSubtract() {
        coinManager.subtract(30);
        assertEquals(70, coinManager.getValue());
    }

    @Test
    void testSubtractToZero() {
        coinManager.subtract(100);
        assertEquals(0, coinManager.getValue());
    }

    @Test
    void testToJson() {
        CoinManager coinCoinManager = new CoinManager(100);
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("coins", 100);

        JSONObject actualJson = coinCoinManager.toJson();
        assertEquals(expectedJson.toString(), actualJson.toString());
    }

    
}
