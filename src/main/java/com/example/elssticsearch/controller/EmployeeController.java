package com.example.elssticsearch.controller;

import com.example.elssticsearch.dao.EmployeeRepository;
import com.example.elssticsearch.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

/**
 * @author: GuanBin
 * @date: Created in 下午3:44 2021/3/8
 */
@Slf4j
@RestController
@RequestMapping("es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String add() {
        save1();
//        save2();
//        save3();
//        save4();
//        save5();
        return "success";
    }

    private void save1() {
        Employee employee = new Employee();
        employee.setFirstName("好好好好好\n+"+
                "A module's developer uses each part of a module's version number to signal the version’s stability and backward compatibility. For each new release, a module's release version number specifically reflects the nature of the module's changes since the preceding release.");
        employee.setLastName("li");
        employee.setAge(20);
        employee.setAbout("i am EmployeeController");
        log.info("新增成功");
        employeeRepository.save(employee);
    }

    private void save2() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("#asdfasdf 中郭阿斯顿佛啊是的呢佛啊是的佛啊水电费请问 asdfasdf\n" +
                "asdfasdfa\n" +
                "asdfasdf\n");
        employee.setLastName("li");
        employee.setAge(21);
        employee.setAbout("i am hahahasdfr");
        log.info("新增成功");
        employeeRepository.save(employee);
    }


    private void save3() {
        Employee employee = new Employee();
        employee.setId("2");
        employee.setFirstName("#llllasdfasdf uiasdfooooooo ppppppp asdfasdf\n" +
                "中啊u哦发水电费\n" +
                "扣扣爬山的番茄味热污染\n");
        employee.setLastName("li");
        employee.setAge(290);
        employee.setAbout("i am hahahasdfr");
        log.info("新增成功");
        employeeRepository.save(employee);
    }

    private void save4() {
        Employee employee = new Employee();
        employee.setId("3");
        employee.setFirstName("#l好哦啊吼吼吼哦呵呵\n" +
                "uiasdfooooooo\n" +
                "klaksdf  hello\n");
        employee.setLastName("li");
        employee.setAge(21);
        employee.setAbout("i am hello");
        log.info("新增成功");
        employeeRepository.save(employee);
    }
    private void save5() {
        Employee employee = new Employee();
        employee.setFirstName("#316\n" +
                "uiasdfooooooo\n" +
                "klaksdf  hello\n");
        employee.setLastName("316");
        employee.setAge(316);
        employee.setAbout("i am hello");
        log.info("新增成功");
        employeeRepository.save(employee);
    }
    @RequestMapping(path = "/findById", method = RequestMethod.GET)
    public Employee query(@RequestParam(value = "id") String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            log.info("查询成功");
            return employeeOptional.get();
        }
        return null;
    }

    @RequestMapping(path = "/update", method = RequestMethod.GET)
    public Employee update(@RequestParam(value = "id") String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = null;
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
            employee.setFirstName("tomcat");
            employeeRepository.save(employee);
            log.info("更新成功");
        }
        return employee;
    }


    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        employeeRepository.delete(employeeOptional.get());
        return "success";
    }

    @RequestMapping(path = "/findByAbout", method = RequestMethod.GET)
    public Page<Employee> findByAbout(@RequestParam(value = "about") String about, @RequestParam(value = "firstName") String firstName) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Employee> employees = employeeRepository.findDistinctEmployeeByAboutContainingOrFirstNameContaining(about, firstName, pageRequest);
        return employees;
    }


    @RequestMapping(path = "/findByAbout1", method = RequestMethod.GET)
    private Page<Employee> queryPage(@RequestParam(value = "queryValue") String value) {
        Pageable pageable = PageRequest.of(0, 100);
        String preTag = "<font color='#dd4b39'>";//google的色值
        String postTag = "</font>";

//
//        Query query = new NativeSearchQueryBuilder().
//                withQuery(matchQuery("firstName", value).fuzziness(Fuzziness.AUTO).prefixLength(3).maxExpansions(10)).
//                withHighlightFields(new HighlightBuilder.Field("firstName").preTags(preTag).postTags(postTag)).build();
//        query.setPageable(pageable);

//        BoolQueryBuilder bb = boolQuery();
//
////        bb.must(rangeQuery("date").gt(开始时间).lt(结束时间));
////        bb.must( QueryBuilders.termQuery("lastName.keyword",value));
//        //
//        bb.must(QueryBuilders.termQuery("lastName",value));
//        bb.must(QueryBuilders.queryStringQuery(value));

        BoolQueryBuilder builder = boolQuery();
        String[] userIdArray = StringUtils.split("i am hello,i am hahahasdfr", ",");
        builder.must(QueryBuilders.termsQuery("about.keyword",userIdArray));
//        builder.must(QueryBuilders.termQuery("about.keyword","i am hello"));
        Query query = new NativeSearchQueryBuilder()
                .withQuery(builder)
//                .withQuery(QueryBuilders.queryStringQuery(value).defaultField("firstName"))
                .withPageable(pageable)
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC))
//                .withHighlightFields(new HighlightBuilder.Field("firstName").preTags(preTag).postTags(postTag))
                .withHighlightFields(new HighlightBuilder.Field("firstName").preTags(preTag).postTags(postTag).fragmentSize(200).numOfFragments(1))
//                .withHighlightFields(new HighlightBuilder.Field("about").preTags(preTag).postTags(postTag))
                .build();



        SearchHits<Employee> hits = elasticsearchRestTemplate.search(query, Employee.class);
        List<Employee> content = new ArrayList<>(hits.getSearchHits().size());

        for (SearchHit<Employee> hit : hits) {
            content.add(hit.getContent());
            System.out.println(hit.getContent().getAbout());
        }
        return new PageImpl<>(content, pageable, hits.getTotalHits());

//        SearchHits<Employee> searchHits = elasticsearchRestTemplate.search(query, Employee.class, IndexCoordinates.of("company"));
//        SearchPage<Employee> page = SearchHitSupport.searchPageFor(searchHits, query.getPageable());
//        return (Page<Employee>) SearchHitSupport.unwrapSearchHits(page);


    }

}
