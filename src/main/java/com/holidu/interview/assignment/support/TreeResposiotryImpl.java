package com.holidu.interview.assignment.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TreeResposiotryImpl implements TreeRepository {

    Logger logger = LoggerFactory.getLogger(TreeResposiotryImpl.class);
    @Value("${treesCount.api.url}")
    private String treesCountApiUrl;

    /**
     * RestClient to get Tree Census data from NYC open data
     * First Call will take some time to load the data from API but all subsequent calls will be served from cache.
     * Another option is to create cache in advance on application start or on a schedule using Quartz scheduler
     *
     * @return list of tree data
     */
    @Cacheable("trees")
    public List<Tree> getTreeData() {
        RestTemplate restTemplate = new RestTemplate();
        logger.info("Making rest api call to get Tree Data.");
        ResponseEntity<List<Tree>> response = restTemplate.exchange(this.treesCountApiUrl, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Tree>>() {
                });
        return response.getBody();
    }
}
