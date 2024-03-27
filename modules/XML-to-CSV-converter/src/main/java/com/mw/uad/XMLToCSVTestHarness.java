package com.mw.uad;

public class XMLToCSVTestHarness {
	public static void main(String[] args) {
		
        String[] arguments = new String[3];
        	
        arguments[0] = "C:\\mw_temp\\source\\";
        arguments[1] = "C:\\mw_temp\\output\\";
        arguments[2] = "true";
        
        XMLToCSV.main(arguments);
    }
}