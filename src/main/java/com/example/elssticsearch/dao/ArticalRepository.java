package com.example.elssticsearch.dao;

import com.example.elssticsearch.entity.Artical;
import com.example.elssticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: GuanBin
 * @date: Created in 上午11:20 2021/3/8
 */
@Repository
public interface ArticalRepository extends ElasticsearchRepository<Artical,String> {

    Artical queryArticalById(String id);
}
