//package com.example.elssticsearch.controller;
//
//import com.example.elssticsearch.dao.CommandRecordRepository;
//import com.example.elssticsearch.entity.CommandRecord;
//import com.example.elssticsearch.entity.Employee;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.Query;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.*;
//
//import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
//import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
//
///**
// * @author: GuanBin
// * @date: Created in 上午10:51 2021/3/18
// */
//@Slf4j
//@RestController
//@RequestMapping("command")
//public class CommandRecordController {
//
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @Autowired
//    private CommandRecordRepository recordRepository;
//
//
//    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
//    public String delete(@RequestParam(value = "id") String id) {
//        Optional<CommandRecord> employeeOptional = recordRepository.findById(id);
//        recordRepository.delete(employeeOptional.get());
//        return "success";
//    }
//
//    @RequestMapping(path = "/search", params = "query", method = RequestMethod.GET)
//    Page<CommandRecord> search(@RequestParam(value = "userId", required = false) String userId,
//                               @RequestParam(value = "startTimeFrom", required = false) Long startTimeFrom,
//                               @RequestParam(value = "startTimeTo", required = false) Long startTimeTo,
//                               @RequestParam(value = "queryValue", required = false) String queryValue,
//                               Pageable pageable) {
//        String preTag = "<font color=\'#D73436\'>";
//        String postTag = "</font>";
//
//        BoolQueryBuilder builder = boolQuery();
//
//        //加租户过滤
//        builder.must(QueryBuilders.termQuery("tenantId.keyword", "default"));
//
//        if (StringUtils.isNotBlank(userId)) {
//            builder.must(QueryBuilders.termsQuery("userId.keyword", StringUtils.split(userId, ",")));
//        }
//        if (startTimeFrom != null && startTimeTo != null) {
//            builder.must(rangeQuery("startTime").gt(startTimeFrom).lt(startTimeTo));
//        }
//        if (StringUtils.isNotBlank(queryValue)) {
//            queryValue = QueryParser.escape(queryValue);
//            BoolQueryBuilder queryValueBuilder = boolQuery()
//                    .should(QueryBuilders.matchPhraseQuery("vmName", queryValue))
//                    .should(QueryBuilders.matchPhraseQuery("catalogName", queryValue))
//                    .should(QueryBuilders.matchPhraseQuery("groupName", queryValue))
//                    .should(QueryBuilders.matchPhraseQuery("businessGroupName", queryValue))
//                    .should(QueryBuilders.matchPhraseQuery("deploymentName", queryValue))
//                    .should(QueryBuilders.matchPhraseQuery("resourceBundleName", queryValue))
//                    .should(QueryBuilders.matchPhraseQuery("title", queryValue))
//                    .should(QueryBuilders.queryStringQuery(queryValue).field("commandText"));
//            builder.must(queryValueBuilder);
//        }
//        Query query = new NativeSearchQueryBuilder()
//                .withQuery(builder)
//                .withPageable(pageable)
//                .withHighlightFields(new HighlightBuilder.Field("commandText").preTags(preTag).postTags(postTag).fragmentSize(200).numOfFragments(1))
//                .build();
//        SearchHits<CommandRecord> hits = elasticsearchRestTemplate.search(query, CommandRecord.class);
//        List<CommandRecord> content = new ArrayList<>(hits.getSearchHits().size());
//
//        for (SearchHit<CommandRecord> hit : hits) {
//            CommandRecord commandRecord = hit.getContent();
//            Map<String, List<String>> highlightFields = hit.getHighlightFields();
//            //按关键字没有搜索出内容时，列表里不显示命令内容
//            if (!highlightFields.keySet().contains("commandText")) {
//                commandRecord.setCommandText(null);
//            } else {
//                //搜索出关键字，列表和标题需要替换高亮的内容
//                highlightFields.forEach((k, v) -> {
//                    if (StringUtils.equalsIgnoreCase(k, "commandText")) {
//                        commandRecord.setCommandText(v.get(0));
//                    }
//                });
//            }
//            content.add(commandRecord);
//        }
//        return new PageImpl<>(content, pageable, hits.getTotalHits());
//    }
//}
