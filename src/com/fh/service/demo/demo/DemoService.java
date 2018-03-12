package com.fh.service.demo.demo;

import com.fh.entity.demo.Demo;
import com.fh.framewrok.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/12/21.
 */
@Service
public class DemoService extends BaseService{

    public void insertDemo() throws Exception {
        Demo d=new Demo();
        d.setId("1111111111112");
        d.setName("jinshan");
        this.save(d);
    }
}
