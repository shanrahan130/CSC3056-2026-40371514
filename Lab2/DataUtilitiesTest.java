package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataUtilitiesTest {

    private Values2D values2D;

    @Before
    public void setUp() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values2D = testValues;
        testValues.addValue(1, 0, 0);
        testValues.addValue(4, 1, 0);
    }

    @After
    public void tearDown() {
        values2D = null;
    }

    // ==================== calculateColumnTotal() tests ====================

    @Test
    public void testCalculateColumnTotalValidData() {
        assertEquals("Column total should be 5.0",
                5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.000000001d);
    }

    @Test
    public void testCalculateColumnTotalNullData() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("No exception thrown. Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCalculateColumnTotalSingleRow() {
        DefaultKeyedValues2D single = new DefaultKeyedValues2D();
        single.addValue(7, 0, 0);
        assertEquals("Column total of single row should be 7.0",
                7.0, DataUtilities.calculateColumnTotal(single, 0), 0.000000001d);
    }

    @Test
    public void testCalculateColumnTotalNegativeValues() {
        DefaultKeyedValues2D negValues = new DefaultKeyedValues2D();
        negValues.addValue(-3, 0, 0);
        negValues.addValue(-2, 1, 0);
        assertEquals("Column total of negative values should be -5.0",
                -5.0, DataUtilities.calculateColumnTotal(negValues, 0), 0.000000001d);
    }

    @Test
    public void testCalculateColumnTotalEmptyData() {
        DefaultKeyedValues2D empty = new DefaultKeyedValues2D();
        assertEquals("Column total of empty data should be 0.0",
                0.0, DataUtilities.calculateColumnTotal(empty, 0), 0.000000001d);
    }

    // ==================== calculateRowTotal() tests ====================

    @Test
    public void testCalculateRowTotalValidData() {
        DefaultKeyedValues2D rowData = new DefaultKeyedValues2D();
        rowData.addValue(2, 0, 0);
        rowData.addValue(3, 0, 1);
        assertEquals("Row total should be 5.0",
                5.0, DataUtilities.calculateRowTotal(rowData, 0), 0.000000001d);
    }

    @Test
    public void testCalculateRowTotalNullData() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("No exception thrown. Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCalculateRowTotalSingleColumn() {
        DefaultKeyedValues2D single = new DefaultKeyedValues2D();
        single.addValue(9, 0, 0);
        assertEquals("Row total of single column should be 9.0",
                9.0, DataUtilities.calculateRowTotal(single, 0), 0.000000001d);
    }

    @Test
    public void testCalculateRowTotalNegativeValues() {
        DefaultKeyedValues2D negValues = new DefaultKeyedValues2D();
        negValues.addValue(-4, 0, 0);
        negValues.addValue(-6, 0, 1);
        assertEquals("Row total of negative values should be -10.0",
                -10.0, DataUtilities.calculateRowTotal(negValues, 0), 0.000000001d);
    }

    @Test
    public void testCalculateRowTotalEmptyData() {
        DefaultKeyedValues2D empty = new DefaultKeyedValues2D();
        assertEquals("Row total of empty data should be 0.0",
                0.0, DataUtilities.calculateRowTotal(empty, 0), 0.000000001d);
    }

    // ==================== createNumberArray() tests ====================

    @Test
    public void testCreateNumberArrayValidData() {
        try {
            double[] input = {1.0, 2.0, 3.0};
            Number[] result = DataUtilities.createNumberArray(input);
            assertEquals("Array length should be 3", 3, result.length);
            assertEquals("First element should be 1.0", 1.0, result[0].doubleValue(), 0.000000001d);
            assertEquals("Second element should be 2.0", 2.0, result[1].doubleValue(), 0.000000001d);
            assertEquals("Third element should be 3.0", 3.0, result[2].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArrayNullData() {
        try {
            DataUtilities.createNumberArray(null);
            fail("No exception thrown. Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCreateNumberArrayEmptyArray() {
        try {
            double[] input = {};
            Number[] result = DataUtilities.createNumberArray(input);
            assertEquals("Empty array should return array of length 0", 0, result.length);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArrayNegativeValues() {
        try {
            double[] input = {-1.0, -2.0, -3.0};
            Number[] result = DataUtilities.createNumberArray(input);
            assertEquals("First element should be -1.0", -1.0, result[0].doubleValue(), 0.000000001d);
            assertEquals("Second element should be -2.0", -2.0, result[1].doubleValue(), 0.000000001d);
            assertEquals("Third element should be -3.0", -3.0, result[2].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArraySingleElement() {
        try {
            double[] input = {5.0};
            Number[] result = DataUtilities.createNumberArray(input);
            assertEquals("Array length should be 1", 1, result.length);
            assertEquals("First element should be 5.0", 5.0, result[0].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    // ==================== createNumberArray2D() tests ====================

    @Test
    public void testCreateNumberArray2DValidData() {
        try {
            double[][] input = {{1.0, 2.0}, {3.0, 4.0}};
            Number[][] result = DataUtilities.createNumberArray2D(input);
            assertEquals("Should have 2 rows", 2, result.length);
            assertEquals("Should have 2 columns", 2, result[0].length);
            assertEquals("Element [0][0] should be 1.0", 1.0, result[0][0].doubleValue(), 0.000000001d);
            assertEquals("Element [1][1] should be 4.0", 4.0, result[1][1].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArray2DNullData() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("No exception thrown. Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCreateNumberArray2DEmptyArray() {
        try {
            double[][] input = {};
            Number[][] result = DataUtilities.createNumberArray2D(input);
            assertEquals("Empty 2D array should return array of length 0", 0, result.length);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArray2DSingleElement() {
        try {
            double[][] input = {{7.0}};
            Number[][] result = DataUtilities.createNumberArray2D(input);
            assertEquals("Should have 1 row", 1, result.length);
            assertEquals("Element [0][0] should be 7.0", 7.0, result[0][0].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArray2DNegativeValues() {
        try {
            double[][] input = {{-1.0, -2.0}, {-3.0, -4.0}};
            Number[][] result = DataUtilities.createNumberArray2D(input);
            assertEquals("Element [0][0] should be -1.0", -1.0, result[0][0].doubleValue(), 0.000000001d);
            assertEquals("Element [1][1] should be -4.0", -4.0, result[1][1].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    // ==================== getCumulativePercentages() tests ====================

    @Test
    public void testGetCumulativePercentagesValidData() {
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>) Integer.valueOf(0), 6.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(1), 11.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(2), 3.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
        assertEquals("First cumulative percentage should be ~0.3",
                0.3, result.getValue(0).doubleValue(), 0.01d);
        assertEquals("Last cumulative percentage should be 1.0",
                1.0, result.getValue(2).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentagesNullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("No exception thrown. Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testGetCumulativePercentagesSingleValue() {
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>) Integer.valueOf(0), 5.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
        assertEquals("Single value cumulative percentage should be 1.0",
                1.0, result.getValue(0).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentagesEqualValues() {
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>) Integer.valueOf(0), 5.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(1), 5.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
        assertEquals("First cumulative percentage of equal values should be 0.5",
                0.5, result.getValue(0).doubleValue(), 0.000000001d);
        assertEquals("Second cumulative percentage of equal values should be 1.0",
                1.0, result.getValue(1).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentagesResultSize() {
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>) Integer.valueOf(0), 1.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(1), 2.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(2), 3.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
        assertEquals("Result should have 3 entries", 3, result.getItemCount());
    }
}
