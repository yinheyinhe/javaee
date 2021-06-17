package com.springboot.jpa_demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Book;
import com.springboot.jpa_demo.datasource1.domain.Consumer;
import com.springboot.jpa_demo.datasource1.domain.Product;
import com.springboot.jpa_demo.datasource1.service.BookService;
import com.springboot.jpa_demo.datasource1.service.ConsumerService;
import com.springboot.jpa_demo.datasource1.service.ProductService;
import com.springboot.jpa_demo.utils.GetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    BookService bookService;

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/")
    public ModelAndView init(){
        ModelAndView model=new ModelAndView("login");
        return model;
    }

    @GetMapping("api/v1/user/register")
    public ModelAndView register(){
        ModelAndView modelAndView=new ModelAndView("register");
        return modelAndView;
    }

    @PostMapping("api/v1/user/login")
    public ModelAndView login(int id,String password){
        ModelAndView modelAndView;
        JSONObject jsonObject= productService.login(id,password);
        if(jsonObject.get("code").equals(200)){

            String token= GetToken.getToken(id,password);
            System.out.println("token is "+token);
            modelAndView=new ModelAndView("mainpage");
            modelAndView.addObject("token",token);
        }else{
            modelAndView=new ModelAndView("login");
            modelAndView.addObject("message",jsonObject.get("message"));
            modelAndView.addObject("id",id);
        }
        return modelAndView;
    }

    @PostMapping("api/v1/user/add")
    public ModelAndView addUser(Product product){
        JSONObject res= productService.addUser(product);
        ModelAndView modelAndView;
        if(res.get("code").equals(200)){
            modelAndView=new ModelAndView("login");
            modelAndView.addObject("id",res.getJSONObject("data").get("id"));
        }else{
            modelAndView=new ModelAndView("register");
        }
        return modelAndView;
    }

//    @UserLoginToken
    @GetMapping("/mainpage")
    public ModelAndView mainpage(){
        ModelAndView model=new ModelAndView("mainpage");
        List<Book> books = bookService.getallGym();
        model.addObject("gyms", books);
        List<Consumer> consumers = consumerService.getallTrainer();
        model.addObject("trainers", consumers);
        return model;
    }

}
