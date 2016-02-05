package ch11.javabeans;

import java.beans.BeanInfo;
import java.beans.Expression;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * {@link java.beans.Introspector} 를 통해 reflection API없이 Java Bean의 속성과 메소드 이벤트 정보를 알아낼 수 있다.
 */
public class JavaBeanExample {
    public static void main(String[] args) throws Exception {
        Person person = new Person();

        String[] arguments = {"Peter"};
        Expression expression = new Expression(null, person, "setName", arguments);

        System.out.println("Name : " + person.getName());
        expression.execute();
        System.out.println("Name : " + person.getName());

        System.out.println();
        expression = new Expression(null, person, "getName", null);
        System.out.println("Name: " + person.getName());
        expression.execute();
        System.out.println("getValue: " + expression.getValue());

        final BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            System.out.println(propertyDescriptor.getReadMethod() + " - " +  propertyDescriptor.getPropertyType() + " - " + propertyDescriptor.getName() + " - " + propertyDescriptor.getDisplayName());
        }
    }
}
