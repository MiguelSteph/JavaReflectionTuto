package com.be.expert.helper;

import static com.be.expert.helper.ReflectionHelper.getInstanceField;
import static com.be.expert.helper.ReflectionHelper.getStaticField;
import static com.be.expert.helper.ReflectionHelper.setInstanceField;
import static com.be.expert.helper.ReflectionHelper.setStaticField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.be.expert.helper.User.Sex;
import org.junit.jupiter.api.Test;

public class ReflectionHelperTest {

    private static final String OLD_USER_NAME = "oldUserName";
    private static final String NEW_USER_NAME = "newUsername";
    private static final String NEW_PREFIX_MESSAGE = "Hi, Welcome to BeExpert, ";

    @Test
    public void givenSetAndGetInstanceField() throws Exception {
        // Arrange
        User user = new User(OLD_USER_NAME, Sex.MALE);

        // Act
        setInstanceField(User.class, user, "sex", Sex.FEMALE);
        setInstanceField(User.class, user, "name", NEW_USER_NAME);

        // Assert
        assertEquals(user.getSex(), Sex.FEMALE);
        assertEquals(user.getName(), NEW_USER_NAME);

        Sex currentSexVal = getInstanceField(User.class, user, "sex", Sex.class);
        String currentUserName = getInstanceField(User.class, user, "name", String.class);

        assertEquals(currentSexVal, Sex.FEMALE);
        assertEquals(currentUserName, NEW_USER_NAME);
    }

    @Test
    public void givenSetAndGetStaticField() throws Exception {
        // Arrange
        User user = new User(OLD_USER_NAME, Sex.MALE);

        // Act
        setStaticField(User.class, "PREFIX_MSG", NEW_PREFIX_MESSAGE);

        // Assert
        assertTrue(user.toString().startsWith(NEW_PREFIX_MESSAGE));
        String currentPrefixMsgval = getStaticField(User.class, "PREFIX_MSG", String.class);
        assertEquals(currentPrefixMsgval, NEW_PREFIX_MESSAGE);
    }

}
