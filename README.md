# Setup

- Maven Java Project
```
mvn archetype:generate -DgroupId=codejitsu.coffee.app -DartifactId=coffee-java-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

# App Logic

- Create custom annotations `@CoffeeApplicationController` and `@CoffeeApplicationCreate` to mark controller classes and create method.

- Use reflection to scan for classes with `@CoffeeApplicationController` and invoke methods annotated with `@CoffeeApplicationCreate`.

- Example controller class `AppController` with a create method that prints input to console.

# Build

```
mvn clean install
```

# Run

```
cd /coffee-java-app/
mvn compile exec:java -Dexec.mainClass="codejitsu.coffee.app.App"
```