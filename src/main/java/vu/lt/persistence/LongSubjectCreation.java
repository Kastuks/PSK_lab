package vu.lt.persistence;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

// Decorators have a special injection point, called the delegate
// injection point, with the same type as the beans they decorate, and the annotation @Delegate
// There must be exactly one delegate injection point
//        A decorator is bound to any bean which:
//        has the type of the delegate injection point as a bean type, and
//        has all qualifiers that are declared at the delegate injection point.

@Decorator
public class LongSubjectCreation implements Subject {

    @Inject
    @Delegate
    @Any
    Subject subjects;

    @Override
    public void persist(vu.lt.entities.Subject subjectToCreate) {
        subjects.persist(subjectToCreate);

        System.out.println(subjectToCreate.getName());
        if (subjectToCreate.getName().length() > 7) {
            System.out.println("Subject with very long name has been created: " + subjectToCreate.getName());
        }
    }

}
