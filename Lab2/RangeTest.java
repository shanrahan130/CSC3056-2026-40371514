package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RangeTest {

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-1, 1);
    }

    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }

    // ==================== getUpperBound() tests ====================

    @Test
    public void testGetUpperBoundPositiveRange() {
        Range r = new Range(2, 5);
        assertEquals("Upper bound of range(2,5) should be 5",
                5.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testGetUpperBoundNegativeRange() {
        Range r = new Range(-10, -3);
        assertEquals("Upper bound of range(-10,-3) should be -3",
                -3.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testGetUpperBoundCrossesZero() {
        Range r = new Range(-5, 5);
        assertEquals("Upper bound of range(-5,5) should be 5",
                5.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testGetUpperBoundEqualBounds() {
        Range r = new Range(3, 3);
        assertEquals("Upper bound of range(3,3) should be 3",
                3.0, r.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testGetUpperBoundLargeValue() {
        Range r = new Range(0, 1000000);
        assertEquals("Upper bound of range(0,1000000) should be 1000000",
                1000000.0, r.getUpperBound(), 0.000000001d);
    }

    // ==================== getLowerBound() tests ====================

    @Test
    public void testGetLowerBoundPositiveRange() {
        Range r = new Range(2, 5);
        assertEquals("Lower bound of range(2,5) should be 2",
                2.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testGetLowerBoundNegativeRange() {
        Range r = new Range(-10, -3);
        assertEquals("Lower bound of range(-10,-3) should be -10",
                -10.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testGetLowerBoundCrossesZero() {
        Range r = new Range(-5, 5);
        assertEquals("Lower bound of range(-5,5) should be -5",
                -5.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testGetLowerBoundEqualBounds() {
        Range r = new Range(3, 3);
        assertEquals("Lower bound of range(3,3) should be 3",
                3.0, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testGetLowerBoundLargeNegativeValue() {
        Range r = new Range(-1000000, 0);
        assertEquals("Lower bound of range(-1000000,0) should be -1000000",
                -1000000.0, r.getLowerBound(), 0.000000001d);
    }

    // ==================== getLength() tests ====================

    @Test
    public void testGetLengthBothPositiveEqual() {
        Range r = new Range(2, 2);
        assertEquals("Length of range(2,2) should be 0",
                0.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthBothPositiveNotEqual() {
        Range r = new Range(4, 9);
        assertEquals("Length of range(4,9) should be 5",
                5.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthBothNegativeEqual() {
        Range r = new Range(-99, -99);
        assertEquals("Length of range(-99,-99) should be 0",
                0.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthBothNegativeNotEqual() {
        Range r = new Range(-11, -4);
        assertEquals("Length of range(-11,-4) should be 7",
                7.0, r.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthOnePositiveOneNegative() {
        Range r = new Range(-5, 3);
        assertEquals("Length of range(-5,3) should be 8",
                8.0, r.getLength(), 0.000000001d);
    }

    // ==================== contains(double value) tests ====================

    @Test
    public void testContainsValueInsideRange() {
        Range r = new Range(1, 10);
        assertTrue("Range(1,10) should contain 5",
                r.contains(5.0));
    }

    @Test
    public void testContainsValueBelowRange() {
        Range r = new Range(1, 10);
        assertFalse("Range(1,10) should not contain 0",
                r.contains(0.0));
    }

    @Test
    public void testContainsValueAboveRange() {
        Range r = new Range(1, 10);
        assertFalse("Range(1,10) should not contain 11",
                r.contains(11.0));
    }

    @Test
    public void testContainsLowerBoundary() {
        Range r = new Range(1, 10);
        assertTrue("Range(1,10) should contain lower boundary 1",
                r.contains(1.0));
    }

    @Test
    public void testContainsUpperBoundary() {
        Range r = new Range(1, 10);
        assertTrue("Range(1,10) should contain upper boundary 10",
                r.contains(10.0));
    }

    // ==================== combine(Range, Range) tests ====================

    @Test
    public void testCombineTwoValidRanges() {
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
        Range r2 = new Range(3, 8);
        Range result = Range.combine(null, r2);
        assertEquals("Combining null with range(3,8) should return range(3,8)",
                r2, result);
    }

    @Test
    public void testCombineSecondRangeNull() {
        Range r1 = new Range(1, 5);
        Range result = Range.combine(r1, null);
        assertEquals("Combining range(1,5) with null should return range(1,5)",
                r1, result);
    }

    @Test
    public void testCombineBothRangesNull() {
        Range result = Range.combine(null, null);
        assertNull("Combining null with null should return null",
                result);
    }

    @Test
    public void testCombineNonOverlappingRanges() {
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
