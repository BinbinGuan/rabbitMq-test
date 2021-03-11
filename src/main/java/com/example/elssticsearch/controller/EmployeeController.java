package com.example.elssticsearch.controller;

import com.example.elssticsearch.dao.EmployeeRepository;
import com.example.elssticsearch.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

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
        Employee employee = new Employee();
        employee.setId("4");
        employee.setFirstName("#   SearchQuery searchQuery = new NativeSearchQueryBuilder()\n" +
                "#                 .withQuery(QueryBuilders.queryStringQuery(\"东西\"))\n" +
                "#                 .withIndices(\"my-index\")\n" +
                "#                 .withPageable(PageRequest.of(0, 10))\n" +
                "#                 .build();\n" +
                "#         List<ElasticsearchDto> data = elasticsearchRestTemplate.queryForList(searchQuery, ElasticsearchDto.class);\n" +
                "#         System.out.println(data);\n" +
                "#         //查询list\n" +
                "#         Iterable<ElasticsearchDto> temp = elasticsearchRepository.findAll();\n" +
                "#         System.out.println(temp);");
        employee.setLastName("li");
        employee.setAge(20);
        employee.setAbout("i am EmployeeController");
        log.info("新增成功");
        employeeRepository.save(employee);

        return "success";
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
        Pageable pageable = PageRequest.of(0, 10);
        String preTag = "<font color='#dd4b39'>";//google的色值
        String postTag = "</font>";

//
//        Query query = new NativeSearchQueryBuilder().
//                withQuery(matchQuery("firstName", value).fuzziness(Fuzziness.AUTO).prefixLength(3).maxExpansions(10)).
//                withHighlightFields(new HighlightBuilder.Field("firstName").preTags(preTag).postTags(postTag)).build();
//        query.setPageable(pageable);

        Query query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("SearchQuery").defaultField("firstName"))
                .withPageable(PageRequest.of(0, 5))
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC))
                .withHighlightFields(new HighlightBuilder.Field("firstName").preTags(preTag).postTags(postTag))
                .build();


        SearchHits<Employee> hits = elasticsearchRestTemplate.search(query, Employee.class);
        List<Employee> content = new ArrayList<>(hits.getSearchHits().size());

        for (SearchHit<Employee> hit : hits) {
            content.add(hit.getContent());
        }
        return new PageImpl<>(content, pageable, hits.getTotalHits());

    }

}
