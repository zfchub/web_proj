package pers.husen.web.service;

import org.springframework.stereotype.Service;
import pers.husen.web.bean.vo.CodeLibraryVo;

import java.util.List;

public interface CodeLibraryService {

    /**
     * 根据条件查询某一页的代码库
     * @param cVo
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<CodeLibraryVo> queryCodeLibraryPerPage(CodeLibraryVo cVo, int pageSize, int pageNo);
}
