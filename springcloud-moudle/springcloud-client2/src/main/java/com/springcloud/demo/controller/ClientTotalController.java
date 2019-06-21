package com.springcloud.demo.controller;


import com.springcloud.demo.service.ClientTotalService;
import com.springcloud.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLDataException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-06-19
 */
@AllArgsConstructor
@RestController
@RequestMapping("/clientTotal")
public class ClientTotalController {

    private ClientTotalService clientTotalService;

    /**
     * 新增客户端，更改路径下客户端的总数
     * @param clientPath 客户端路径
     * @return
     */
    @PostMapping
    public R addClient(String clientPath) throws SQLDataException, InterruptedException {
        this.clientTotalService.saveClient(clientPath);
        return R.suc();
    }



}

