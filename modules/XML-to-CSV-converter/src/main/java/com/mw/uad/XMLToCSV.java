package com.mw.uad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLToCSV {
	
	public interface XSLT_TEMPLATES {
		public static final String HEADER = "/header.xsl";
		public static final String CONTENT = "/content.xsl";
	}	

	public static void main(String[] args) {
    	if (args == null || args.length != 3) {
    		System.out.println("Arguments not as expected. See the readme for more details on the arguments expected. Unable to proceed.");
    		
    		return;
    	}
        
    	String inputFolderString = args[0];
    	String outputFolderString = args[1];
    	boolean debugLogging = Boolean.valueOf(args[2]);
    	
    	System.out.println("inputFolder: " + inputFolderString);
    	System.out.println("outputFolder: " + outputFolderString);
    	System.out.println("debugLogging: " + debugLogging);
		
		XMLToCSV xmlToCSV = new XMLToCSV(inputFolderString, outputFolderString, debugLogging);
		
		xmlToCSV.run();
	}

	public XMLToCSV(String inputFolderString, String outputFolderString, boolean debugLogging) {
		super();
		this.debugLogging = debugLogging;
		this.inputFolderString = inputFolderString;
		this.outputFolderString = outputFolderString;
	}

	private void run() {
		
        try {
            File inputFolder = new File(inputFolderString);
            File outputFolder = new File(outputFolderString);
            
            if (!inputFolder.exists() || !inputFolder.isDirectory() || !inputFolder.canRead()) {
            	System.out.println("inputFolder not found or cannot be read. Unable to proceed.");
        		
        		return;
            }

            if (!outputFolder.exists() || !outputFolder.isDirectory() || !outputFolder.canWrite()) {
            	System.out.println("outputFolder not found or cannot be written to. Unable to proceed.");
        		
        		return;
            }
            
            TransformerFactory factory = TransformerFactory.newInstance();            
            Transformer headerTransformer = factory.newTransformer(new StreamSource(ClassLoader.class.getResourceAsStream(XSLT_TEMPLATES.HEADER)));
            Transformer contentTransformer = factory.newTransformer(new StreamSource(ClassLoader.class.getResourceAsStream(XSLT_TEMPLATES.CONTENT)));
            
            processFolderContents(inputFolder.listFiles(), inputFolder.getName(), outputFolderString, headerTransformer, contentTransformer);
            
            System.out.println("Done... " + xmlFileCount + " XML files merged into " + csvFileCount + " CSV files.");
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

	private void processFolderContents(File[] files, String parentFolderName, String outputFolderString, Transformer headerTransformer,
			Transformer contentTransformer) {
		
		if (debugLogging) System.out.println("Processing folder: " + parentFolderName);

		FileOutputStream outputStream = null;
        OutputStreamWriter writer = null;
    	File outputFile = null;
    	
		boolean headerWritten = false;
		boolean fileFound = false;

    	try {
			for (File file : files) {
	    		String outputFileName = outputFolderString + parentFolderName + ".csv";
	        
		        if (file.isDirectory()) {
		        	processFolderContents(file.listFiles(), file.getName(), outputFolderString, headerTransformer, contentTransformer);
		        } else if (file.isFile() && file.getName() != null && file.getName().toLowerCase().endsWith(".xml")) { // Only interested in XML files... 			        	
					if (!fileFound) {
						fileFound = true;

						outputFile = new File(outputFileName);
						outputStream = new FileOutputStream(outputFile);
						writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
						
						if (debugLogging) System.out.println("Created file: " + outputFile.getName());
					}
					
		        	if (debugLogging) System.out.println("Processing file: " + file.getName() + " in folder " + parentFolderName);
		
					// All XML files in the folder should be the same so same header applies to all
					if (!headerWritten) {
						headerWritten = true;

						headerTransformer.transform(new StreamSource(file), new StreamResult(writer));
						
						csvFileCount ++;
					}
		        	
		       		contentTransformer.transform(new StreamSource(file), new StreamResult(writer));
		       		
		       		xmlFileCount ++;
		        }
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (writer != null) try { writer.close(); } catch(Exception e) {};
            if (outputStream != null) try { outputStream.close(); } catch(Exception e) {};
        }		
	}
	
	private boolean debugLogging = false;
	
	private String inputFolderString;
	private String outputFolderString;
	
	private long xmlFileCount = 0;
	private long csvFileCount = 0;	
}