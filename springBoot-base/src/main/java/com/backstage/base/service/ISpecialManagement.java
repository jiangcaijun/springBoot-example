package com.backstage.base.service;

import com.backstage.base.models.ZcdcZx;
import com.backstage.base.models.PageDo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ISpecialManagement {

	/**
	 * 插入一个专项
	 * 
	 * @param zcdcZx
	 * @return
	 */
	int insert(ZcdcZx zcdcZx);

	/**
	 * 更新一个专项
	 * @param zcdcZx
	 * @return
	 */
	int update(ZcdcZx zcdcZx);

	int deleteByIdList(List<String> idList);

	ZcdcZx queryById(String id);

	void findDataGrid(PageDo pageDo);

	/**
	 * 生成编号，eg:20170922-001,默认三位，最高999，超过则从1000后往后累加
	 * @return
	 */
	String createZxCode();

	/**
	 * 结束专项
	 * @param idList
	 * @return
	 */
	int stopZX(List<String> idList);

	/**
	 * 测试事物
	 */
	void testTransactionManagement();
}
