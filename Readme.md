
## Database setup
1. Download Oracle DB developer VM from <http://www.oracle.com/technetwork/database/enterprise-edition/databaseappdev-vm-161299.html>
2. Import the downloaded ova image in your virtual box.
3. Right click on Oracle DB developer VM in your virtual box
    1. `Settings` > `Network` > `Adapter 2` > Add `Host-only Adapter`
    2. Start the VM
    3. Check your ifconfig for the IP address of the newly added Host-only adapter. (Note down this IP)
    4. Also note down the name of pluggable DB when you start your SQL plus
        * For Example:  
            `spring.datasource.url=jdbc:oracle:thin:@//192.168.56.101:1521/orcl` in application.properties  
            here,   
            `orcl` is the pluggable db name
    5. Change `spring.datasource.url` in application.properties with the IP address that you got in step iii.
4. Once connected, you can create a user in oracle, and replace `<USER_NAME>` with your newly created user
    1. `CREATE USER <USER_NAME> IDENTIFIED BY <Password>;`
    2. `GRANT CONNECT TO <USER_NAME>;`
    3. `GRANT CONNECT, RESOURCE, DBA TO <USER_NAME>;`
    4. `GRANT CREATE SESSION TO <USER_NAME>;`
    5. Also replace `spring.datasource.username` and `spring.datasource.password` in application.properties
    

## Jprofiler setup
1. Download JProfiler from <https://www.ej-technologies.com/download/jprofiler/files>
2. Attach JProfiler to running Spring boot application JVM


## Steps to start your application

* Run `mvn clean install -DskipTests`
* Run SpringbootoracleApplication from intelliJ
* Make a GET request to <http://localhost:8080/allSamples/stream>.

    

