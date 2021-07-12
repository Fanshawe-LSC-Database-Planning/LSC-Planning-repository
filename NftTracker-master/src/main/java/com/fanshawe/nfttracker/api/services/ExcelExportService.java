package com.fanshawe.nfttracker.api.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.entities.CourseInfo;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.ProfessorStatus;
import com.fanshawe.nfttracker.api.entities.TermBlock;
import com.fanshawe.nfttracker.api.entities.TermSheet;
import com.fanshawe.nfttracker.api.repositories.ProfessorRepository;

@Service
public class ExcelExportService {

	@Autowired
	ProfessorRepository professorRepository;

	public ByteArrayInputStream exportFile(TermSheet termSheet) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// Blank workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		Sheet sheet = workbook.createSheet("Termsheet Data");
		// sheet.setDisplayGridlines(false);

		int rownum = 2;
		// For first line of the termblock
		CellStyle headerTermBlockStyle = workbook.createCellStyle();
		headerTermBlockStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerTermBlockStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerTermBlockStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerTermBlockStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);

		Font headerfont = workbook.createFont();
		headerfont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerfont.setFontName("Arial");
		headerfont.setFontHeight((short) 200);
		headerfont.setItalic(true);
		headerTermBlockStyle.setFont(headerfont);

		// For second line of the termblock
		CellStyle secondLineStyle = workbook.createCellStyle();
		secondLineStyle.setAlignment(CellStyle.ALIGN_CENTER);
		secondLineStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		secondLineStyle.setFont(headerfont);

		Font textFont = workbook.createFont();
		textFont.setFontName("Arial");
		textFont.setFontHeight((short) 180);
		CellStyle c1 = workbook.createCellStyle();
		c1.setWrapText(false);
		c1.setAlignment(CellStyle.ALIGN_GENERAL);
		c1.setVerticalAlignment(CellStyle.ALIGN_LEFT);
		c1.setFont(textFont);

		// blocked status cell styles
		CellStyle potentialStatus = workbook.createCellStyle();
		potentialStatus.setWrapText(false);
		potentialStatus.setAlignment(CellStyle.ALIGN_CENTER);
		potentialStatus.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		potentialStatus.setFont(textFont);
		potentialStatus.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		potentialStatus.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		potentialStatus.setFont(textFont);

		// LOI sent status
		CellStyle loiSentStatus = workbook.createCellStyle();
		loiSentStatus.setWrapText(false);
		loiSentStatus.setAlignment(CellStyle.ALIGN_CENTER);
		loiSentStatus.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		loiSentStatus.setFont(textFont);
		loiSentStatus.setFillForegroundColor(HSSFColor.RED.index);
		loiSentStatus.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		loiSentStatus.setFont(textFont);

		// Teaching notification sent status
		CellStyle teachingNotificationSentStatus = workbook.createCellStyle();
		teachingNotificationSentStatus.setWrapText(false);
		teachingNotificationSentStatus.setAlignment(CellStyle.ALIGN_CENTER);
		teachingNotificationSentStatus.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		teachingNotificationSentStatus.setFont(textFont);
		teachingNotificationSentStatus.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		teachingNotificationSentStatus.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		teachingNotificationSentStatus.setFont(textFont);

		// contract notification sent status
		CellStyle contractSentStatus = workbook.createCellStyle();
		contractSentStatus.setWrapText(false);
		contractSentStatus.setAlignment(CellStyle.ALIGN_CENTER);
		contractSentStatus.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		contractSentStatus.setFont(textFont);
		contractSentStatus.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		contractSentStatus.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contractSentStatus.setFont(textFont);

		// contract notification sent to HR status
		CellStyle contractSentToHRStatus = workbook.createCellStyle();
		contractSentToHRStatus.setWrapText(false);
		contractSentToHRStatus.setAlignment(CellStyle.ALIGN_CENTER);
		contractSentToHRStatus.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		contractSentToHRStatus.setFont(textFont);
		contractSentToHRStatus.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		contractSentToHRStatus.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contractSentToHRStatus.setFont(textFont);

		CellStyle c2 = workbook.createCellStyle();
		c2.setWrapText(false);
		c2.setAlignment(CellStyle.ALIGN_CENTER);
		c2.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		c2.setFont(textFont);

		Row statusRow = sheet.createRow(0);
		Cell statusCell1 = (Cell) statusRow.createCell((short) 2);
		statusCell1.setCellStyle(potentialStatus);
		statusCell1.setCellValue("Blocked");

		Cell statusCell2 = (Cell) statusRow.createCell((short) 3);
		statusCell2.setCellStyle(loiSentStatus);
		statusCell2.setCellValue("newHire");

		Cell statusCell3 = (Cell) statusRow.createCell((short) 4);
		statusCell3.setCellStyle(teachingNotificationSentStatus);
		statusCell3.setCellValue("teachingNotification");

		Cell statusCell4 = (Cell) statusRow.createCell((short) 5);
		statusCell4.setCellStyle(contractSentStatus);
		statusCell4.setCellValue("contractSentToFaculty");

		Cell statusCell5 = (Cell) statusRow.createCell((short) 6);
		statusCell5.setCellStyle(contractSentToHRStatus);
		statusCell5.setCellValue("contractSentToHR");

		for (TermBlock termBlock : termSheet.getListOfTermBlocks()) {

			Row row = sheet.createRow(rownum);
			Cell cell = (Cell) row.createCell((short) 0);

			cell.setCellStyle(headerTermBlockStyle);
			CellRangeAddress mergedCell = new CellRangeAddress(rownum, rownum, 0, 3);
			RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCell, sheet, workbook);
			RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCell, sheet, workbook);
			RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCell, sheet, workbook);
			RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, mergedCell, sheet, workbook);

			sheet.addMergedRegion(mergedCell);
			cell.setCellValue(termBlock.getProgramName() + "-" + termBlock.getLevelName());

			Cell cell1 = row.createCell((short) 6);
			cell1.setCellStyle(headerTermBlockStyle);
			cell1.setCellValue(termBlock.getTotalWeeks() + "-WEEK COURSES");
			// cell1.setCellStyle(style);

			Cell cell2 = row.createCell((short) 7);
			cell2.setCellStyle(headerTermBlockStyle);
			cell2.setCellValue(termBlock.getTermStartDate() + " to " + termBlock.getTermEndDate());
			++rownum;

			// Adding column names
			String[] columnNames = new String[] { "Course Name", "FACS #", "Sec #", "Program", "Max", "Hrs.", "Name",
					"Scheduling Request", "status" };
			Row row2 = sheet.createRow(rownum);
			for (int i = 0; i < columnNames.length; i++) {
				Cell secondLineCell = row2.createCell((short) i);
				secondLineCell.setCellStyle(secondLineStyle);
				secondLineCell.setCellValue(columnNames[i].toString());
			}
			++rownum;
			for (CourseInfo courseInfo : termBlock.getListOfCourses()) {

				if (courseInfo.getAssignedProfessors().size() > 1) {

					for (int i = 0; i < courseInfo.getAssignedProfessors().size(); i++) {
						Row row3 = sheet.createRow(rownum);
						if (i == 0) {
							CellStyle mergedCellStyle = workbook.createCellStyle();
							mergedCellStyle.setWrapText(false);
							mergedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
							mergedCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
							mergedCellStyle.setFont(textFont);
							Cell courseCell1 = (Cell) row3.createCell((short) 0);
							courseCell1.setCellStyle(mergedCellStyle);
							CellRangeAddress mergedDataCell1 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 0, 0);
							sheet.addMergedRegion(mergedDataCell1);
							courseCell1.setCellValue(courseInfo.getCourseName());

							Cell courseCell2 = (Cell) row3.createCell((short) 1);
							CellRangeAddress mergedDataCell2 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 1, 1);
							sheet.addMergedRegion(mergedDataCell2);
							courseCell2.setCellStyle(mergedCellStyle);
							courseCell2.setCellValue(courseInfo.getCourseCode());

							Cell courseCell3 = (Cell) row3.createCell((short) 2);
							CellRangeAddress mergedDataCell3 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 2, 2);
							sheet.addMergedRegion(mergedDataCell3);
							courseCell3.setCellStyle(mergedCellStyle);
							courseCell3.setCellValue(courseInfo.getSectionName());

							Cell courseCell4 = (Cell) row3.createCell((short) 3);
							CellRangeAddress mergedDataCell4 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 3, 3);
							sheet.addMergedRegion(mergedDataCell4);
							courseCell4.setCellStyle(mergedCellStyle);
							courseCell4.setCellValue(courseInfo.getProgramBlock());

							Cell courseCell5 = (Cell) row3.createCell((short) 4);
							CellRangeAddress mergedDataCell5 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 4, 4);
							sheet.addMergedRegion(mergedDataCell5);
							courseCell5.setCellStyle(mergedCellStyle);
							courseCell5.setCellValue(courseInfo.getNumberOfStudents() != null
									? courseInfo.getNumberOfStudents().toString()
									: "");

							Cell courseCell6 = (Cell) row3.createCell((short) 5);
							CellRangeAddress mergedDataCell6 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 5, 5);
							sheet.addMergedRegion(mergedDataCell6);
							courseCell6.setCellStyle(mergedCellStyle);
							courseCell6.setCellValue(
									courseInfo.getCourseCredit() != null ? courseInfo.getCourseCredit().toString()
											: "");

							Cell courseCell8 = (Cell) row3.createCell((short) 7);
							CellRangeAddress mergedDataCell8 = new CellRangeAddress(rownum,
									rownum + courseInfo.getAssignedProfessors().size() - 1, 7, 7);
							sheet.addMergedRegion(mergedDataCell8);
							courseCell8.setCellStyle(mergedCellStyle);
							courseCell8.setCellValue(courseInfo.getScheduledRequest());
						}

						Cell courseCell7 = (Cell) row3.createCell((short) 6);
						courseCell7.setCellStyle(c2);

						Professor professor = professorRepository.findById(courseInfo.getAssignedProfessors().get(i))
								.get();
						String professors = professor.getFirstName() + " " + professor.getLastName() + ", ";
						courseCell7.setCellValue(
								professors.equals("") ? "" : professors.substring(0, professors.length() - 2));

						Cell courseCell9 = (Cell) row3.createCell((short) 8);

						String status = courseInfo.getProfessorStatus().get(courseInfo.getAssignedProfessors().get(i));
						if (status.equals(ProfessorStatus.BLOCKED.getStatus()))
							courseCell9.setCellStyle(potentialStatus);
						else if (status.equals(ProfessorStatus.NEW_HIRE.getStatus()))
							courseCell9.setCellStyle(loiSentStatus);
						else if (status.equals(ProfessorStatus.TEACHING_NOTIFICATION.getStatus()))
							courseCell9.setCellStyle(teachingNotificationSentStatus);
						else if (status.equals(ProfessorStatus.CONTRACT_SENT_TO_FACULTY.getStatus()))
							courseCell9.setCellStyle(contractSentStatus);
						else if (status.equals(ProfessorStatus.CONTRACT_SENT_TO_HR.getStatus()))
							courseCell9.setCellStyle(contractSentToHRStatus);
						else
							courseCell9.setCellStyle(c2);
						courseCell9.setCellValue(status);

						++rownum;
					}
				} else {
					Row row4 = sheet.createRow(rownum);

					CellRangeAddress mergedDataCell = new CellRangeAddress(rownum,
							courseInfo.getAssignedProfessors().size(), 0, 5);
					sheet.addMergedRegion(mergedDataCell);
					Cell courseCell1 = (Cell) row4.createCell((short) 0);
					courseCell1.setCellStyle(c1);
					courseCell1.setCellValue(courseInfo.getCourseName());

					Cell courseCell2 = (Cell) row4.createCell((short) 1);
					courseCell2.setCellStyle(c2);
					courseCell2.setCellValue(courseInfo.getCourseCode());

					Cell courseCell3 = (Cell) row4.createCell((short) 2);
					courseCell3.setCellStyle(c2);
					courseCell3.setCellValue(courseInfo.getSectionName());

					Cell courseCell4 = (Cell) row4.createCell((short) 3);
					courseCell4.setCellStyle(c2);
					courseCell4.setCellValue(courseInfo.getProgramBlock());

					Cell courseCell5 = (Cell) row4.createCell((short) 4);
					courseCell5.setCellStyle(c2);
					courseCell5.setCellValue(
							courseInfo.getNumberOfStudents() != null ? courseInfo.getNumberOfStudents().toString()
									: "");

					Cell courseCell6 = (Cell) row4.createCell((short) 5);
					courseCell6.setCellStyle(c2);
					courseCell6.setCellValue(
							courseInfo.getCourseCredit() != null ? courseInfo.getCourseCredit().toString() : "");

					Cell courseCell7 = (Cell) row4.createCell((short) 6);
					courseCell7.setCellStyle(c2);
					String professors = "";
					String status = "";
					if (courseInfo.getAssignedProfessors().size() == 1) {
						Professor professor = professorRepository.findById(courseInfo.getAssignedProfessors().get(0))
								.get();
						professors = professor.getFirstName() + " " + professor.getLastName();
						status = courseInfo.getProfessorStatus().get(courseInfo.getAssignedProfessors().get(0));
					}
					courseCell7.setCellValue(professors);

					Cell courseCell8 = (Cell) row4.createCell((short) 7);
					courseCell8.setCellStyle(c2);
					courseCell8.setCellValue(courseInfo.getScheduledRequest());

					Cell courseCell9 = (Cell) row4.createCell((short) 8);
					if (status.equals(ProfessorStatus.BLOCKED.getStatus()))
						courseCell9.setCellStyle(potentialStatus);
					else if (status.equals(ProfessorStatus.NEW_HIRE.getStatus()))
						courseCell9.setCellStyle(loiSentStatus);
					else if (status.equals(ProfessorStatus.TEACHING_NOTIFICATION.getStatus()))
						courseCell9.setCellStyle(teachingNotificationSentStatus);
					else if (status.equals(ProfessorStatus.CONTRACT_SENT_TO_FACULTY.getStatus()))
						courseCell9.setCellStyle(contractSentStatus);
					else if (status.equals(ProfessorStatus.CONTRACT_SENT_TO_HR.getStatus()))
						courseCell9.setCellStyle(contractSentToHRStatus);
					else
						courseCell9.setCellStyle(c2);
					courseCell9.setCellValue(status);
					++rownum;
				}
			}
			++rownum;
		}
		sheet.setDefaultColumnWidth(20);
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);

		/*
		 * sheet.autoSizeColumn(0); sheet.autoSizeColumn(1); sheet.setColumnWidth(2,
		 * 20); sheet.setColumnWidth(3, 25); sheet.setColumnWidth(4, 20);
		 * sheet.setColumnWidth(5, 20); sheet.setColumnWidth(6, 50);
		 * sheet.setColumnWidth(7, 50);
		 */
		try {
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
