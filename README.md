# finchVersion
A sample project demonstrating API-Versioning based on Finch.

## Starting the API
To start the API, checkout the source code and run:

```sbt "runMain com.dsd.finchVersion.Main [-pathVersioning]"```

If the flag `-pathVersioning` is set, the version is path-based. Otherwise, a
client is required to properly set the Content-Type as described below.
 
## Querying the API

The API provides example entries for both users and departments. For example,
 if path-versioning is turn on, the user with id `1` can be requested as 
 follows:

```bash
$ curl -v http://localhost:8080/api/v1/users/1 
  *   Trying ::1...
  * TCP_NODELAY set
  * Connected to localhost (::1) port 8080 (#0)
  > GET /api/v1/users/1 HTTP/1.1
  > Host: localhost:8080
  > User-Agent: curl/7.54.0
  > Accept: */*
  > 
  < HTTP/1.1 200 OK
  < Date: Sat, 24 Nov 2018 17:03:50 GMT
  < Server: Finch
  < Content-Length: 44
  < Content-Type: application/json
  < 
  * Connection #0 to host localhost left intact
  {"username":"albi","name":"Albert Einstein"} 
```

Similarly, the version is indicated via the content-type, the same user can 
be retrieved as follows:

```bash
$ curl -v -H 'Content-Type: application/vnd.example.com.v1+json' 
http://localhost:8080/api/users/1
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /api/users/1 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: application/vnd.example.com.v1+json
> 
< HTTP/1.1 200 OK
< Date: Sat, 24 Nov 2018 17:06:25 GMT
< Server: Finch
< Content-Length: 44
< Content-Type: application/json
< 
* Connection #0 to host localhost left intact
{"username":"albi","name":"Albert Einstein"}
```
