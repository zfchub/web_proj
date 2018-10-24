package pers.husen.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pers.husen.web.bean.vo.CodeLibraryVo;

import java.util.List;

@Component
public interface CodeLibraryMapper {

    List<CodeLibraryVo> queryCodeLibraryPerPage(@Param("codeTitle") String codeTitle, @Param("codeCategory") Integer codeCategory,
            @Param("limit") Integer limit, @Param("offset") Integer offset);
}
