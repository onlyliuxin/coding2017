package com.coderising.payroll;

import static org.junit.Assert.fail;

import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBetween() {
		Date dateOne = DateUtil.parseDate("2017-6-29");
		Date dateTwo = DateUtil.parseDate("2017-6-30");
		Date dateThree = DateUtil.parseDate("2017-7-1");
		Date dateFour = DateUtil.parseDate("2017-7-2");
		Date dateFive = DateUtil.parseDate("2017-7-3");
		
		Date startDate = DateUtil.parseDate("2017-6-30");
		Date endDate = DateUtil.parseDate("2017-7-2");
		
		Assert.assertEquals(false,DateUtil.between(dateOne, startDate, endDate));
		Assert.assertEquals(true,DateUtil.between(dateTwo, startDate, endDate));
		Assert.assertEquals(true,DateUtil.between(dateThree, startDate, endDate));
		Assert.assertEquals(true,DateUtil.between(dateFour, startDate, endDate));
		Assert.assertEquals(false,DateUtil.between(dateFive, startDate, endDate));
	}

	@Test
	public void testIsFriday() {
		Assert.assertEquals(true,DateUtil.isFriday(DateUtil.parseDate("2017-7-7")));
		Assert.assertEquals(true,DateUtil.isFriday(DateUtil.parseDate("2017-7-14")));
		Assert.assertEquals(false,DateUtil.isFriday(DateUtil.parseDate("2017-7-6")));
		Assert.assertEquals(false,DateUtil.isFriday(DateUtil.parseDate("2017-7-8")));
	}

	@Test
	public void testAdd() {
		Date dateOne = DateUtil.parseDate("2017-7-2");
		Date dateTwo = DateUtil.parseDate("2017-7-30");
		
		Assert.assertEquals("Sun Jul 02 00:00:00 CST 2017", DateUtil.add(dateOne, 0).toString());
		Assert.assertEquals("Tue Jul 04 00:00:00 CST 2017", DateUtil.add(dateOne, 2).toString());
		Assert.assertEquals("Fri Jun 30 00:00:00 CST 2017", DateUtil.add(dateOne, -2).toString());
		
		Assert.assertEquals("Wed Aug 02 00:00:00 CST 2017", DateUtil.add(dateTwo, 3).toString());
		Assert.assertEquals("Sat Jul 29 00:00:00 CST 2017", DateUtil.add(dateTwo, -1).toString());
	}

	@Test
	public void testIsLastDayOfMonth() {
		Assert.assertEquals(true, DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-6-30")));
		Assert.assertEquals(false,DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-6-31")));
		Assert.assertEquals(true, DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-7-31")));
		Assert.assertEquals(false,DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-7-32")));
		Assert.assertEquals(true, DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-12-31")));
		Assert.assertEquals(true, DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-2-28")));
		Assert.assertEquals(false, DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-2-29")));
	}

	@Test
	public void testGetFirstDay() {
		Assert.assertEquals("Sat Jul 01 00:00:00 CST 2017", DateUtil.getFirstDay(DateUtil.parseDate("2017-7-1")).toString());
		Assert.assertEquals("Sat Jul 01 00:00:00 CST 2017", DateUtil.getFirstDay(DateUtil.parseDate("2017-7-15")).toString());
		Assert.assertEquals("Sat Jul 01 00:00:00 CST 2017", DateUtil.getFirstDay(DateUtil.parseDate("2017-7-31")).toString());
		Assert.assertEquals("Tue Aug 01 00:00:00 CST 2017", DateUtil.getFirstDay(DateUtil.parseDate("2017-7-32")).toString());
	}

	@Test
	public void testParseDate() {
		Assert.assertEquals("Sat Jul 01 00:00:00 CST 2017", DateUtil.parseDate("2017-7-1").toString());
		Assert.assertEquals("Sat Jul 01 00:00:00 CST 2017", DateUtil.parseDate("2017-07-01").toString());
		Assert.assertEquals("Mon Jul 03 00:00:00 CST 2017", DateUtil.parseDate("2017-6-33").toString());
		Assert.assertEquals("Tue Aug 01 00:00:00 CST 2017", DateUtil.parseDate("2017-7-32").toString());
		Assert.assertEquals("Mon Jul 31 00:00:00 CST 2017", DateUtil.parseDate("2017-8-0").toString());
	}

	@Test
	public void testGetDaysBetween() {
		Assert.assertEquals(0, DateUtil.getDaysBetween(DateUtil.parseDate("2017-7-1"),DateUtil.parseDate("2017-7-1")));
		Assert.assertEquals(1, DateUtil.getDaysBetween(DateUtil.parseDate("2017-7-1"),DateUtil.parseDate("2017-7-2")));
		Assert.assertEquals(-1, DateUtil.getDaysBetween(DateUtil.parseDate("2017-7-2"),DateUtil.parseDate("2017-7-1")));
		Assert.assertEquals(3, DateUtil.getDaysBetween(DateUtil.parseDate("2017-6-29"),DateUtil.parseDate("2017-7-2")));
	}

	@Test
	public void testFridaysNum() {
		Assert.assertEquals(1, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-7"),DateUtil.parseDate("2017-7-7")));
		Assert.assertEquals(0, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-2"),DateUtil.parseDate("2017-7-3")));
		Assert.assertEquals(0, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-5"),DateUtil.parseDate("2017-7-6")));
		Assert.assertEquals(1, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-6"),DateUtil.parseDate("2017-7-7")));
		Assert.assertEquals(1, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-7"),DateUtil.parseDate("2017-7-8")));
		Assert.assertEquals(0, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-8"),DateUtil.parseDate("2017-7-9")));
		Assert.assertEquals(1, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-6"),DateUtil.parseDate("2017-7-8")));
		Assert.assertEquals(2, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-7"),DateUtil.parseDate("2017-7-14")));
		Assert.assertEquals(2, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-6"),DateUtil.parseDate("2017-7-15")));
		Assert.assertEquals(2, DateUtil.fridaysNum(DateUtil.parseDate("2017-7-8"),DateUtil.parseDate("2017-7-21")));
		Assert.assertEquals(6, DateUtil.fridaysNum(DateUtil.parseDate("2017-6-30"),DateUtil.parseDate("2017-8-4")));
	}

}
