package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.model.Personne;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author SZaoui
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonMapperTest {

    private PersonMapper personMapper = new PersonMapperImpl();

    @Test
    public void testPersonModelToDto() {
        //Given
        Personne person = TestUtils.createPersonEbx(1);


    }

}
