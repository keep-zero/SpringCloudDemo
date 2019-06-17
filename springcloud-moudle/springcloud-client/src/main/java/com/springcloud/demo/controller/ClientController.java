package com.springcloud.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.demo.entity.Client;
import com.springcloud.demo.helper.RedisHelper;
import com.springcloud.demo.service.ClientService;
import com.springcloud.demo.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  client的前端控制器
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-05-24
 */
@Api(value = "ClientController",description = "客户端模块前端控制器",tags = {"client"})
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    //引入redis操作工具
    @Autowired
    private RedisHelper redisHelper;

    /**
     * 新增client
     * @param client client对象
     * @return
     */
    @ApiOperation(value = "添加客户端", notes = "添加客户端", response = R.class, responseContainer = "Item", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client", value = "客户端实体", required = true, dataType = "Client", paramType = "body"),
    })
    @PostMapping
    public R addClient(@RequestBody Client client){
        this.clientService.save(client);
        //填充缓存，key为client_${id}
        this.redisHelper.set("client_"+client.getId(), JSONUtil.toJsonStr(client));
        return R.suc(Boolean.TRUE);
    }

    /**
     * 根据id获取client
     * @param id client的id
     * @return
     */
    @ApiOperation(value = "获取客户端信息", notes = "获取客户端信息", response = Client.class, responseContainer = "Item", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户端id", required = true, dataType = "Long", paramType = "path")
    })
    @GetMapping("/{id}")
    public R getClientById(@PathVariable Integer id){
        //首先从缓存获取
        Client client = JSONUtil.toBean(JSONUtil.toJsonStr(this.redisHelper.get("client_"+id)), Client.class);
        if(ObjectUtil.isNotNull(client)){
            return R.suc(client);
        }
        return R.suc(this.clientService.getById(id));
    }

    /**
     * 根据id删除client
     * @param id client的id
     * @return
     */
    @ApiOperation(value = "删除客户端信息", notes = "删除客户端信息", response = Client.class, responseContainer = "Item", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户端id", required = true, dataType = "Long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public R removeClient(@PathVariable Integer id){
        this.clientService.removeById(id);
        //从缓存移除
        this.redisHelper.del("client_"+id);
        return R.suc(Boolean.TRUE);
    }

    @ApiOperation(value = "获取客户端分页列表", notes = "获取客户端分页列表", response = Client.class, responseContainer = "Item", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页码", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "当前页最大显示条数", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "client", value = "客户端对象", required = true, dataType = "Client", paramType = "query")
    })
    @GetMapping("/page")
    public R getClientPage(Page page, Client client){
//        return R.suc(this.clientService.page(page, new QueryWrapper<>(client)));
        return R.suc(this.clientService.getClientPage(page,client));
    }

}

