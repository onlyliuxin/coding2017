package com.coderising.ood.srp;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class PromotionJobTest {

	@Test
	public void testRun() throws IOException {
		
		Integer i1=4;
		Integer i2=4;
		Integer i3=400;
		Integer i4=400;
		
		boolean b1=(i1==i2);
		boolean b2=(i3==i4);
		
		System.out.println(b1);
		System.out.println(b2);
		
		PromotionJob pJob = new PromotionJob(new ProductServer(), new UserServer());
		pJob.run();
	}

}
