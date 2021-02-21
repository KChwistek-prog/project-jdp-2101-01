package com.kodilla.ecommercee.group.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void GroupDaoCreate() {

        //Given
        List<Product> list = new LinkedList<Product>();
        Group group = new Group("FirstGroup","FirstDescription",list);

        //When
        groupRepository.save(group);
        long id = group.getId();

        //Then
        assertTrue(groupRepository.findById(id).isPresent());


        //CleanUp
        groupRepository.deleteById(id);

    }

    @Test
    public void GroupDaoRead() {

        //Given
        List<Product> list = new LinkedList<Product>();
        Group group = new Group("FirstGroup","FirstDescription",list);

        //When
        groupRepository.save(group);
        long id = group.getId();
        String name = group.getName();
        Group groupRead = groupRepository.findByName(name);


        //Then
        assertEquals("FirstGroup",groupRead.getName());


        //CleanUp
        groupRepository.deleteById(id);

    }

    @Test
    public void GroupDaoUpdate() {

        //Given
        List<Product> listOfProducts = new LinkedList<Product>();
        Group group = new Group("FirstGroup","FirstDescription",listOfProducts);


        //When
        groupRepository.save(group);
        long id = group.getId();
        groupRepository.findByName("FirstGroup").setDescription("Second Description");
        Group group1 = groupRepository.findByName("FirstGroup");


        //Then
        assertEquals("Second Description",group1.getDescription());


        //CleanUp
        groupRepository.deleteById(id);

    }

    @Test
    public void GroupDaoDelete() {

        //Given
        Set<Cart> setOfCarts = new HashSet<>();
        List<Product> listOfProducts = new LinkedList<Product>();
        Group group1 = new Group("FirstGroup","FirstDescription",listOfProducts);
        Group group2 = new Group("SecondGroup","SecondDescription",listOfProducts);
        Product product = new Product("FirstProduct","FirstDescription",10.0,"FirstUnit",group2,setOfCarts);
        listOfProducts.add(product);

        //When
        productRepository.save(product);
        groupRepository.save(group1);
        long id1 = group1.getId();
        groupRepository.save(group2);
        long id2 = group2.getId();
        long id3 = product.getProductId();

        //Then
        assertTrue(groupRepository.findById(id2).isPresent());
        groupRepository.deleteById(id2);
        assertFalse(groupRepository.findById(id2).isPresent());
        assertTrue(productRepository.findById(id3).isPresent());


        //CleanUp
        groupRepository.deleteById(id1);

    }


}