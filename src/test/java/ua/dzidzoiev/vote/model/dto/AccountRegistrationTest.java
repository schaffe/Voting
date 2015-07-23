package ua.dzidzoiev.vote.model.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Created by midnight coder on 21-Jul-15.
 */
public class AccountRegistrationTest {

    private static Validator validator;
    private static AccountRegistration target;


    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        target = new AccountRegistration();
        target.setFirstName("John");
        target.setSurname("Doe");
        target.setPersonalId("LL4525AA");
        target.setRegionCode("AA");
    }

    @Test
    public void testRegionCode() {
        target.setRegionCode("AAA");

        Set<ConstraintViolation<AccountRegistration>> constraintViolations =
                validator.validateProperty( target , "regionCode");

        System.out.println(target.getRegionCode());
        System.out.println(Arrays.toString(constraintViolations.toArray()));

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }

}