package com.pet.sas.stringsandsquare.calculation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SquareTaskTest {
    List<Integer> exampleList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> resultMagicList = Arrays.asList(8, 1, 6, 3, 5, 7, 4, 9, 2);
    List<Integer> resultSemiMagicList = Arrays.asList(2, 4, 9, 7, 3, 5, 6, 8, 1);
    SquareTask squareTask = new SquareTask();

    @Test
    public void checkIfMagic() {
        Assert.assertEquals(squareTask.getBestOfMagicSquare(squareTask.toSquare(exampleList)), resultMagicList);
    }

    @Test
    public void checkCostOfMagic() {
        Assert.assertEquals(squareTask.countCost(exampleList, resultMagicList), 24);
    }

    @Test
    public void checkIfSemiMagic() {
        Assert.assertEquals(squareTask.getBestOfSemiMagicSquare(squareTask.toSquare(exampleList)), resultSemiMagicList);
    }

    @Test
    public void checkCostOfSemiMagic() {
        Assert.assertEquals(squareTask.countCost(exampleList, resultSemiMagicList), 24);
    }
}
