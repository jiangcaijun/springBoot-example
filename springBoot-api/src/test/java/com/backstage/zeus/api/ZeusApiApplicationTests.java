package com.backstage.zeus.api;

import com.backstage.zeus.base.models.PageDo;
import com.backstage.zeus.base.models.ZcdcZx;
import com.backstage.zeus.base.service.ISpecialManagement;
import com.backstage.zeus.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZeusApiApplicationTests {

	@Resource
	private ISpecialManagement iSpecialManagement;

	/**
	 * 测试事物
	 */
	@Test
	public void queryTransactionManagement () {
		ZcdcZx zcdcZx = new ZcdcZx();
		zcdcZx.setZxUuid("A");
		iSpecialManagement.insert(zcdcZx);
		if(true){
			throw new RuntimeException();
		}
		zcdcZx.setZxUuid("B");
		iSpecialManagement.testTransactionManagement();
	}


	@Test
	public void queryZcdc() {
		PageDo pageDo = new PageDo(10, 0);
		iSpecialManagement.findDataGrid(pageDo);
		System.out.println("获取到的数据条数" + pageDo.getTotal());
		System.out.println(pageDo.getRows());
	}

}