# kreditindex-rest-api

This is a REST API used to calculate statistics from a given set of subjects and its values in credits.  
This is useful in Hungarian universities, where these stats are calculated at the end of every semester.
The API also has the capability to calculate the expected amount of study scholarship (in HUF), and some other metrics.

Example request:

GET /api/v1/calc  
```
{
    "subjects": [
        {
            "name": "Földrajz",
            "credit": "2",
            "mark": "3"
        },
        {
            "name": "Történelem",
            "credit": "4",
            "mark": "5"
        }
    ]
}
```

Response:
```
{
    "creditIndex": 0.8666666666666667,
    "average": 4.0,
    "numberOfCredits": 6,
    "weightedAverage": 4.333333333333333,
    "money": 0,
    "catalogFree": false,
    "stateFunded": true
}

```
