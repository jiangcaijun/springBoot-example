package com.backstage.api.controller;

import com.backstage.util.UUIDUtil;
import com.backstage.base.exception.XPFBadRequestException;
import com.backstage.base.models.PageDo;
import com.backstage.base.models.ZcdcZx;
import com.backstage.base.response.XPFSingleResponse;
import com.backstage.base.service.ISpecialManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(description = "专项管理接口")
@CrossOrigin
@RestController
public class SpecialManagementController {

    @Resource
    private ISpecialManagement iSpecialManagement;

    @ApiOperation(value = "专项管理", notes = "notes", httpMethod = "GET")
    @GetMapping(value = "/specialManagements")
    @ResponseBody
    public Object query(Integer limit, Integer offset) throws XPFBadRequestException {
        PageDo pageDo = new PageDo(limit, offset);
        iSpecialManagement.findDataGrid(pageDo);
        return pageDo;
    }


    /**
     * 该方法测试通过，前端 http://localhost:7500/zeus/specialManagementsById
     * @param id
     * @return
     * @throws XPFBadRequestException
     */
    @ApiOperation(value = "专项管理(单个，用于编辑页面的查询)")
    @GetMapping(value = "/specialManagementsById")
    @ResponseBody
    public Object getById(String id) throws XPFBadRequestException {
        ZcdcZx zcdcZx =  iSpecialManagement.queryById(id);
        return zcdcZx;
    }
    /**
     * 该方法测试无法通过，前端 http://localhost:7500/zeus/speciGETalManagements/247897A0A4154509A41A152DF2437DFF 请求无法过来
     * @param id
     * @return
     * @throws XPFBadRequestException
     */
    @ApiOperation(value = "获取实体")
    @GetMapping(value = "/specialManagements/{id}")
    @ResponseBody
    public Object queryById(@PathVariable(value = "id") String id) throws XPFBadRequestException {
        ZcdcZx zcdcZx =  iSpecialManagement.queryById(id);
        return zcdcZx;
    }

    @ApiOperation(value = "删除专项")
    @DeleteMapping(value = "/specialManagements")
    public Object delete(String idSelections) {
        if (StringUtils.isNotBlank(idSelections)) {
            List<String> idList = Arrays.asList(StringUtils.split(idSelections,","));
            if(iSpecialManagement.deleteByIdList(idList) > 0){
                return new XPFSingleResponse("专项删除成功");
            }
        }
        return new XPFBadRequestException("专项删除失败");
    }

    @ApiOperation(value = "专项结束")
    @PatchMapping(value = "/specialManagements/stop")
    @ResponseBody
    public Object stop(String idSelections) {
        if (StringUtils.isNotBlank(idSelections)) {
            List<String> idList = Arrays.asList(StringUtils.split(idSelections,","));
            if(iSpecialManagement.stopZX(idList) > 0){
                return new XPFSingleResponse("专项结束成功");
            }
        }
        return new XPFBadRequestException("专项结束失败");
    }


    @ApiOperation(value = "获取专项新增的编号")
    @GetMapping(value = "/specialManagements/zxCode")
    @ResponseBody
    public Object queryZxCode() throws XPFBadRequestException {
        return new XPFSingleResponse(iSpecialManagement.createZxCode());
    }

    @ApiOperation(value = "新增专项")
    @PostMapping(value = "/specialManagements")
    @ResponseBody
    public Object add( @RequestBody ZcdcZx zcdcZx) throws XPFBadRequestException {
        if (zcdcZx != null) {
            zcdcZx.setZxStatus("1");
            if(StringUtils.isBlank(zcdcZx.getZxName())){
                return new XPFBadRequestException("专项新增失败,名称不可为空");
            }
            if(!zcdcZx.getZxCode().equals(iSpecialManagement.createZxCode())){
                return new XPFBadRequestException("专项新增失败,当前专项编号不是最新的，请刷新页面后重试");
            }
            if((zcdcZx.getStartTime() != null) && (zcdcZx.getEndTime() != null) && (zcdcZx.getEndTime().getTime() < zcdcZx.getStartTime().getTime())){
                return new XPFBadRequestException("专项新增失败,结束时间小于开始时间");
            }
            zcdcZx.setZxUuid(UUIDUtil.getUUID());
            zcdcZx.setCreateTime(new Date());
            if(iSpecialManagement.insert(zcdcZx) > 0){
                return new XPFSingleResponse("专项新增成功");
            }
        }
        return new XPFBadRequestException("专项新增失败");
    }


    @ApiOperation(value = "编辑专项")
    @PatchMapping(value = "/specialManagements")
    @ResponseBody
    public Object edit( @RequestBody ZcdcZx zcdcZx) throws XPFBadRequestException {
        if (zcdcZx != null) {
            zcdcZx.setUpdateTime(new Date());
            if(iSpecialManagement.update(zcdcZx) > 0){
                return new XPFSingleResponse("专项编辑成功");
            }
        }
        return new XPFBadRequestException("专项编辑失败");
    }
}