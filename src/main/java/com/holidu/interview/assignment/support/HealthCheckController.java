package com.holidu.interview.assignment.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HealthCheckController {

    Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @Autowired
    private TreeRepository treeRepository;

    /**
     * This API returns the count of "common name" for all the species of the trees in the given search radius
     * API endpoint accepts two parameters
     * A Cartesian Point specifying a center point along the x & y plane
     * A search radius in meters
     * Sample URL: http://localhost:8080/Xc/Yc/radius,  http://localhost:8080/1021900/208600/33284
     *
     * @param Xc     Cartesian Point specifying a center point along the x & y plane
     * @param Yc     Cartesian Point specifying a center point along the x & y plane
     * @param radius search radius in meters
     * @return List of the count of "common name" for all the species of trees in the given search radius
     */
    @RequestMapping(
            name = "treeCountsEndpoint",
            method = RequestMethod.GET,
            value = "/{Xc}/{Yc}/{radius}"
    )
    public Map<String, Long> treeCounts(@PathVariable double Xc, @PathVariable double Yc, @PathVariable double radius) {

        List<Tree> list = this.treeRepository.getTreeData();

        logger.info("list size: {}", list.size());

        Map<String, Long> result = list
                .parallelStream()
                .filter(tree -> calcDistanceBetweenTwoPoints(Xc, Yc, tree.getX_sp(), tree.getY_sp()) <= radius)
                .filter(tree -> tree.getSpc_common() != null)
                .map(tree -> tree.getSpc_common())
                .collect(Collectors.groupingBy(m -> m, Collectors.counting()));

        return result;
    }


    private double calcDistanceBetweenTwoPoints(double Xc, double Yc, double Xp, double Yp) {
        double d = Math.sqrt((Xp - Xc) * (Xp - Xc) + (Yp - Yc) * (Yp - Yc));
        return d;
    }

}
