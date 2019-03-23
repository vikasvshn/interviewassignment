Assignment

Create a search application where you expose an endpoint for a client to search based on a certain radius for tree related data.

- You can find Street tree data from the TreesCount 2015 here => https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh
- The direct call to api is `https://data.cityofnewyork.us/resource/nwxe-4ae8.json`
- You have to expose and API endpoint accepting two parameters 
    1. A Cartesian Point specifying a center point along the x & y plane
    2. A search radius in meters

Output
 - You have to retrieve the count of "common name" (please see in the documentation on the same page above which field to refer to) for all 
 the species of trees in that search radius
 - Expected outcome from the api
```json
{
    "red maple": 30,
    "American linden": 1,
    "London planetree": 3
}


Solution : 
1. run the App.java file , it will start the Spring boot application on embedded tomcat. 
2. Web API endpoint : http://localhost:8080/{Xc}/{Yc}/{radius}
   Sample URL: http://localhost:8080/1021900/208600/20000
   Sample Output: {"Sophora":19,"Japanese zelkova":1,"eastern redcedar":1,"Chinese fringetree":1,"sweetgum":1,"silver maple":5,"Norway maple":70,"pin oak":17,"silver linden":2,"red maple":1,"willow oak":1,"American elm":1,"crab apple":1,"honeylocust":24,"Callery pear":5,"ginkgo":13,"swamp white oak":1,"tulip-poplar":2,"London planetree":17,"black cherry":1,"American linden":3,"Amur maple":4}
