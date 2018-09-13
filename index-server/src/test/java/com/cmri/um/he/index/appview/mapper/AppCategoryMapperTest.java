package com.cmri.um.he.index.appview.mapper;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lch
 * Created on 2018/06/13 15:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppCategoryMapperTest {
    @Autowired
    private AppCategoryMapper mapper;

    @Test
    public void countOfCategory() {
        List<Map<String, Object>> categorys = mapper.findCategorys();
        Assert.assertTrue(categorys.size() > 0);
    }

    @Autowired
    JestClient jestClient;

    @Test
    public void testEs() throws IOException {
        String query="{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"actorList.name\": \"张译\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(query).addIndex("movie_chn").addType("movie").build();

        SearchResult result = jestClient.execute(search);

        List<SearchResult.Hit<HashMap, Void>> hits = result.getHits(HashMap.class);

        for (SearchResult.Hit<HashMap, Void> hit : hits) {
            HashMap source = hit.source;
            System.err.println("source = " + source);

        }

    }
}
