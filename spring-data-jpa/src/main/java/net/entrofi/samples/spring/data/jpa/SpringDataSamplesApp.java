/*
 * Copyright 2017 Boynergrup A.,S.. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Boynergrup A.,S..
 * Any reproduction of this material must contain this notice.
 */
package net.entrofi.samples.spring.data.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringDataSamplesApp implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(SpringDataSamplesApp.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

    }
}
