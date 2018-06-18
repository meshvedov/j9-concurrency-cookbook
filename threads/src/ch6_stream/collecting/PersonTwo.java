package ch6_stream.collecting;

import ch6_stream.creating_streams.Person;

public class PersonTwo extends Person {
    @Override
    public String toString() {
        return firstName + "-" + lastName;
    }

    public int test() {
        return 0;
    }
}
