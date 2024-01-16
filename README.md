## Application overview

person service is a simple Spring Boot microservice backed by MariaDB database. Integrated with AWS Secrets Manager. Gitlab Pipeline is used for CI.

Helm chart for kubernetes setup may be found in `/chart` directory.

<br/>

Some TODOs left:
- add JWT authentication to APIs
- migrate kubernetes cluster to EKS 
- attach EBS persistent volume to DB pod
- enable autoscaling
- specify deployment strategy
- enable security scans for images stored in registry

<br/><hr/>

## How to run

I didn't migrate the application to EKS yet, so here is guide how to run it locally using minikube.

1. Please use following command in order to run the cluster (I put hardcoded values here just for ease of run and demo purposes):

    ~~~
	helm install person-app --set spring_profile=demo --set registry_credentials_json=eyJhdXRocyI6eyJyZWdpc3RyeS5naXRsYWIuY29tIjp7ImF1dGgiOiJhM1ZpWlMxd2RXeHNaWEk2WjJ4a2RDMVFlWGxSZUVnMlNEbDRlVlpRZGpodVJuQTFkZz09In19fQ== --set db.password=my-secret-pw ./chart
    ~~~

1. Then expose the service via minikube:
    ~~~
    minikube service person-service 
    ~~~

   it will assign port on local machine, let's call it `<ASSIGNED_PORT>`.


1. Please open `http://localhost:<ASSIGNED_PORT>/swagger-ui/index.html#/` link in browser to see OpenAPI specs for APIs.

<br/>

### Note

For demo purposes integration with AWS Secrets Manager is disabled, as it requires passing AWS access tokens to Helm chart.

