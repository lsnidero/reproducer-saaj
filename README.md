# Small reproducer for saaj-error

This little project should use **java 11** and uses the soap WS exposed from  https://github.com/lokeshgupta1981/Spring-Boot3-Demos.git in the **soap-ws-example** folder. 

Just point to http://localhost:9080/api/test in order to test the invocation to the soap server.

*UPDATE:* I removed the logica dependency the local SOAP ws using a public one.  

Build

```shell
podman build -t repoducer-saaj .
```

Run

```shell
podman run -it -p 9080:9080 localhost/reproducer-saaj:latest 
```

Test

```shell
curl -v http://localhost:9080/api/test
```