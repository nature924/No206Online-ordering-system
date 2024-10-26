package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DingdanpeisongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DingdanpeisongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DingdanpeisongView;


/**
 * 订单配送
 *
 * @author 
 * @email 
 * @date 2021-04-01 20:09:02
 */
public interface DingdanpeisongService extends IService<DingdanpeisongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DingdanpeisongVO> selectListVO(Wrapper<DingdanpeisongEntity> wrapper);
   	
   	DingdanpeisongVO selectVO(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
   	
   	List<DingdanpeisongView> selectListView(Wrapper<DingdanpeisongEntity> wrapper);
   	
   	DingdanpeisongView selectView(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DingdanpeisongEntity> wrapper);
   	
}

