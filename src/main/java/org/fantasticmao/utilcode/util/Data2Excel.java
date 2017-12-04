package org.fantasticmao.utilcode.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author maokeluo
 * @desc
 * @create 17-12-1
 */
public class Data2Excel {
    /** 
     * @desc 创建Excel
     * @author maokeluo
     * @methodName createExcel       
     * @param  list
     * @create 17-12-1
     * @return void
     */
    public static void createExcel(List<? extends Object> list,String dirPath) {

        Class<?> aClass = list.get(0).getClass();
        String name = aClass.getSimpleName();
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet(name);
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 设置单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //获取对象属性名称
        Field[] declaredFields = aClass.getDeclaredFields();
        // 添加表头内容
        HSSFCell headCell = null;
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            headCell = hssfRow.createCell(i);
            headCell.setCellValue(declaredFields[i].getName());
            headCell.setCellStyle(cellStyle);
        }
        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow(i + 1);
            try {
                Object o = list.get(i);
                for (int j = 0; j < declaredFields.length; j++) {
                    declaredFields[j].setAccessible(true);
                    HSSFCell cell = hssfRow.createCell(j);
                    cell.setCellValue(declaredFields[j].get(o).toString());
                    cell.setCellStyle(cellStyle);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // 保存Excel文件
        try {
            OutputStream outputStream = new FileOutputStream(new File(dirPath,name+".xls"));
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
