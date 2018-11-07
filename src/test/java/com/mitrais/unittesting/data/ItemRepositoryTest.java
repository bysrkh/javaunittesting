package com.mitrais.unittesting.data;

import com.mitrais.unittesting.model.Item;
import com.mitrais.unittesting.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindAll(){
        List<Item> items = itemRepository.findAll();
        assertEquals(5, items.size()); // 3 at main app.prop, 2 at test app.prop
    }
}
