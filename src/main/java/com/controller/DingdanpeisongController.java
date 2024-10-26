package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DingdanpeisongEntity;
import com.entity.view.DingdanpeisongView;

import com.service.DingdanpeisongService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 订单配送
 * 后端接口
 * @author 
 * @email 
 * @date 2021-04-01 20:09:02
 */
@RestController
@RequestMapping("/dingdanpeisong")
public class DingdanpeisongController {
    @Autowired
    private DingdanpeisongService dingdanpeisongService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DingdanpeisongEntity dingdanpeisong,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			dingdanpeisong.setHuiyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();
		PageUtils page = dingdanpeisongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanpeisong), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DingdanpeisongEntity dingdanpeisong, HttpServletRequest request){
        EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();
		PageUtils page = dingdanpeisongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanpeisong), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DingdanpeisongEntity dingdanpeisong){
       	EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dingdanpeisong, "dingdanpeisong")); 
        return R.ok().put("data", dingdanpeisongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DingdanpeisongEntity dingdanpeisong){
        EntityWrapper< DingdanpeisongEntity> ew = new EntityWrapper< DingdanpeisongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dingdanpeisong, "dingdanpeisong")); 
		DingdanpeisongView dingdanpeisongView =  dingdanpeisongService.selectView(ew);
		return R.ok("查询订单配送成功").put("data", dingdanpeisongView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DingdanpeisongEntity dingdanpeisong = dingdanpeisongService.selectById(id);
        return R.ok().put("data", dingdanpeisong);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DingdanpeisongEntity dingdanpeisong = dingdanpeisongService.selectById(id);
        return R.ok().put("data", dingdanpeisong);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DingdanpeisongEntity dingdanpeisong, HttpServletRequest request){
    	dingdanpeisong.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dingdanpeisong);
        dingdanpeisongService.insert(dingdanpeisong);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DingdanpeisongEntity dingdanpeisong, HttpServletRequest request){
    	dingdanpeisong.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dingdanpeisong);
        dingdanpeisongService.insert(dingdanpeisong);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DingdanpeisongEntity dingdanpeisong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dingdanpeisong);
        dingdanpeisongService.updateById(dingdanpeisong);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dingdanpeisongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<DingdanpeisongEntity> wrapper = new EntityWrapper<DingdanpeisongEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			wrapper.eq("huiyuanzhanghao", (String)request.getSession().getAttribute("username"));
		}

		int count = dingdanpeisongService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	


}
