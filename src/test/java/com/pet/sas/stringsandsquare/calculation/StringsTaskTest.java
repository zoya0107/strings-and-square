package com.pet.sas.stringsandsquare.calculation;

import com.pet.sas.stringsandsquare.model.StringsModel;
import org.junit.Assert;
import org.junit.Test;

public class StringsTaskTest {
    String s1 = "a b c c";
    String s2 = "acc aa acd";
    String result = "a c ";
    StringsTask stringsTask = new StringsTask();

    @Test
    public void checkFilter() {
        StringsModel sm = new StringsModel();
        sm.setS1(s1);
        sm.setS2(s2);
        Assert.assertEquals(stringsTask.getSubstrings(sm), result);
    }
}