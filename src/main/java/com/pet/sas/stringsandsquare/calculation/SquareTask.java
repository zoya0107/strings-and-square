package com.pet.sas.stringsandsquare.calculation;

import com.pet.sas.stringsandsquare.model.SquareModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SquareTask {
    public List<Integer> getBestOfMagicSquare(SquareModel squareModel) {
        List<Integer> per1 = new ArrayList(Arrays.asList(6, 7, 2, 9, 4, 3, 8, 1));
        List<Integer> per2 = new ArrayList(Arrays.asList(2, 7, 6, 1, 8, 3, 4, 9));
        List<Integer> check = toCover(squareModel);
        int cc = squareModel.getA5();
        return insertCent(getBestCover(check, per1, per2), 5);
    }

    public List<Integer> getBestOfSemiMagicSquare(SquareModel squareModel) {
        List<Integer> ans;
        List<Integer> per11 = new ArrayList(Arrays.asList(7, 6, 2, 9, 4, 8, 3, 5));
        List<Integer> per12 = new ArrayList(Arrays.asList(2, 6, 7, 5, 3, 8, 4, 9));
        List<Integer> per31 = new ArrayList(Arrays.asList(9, 5, 1, 8, 6, 7, 2, 4));
        List<Integer> per32 = new ArrayList(Arrays.asList(1, 5, 9, 4, 2, 7, 6, 8));
        List<Integer> per71 = new ArrayList(Arrays.asList(1, 5, 9, 2, 4, 3, 8, 6));
        List<Integer> per72 = new ArrayList(Arrays.asList(9, 5, 1, 6, 8, 3, 4, 2));
        List<Integer> per91 = new ArrayList(Arrays.asList(7, 2, 6, 1, 8, 4, 3, 5));
        List<Integer> per92 = new ArrayList(Arrays.asList(6, 2, 7, 5, 3, 4, 8, 1));
        List<List<Integer>> list = Arrays.asList(per31, per32, per71, per72, per91, per92);
        List<Integer> listcp = Arrays.asList(3, 7, 9);
        List<Integer> check = toCover(squareModel);
        List<Integer> tempBestCov;
        int cc = squareModel.getA5();
        ans = getBestCover(check, per11, per12);
        int min = count(cc, 1, check, ans);
        int temp;
        int bcp = 1;
        for (int i = 0; i < 3; i++) {
            tempBestCov = getBestCover(check, list.get(i * 2), list.get(i * 2 + 1));
            temp = count(cc, listcp.get(i), check, tempBestCov);
            if (temp < min) {
                min = temp;
                ans = tempBestCov;
                bcp = listcp.get(i);
            }
        }
        return insertCent(ans, bcp);
    }

    public List<Integer> getBestCover(List<Integer> check, List<Integer> per1, List<Integer> per2) {
        List<Integer> ans = new ArrayList<>();
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
            ans.addAll(per2);
        } else {
            ans.addAll(per1);
        }
        int cost;
        for (int i = 0; i < 3; i++) {
            cost = 0;
            check = shiftLeft(check, 2);
            for (int j = 0; j < 8; j++) {
                cost += Math.abs(per1.get(j) - check.get(j));
            }
            if (cost < min) {
                min = cost;
                ans = shiftRight(per1, i * 2 + 2);
            }
            cost = 0;
            for (int j = 0; j < 8; j++) {
                cost += Math.abs(per2.get(j) - check.get(j));
            }
            if (cost < min) {
                min = cost;
                ans = shiftRight(per2, i * 2 + 2);
            }
        }
        check = shiftRight(check, 6);
        return ans;
    }

    public int count(int cc, int cp, List<Integer> check, List<Integer> per) {
        int ans = Math.abs(cc - cp);
        for (int i = 0; i < 8; i++) {
            ans += Math.abs(check.get(i) - per.get(i));
        }
        return ans;
    }

    public List<Integer> shiftRight(List<Integer> list, int n) {
        Collections.reverse(list);
        shiftLeft(list, n);
        Collections.reverse(list);
        return list;
    }

    public List<Integer> shiftLeft(List<Integer> list, int n) {
        while (n > 0) {
            int cur = list.get(0);
            list.remove(0);
            list.add(cur);
            n--;
        }
        return list;
    }

    public List<Integer> insertCent(List<Integer> list, int c) {
        List<Integer> ans = new ArrayList<>();
        ans.add(list.get(0));
        ans.add(list.get(1));
        ans.add(list.get(2));
        ans.add(list.get(7));
        ans.add(c);
        ans.add(list.get(3));
        ans.add(list.get(6));
        ans.add(list.get(5));
        ans.add(list.get(4));
        return ans;
    }

    public int countCost(List<Integer> check, List<Integer> per) {
        int ans = 0;
        for (int i = 0; i < 9; i++) {
            ans += Math.abs(check.get(i) - per.get(i));
        }
        return ans;
    }

    public List<Integer> toList(SquareModel squareModel) {
        List<Integer> list = new ArrayList<>();
        list.add(squareModel.getA1());
        list.add(squareModel.getA2());
        list.add(squareModel.getA3());
        list.add(squareModel.getA4());
        list.add(squareModel.getA5());
        list.add(squareModel.getA6());
        list.add(squareModel.getA7());
        list.add(squareModel.getA8());
        list.add(squareModel.getA9());
        return list;
    }

    public SquareModel toSquare(List<Integer> list) {
        SquareModel sm = new SquareModel();
        sm.setA1(list.get(0));
        sm.setA2(list.get(1));
        sm.setA3(list.get(2));
        sm.setA4(list.get(3));
        sm.setA5(list.get(4));
        sm.setA6(list.get(5));
        sm.setA7(list.get(6));
        sm.setA8(list.get(7));
        sm.setA9(list.get(8));
        return sm;
    }

    public List<Integer> toCover(SquareModel squareModel) {
        List<Integer> list = new ArrayList<>();
        list.add(squareModel.getA1());
        list.add(squareModel.getA2());
        list.add(squareModel.getA3());
        list.add(squareModel.getA6());
        list.add(squareModel.getA9());
        list.add(squareModel.getA8());
        list.add(squareModel.getA7());
        list.add(squareModel.getA4());
        return list;
    }
}