package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.entity.EduSubject;
import com.online.school.edu.mapper.SubjectMapper;
import com.online.school.edu.service.EduSubjectService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class EduSubjectServiceImpl extends ServiceImpl<SubjectMapper, EduSubject> implements EduSubjectService {

    private static final Logger logger = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Override
    public List<String> importExcel(MultipartFile file) {
        logger.info("--开始读取excel文件--");
        List<String> list = new ArrayList<>();
        try{
            InputStream in = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <=lastRowNum ; i++) {
                Row row = sheet.getRow(i);
                if(row==null){
                    logger.info("--excel读取行数 row:{}时数据为空--",i);
                    continue;
                }
                if(null!=row.getCell(0)&&null!=row.getCell(1)){
                    EduSubject subjectOne = existSubject(row.getCell(0).getStringCellValue(),"0");
                    if(subjectOne==null){
                        String title = row.getCell(0).getStringCellValue();
                        EduSubject subject = new EduSubject();
                        subject.setTitle(title);
                        subject.setParentId("0");
                        subject.setSort(0);
                        baseMapper.insert(subject);
                        subjectOne = existSubject(title,"0");
                    }
                    if(existSubject(row.getCell(1).getStringCellValue(),subjectOne.getId())==null) {
                        EduSubject subjectTwo = new EduSubject();
                        subjectTwo.setParentId(subjectOne.getId());
                        subjectTwo.setTitle(row.getCell(1).getStringCellValue());
                        subjectTwo.setSort(0);
                        baseMapper.insert(subjectTwo);
                    }
                }else {
                    logger.info(file.getName()+"第"+row.getRowNum()+"数据有空值无法导入");
                    list.add("第"+row.getRowNum()+"数据导入失败");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private EduSubject existSubject(String name, String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);
        return baseMapper.selectOne(wrapper);
    }

}
