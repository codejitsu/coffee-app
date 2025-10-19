package codejitsu.coffee.app;

import codejitsu.coffee.annotations.CoffeeApplicationController;
import codejitsu.coffee.annotations.CoffeeApplicationCreate;

@CoffeeApplicationController
public class AppController {
    
    @CoffeeApplicationCreate
    public void create(String input) {
        System.out.println("Create coffee: " + input);
    }
}
