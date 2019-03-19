package com.mininglamp.service.index.controller;

import java.util.HashMap;
import java.util.Map;

import com.mininglamp.service.index.service.TargetService;
import com.tusmart.service.base.BaseController;
import com.tusmart.service.base.bean.RequestBean;
import com.tusmart.service.base.bean.ResponseBean;
import com.mininglamp.service.index.service.IndexService;

import com.tusmart.service.utils.FeignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api(description="控制器描述信息", value="index")
@RestController
public class IndexController extends BaseController{
    @Value("${foo:lll}")
    private String foo;


    @Value("${aa:aa value}")
    private String aa;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private IndexService service;

    @Autowired
    private TargetService targetService;

    @ApiOperation(value="接口标题", notes="接口描述信息")
    @RequestMapping(value="/", method={RequestMethod.GET})
    String index(HttpServletRequest request){
        System.out.println("当前Sessionid为：" + FeignUtil.getServiceSessionId(request));
        return "hello service and foo value is:" + foo  + "\r\naaa is :" + aa;
    }

    /**
     * 获取服务列表和明细
     * @param name
     * @return
     */
    @GetMapping("/instances")
    @ApiOperation(value = "获取服务列表或实例", notes = "serviceId{String}: 服务id, 若为空则获取所有服务列表，返回服务id集合；若不为空返回该服务详情")
    ResponseBean instances(@RequestParam(required = false)  String serviceId){
        if (serviceId == null){
            return ResponseBean.ok().setData(discoveryClient.getServices());
        }else{
            return ResponseBean.ok().setData(discoveryClient.getInstances(serviceId));
        }
    }

    @ApiOperation(value="基于BaseMapper执行数据查找", notes="能力来自mybatis-plus")
    @ResponseBody
    @RequestMapping(value="/list", method={RequestMethod.GET})
    ResponseBean list(){
        return ResponseBean.ok().setData(service.findList());
    }

    @ApiOperation(value="基于SQL查询数据库", notes="id取值为 1或2 有结果")
    @ResponseBody
    @RequestMapping(value="/filterList", method={RequestMethod.GET, RequestMethod.POST})
    ResponseBean filterList(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return ResponseBean.ok().setData(service.findListBySql(map));
    }

    @ApiOperation(value="其他服务", notes="基于Feign调用其它服务")
    @ResponseBody
    @RequestMapping(value="otherService", method={RequestMethod.POST})
    ResponseBean otherService(@RequestBody RequestBean requestBean){
        return targetService.someMethod((String) requestBean.getParams().get("id"));
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE  )
    ResponseBean upload(@RequestParam(required = false) MultipartFile icon, @RequestParam("id") String id){
        return ResponseBean.ok().setData(id + ": " + (icon == null ? "None Icon" : icon.getName()));
    }
}