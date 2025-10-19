package codejitsu.coffee.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import codejitsu.coffee.annotations.CoffeeApplicationController;
import codejitsu.coffee.annotations.CoffeeApplicationCreate;

public class App 
{
    public static void main( String[] args )
    {
        ClassPathScanningCandidateComponentProvider scannerProvider = new ClassPathScanningCandidateComponentProvider(false);
        
        // we want to scan for classes annotated with @CoffeeApplicationController
        scannerProvider.addIncludeFilter(new AnnotationTypeFilter(CoffeeApplicationController.class));

        // read all class definitions from the specified package
        Set<BeanDefinition> beanDefinitions = scannerProvider.findCandidateComponents("codejitsu.coffee");

        // list to hold names of beans with our annotation
        List<String> annotatedBeans = new ArrayList<>();

        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                annotatedBeans.add(beanDefinition.getBeanClassName());

                AnnotatedBeanDefinition abd = (AnnotatedBeanDefinition) beanDefinition;

                abd.getMetadata().getAnnotatedMethods(CoffeeApplicationCreate.class.getCanonicalName()).forEach(methodMetadata -> {
                    System.out.println("Found method with @CoffeeApplicationCreate: " + methodMetadata.getMethodName());

                    try {
                        Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        clazz.getMethod(methodMetadata.getMethodName(), String.class).invoke(instance, "Cappuccino");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
