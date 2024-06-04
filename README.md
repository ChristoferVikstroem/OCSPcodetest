# OCSPcodetest
Code test for Keyfactor by Christofer Vikström. 
This solution is for command line based. Comments exist in the code but this readme is a short summary.

## OCSPClient.java
A simple implementation of an OCSP client. It contains two methods: main and printOCSPResponse.

### main
This method initializes an OCSPClient object and invokes the printOCSPResponse method. For this code test, a given predefined byte array was provided. If it would fail, it catches the eventual Exception and prints a stacktrace.

### printOCSPResponse
This method takes a byte array representing an OCSP response as input, parses the response, and extracts information regarding the certificate using BouncyCastle. In this case, the current status of the certificate, the current update time, and the next update time. The method prints the response in the console, unless an exception happens during parsing, where an error message is printed instead.

## OCSPClientTest.java
This test class uses junit to try different responses for the OCSPClient class.

### testValidOCSPResponse
Hard coded the provided byte array and calls the printOCSPResponse. A successful test asserts true, a failed test prints the exception.

### testShortArray
Created a byte array that is too short and throws an IOException.

### testNullArray
The test invokes the printOCSPResponse will a null parameter, resulting in test asserting a NullPointerException.


## Running the program

### Compile with Maven
Run the command in the directory that the pom.xml file is located.

    $ mvn compile

### Using the OCSPClient
    $ mvn exec:java -Dexec.mainClass="com.example.ocspclient.OCSPClient"

The result is printed in the terminal and includes the basic response and different single responses (such as ID, Status) by using BouncyCastle's basicOCSPResp and SingleResp. 

### Running the tests
    $ mvn test

The test result is printed in the terminal but can be found under surefire-reports.