package com.pet.sas.stringsandsquare.calculation;

import com.pet.sas.stringsandsquare.model.SquareModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SquareTask {
    public int costOfMagicSquare(SquareModel squareModel) {
        List<Integer> per1 = Arrays.asList(6, 7, 2, 9, 4, 3, 8, 1);
        List<Integer> per2 = Arrays.asList(2, 7, 6, 1, 8, 3, 4, 9);
        List<Integer> check = new ArrayList<>();
        check.add(squareModel.getA1());
        check.add(squareModel.getA2());
        check.add(squareModel.getA3());
        check.add(squareModel.getA6());
        check.add(squareModel.getA9());
        check.add(squareModel.getA8());
        check.add(squareModel.getA7());
        check.add(squareModel.getA4());
        int min = 0;
        int mintemp = 0;
        for (int i = 0; i < 8; i++) {
            min += Math.abs(per1.get(i) - check.get(i));
        }
        for (int i = 0; i < 8; i++) {
            mintemp += Math.abs(per2.get(i) - check.get(i));
        }
        if (mintemp < min) {
            min = mintemp;
        }
        int cur;
        int cost = 0;
        for (int i = 0; i < 3; i++) {
            cost = 0;
            cur = check.get(0);
            check.remove(0);
            check.add(cur);
            cur = check.get(0);
            check.remove(0);
            check.add(cur);
            for (int j = 0; j < 8; j++) {
                cost += Math.abs(per1.get(j) - check.get(j));
            }
            if (cost < min) {
                min = cost;
            }
            cost = 0;
            for (int j = 0; j < 8; j++) {
                cost += Math.abs(per2.get(j) - check.get(j));
            }
            if (cost < min) {
                min = cost;
            }
        }
        min += Math.abs(squareModel.getA5() - 5);
        return min;
    }
}
