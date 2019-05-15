# Rsort

[![Build Status](https://travis-ci.org/vadim-zavgorodniy/rsort.svg?branch=develop)](https://travis-ci.org/vadim-zavgorodniy/rsort)
[![codecov](https://codecov.io/gh/vadim-zavgorodniy/rsort/branch/develop/graph/badge.svg)](https://codecov.io/gh/vadim-zavgorodniy/rsort)

Rsort is a simple library for adding sorting in RESTful APIs.

Sorting criteria format: `fieldName1:SortOrder,fieldName2:SortOrder`

SortOrder can be *ASC* for ascending ao *DESC* for descending sorting. 

Sample: `name:ASC,age:DESC`

Usage:

```java
@GET
public List<EntityType> search(@QueryParam("sort") String sort) {
    SortCriteriaParser criteriaParser = new SortCriteriaParser();
    final List<SortCriteria> orderBy = criteriaParser.parse(sort);
    return searchOrdered(orderBy);
}
```
