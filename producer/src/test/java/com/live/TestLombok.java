package com.live;

import com.live.kafka.producer.DTO.CarDTO;

public class TestLombok {

    public static void main(String[] args) {
        CarDTO car = CarDTO.builder()
                .id("1")
                .model("HB20")
                .color("Preto")
                .build();
        System.out.println(car.getModel());
    }
}
