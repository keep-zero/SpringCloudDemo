package com.springcloud.demo.controller;


import com.springcloud.demo.constant.DatabaseCons;
import com.springcloud.demo.service.SysDataDictionaryService;
import com.springcloud.demo.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  数据字典controller
 * </p>
 *
 * @author lai.guanfu
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/sys-data-dictionary")
public class SysDataDictionaryController {

    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    @GetMapping("scan")
    public R scanDataTable(){
        return R.suc(this.sysDataDictionaryService.scanDataTable(DatabaseCons.DEFAULT_DATABASE));
    }


}

