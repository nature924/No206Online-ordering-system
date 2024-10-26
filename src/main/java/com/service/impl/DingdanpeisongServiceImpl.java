package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DingdanpeisongDao;
import com.entity.DingdanpeisongEntity;
import com.service.DingdanpeisongService;
import com.entity.vo.DingdanpeisongVO;
import com.entity.view.DingdanpeisongView;

@Service("dingdanpeisongService")
public class DingdanpeisongServiceImpl extends ServiceImpl<DingdanpeisongDao, DingdanpeisongEntity> implements DingdanpeisongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DingdanpeisongEntity> page = this.selectPage(
                new Query<DingdanpeisongEntity>(params).getPage(),
                new EntityWrapper<DingdanpeisongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DingdanpeisongEntity> wrapper) {
		  Page<DingdanpeisongView> page =new Query<DingdanpeisongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DingdanpeisongVO> selectListVO(Wrapper<DingdanpeisongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DingdanpeisongVO selectVO(Wrapper<DingdanpeisongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DingdanpeisongView> selectListView(Wrapper<DingdanpeisongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DingdanpeisongView selectView(Wrapper<DingdanpeisongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
