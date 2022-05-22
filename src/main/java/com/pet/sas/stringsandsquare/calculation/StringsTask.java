package com.pet.sas.stringsandsquare.calculation;

import com.pet.sas.stringsandsquare.model.StringsModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StringsTask {
    public String getSubstrings(StringsModel stringsModel) {
        String s1 = stringsModel.getS1();
        String s2 = stringsModel.getS2();
        String delimiter = " ";
        String[] a1 = s1.split(delimiter);
        String[] a2 = s2.split(delimiter);
        List<String> ans = new ArrayList<>();
        for (String p : a1) {
            for (String s : a2) {
                if (s.contains(p)) {
                    ans.add(p);
                    break;
                }
            }
        }
        ans = ans.stream().distinct().collect(Collectors.toList());
        Collections.sort(ans);
        String res = "";
        for (String s : ans) {
            res = res.concat(s);
            res = res.concat(" ");
        }
        return res;
    }
}
