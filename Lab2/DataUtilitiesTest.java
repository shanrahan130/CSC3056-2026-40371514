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
 
/**
 * Methods under test:
 * - calculateColumnTotal(Values2D data, int column)
 * - calculateRowTotal(Values2D data, int row)
 * - createNumberArray(double[] data)
 * - createNumberArray2D(double[][] data)
 * - getCumulativePercentages(KeyedValues data)
 */
public class DataUtilitiesTest {
 
    // Shared Values2D object used by calculateColumnTotal tests
    // Initialised in setUp() with values {1, 4} in column 0
    private Values2D values2D;
 
    /**
     * Sets up the shared Values2D object before each test method runs.
     * DefaultKeyedValues2D is used as the implementing class for the
     * Values2D interface as specified in the Javadoc API documentation.
     * Values added: row 0 = 1, row 1 = 4 in column 0 (total = 5)
     */
    @Before
    public void setUp() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values2D = testValues;
        testValues.addValue(1, 0, 0);
        testValues.addValue(4, 1, 0);
    }
 
    /**
     * Tears down the shared Values2D object after each test method runs.
     * Setting to null ensures no state leaks between test methods.
     */
    @After
    public void tearDown() {
        values2D = null;
    }
 
    // ==================== calculateColumnTotal() tests ====================
    // Javadoc: Returns the sum of the values in one column of the supplied
    // data table. With invalid input, a total of zero will be returned.
    // Throws: IllegalArgumentException if invalid data object is passed in.
    // Equivalence classes: valid positive data, valid negative data,
    // single row, empty data, null data (boundary)
 
    @Test
    public void testCalculateColumnTotalValidData() {
        // EC: valid non-null data with positive values, uses shared setUp object
        assertEquals("Column total should be 5.0",
                5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.000000001d);
    }
 
    @Test
    public void testCalculateColumnTotalNullData() {
        // BVA boundary: null data should throw IllegalArgumentException per Javadoc
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
        // EC: valid data with only a single row value
        DefaultKeyedValues2D single = new DefaultKeyedValues2D();
        single.addValue(7, 0, 0);
        assertEquals("Column total of single row should be 7.0",
                7.0, DataUtilities.calculateColumnTotal(single, 0), 0.000000001d);
    }
 
    @Test
    public void testCalculateColumnTotalNegativeValues() {
        // EC: valid data with negative values in the column
        DefaultKeyedValues2D negValues = new DefaultKeyedValues2D();
        negValues.addValue(-3, 0, 0);
        negValues.addValue(-2, 1, 0);
        assertEquals("Column total of negative values should be -5.0",
                -5.0, DataUtilities.calculateColumnTotal(negValues, 0), 0.000000001d);
    }
 
    @Test
    public void testCalculateColumnTotalEmptyData() {
        // BVA boundary: empty table with no values, should return 0.0
        DefaultKeyedValues2D empty = new DefaultKeyedValues2D();
        assertEquals("Column total of empty data should be 0.0",
                0.0, DataUtilities.calculateColumnTotal(empty, 0), 0.000000001d);
    }
 
    // ==================== calculateRowTotal() tests ====================
    // Javadoc: Returns the sum of the values in one row of the supplied
    // data table. With invalid input, a total of zero will be returned.
    // Throws: IllegalArgumentException if invalid data object is passed in.
    // Equivalence classes: valid positive data, valid negative data,
    // single column, empty data, null data (boundary)
 
    @Test
    public void testCalculateRowTotalValidData() {
        // EC: valid non-null data with positive values across columns
        DefaultKeyedValues2D rowData = new DefaultKeyedValues2D();
        rowData.addValue(2, 0, 0);
        rowData.addValue(3, 0, 1);
        assertEquals("Row total should be 5.0",
                5.0, DataUtilities.calculateRowTotal(rowData, 0), 0.000000001d);
    }
 
    @Test
    public void testCalculateRowTotalNullData() {
        // BVA boundary: null data should throw IllegalArgumentException per Javadoc
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
        // EC: valid data with only a single column value
        DefaultKeyedValues2D single = new DefaultKeyedValues2D();
        single.addValue(9, 0, 0);
        assertEquals("Row total of single column should be 9.0",
                9.0, DataUtilities.calculateRowTotal(single, 0), 0.000000001d);
    }
 
    @Test
    public void testCalculateRowTotalNegativeValues() {
        // EC: valid data with negative values across columns
        DefaultKeyedValues2D negValues = new DefaultKeyedValues2D();
        negValues.addValue(-4, 0, 0);
        negValues.addValue(-6, 0, 1);
        assertEquals("Row total of negative values should be -10.0",
                -10.0, DataUtilities.calculateRowTotal(negValues, 0), 0.000000001d);
    }
 
    @Test
    public void testCalculateRowTotalEmptyData() {
        // BVA boundary: empty table with no values, should return 0.0
        DefaultKeyedValues2D empty = new DefaultKeyedValues2D();
        assertEquals("Row total of empty data should be 0.0",
                0.0, DataUtilities.calculateRowTotal(empty, 0), 0.000000001d);
    }
 
    // ==================== createNumberArray() tests ====================
    // Javadoc: Constructs an array of Number objects from an array of
    // double primitives.
    // Throws: IllegalArgumentException if invalid data array is passed in.
    // Equivalence classes: valid positive values, valid negative values,
    // single element, empty array (boundary), null (boundary)
 
    @Test
    public void testCreateNumberArrayValidData() {
        // EC: valid array with multiple positive double values
        // try/catch handles injected defect in SUT createNumberArray() method
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
        // BVA boundary: null input should throw IllegalArgumentException per Javadoc
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
        // BVA boundary: empty array should return a Number array of length 0
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
        // EC: valid array with multiple negative double values
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
        // BVA boundary: array with a single element, minimum meaningful array size
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
    // Javadoc: Constructs an array of Number objects from a corresponding
    // structure containing double primitives.
    // Throws: IllegalArgumentException if invalid data array is passed in.
    // Equivalence classes: valid positive values, valid negative values,
    // single element, empty array (boundary), null (boundary)
 
    @Test
    public void testCreateNumberArray2DValidData() {
        // EC: valid 2D array with positive values, checks dimensions and values
        // try/catch handles injected defect in SUT createNumberArray2D() method
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
        // BVA boundary: null input should throw IllegalArgumentException per Javadoc
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
        // BVA boundary: empty 2D array should return a Number array of length 0
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
        // BVA boundary: 2D array with a single element, minimum meaningful size
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
        // EC: valid 2D array with negative values, checks correct conversion
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
    // Javadoc: Returns a KeyedValues instance that contains the cumulative
    // percentage values for the data in another KeyedValues instance.
    // Throws: IllegalArgumentException if invalid data object is passed in.
    // Equivalence classes: valid multiple entries, single entry (boundary),
    // equal values, result size check, null (boundary)
    // Note: DefaultKeyedValues used as implementing class for KeyedValues interface
 
    @Test
    public void testGetCumulativePercentagesValidData() {
        // EC: valid KeyedValues with multiple entries of varying values
        // Keys {0,1,2} with values {6.0, 11.0, 3.0}, total = 20.0
        // Expected: first = 6/20 = 0.3, last = 20/20 = 1.0
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
        // BVA boundary: null input should throw IllegalArgumentException per Javadoc
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
        // BVA boundary: single entry should give cumulative percentage of 1.0
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>) Integer.valueOf(0), 5.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
        assertEquals("Single value cumulative percentage should be 1.0",
                1.0, result.getValue(0).doubleValue(), 0.000000001d);
    }
 
    @Test
    public void testGetCumulativePercentagesEqualValues() {
        // EC: two equal values should give 0.5 and 1.0 as cumulative percentages
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
        // EC: result KeyedValues should have same number of entries as input
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>) Integer.valueOf(0), 1.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(1), 2.0);
        keyValues.addValue((Comparable<?>) Integer.valueOf(2), 3.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
        assertEquals("Result should have 3 entries", 3, result.getItemCount());
    }
}
 
