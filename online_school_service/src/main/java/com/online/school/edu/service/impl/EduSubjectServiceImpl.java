package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.entity.EduSubject;
import com.online.school.edu.entity.response.OneSubjectDtoResponse;
import com.online.school.edu.entity.response.TwoSubjectDtoResponse;
import com.online.school.edu.mapper.EduSubjectMapper;
import com.online.school.edu.service.EduSubjectService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-07
 */
@Service("subjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Resource
    private EduSubjectMapper subjectMapper;

    private static final Logger logger = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    /**
     * 功能描述 : 读取excel文件内容
     *
     * @param file : 文件
     * @return
     * @author 王威
     * @created 2020-01-07 13:59
     */
    @Override
    public List<String> importExcel(MultipartFile file) {
        logger.info("--开始读取excel文件--");
        List<String> list = new ArrayList<>();
        try {
            InputStream in = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    logger.info("--excel读取行数 row:{}时数据为空--", i);
                    continue;
                }
                if (null != row.getCell(0) && null != row.getCell(1)) {
                    EduSubject subjectOne = existSubject(row.getCell(0).getStringCellValue(), "0");
                    if (subjectOne == null) {
                        String title = row.getCell(0).getStringCellValue();
                        EduSubject subject = new EduSubject();
                        subject.setTitle(title);
                        subject.setParentId("0");
                        subject.setSort(0);
                        baseMapper.insert(subject);
                        subjectOne = existSubject(title, "0");
                    }
                    if (existSubject(row.getCell(1).getStringCellValue(), subjectOne.getId()) == null) {
                        EduSubject subjectTwo = new EduSubject();
                        subjectTwo.setParentId(subjectOne.getId());
                        subjectTwo.setTitle(row.getCell(1).getStringCellValue());
                        subjectTwo.setSort(0);
                        baseMapper.insert(subjectTwo);
                    }
                } else {
                    logger.info(file.getName() + "第" + row.getRowNum() + "数据有空值无法导入");
                    list.add("第" + row.getRowNum() + "数据导入失败");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 功能描述 : 获取指定二级分类课程
     *
     * @param name : 课程名
     * @param parentId : 父分类id
     * @return
     * @author 王威
     * @created 2020-01-08 16:18
     */
    private EduSubject existSubject(String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 功能描述 : 获取课程分级树状结构信息
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-08 16:14
     */
    @Override
    public List<OneSubjectDtoResponse> getAllSubjectDto() {
        List<OneSubjectDtoResponse> list = new ArrayList<>();
        this.getOneSubject().forEach(eduSubject -> {
            OneSubjectDtoResponse oneSubjectDtoResponse = new OneSubjectDtoResponse();
            oneSubjectDtoResponse.setId(eduSubject.getId());
            oneSubjectDtoResponse.setTitle(eduSubject.getTitle());
            oneSubjectDtoResponse.setChildren(getTwoSubject(eduSubject.getId()));
            list.add(oneSubjectDtoResponse);
        });
        return list;
    }

    /**
     * 功能描述 : 获取全部一级分类
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-08 16:16
     */
    private List<EduSubject> getOneSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        return subjectMapper.selectList(wrapper);
    }

    /**
     * 功能描述 : 获取全部二级分类
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-08 16:16
     */
    private List<TwoSubjectDtoResponse> getTwoSubject(String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<TwoSubjectDtoResponse> list = new ArrayList<>();
        baseMapper.selectList(wrapper).forEach(eduSubject -> {
            TwoSubjectDtoResponse twoSubjectDtoResponse = new TwoSubjectDtoResponse();
            twoSubjectDtoResponse.setId(eduSubject.getId());
            twoSubjectDtoResponse.setTitle(eduSubject.getTitle());
            list.add(twoSubjectDtoResponse);
        });
        return list;
    }


    @Override
    public Boolean deleteSubjectDto(String id) {
        if(this.getTwoSubject(id).size()>0){
            return false;
        }
        return this.removeById(id);
    }

    @Override
    public Boolean addSubject(EduSubject subject) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",subject.getTitle());
        if(subject.getParentId().equals("")){
            subject.setParentId("0");
            wrapper.eq("parent_id","0");
        }else {
            wrapper.eq("parent_id", subject.getParentId());
        }
        if(baseMapper.selectOne(wrapper)!=null){
            return false;
        }
        return this.save(subject);
    }
}
