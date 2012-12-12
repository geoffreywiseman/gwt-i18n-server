package com.teklabs.gwt.i18n.server;

import com.teklabs.gwt.i18n.client.LocaleFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author Vladimir Kulev
 */
public class MessagesProxyTest {
    private TestMessages getMessages() {
        return LocaleFactory.get(TestMessages.class);
    }
    
    @BeforeClass
    public static void init() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void simple() {
        MessagesProxy.setLocale(new Locale("fi", "FI", "var"));
        Assert.assertEquals("Simple текст", getMessages().simple());
    }

    @Test
    public void lookup() {
        MessagesProxy.setLocale(new Locale("fi", "FI", "var"));
        Assert.assertEquals("Simple текст", getMessages().getString("simple"));
    }

    @Test
    public void plural() {
        MessagesProxy.setLocale(new Locale("en"));
        Assert.assertEquals("No apples", getMessages().apples(0));
        Assert.assertEquals("An 1 apple", getMessages().apples(1));
        Assert.assertEquals("2 apples", getMessages().apples(2));
    }

    @Test
    public void select() {
        MessagesProxy.setLocale(new Locale("en"));
        Assert.assertEquals("One 0 selected", getMessages().select(1, "select one"));
        Assert.assertEquals("Two selected", getMessages().select(2, "select two"));
        Assert.assertEquals("Not two selected", getMessages().select(2, "not select"));
        Assert.assertEquals("Three selected localized", getMessages().select(3, "select three"));
        Assert.assertEquals("Four selected", getMessages().select(4, "select four"));
    }

    @Test
    public void dates() {
        MessagesProxy.setLocale(new Locale("en_US"));
        Calendar cal = Calendar.getInstance();
        cal.set( 1970, Calendar.JANUARY, 1, 0, 0, 0 );
        Assert.assertEquals("Today is January 1, 1970", getMessages().today(cal.getTime()));
    }

    @Test
    public void inheritance() {
        MessagesProxy.setLocale(new Locale("fi"));
        Assert.assertEquals("Perintö 1", getMessages().inheritance1());
        Assert.assertEquals("Perintö 2", getMessages().inheritance2());
        Assert.assertEquals("Inheritance 3", getMessages().inheritance3());
    }
}
