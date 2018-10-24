package pers.husen.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.dao.CodeLibraryMapper;
import pers.husen.web.service.CodeLibraryService;

import java.util.List;

@Service
public class CodeLibraryServiceImpl implements CodeLibraryService{

    @Autowired(required = false)
    private CodeLibraryMapper codeLibraryMapper;

    @Override
    public List<CodeLibraryVo> queryCodeLibraryPerPage(CodeLibraryVo cVo, int pageSize, int pageNo) {
        cVo.setCodeTitle(".*" + cVo.getCodeTitle() + ".*");
        return codeLibraryMapper.queryCodeLibraryPerPage(cVo.getCodeTitle(), cVo.getCodeCategory(), pageSize, pageSize*pageNo-pageSize);
    }
}
