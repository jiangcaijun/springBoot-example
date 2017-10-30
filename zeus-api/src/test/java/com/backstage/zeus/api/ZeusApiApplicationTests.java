package com.backstage.zeus.api;

import com.backstage.zeus.base.models.PageDo;
import com.backstage.zeus.base.service.ISpecialManagement;
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

	@Test
	public void query() {
		PageDo pageDo = new PageDo(10, 0);
		iSpecialManagement.findDataGrid(pageDo);
		System.out.println("获取到的数据条数" + pageDo.getTotal());
		System.out.println(pageDo.getRows());
	}
}