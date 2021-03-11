package com.example.elssticsearch.controller;

import com.example.elssticsearch.dao.ArticalRepository;
import com.example.elssticsearch.entity.Artical;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

/**
 * @author: GuanBin
 * @date: Created in 下午3:44 2021/3/8
 */
@Slf4j
@RestController
@RequestMapping("artical")
public class ArticalController {

    @Autowired
    private ArticalRepository articalRepository;

    @RequestMapping(path = "/add",method = RequestMethod.GET)
    public String add() {
        Artical artical = new Artical();
        artical.setId(UUID.randomUUID().toString());
        artical.setName("guanbinbin");
        artical.setTitle("test");
        articalRepository.save(artical);
        return "success";
    }

    @RequestMapping(path = "/findById", method = RequestMethod.GET)
    public Artical query(@RequestParam(value = "id") String id){
        Optional<Artical> articalOptional = articalRepository.findById(id);
        if(articalOptional.isPresent()){
            log.info("查询成功");
            return articalOptional.get();
        }
        return null;
    }

    @RequestMapping(path = "/update", method = RequestMethod.GET)
    public Artical update(@RequestParam(value = "id") String id){
        Optional<Artical> articalOptional = articalRepository.findById(id);
        Artical artical=null;
        if(articalOptional.isPresent()){
            artical = articalOptional.get();
            artical.setName("tomcat");
            articalRepository.save(artical);
            log.info("更新成功");
        }
        return artical;
    }


    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") String id){
        Optional<Artical> articalOptional = articalRepository.findById(id);
        articalRepository.delete(articalOptional.get());
        return "success";
    }
}
