# Live Score

This API will allow us to perform different operations with all the matches we currently have in our system.

## Get all matches

We must call the following endpoint:

``` bash
/v01/match
```

It will return all the matches ordered from the first one added into the system to the latest one.

## Start a match

We must call the following endpoint:

``` bash
/v01/match/{matchId}/RUNNING
```

It will set a match as RUNNING.

## Finish a match

We must call the following endpoint:

``` bash
/v01/match/{matchId}/FINISHED
```

It will set a match as FINISHED.

## Update an score

We must call the following endpoint:

``` bash
/v01/match/{matchId}/score
```

As a body we must send a JSON of the score:

``` bash
{
  "id" : "655cb87f-8572-4b72-b14d-8e113b69441c",
  "scoreHomeTeam" : 1,
  "scoreAwayTeam" : 0
}
```

It will update the score of the corresponding match. In order to update the score, the match must be RUNNING.

A postman collection is attached to the project in order to run some tests.