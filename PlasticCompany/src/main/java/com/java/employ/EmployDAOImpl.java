package com.java.employ;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployDAOImpl implements EmployDAO {

	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String saveEmpDetailsDao(Employ employ) throws IOException {
		upload(employ.getFile());
		employ.setImgUrl(filePath);
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		session.save(employ);
		trans.commit();
		System.out.println("Save "+employ);
		return "Success";
	}
	
	public void upload(Part file) {
		if (file != null) {
			try (InputStream input = file.getInputStream()) {
				String fileName = getSubmittedFileName(file);
				filePath = "D:/EmploySaveImage/SaveImage/" + fileName;
				try (OutputStream output = new FileOutputStream(new File(filePath))) {
					int bytesRead;
					final byte[] CHUNK = new byte[1024];

					while ((bytesRead = input.read(CHUNK)) != -1) {
						output.write(CHUNK, 0, bytesRead);
					}
				}
				System.out.println("Upload done in "+filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("File is Empty");            
		}
	}
	
	private String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				fileName =  fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
				fileName = fileName.replace(" ", "_");
				return fileName;
			}
		}
		return null;
	}
}

