$baseDir = "c:\Users\Kundan kumar\Desktop\Project\microservices"

function Create-Project {
    param([string]$name, [string]$mainClass, [string]$pomContent, [string]$propsContent, [string]$javaFiles)
    
    $projectDir = Join-Path $baseDir $name
    $srcMainJava = Join-Path $projectDir "src\main\java\com\cognizant\$name"
    $srcMainRes = Join-Path $projectDir "src\main\resources"
    
    New-Item -ItemType Directory -Force -Path $srcMainJava | Out-Null
    New-Item -ItemType Directory -Force -Path $srcMainRes | Out-Null
    
    Set-Content -Path (Join-Path $projectDir "pom.xml") -Value $pomContent
    Set-Content -Path (Join-Path $srcMainRes "application.properties") -Value $propsContent
    
    # We will pass a script block or hashtable for java files, or just create them below.
}

# 1. ACCOUNT SERVICE
$accountPom = @"
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.cognizant</groupId>
	<artifactId>account</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>account</name>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2024.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>`${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
"@

$accountProps = @"
spring.application.name=account-service
"@

Create-Project -name "account" -mainClass "AccountApplication" -pomContent $accountPom -propsContent $accountProps

$accountMain = @"
package com.cognizant.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
"@
Set-Content -Path (Join-Path $baseDir "account\src\main\java\com\cognizant\account\AccountApplication.java") -Value $accountMain

$accountController = @"
package com.cognizant.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @GetMapping("/accounts/{number}")
    public Map<String, Object> getAccount(@PathVariable String number) {
        Map<String, Object> account = new HashMap<>();
        account.put("number", number);
        account.put("type", "savings");
        account.put("balance", 234343);
        return account;
    }
}
"@
Set-Content -Path (Join-Path $baseDir "account\src\main\java\com\cognizant\account\AccountController.java") -Value $accountController

# 2. LOAN SERVICE
$loanPom = $accountPom.Replace("<artifactId>account</artifactId>", "<artifactId>loan</artifactId>").Replace("<name>account</name>", "<name>loan</name>")
$loanProps = @"
server.port=8081
spring.application.name=loan-service
"@

Create-Project -name "loan" -mainClass "LoanApplication" -pomContent $loanPom -propsContent $loanProps

$loanMain = $accountMain.Replace("account", "loan").Replace("Account", "Loan")
Set-Content -Path (Join-Path $baseDir "loan\src\main\java\com\cognizant\loan\LoanApplication.java") -Value $loanMain

$loanController = @"
package com.cognizant.loan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public Map<String, Object> getLoan(@PathVariable String number) {
        Map<String, Object> loan = new HashMap<>();
        loan.put("number", number);
        loan.put("type", "home");
        loan.put("loan", 400000);
        loan.put("emi", 3200);
        loan.put("tenure", 120);
        return loan;
    }
}
"@
Set-Content -Path (Join-Path $baseDir "loan\src\main\java\com\cognizant\loan\LoanController.java") -Value $loanController

# 3. EUREKA SERVER
$eurekaPom = @"
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.1</version>
		<relativePath/>
	</parent>
	<groupId>com.cognizant</groupId>
	<artifactId>eureka-discovery-server</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>eureka-discovery-server</name>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2024.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>`${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
"@

$eurekaProps = @"
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
"@

$eurekaDir = Join-Path $baseDir "eureka-discovery-server"
$eurekaSrc = Join-Path $eurekaDir "src\main\java\com\cognizant\eurekadiscoveryserver"
$eurekaRes = Join-Path $eurekaDir "src\main\resources"
New-Item -ItemType Directory -Force -Path $eurekaSrc | Out-Null
New-Item -ItemType Directory -Force -Path $eurekaRes | Out-Null
Set-Content -Path (Join-Path $eurekaDir "pom.xml") -Value $eurekaPom
Set-Content -Path (Join-Path $eurekaRes "application.properties") -Value $eurekaProps

$eurekaMain = @"
package com.cognizant.eurekadiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
	}
}
"@
Set-Content -Path (Join-Path $eurekaSrc "EurekaDiscoveryServerApplication.java") -Value $eurekaMain


# 4. API GATEWAY
$gatewayPom = @"
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.1</version>
		<relativePath/>
	</parent>
	<groupId>com.cognizant</groupId>
	<artifactId>api-gateway</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>api-gateway</name>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2024.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>`${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
"@

$gatewayProps = @"
server.port=9090
spring.application.name=api-gateway
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
"@

$gatewayDir = Join-Path $baseDir "api-gateway"
$gatewaySrc = Join-Path $gatewayDir "src\main\java\com\cognizant\apigateway"
$gatewayRes = Join-Path $gatewayDir "src\main\resources"
New-Item -ItemType Directory -Force -Path $gatewaySrc | Out-Null
New-Item -ItemType Directory -Force -Path $gatewayRes | Out-Null
Set-Content -Path (Join-Path $gatewayDir "pom.xml") -Value $gatewayPom
Set-Content -Path (Join-Path $gatewayRes "application.properties") -Value $gatewayProps

$gatewayMain = @"
package com.cognizant.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
"@
Set-Content -Path (Join-Path $gatewaySrc "ApiGatewayApplication.java") -Value $gatewayMain

$logFilter = @"
package com.cognizant.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LogFilter implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("====> Request URL {}", exchange.getRequest().getURI());
        return chain.filter(exchange);
    }
}
"@
Set-Content -Path (Join-Path $gatewaySrc "LogFilter.java") -Value $logFilter

Write-Host "All files generated successfully."
