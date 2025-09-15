package pe.joedayz.restapis.unit;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AssertJAssertions {


    @Test
    void stringAssertion(){
        assertThat("REST APIs with Spring, JPA, and Springfox").isNotNull()
                .startsWith("REST")
                .containsIgnoringCase("jpa")
                .endsWith("Springfox");
    }

    @Test
    void assertionWithDescriptionMessage(){
        int age = 49;

        assertThat(age).as("Checking age").isEqualTo(49);
    }

    @Test
    void assertionForList(){
        List<Integer> marks = new ArrayList<>();
        marks.add(80);
        marks.add(70);
        marks.add(65);
        marks.add(75);
        marks.add(95);

        assertThat(marks).hasSize(5);
        assertThat(80).isIn(marks);
        assertThat(marks).matches(m -> m.size()==5);
    }

    @Test
    void assertionForMap(){
        Map<Integer, String> customerInfo = new HashMap<>();
        customerInfo.put(1, "Joe");
        customerInfo.put(2, "Doe");
        customerInfo.put(3, "John");
        customerInfo.put(4, "Jane");

        assertThat(customerInfo).containsEntry(2, "Doe");
        assertThat(customerInfo).doesNotContainKey(7);
        assertThat(customerInfo).containsValues("Joe", "Doe", "John");
        assertThat(customerInfo).containsKeys(1, 2, 3, 4);

    }

    @Test
    void assertionForExceptions(){
        List<Integer> marks = new ArrayList<>();
        marks.add(80);
        marks.add(70);
        marks.add(65);
        marks.add(75);
        marks.add(95);

        assertThatThrownBy(() -> marks.get(6)).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Index 6 out of bounds for length 5");

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> marks.get(6))
                .withMessageMatching("Index 6 out of bounds for length 5");

        assertThatNoException().isThrownBy(() -> marks.get(4));
    }

    @Test
    void assertionForLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();

        assertThat(now).isBefore(LocalDateTime.now());
        assertThat(now).isInstanceOf(LocalDateTime.class);
        assertThat(now).isStrictlyBetween(now.minusSeconds(10), now.plusSeconds(100));
        assertThat(now).isBetween(now.minusDays(1), now.plusSeconds(100));

    }
}
