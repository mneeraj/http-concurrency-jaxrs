# http-concurrency-jaxrs
Example code that demonostrates the use of HTTP Conditional Updates for handling concurrent requests using JAX-RS as mentioned @
http://relishcode.com/http-conditional-updates-with-jax-rs

#1. Pre-requisites
Maven

JDK 1.8

Wildfly 10 (can be downloaded via Maven)

# 2. Build the code
mvn clean package

# 3. Deploy the code on existing, already running wildfly
mvn wildfly:deploy

# or

# 3. Download Wildfly and deploy package
mvn wildfly:run
