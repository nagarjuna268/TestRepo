package com.projectname.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

public class Variables {
	public Workbook testDataWorkBook, controllerwb;
	public Sheet testDataSuite,testDataSheet, controllerSheet;
	public Properties OR, V, EMAIL;
	public String currentpath;
	public String path;
	public FileInputStream fi;
	public Logger log;
	
	
	public ArrayList<Variables> steps;
	
	public String testStatus;
	public String startTime;
	
	public	String result;
	public	String desc;
	public	String keyword;
	public	String fileName;
	public	String object;
	public	String testcaseid;
	public String testLinkID;

	public FileOutputStream fo, foCsv;
	public  WritableWorkbook wwb, csvWwb;
	public WritableSheet ws, wws;
	
	public static int failcount;
	public static int rptFailCnt = 0;
	static String pastevalue = null;
	public File dir;
	
	
	
	
}
