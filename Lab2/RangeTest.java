
Copy

package org.jfree.data.test;
 
import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
 
/**
 * Black-box unit test suite for the org.jfree.data.Range class.
 * Test cases were designed using equivalence class partitioning and
 * boundary value analysis (BVA) based strictly on the Javadoc API
 * specifications for JFreeChart.
 *
 * Methods under test:
 * - getUpperBound()
 * - getLowerBound()
 * - getLength()
 * - contains(double value)
 * - combine(Range range1, Range range2)
 *
 */
public class RangeTest {
 
    // Shared Range object used across multiple test methods
    private Range rangeObjectUnderTest;
 
    /**
     * Sets up the shared Range object before each test method runs.
     * Range(-1, 1) is used as the default shared object as it covers
     * a range that crosses zero which is a meaningful test case.
     */
    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-1, 1);
    }
 
    /**
     * Tears down the shared Range object after each test method runs.
     * Setting to null ensures no state leaks between test methods.
     */
    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }
 
    // ==================== getUpperBound() tests ====================
    // Javadoc: Returns the upper bound for the range.
    // Equivalence classes: positive range, negative range, cross-zero range,
    // equal bounds (boundary), large value (boundary)
 
    @Test
    public void testGetUpperBoundPositiveRange() {
        // EC: both bounds positive, upper > lower
        Range r = new Range(2, 5);
        assertEquals("Upper bound of range(2,5) should be 5",
                5.0, r.getUpperBound(), 0.000000001d);
    }
 
    @Test
    public void testGetUpperBoundNegativeRange() {
        // EC: both bounds negative, upper > lower
        Range r = new Range(-10, -3);
        assertEquals("Upper bound of range(-10,-3) should be -3",
                -3.0, r.getUpperBound(), 0.000000001d);
    }
 
    @Test
    public void testGetUpperBoundCrossesZero() {
        // EC: range crosses zero, lower is negative, upper is positive
        Range r = new Range(-5, 5);
        assertEquals("Upper bound of range(-5,5) should be 5",
                5.0, r.getUpperBound(), 0.000000001d);
    }
 
    @Test
    public void testGetUpperBoundEqualBounds() {
        // BVA boundary: lower equals upper, zero-length range
        Range r = new Range(3, 3);
        assertEquals("Upper bound of range(3,3) should be 3",
                3.0, r.getUpperBound(), 0.000000001d);
    }
 
    @Test
    public void testGetUpperBoundLargeValue() {
        // BVA boundary: very large upper bound value
        Range r = new Range(0, 1000000);
        assertEquals("Upper bound of range(0,1000000) should be 1000000",
                1000000.0, r.getUpperBound(), 0.000000001d);
    }
 
    // ==================== getLowerBound() tests ====================
    // Javadoc: Returns the lower bound for the range.
    // Equivalence classes: positive range, negative range, cross-zero range,
    // equal bounds (boundary), large negative value (boundary)
 
    @Test
    public void testGetLowerBoundPositiveRange() {
        // EC: both bounds positive, lower < upper
        Range r = new Range(2, 5);
        assertEquals("Lower bound of range(2,5) should be 2",
                2.0, r.getLowerBound(), 0.000000001d);
    }
 
    @Test
    public void testGetLowerBoundNegativeRange() {
        // EC: both bounds negative, lower < upper
        Range r = new Range(-10, -3);
        assertEquals("Lower bound of range(-10,-3) should be -10",
                -10.0, r.getLowerBound(), 0.000000001d);
    }
 
    @Test
    public void testGetLowerBoundCrossesZero() {
        // EC: range crosses zero, lower is negative, upper is positive
        Range r = new Range(-5, 5);
        assertEquals("Lower bound of range(-5,5) should be -5",
                -5.0, r.getLowerBound(), 0.000000001d);
    }
 
    @Test
    public void testGetLowerBoundEqualBounds() {
        // BVA boundary: lower equals upper, zero-length range
        Range r = new Range(3, 3);
        assertEquals("Lower bound of range(3,3) should be 3",
                3.0, r.getLowerBound(), 0.000000001d);
    }
 
    @Test
    public void testGetLowerBoundLargeNegativeValue() {
        // BVA boundary: very large negative lower bound value
        Range r = new Range(-1000000, 0);
        assertEquals("Lower bound of range(-1000000,0) should be -1000000",
                -1000000.0, r.getLowerBound(), 0.000000001d);
    }
 
    // ==================== getLength() tests ====================
    // Javadoc: Returns the length of the range (upper - lower).
    // Equivalence classes: both positive equal, both positive not equal,
    // both negative equal (boundary), both negative not equal, cross-zero
 
    @Test
    public void testGetLengthBothPositiveEqual() {
        // BVA boundary: zero-length range with positive equal bounds
        Range r = new Range(2, 2);
        assertEquals("Length of range(2,2) should be 0",
                0.0, r.getLength(), 0.000000001d);
    }
 
    @Test
    public void testGetLengthBothPositiveNotEqual() {
        // EC: both bounds positive, upper > lower
        Range r = new Range(4, 9);
        assertEquals("Length of range(4,9) should be 5",
                5.0, r.getLength(), 0.000000001d);
    }
 
    @Test
    public void testGetLengthBothNegativeEqual() {
        // BVA boundary: zero-length range with negative equal bounds
        Range r = new Range(-99, -99);
        assertEquals("Length of range(-99,-99) should be 0",
                0.0, r.getLength(), 0.000000001d);
    }
 
    @Test
    public void testGetLengthBothNegativeNotEqual() {
        // EC: both bounds negative, upper > lower
        Range r = new Range(-11, -4);
        assertEquals("Length of range(-11,-4) should be 7",
                7.0, r.getLength(), 0.000000001d);
    }
 
    @Test
    public void testGetLengthOnePositiveOneNegative() {
        // EC: range crosses zero, lower negative upper positive
        Range r = new Range(-5, 3);
        assertEquals("Length of range(-5,3) should be 8",
                8.0, r.getLength(), 0.000000001d);
    }
 
    // ==================== contains(double value) tests ====================
    // Javadoc: Returns true if the specified value is within the range.
    // Equivalence classes: value inside range, value below range,
    // value above range, value at lower boundary, value at upper boundary
 
    @Test
    public void testContainsValueInsideRange() {
        // EC: value is inside the range, should return true
        Range r = new Range(1, 10);
        assertTrue("Range(1,10) should contain 5",
                r.contains(5.0));
    }
 
    @Test
    public void testContainsValueBelowRange() {
        // EC: value is below the lower bound, should return false
        Range r = new Range(1, 10);
        assertFalse("Range(1,10) should not contain 0",
                r.contains(0.0));
    }
 
    @Test
    public void testContainsValueAboveRange() {
        // EC: value is above the upper bound, should return false
        Range r = new Range(1, 10);
        assertFalse("Range(1,10) should not contain 11",
                r.contains(11.0));
    }
 
    @Test
    public void testContainsLowerBoundary() {
        // BVA boundary: value is exactly at the lower bound, should return true
        Range r = new Range(1, 10);
        assertTrue("Range(1,10) should contain lower boundary 1",
                r.contains(1.0));
    }
 
    @Test
    public void testContainsUpperBoundary() {
        // BVA boundary: value is exactly at the upper bound, should return true
        Range r = new Range(1, 10);
        assertTrue("Range(1,10) should contain upper boundary 10",
                r.contains(10.0));
    }
 
    // ==================== combine(Range, Range) tests ====================
    // Javadoc: Creates a new range by combining two existing ranges.
    // Returns null if both are null, returns the other range if one is null.
    // Equivalence classes: both valid, first null, second null,
    // both null (boundary), non-overlapping ranges
 
    @Test
    public void testCombineTwoValidRanges() {
        // EC: both ranges are valid and non-null, should combine correctly
        // try/catch used to handle injected defect in SUT combine() method
        try {
            Range r1 = new Range(1, 5);
            Range r2 = new Range(3, 8);
            Range result = Range.combine(r1, r2);
            assertEquals("Combined lower bound should be 1",
                    1.0, result.getLowerBound(), 0.000000001d);
            assertEquals("Combined upper bound should be 8",
                    8.0, result.getUpperBound(), 0.000000001d);
        } catch (IllegalArgumentException e) {
            fail("combine() threw unexpected IllegalArgumentException: " + e.getMessage());
        }
    }
 
    @Test
    public void testCombineFirstRangeNull() {
        // BVA boundary: first range is null, should return second range
        Range r2 = new Range(3, 8);
        Range result = Range.combine(null, r2);
        assertEquals("Combining null with range(3,8) should return range(3,8)",
                r2, result);
    }
 
    @Test
    public void testCombineSecondRangeNull() {
        // BVA boundary: second range is null, should return first range
        Range r1 = new Range(1, 5);
        Range result = Range.combine(r1, null);
        assertEquals("Combining range(1,5) with null should return range(1,5)",
                r1, result);
    }
 
    @Test
    public void testCombineBothRangesNull() {
        // BVA boundary: both ranges are null, should return null
        Range result = Range.combine(null, null);
        assertNull("Combining null with null should return null",
                result);
    }
 
    @Test
    public void testCombineNonOverlappingRanges() {
        // EC: two valid non-overlapping ranges, should span full extent
        // try/catch used to handle injected defect in SUT combine() method
        try {
            Range r1 = new Range(1, 3);
            Range r2 = new Range(7, 10);
            Range result = Range.combine(r1, r2);
            assertEquals("Combined lower bound should be 1",
                    1.0, result.getLowerBound(), 0.000000001d);
            assertEquals("Combined upper bound should be 10",
                    10.0, result.getUpperBound(), 0.000000001d);
        } catch (IllegalArgumentException e) {
            fail("combine() threw unexpected IllegalArgumentException: " + e.getMessage());
        }
    }
}
