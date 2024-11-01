package edu.gustadev.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class TrafficFortubeServiceImpl implements TrafficFortuneService {

    @Override
    public String getFortune() {

        //simulate delay;
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "Today is your lucky day! There's no traffic, you're free to ride your bike!";
    }

    @Override
    public String getFortune(boolean tripWire) {
        
        if(tripWire) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }
        return getFortune();
    }

}
