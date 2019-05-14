# Rsort

Rsort is a simple library for adding sorting in RESTful APIs.

Sample:

```java
@GET
public List<EntityType> search(@QueryParam("sort") String sort) {
    SortCriteriaParser criteriaParser = new SortCriteriaParser();
    final List<SortCriteria> orderBy = criteriaParser.parse(sort);
    return searchOrdered(orderBy);
}
```
