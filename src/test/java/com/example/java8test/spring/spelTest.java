package com.example.java8test.spring;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 下午7:44 2019/5/22
 */
public class spelTest {

    @Test
    public void test08_3() {

        Map<String, Object> interests = new HashMap<String, Object>();
        interests.put("key1", "BasketBall");
        interests.put("key2", "FootBall");

        SpelContext spelContext = new SpelContext();
        spelContext.setTag(interests);
        ExpressionParser parser = new SpelExpressionParser();
        String expression1= "tag['key3'] != null ? tag['key1'] : \"\"";
        String expression2= "tag['key3']+tag['key1']";
        String expression3 = "(tag['key2'] != null ? tag['key2'] : \"\") +tag['key1']";
//        String expression3 = "tag['key2'] != null ? tag['key2'] : \"\"";
//        String expression4= "tag['key3'] != null ? tag['key1'] : ''";

        String value = parser.parseExpression(expression3).getValue(spelContext, String.class);
        System.out.println(value);
    }



    public class SpelContext{
        private String deploymentName;
        private String nodeName;
        private String nodeType;
        private Map<String,Object> tag;

        public String getDeploymentName() {
            return deploymentName;
        }

        public void setDeploymentName(String deploymentName) {
            this.deploymentName = deploymentName;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public Map<String, Object> getTag() {
            return tag;
        }

        public void setTag(Map<String, Object> tag) {
            this.tag = tag;
        }
    }


}
