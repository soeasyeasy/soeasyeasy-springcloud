// DocumentVersionMapper.java
package com.soeasyeasy.ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soeasyeasy.ai.model.DocumentVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hc
 */
@Mapper
public interface DocumentVersionMapper extends BaseMapper<DocumentVersion> {

    @Select("SELECT * FROM document_version WHERE file_id = #{fileId} ORDER BY operated_at DESC")
    List<DocumentVersion> selectByFileId(String fileId);
}