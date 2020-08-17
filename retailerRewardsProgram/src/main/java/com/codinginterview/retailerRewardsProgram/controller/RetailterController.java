package com.codinginterview.retailerRewardsProgram.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class RetailerController {

    //Map has customer id's and amount they spent
    private static Map<Integer, Integer> map;

    // Instantiating the static map
    static {
        map = new HashMap<>();
        map.put(1, 500);
        map.put(2, 99);
        map.put(3, 300);
    }

    @RequestMapping("/getRewards/{custId}")
    public String getRewardPoints(@PathVariable int custId) {
        String outputValue;
        if (map.containsKey(custId)) {
            int custAmount = map.get(custId);
            outputValue = String.valueOf(getRewards(custAmount));
            return outputValue + " Reward Points available as you spent " + map.get(custId) + " dollars.";
        } else {
            return "Customer Number " + custId + " not found";
        }
    }

    // Calculates the reward points and returns back the value
    public int getRewards(int amount) {
        if (amount > 100) {
            int twoPointAmount = amount - 100;
            int twoPointRewards = twoPointAmount * 2;
            int onePointRewards = amount - 50;
            return twoPointRewards + onePointRewards;
        } else if (amount > 50) {
            return amount - 50;
        } else {
            return 0;
        }
    }
}
