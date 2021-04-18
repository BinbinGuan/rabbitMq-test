package com.example.elssticsearch.controller;

import com.example.elssticsearch.dao.CommandRecordRepository;
import com.example.elssticsearch.entity.CommandRecord;
import com.example.elssticsearch.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

/**
 * @author: GuanBin
 * @date: Created in 上午10:51 2021/3/18
 */
@Slf4j
@RestController
@RequestMapping("command")
public class LogController {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private CommandRecordRepository recordRepository;





    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") String id) {
        Optional<CommandRecord> employeeOptional = recordRepository.findById(id);
        recordRepository.delete(employeeOptional.get());
        return "success";
    }

    @RequestMapping(path = "/save", method = RequestMethod.GET)
    public Log save() {
        Log log = new Log();
        log.setId("hahaha");
        log.setLog("");
        log.setExecution_id("hahaha");
        return elasticsearchRestTemplate.save(log, IndexCoordinates.of("0329_job_logger"));

    }

    @RequestMapping(path = "/search", params = "query", method = RequestMethod.GET)
    Page<Log> search(@RequestParam(value = "userId", required = false) String userId,
                               @RequestParam(value = "startTimeFrom", required = false) Long startTimeFrom,
                               @RequestParam(value = "startTimeTo", required = false) Long startTimeTo,
                               @RequestParam(value = "queryValue", required = false) String queryValue,
                               Pageable pageable) {

        String preTag = "<font color=\'#D73436\'>";
        String postTag = "</font>";
        String indexName = "default" + "_job_logger";
        //判断index是否存在
        if (!elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName)).exists()) {
            return new PageImpl<>(Collections.EMPTY_LIST, pageable, 0);
        }
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));

        BoolQueryBuilder builder = boolQuery();
        builder.must(QueryBuilders.termsQuery("type.keyword", "run_script", "web_ssh"));


        if (StringUtils.isNotBlank(userId)) {
            builder.must(QueryBuilders.termsQuery("executor_id.keyword", StringUtils.split(userId, ",")));
        }
        if (startTimeFrom != null && startTimeTo != null) {
            builder.must(rangeQuery("started_at").gt(startTimeFrom).lt(startTimeTo));
        }
        if (StringUtils.isNotBlank(queryValue)) {
            //es中保留的特殊字符需要转义
            queryValue = QueryParser.escape(queryValue);
            BoolQueryBuilder queryValueBuilder = boolQuery()
                    .should(QueryBuilders.matchPhraseQuery("vm_name", queryValue))
                    .should(QueryBuilders.matchPhraseQuery("catalog_name", queryValue))
                    .should(QueryBuilders.matchPhraseQuery("group_name", queryValue))
                    .should(QueryBuilders.matchPhraseQuery("business_group_name", queryValue))
                    .should(QueryBuilders.matchPhraseQuery("deployment_name", queryValue))
                    .should(QueryBuilders.matchPhraseQuery("resource_bundle_name", queryValue))
                    .should(QueryBuilders.matchPhraseQuery("title", queryValue))
                    .should(QueryBuilders.queryStringQuery(queryValue).field("log"));
            builder.must(queryValueBuilder);
        }

        Query query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .withHighlightFields(new HighlightBuilder.Field("log").preTags(preTag).postTags(postTag).fragmentSize(200).numOfFragments(1))
                .build();


        SearchHits<Log> hits = elasticsearchRestTemplate.search(query, Log.class, IndexCoordinates.of(indexName));
        List<Log> content = new ArrayList<>(hits.getSearchHits().size());

        for (SearchHit<Log> hit : hits) {
            Log Log = hit.getContent();
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            //按关键字没有搜索出内容时，列表里不显示命令内容
            if (!highlightFields.keySet().contains("log")) {
                Log.setLog(null);
            }
            //搜索出关键字，列表和标题需要替换高亮的内容
            highlightFields.forEach((k, v) -> {
                if (StringUtils.equalsIgnoreCase(k, "log")) {
                    Log.setLog(v.get(0));
                }
            });
            content.add(Log);
        }
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }
}
