package fr.cfdt.gasel.groupeldap.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * TechnicalException Tester.
 *
 */
public class TechnicalExceptionTest {
    private static final String MESSAGE = "test exception";

    @Test
    public void testTechnicalException() {
        TechnicalException exception = new TechnicalException(MESSAGE);
        assertNotNull(exception);
        assertEquals(MESSAGE, exception.getMessage());

        TechnicalException exception2 = new TechnicalException(MESSAGE, new Throwable(MESSAGE));
        assertNotNull(exception2);
        assertEquals(MESSAGE, exception2.getMessage());
    }
}
