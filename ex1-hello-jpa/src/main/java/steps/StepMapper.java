package steps;

import java.util.HashMap;
import java.util.Map;

public class StepMapper {
    private static Map<String, Step> steps = new HashMap<String, Step>();

    static {
        steps.put("step00" , new Step00());
        steps.put("step01" , new Step01());
        steps.put("step02" , new Step02());
        steps.put("step03" , new Step03());
        steps.put("step04" , new Step04());
        steps.put("step05" , new Step05());
        steps.put("step06" , new Step06());
        steps.put("step07" , new Step07());
        steps.put("step08" , new Step08());
        steps.put("step09" , new Step09());
        steps.put("step10" , new Step10());
        steps.put("step11" , new Step11());

    }

    public static Step getStep(String target){
        return steps.get(target);
    }
}
