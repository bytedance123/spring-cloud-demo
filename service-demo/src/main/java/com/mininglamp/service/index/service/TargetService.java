package com.mininglamp.service.index.service;

import com.tusmart.service.base.bean.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service-target", fallback=TargetService.TargetServiceImpl.class)
public interface TargetService {
    @RequestMapping(value="/xxx/yyy" , method={RequestMethod.POST})
    ResponseBean someMethod(@RequestParam(value="id") String id);

    static class TargetServiceImpl implements TargetService{

        @Override
        public ResponseBean someMethod(String id) {
            return ResponseBean.fail();
        }
    }
}