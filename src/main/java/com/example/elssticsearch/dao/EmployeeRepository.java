package com.example.elssticsearch.dao;

import com.example.elssticsearch.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: GuanBin
 * @date: Created in 上午11:20 2021/3/8
 */
@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

    Employee queryEmployeeById(String id);

//    Page<Employee> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary,
//                                                                                             String content, PageRequest pageRequest);

    Page<Employee> findDistinctEmployeeByAboutContainingOrFirstNameContaining(String about, String firstName,PageRequest pageRequest);
}
