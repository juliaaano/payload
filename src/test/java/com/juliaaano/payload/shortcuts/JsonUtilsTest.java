package com.juliaaano.payload.shortcuts;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class JsonUtilsTest {

    @Test
    public void testToJson() {
        TestObject testObject = new TestObject("TEST");
        Assert.assertEquals("{\"content\":\"TEST\"}", JsonUtils.toJson(testObject));
    }

    @Test
    public void testFromJson() {

        String json = "{\"content\":\"TEST\"}";
        TestObject object = JsonUtils.fromJson(json, TestObject.class);
        Assert.assertEquals("TEST", object.content);
    }

    @Test
    public void testFromJsonInputString() throws FileNotFoundException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("test_object.json");

        TestObject object = JsonUtils.fromJson(in, TestObject.class);
        Assert.assertEquals("TEST", object.content);
    }

    private static class TestObject {
        private String content;

        private TestObject(String content) {
            this.content = content;
        }
    }

}
