package com.goddess.server.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import com.goddess.server.dao.AccountMapper;
import com.goddess.server.dao.OrderMapper;
import com.goddess.server.entity.Account;
import com.goddess.server.entity.Order;
import com.goddess.server.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private IdGenerator idGenerator;

    @PostMapping("/order")
    @Transactional
    public Dict order(@RequestBody Order order) {
        String orderCode = IdUtil.simpleUUID().substring(0,10);
        order.setCode(orderCode);
        order.setId(idGenerator.snowflakeId());
        order.setOrderAmt(order.getOrderPrice().multiply(new BigDecimal(order.getOrderQty())).setScale(2, RoundingMode.HALF_UP));
        Integer save = orderMapper.insert(order);
        Account account = accountMapper.selectById(order.getAcctId());
        account.setUsableAmt(account.getUsableAmt().subtract(order.getOrderAmt()));
        accountMapper.updateById(account);
        return Dict.create().set("code",200).set("msg","成功").set("data",order);
    }
    @PostMapping(value = "/orderForm",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    public Dict orderForm(@RequestBody MultiValueMap<String, String> formData) {
        return Dict.create().set("code",200).set("msg","成功").set("data",null);
    }

    @GetMapping(value = "/getOrders", produces = {"application/json"})
    public List<Order> getOrders(@RequestParam Map<String, Object> params) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1l);
        order.setCode("asdasd");
        order.setOrderPrice(BigDecimal.ZERO);
        order.setOrderAmt(BigDecimal.ZERO);
        order.setOrderQty(12);
        orders.add(order);
        return orders;
    }
    @GetMapping(value = "/getOrder/{id}", produces = {"application/json"})
    public Order getOrder(@PathVariable("id") Long id,@RequestParam Map<String, Object> params) {
        Order order = new Order();
        order.setId(id);
        order.setCode("asdasd");
        return order;
    }
    @PostMapping(value = "/getOrdersXml", consumes = MediaType.APPLICATION_XML_VALUE)
    public List<Order> getOrdersXml(@RequestBody String xmlData) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1l);
        order.setCode("asdasd");
        orders.add(order);
        return orders;
    }
    @PostMapping(value = "/getOrdersText",consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<Order> getOrdersText(@RequestBody String requestBody) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1l);
        order.setCode("asdasd");
        orders.add(order);
        return orders;
    }
    @PostMapping(value = "/returnXml", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String handlePostRequest(@RequestBody String xmlData) throws JAXBException {
        // 处理请求数据


        return xmlData;
    }
    @XmlRootElement
    class RequestData {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @XmlRootElement
    class ResponseData {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
