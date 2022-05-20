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
        int cc = squareModel.getA5();
        return countCost(cc, 5, check, per1, per2);
    }

    public int costOfSemiMagicSquare(SquareModel squareModel) {
        List<Integer> per11 = Arrays.asList(7, 6, 2, 9, 4, 8, 3, 5);
        List<Integer> per12 = Arrays.asList(2, 6, 7, 5, 3, 8, 4, 9);

        List<Integer> per31 = Arrays.asList(9, 5, 1, 8, 6, 7, 2, 4);
        List<Integer> per32 = Arrays.asList(1, 5, 9, 4, 2, 7, 6, 8);


        List<Integer> per71 = Arrays.asList(1, 5, 9, 2, 4, 3, 8, 6);
        List<Integer> per72 = Arrays.asList(9, 5, 1, 6, 8, 3, 4, 2);

        List<Integer> per91 = Arrays.asList(7, 2, 6, 1, 8, 4, 3, 5);
        List<Integer> per92 = Arrays.asList(6, 2, 7, 5, 3, 4, 8, 1);
        List<List<Integer>> list = Arrays.asList(per31, per32, per71, per72, per91, per92);
        List<Integer> listcp = Arrays.asList(3, 7, 9);
        List<Integer> check = new ArrayList<>();
        check.add(squareModel.getA1());
        check.add(squareModel.getA2());
        check.add(squareModel.getA3());
        check.add(squareModel.getA6());
        check.add(squareModel.getA9());
        check.add(squareModel.getA8());
        check.add(squareModel.getA7());
        check.add(squareModel.getA4());
        int cc = squareModel.getA5();
        int min = countCost(cc, 1, check, per11, per12);
        int temp;
        for (int i = 0; i < 3; i++) {
            temp = countCost(cc, listcp.get(i), check, list.get(i*2), list.get(i*2 + 1));
            if (temp < min) {
                System.out.println(i);
                min = temp;
            }
        }
        return min;
    }

    public int countCost(int cc, int cp, List<Integer> check, List<Integer> per1, List<Integer> per2) {
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
        min += Math.abs(cc - cp);
        return min;
    }
}