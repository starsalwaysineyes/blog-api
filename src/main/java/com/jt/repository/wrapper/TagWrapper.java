package com.jt.repository.wrapper;


import java.util.List;
import com.jt.vo.TagVO;

public interface TagWrapper {

    List<TagVO> findAllDetail();

    TagVO getTagDetail(Integer tagId);


}
