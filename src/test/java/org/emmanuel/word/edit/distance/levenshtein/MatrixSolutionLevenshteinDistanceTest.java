package org.emmanuel.word.edit.distance.levenshtein;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixSolutionLevenshteinDistanceTest {

    private LevenshteinDistance levenshteinDistance;

    @Before
    public void setUp() {
        this.levenshteinDistance = new MatrixSolutionLevenshteinDistance();
    }

    @Test
    public void distanceFromKittenToSittingShouldBe3() {
        int distance = this.levenshteinDistance.distance("sitting", "kitten");
        assertEquals(3, distance);
    }

    @Test
    public void distanceFromCatToCapShouldBe1() {
        int distance = this.levenshteinDistance.distance("cat", "cap");
        assertEquals(1, distance);
    }
    
}
