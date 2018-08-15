package com.hanlet.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hanlet.biz.entity.QuoteDetail;


public class ExportExcelUtil {

	/**
	 * 将用户的信息导入到excel文件中去
	 * 
	 * @param userList
	 *            用户列表
	 * @param out
	 *            输出表
	 */
	public static void exportUserExcel(List<QuoteDetail> detail, ServletOutputStream out) {
		try {
			// 1.创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1创建合并单元格对象
			CellRangeAddress quoteTitle = new CellRangeAddress(0, 0, 0, 6);// 起始行,结束行,起始列,结束列
			CellRangeAddress productreportPriceTitle = new CellRangeAddress(1, 1, 0, 0);
			CellRangeAddress productreportPrice = new CellRangeAddress(1, 1, 1, 3);
			CellRangeAddress productPriceTitle = new CellRangeAddress(1, 1, 4, 4);
			CellRangeAddress productPrice = new CellRangeAddress(1, 1, 5, 6);
			CellRangeAddress clienteleNameTitle = new CellRangeAddress(2, 2, 0, 0);
			CellRangeAddress clienteleName = new CellRangeAddress(2, 2, 1, 3);
			CellRangeAddress clienteleAddressTitle = new CellRangeAddress(2, 2, 4, 4);
			CellRangeAddress clienteleAddress = new CellRangeAddress(2, 2, 5, 6);
			
			CellRangeAddress productTitle = new CellRangeAddress(3, 3, 0, 7);
			
			// 1.2头标题样式
			HSSFCellStyle headStyle = createCellStyle(workbook, (short) 16);
			// 1.3列标题样式
			HSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
			// 2.创建工作表
			HSSFSheet sheet = workbook.createSheet("报价单");
			
			// 2.1加载合并单元格对象
			sheet.addMergedRegion(quoteTitle);
			sheet.addMergedRegion(productreportPriceTitle);
			sheet.addMergedRegion(productreportPrice);
			sheet.addMergedRegion(productPriceTitle);
			sheet.addMergedRegion(productPrice);
			sheet.addMergedRegion(clienteleNameTitle);
			sheet.addMergedRegion(clienteleName);
			sheet.addMergedRegion(clienteleAddressTitle);
			sheet.addMergedRegion(clienteleAddress);
			sheet.addMergedRegion(productTitle);
			
			
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 3.创建行
			// 3.1创建头标题行;并且设置头标题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			
			HSSFRow row1 = sheet.createRow(1);
			HSSFCell cell10 = row1.createCell(0);
			HSSFCell cell11 = row1.createCell(1);
			HSSFCell cell12 = row1.createCell(4);
			HSSFCell cell13 = row1.createCell(5);
			
			HSSFRow row2 = sheet.createRow(2);
			HSSFCell cell20 = row2.createCell(0);
			HSSFCell cell21 = row2.createCell(1);
			HSSFCell cell22 = row2.createCell(4);
			HSSFCell cell23 = row2.createCell(5);
			
			HSSFRow row3 = sheet.createRow(3);
			HSSFCell cell31 = row3.createCell(0);
			
			// 加载单元格样式
			cell.setCellStyle(headStyle);
			cell.setCellValue(detail.get(0).getQuote().getQuoteName());
			
			cell10.setCellStyle(headStyle);
			cell10.setCellValue("产品报价：");
			cell11.setCellStyle(headStyle);
			cell11.setCellValue(detail.get(0).getQuote().getReportPrice().doubleValue());
			cell12.setCellStyle(headStyle);
			cell12.setCellValue("产品售价：");
			cell13.setCellStyle(headStyle);
			cell13.setCellValue(detail.get(0).getQuote().getProductPrice().doubleValue());
			
			cell20.setCellStyle(headStyle);
			cell20.setCellValue("客户名称：");
			cell21.setCellStyle(headStyle);
			cell21.setCellValue(detail.get(0).getQuote().getClientele().getClienteleName());
			cell22.setCellStyle(headStyle);
			cell22.setCellValue("客户地址：");
			cell23.setCellStyle(headStyle);
			cell23.setCellValue(detail.get(0).getQuote().getClientele().getClienteleAddress());
			
			// 加载单元格样式
			cell31.setCellStyle(headStyle);
			cell31.setCellValue("产品列表：");
			

			// 3.2创建列标题;并且设置列标题
			HSSFRow row4 = sheet.createRow(4);
			String[] titles = { "产品名称", "制造厂商", "产品售价(元)", "产品报价(元)", "数量","产品总售价(元)", "产品总报价(元)"};
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = row4.createCell(i);
				// 加载单元格样式
				cell2.setCellStyle(colStyle);
				cell2.setCellValue(titles[i]);
			}

			// 4.操作单元格;将用户列表写入excel
			if (detail != null) {
				for (int j = 0; j < detail.size(); j++) {
					HSSFCellStyle style = workbook.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
					// 创建数据行,前面有两行,头标题行和列标题行
					HSSFRow row5 = sheet.createRow(j + 5);
					HSSFCell cell1 = row5.createCell(0);
					cell1.setCellStyle(style);
					cell1.setCellValue(detail.get(j).getProduct().getProductName());
					HSSFCell cell2 = row5.createCell(1);
					cell2.setCellStyle(style);
					cell2.setCellValue(detail.get(j).getProduct().getManufactor());
					HSSFCell cell3 = row5.createCell(2);
					cell3.setCellStyle(style);
					cell3.setCellValue(detail.get(j).getProduct().getProductPrice().doubleValue());
					HSSFCell cell4 = row5.createCell(3);
					cell4.setCellStyle(style);
					cell4.setCellValue(detail.get(j).getProduct().getProductReportPrice().doubleValue());
					HSSFCell cell5 = row5.createCell(4);
					cell5.setCellStyle(style);
					cell5.setCellValue(detail.get(j).getProductCount());
					HSSFCell cell6 = row5.createCell(5);
					cell6.setCellStyle(style);
					cell6.setCellValue(detail.get(j).getPrice().doubleValue());
					HSSFCell cell7 = row5.createCell(6);
					cell7.setCellStyle(style);
					cell7.setCellValue(detail.get(j).getReportPrice().doubleValue());
				}
			}
			// 5.输出
			workbook.write(out);
//			workbook.close();
			 out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param workbook
	 * @param fontsize
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize) {
		// TODO Auto-generated method stub
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontsize);
		// 加载字体
		style.setFont(font);
		return style;
	}

}
