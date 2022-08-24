# Spring Boot with Angular Embedded

<p>Template project to develop Spring Boot applications with views engine in Angular.</p> 

## Techs

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=angular,spring,typescript,html,js,maven,git" />
  </a>
</p>

## How this project works?

This project deploy a REST Api which provides endpoints. Those endpoints would be called in Angular Services for get data. Services would return that data and will be represented in Angular views.

In this project we got and endpoint named "/message":

```
    @GetMapping("/message")
    public ResponseEntity<?> getMessage() {

        GreetingMessage greetingMessage = new GreetingMessage();

        try{

            greetingMessage.setMessage(apiClientService.getGreetingMessage());

        }catch (TestException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorAlert("An error occurred in the welcome message."));
        }

        return ResponseEntity.ok(greetingMessage);
    }
```
This method gets info from a service that returns a GreetingMessage type bean. This bean only contains a String property where it contains the message.

This Angular component invoke a service wich call the previous Spring Boot endpoint:

```
    @Component({
      selector: 'app-greeting',
      templateUrl: './greeting.component.html',
      styleUrls: ['./greeting.component.css']
    })
    export class GreetingComponent implements OnInit {
    
      message: string | undefined = "";
    
      constructor(private _greetingService: GreetingService) { }
    
      ngOnInit(): void {
    
        this._greetingService.getGreetingMessage().subscribe({
            error: (error) => {
              console.log("Error happened! " + error);
            },    // errorHandler
            next: (data) => {
              this.message = data.message;
            },
          }
        );
    
      }
    }
```

## How to build this project?


This project is built with Maven and contains two profiles:

- development
- production

Those profiles not involved the application.properties file from Spring Boot, only apply build changes by environment files from Angular.

### How to run those profiles?

We can build our project using maven using the Maven´s IDE tools or invoking by command line:

```
    mvn clean install -P production
```
### How this build works?

Maven provides a profile engine. When user chooses a profile, Maven set a property named build with a value:

```
    <profiles>
        <profile>
            <id>development</id>
            <properties>
                <build.property>development</build.property>
            </properties>
        </profile>
        <profile>
            <id>production</id>
            <properties>
                <build.property>production</build.property>
            </properties>
        </profile>
    </profiles>
```

When user invokes the install goal, Maven triggers two plugins. 
First plugin triggers two jobs:
- One of them invokes Angular dependencies installer command.
```
    <execution>
        <id>npm-install</id>
        <phase>generate-sources</phase>
        <goals>
            <goal>exec</goal>
        </goals>
        <configuration>
            <executable>npm</executable>
            <workingDirectory>${basedir}/src/main/js/angularclient</workingDirectory>
            <arguments>install</arguments>
        </configuration>
    </execution>
```
- The other one invokes Angular build command using the build property setting in Maven profile.

```
    <execution>
        <id>build-ui</id>
        <phase>prepare-package</phase>
        <goals>
            <goal>exec</goal>
        </goals>
        <configuration>
            <executable>ng</executable>
            <workingDirectory>${basedir}/src/main/js/angularclient</workingDirectory>
            <arguments>
                <argument>build</argument>
                <argument>--configuration="${build.property}"</argument>
            </arguments>
        </configuration>
    </execution>
```

Second plugin just move the Angular dist folder to another folder where Spring Boot can redirect for rendering views.

```
    <execution>
        <id>copy-resources</id>
        <phase>prepare-package</phase>
        <goals>
            <goal>copy-resources</goal>
        </goals>
        <configuration>
            <outputDirectory>${basedir}/target/classes/static/</outputDirectory >
            <resources>
                <resource>
                    <directory>${basedir}/src/main/js/angularclient/dist/angularclient</directory >
                </resource>
            </resources>
        </configuration>
    </execution>
```

### What the previous builds produces?

When the build is finished, it produces a ".jar" file wich can be deployed on application servers or running directly as Spring Boot embeded Tomcat server.
