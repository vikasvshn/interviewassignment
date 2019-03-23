package com.holidu.interview.assignment.support;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckControllerTest {

    private List<Tree> data;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreeRepository treeRepository;

    @Before
    public void setUp() throws Exception {
        //Prepare Dummy test data
        Tree t1 = new Tree("American elm", 1007038.864, 247645.678);
        Tree t2 = new Tree("American elm", 1010011.314, 248279.1072);
        Tree t3 = new Tree("American elm", 988981.6661, 219856.3989);
        Tree t4 = new Tree("American elm", 991703.0222, 224365.9319);
        Tree t5 = new Tree("American elm", 994181.3574, 234801.2532);
        Tree t6 = new Tree("American elm", 1011307.116, 251928.6314);
        Tree t7 = new Tree("American elm", 1011138.252, 248910.4094);
        Tree t8 = new Tree("American elm", 1026117.183, 208155.5877);
        Tree t9 = new Tree("American elm", 1027689.996, 177415.365);


        this.data = Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9);

        //Mock the NYC api for unit testing
        Mockito.when(treeRepository.getTreeData()).thenReturn(data);
    }

    @Test
    public void testTreeDataRepo() {
        List<Tree> returnTrees = this.treeRepository.getTreeData();
        assertEquals(this.data, returnTrees);
    }

    @Test
    public void treeCounts() throws Exception {
        Double Xc = 1021900.0;
        Double Yc = 208600.0;
        Double radius = 33284.0;

        String url = "/" + Xc + "/" + Yc + "/" + radius;

        MvcResult result = this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.equals("{\"American elm\":2}"));
    }
}