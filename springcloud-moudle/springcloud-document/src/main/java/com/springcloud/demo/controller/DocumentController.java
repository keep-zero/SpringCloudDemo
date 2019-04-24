package com.springcloud.demo.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.demo.enity.Document;
import com.springcloud.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: document的前端控制器
 * @auther: lai.guanfu
 * @date: 2019-04-07 17:10
 */
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    /**
     * @description: 根据id获取文档信息
     * @author: lai.guanfu
     * @date: 2019-04-07
     * @param id:
     * @return:
     */
    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable Integer id){
        Document document = this.documentService.getById(id);
        return JSONUtil.toJsonStr(document);
    }

    /**
     * @description: 新增文档
     * @author: lai.guanfu
     * @date: 2019-04-08
     * @param data:
     * @return:
     */
    @PostMapping("/add")
    public String add(String data){
        Document document = JSONUtil.toBean(data, Document.class);

        return JSONUtil.toJsonStr(Boolean.valueOf(this.documentService.save(document)));
    }

    /**
     * @description: 删除文档
     * @author: lai.guanfu
     * @date: 2019-04-08
     * @param id:
     * @return:
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return JSONUtil.toJsonStr(Boolean.valueOf(this.documentService.removeById(id)));
    }

    /**
     * @description: 更新文档
     * @author: lai.gaunfu
     * @date: 2019-04-08
     * @param data:
     * @return:
     */
    @PutMapping("/update")
    public String update(String data){
        return JSONUtil.toJsonStr(Boolean.valueOf(this.documentService.updateById(JSONUtil.toBean(data, Document.class))));
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        return JSONUtil.toJsonStr(Boolean.valueOf(this.documentService.remove(new QueryWrapper<>())));
    }

}
