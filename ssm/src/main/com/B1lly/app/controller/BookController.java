package com.B1lly.app.controller;

import com.B1lly.app.bean.Book;
import com.B1lly.app.service.impl.BookServiceImpl;
import com.B1lly.app.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Api(tags = "图书价格API")
@RestController
@RequestMapping("/book")
@CrossOrigin()
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;
    @ApiOperation("获取全部图书的价格")
    @RequestMapping("/PriceList")
    public CommonResult listBookPrice(){
        return CommonResult.success(bookServiceImpl.listBookPrice());
    }

    @ApiOperation("获取全部图书的信息")
    @RequestMapping("/BookList")
    public CommonResult listPage(int page,int size,String bname){
        Map<String,Object> map = new HashMap<>();
        map.put("data",bookServiceImpl.listPage(page,size,bname));
        map.put("total",bookServiceImpl.count(bname));
        return CommonResult.success(map);
    }

    @ApiOperation("根据id删除的图书信息")
    @RequestMapping("/deleteBook")
    public CommonResult deleteBook(int bid){
        return CommonResult.success(bookServiceImpl.deleteBook(bid));
    }

    @ApiOperation("添加图书的信息")
    @PostMapping ("/addBook")
    public CommonResult addBook(@RequestBody Book book){
        return CommonResult.success(bookServiceImpl.addBook(book));
    }

    @ApiOperation("编辑图书的信息")
    @PutMapping ("/editBook")
    public CommonResult editBook(@RequestBody Book book){
        return CommonResult.success(bookServiceImpl.editBook(book));
    }
}
