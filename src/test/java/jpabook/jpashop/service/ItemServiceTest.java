package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class ItemServiceTest {
    @Autowired ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;
    @Test
    public void 테스트_책_생성() throws Exception{
        //given
        Item item = new Book();
        item.setName("편의점인간");
        item.setPrice(19000);
        item.setStockQuantity(5);
        Book book = new Book();
        book.setAuthor("무라타 사야카");
        book.setIsbn("800_001");
        //when
        itemRepository.save(item);

        //then
        em.flush();
    }
    @Test
    public void 테스트_책_생성2() throws Exception{
        //given
        Item item = new Book();
        item.setName("미드나잇 라이브러리");
        item.setPrice(17000);
        item.setStockQuantity(5);
        Book book = new Book();
        book.setAuthor("무라타 사야카");
        book.setIsbn("800_001");
        //when
        itemRepository.save(item);

        //then
        em.flush();
    }

    @Test
    public void 테스트_책_생성3() throws Exception{
        //given
        Item item = new Book();
        item.setName("돌이킬수 없는 약속");
        item.setPrice(21000);
        item.setStockQuantity(5);
        Book book = new Book();
        book.setAuthor("무라타 사야카");
        book.setIsbn("800_001");
        //when
        itemRepository.save(item);

        //then
        em.flush();
    }
    @Test
    public void 테스트_책_생성4() throws Exception{
        //given
        Item item = new Book();
        item.setName("지키려는 고양이");
        item.setPrice(15000);
        item.setStockQuantity(5);
        Book book = new Book();
        book.setAuthor("무라타 사야카");
        book.setIsbn("800_001");
        //when
        itemRepository.save(item);

        //then
        em.flush();
    }

    @Test
    public void 테스트_책_생성5() throws Exception{
        //given
        Item item = new Book();
        item.setName("불편한편의점");
        item.setPrice(25000);
        item.setStockQuantity(5);
        Book book = new Book();
        book.setAuthor("무라타 사야카");
        book.setIsbn("800_001");
        //when
        itemRepository.save(item);

        //then
        em.flush();
    }




}