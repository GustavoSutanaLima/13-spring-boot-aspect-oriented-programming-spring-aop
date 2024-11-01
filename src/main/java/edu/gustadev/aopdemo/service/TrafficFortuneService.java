package edu.gustadev.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public interface TrafficFortuneService {

    String getFortune(boolean tripWire);

    String getFortune();
}
