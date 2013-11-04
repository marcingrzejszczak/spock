package spock.util.injectmock

import spock.lang.Specification

class InjectMocksSettersWithCollaboratorsAndNonMatchingConstructorSpec extends Specification {

    public static final String TEST_METHOD_1 = "Test method 1"

    SomeOtherClass someOtherClassToBeInjected = Mock()

    @Collaborator
    SomeOtherClass someOtherClass = Mock()

    @Subject
    SomeClass objectUnderTest

    def "should inject collaborator into subject via setters ignoring nonmatching constructor"() {
        given:
        someOtherClass.someMethod() >> TEST_METHOD_1

        when:
        String firstResult = objectUnderTest.someOtherClass.someMethod()

        then:
        firstResult == TEST_METHOD_1
        objectUnderTest.someOtherClass == someOtherClass
    }


    class SomeClass {
        SomeOtherClass someOtherClass
        SomeOtherClass someOtherClassToBeInjected

        SomeClass() {

        }

        SomeClass(Integer integer) {

        }
    }

    class SomeOtherClass {
        String someMethod() {
            "Some other class"
        }
    }

}


