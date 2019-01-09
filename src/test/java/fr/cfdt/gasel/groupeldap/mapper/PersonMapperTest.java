package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.util.TestUtils;
import fr.cfdt.gasel.schema.v0.ebx.personne.PersonneType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
        PersonneType.Gasel.Personne person = TestUtils.createPersonEbx(1);


    }

}
