package com.mitrais.unittesting.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIT { // Integration Test

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws JSONException {
        String response = restTemplate.getForObject("/all-items-from-database", String.class);
        /*
          insert into item values(11, 'Item 1', 1000, 15);
          insert into item values(12, 'Item 2', 1300, 11);
          insert into item values(13, 'Item 3', 1100, 12);
          insert into item values(14, 'Item 4', 1400, 14);
          insert into item values(15, 'Item 5', 1500, 17);
         */
        JSONAssert.assertEquals("[{id:11},{id:15},{id:13},{id:14},{id:12}]", response, false);

    }
}
