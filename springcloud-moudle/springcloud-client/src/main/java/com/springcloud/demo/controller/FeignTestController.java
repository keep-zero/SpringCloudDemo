package com.springcloud.demo.controller;

import cn.hutool.json.JSONUtil;
import com.springcloud.demo.entity.Client;
import com.springcloud.demo.service.ClientService;
import com.springcloud.demo.util.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @auther: lai.guanfu
 * @date: 2019-06-14 10:11
 */
@AllArgsConstructor
@RestController
@RequestMapping("/test/feign")
public class FeignTestController {

    private ClientService clientService;

    @GetMapping()
    public R getUser(){
        Map result = new HashMap();
        result.put("name", "xiaoming");
        result.put("sex", "male");
        result.put("age", 12);
        return R.suc(result);
    }

    /**
     * 新增client,用于测试分布式事务
     * @param client client对象
     * @return
     */
    @ApiOperation(value = "添加客户端", notes = "添加客户端", response = R.class, responseContainer = "Item", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client", value = "客户端实体", required = true, dataType = "Client", paramType = "body"),
    })
    @PostMapping
    public R addClient(@RequestBody Client client){
        this.clientService.addClient(client);
        return R.suc(Boolean.TRUE);
    }
}
