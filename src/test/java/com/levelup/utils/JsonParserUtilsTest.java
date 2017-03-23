package com.levelup.utils;

import org.junit.Assert;
import org.junit.Test;

import com.levelup.persist.model.Sequence;

/**
 * Unit test for Json Parser Utility class
 */
public class JsonParserUtilsTest {

	@Test
	public void testConvertFromJavaToJson() {
		Sequence sequence = new Sequence();
		sequence.setId("carId");
		sequence.setSeq(0);

		String expectedString = "{\"id\":\"carId\",\"seq\":0}";
		Assert.assertEquals(expectedString, JsonParserUtils.convertObjectToJson(sequence));
	}

	@Test
	public void testConvertJsonToJava() {
		String jsonString = "{\"id\":\"userId\",\"seq\":88}";
		Sequence sequence = JsonParserUtils.convertJsonToObject(jsonString, Sequence.class);

		Assert.assertEquals("userId", sequence.getId());
		Assert.assertEquals(88, sequence.getSeq());
	}

	@Test
	public void testInvalidInputs() {
		String result = JsonParserUtils.convertObjectToJson(null);
		Assert.assertEquals(null, result);

		Sequence sequence = JsonParserUtils.convertJsonToObject(null, Sequence.class);
		Assert.assertEquals(null, sequence);
	}
}
