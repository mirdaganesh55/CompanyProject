package com.java.employ;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class EmployDAOImpl implements EmployDAO {

	private String filePath;
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public static String generateEmployID() {
	    SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Query query = session.createQuery("SELECT MAX(e.empId) FROM Employ e");
	    String lastUHID = (String) query.uniqueResult();

	    if (lastUHID == null) {
	        lastUHID = "PCE0"; // Set an initial value if the table is empty
	    }

	    int numericPart = Integer.parseInt(lastUHID.substring(3)) + 1;
	    String newUHID = String.format("PCE%01d", numericPart);
	    System.out.println(newUHID);

	    session.close();
	    return newUHID;
	}

    public String saveEmpDetailsDao(Employ employ, EmpLogin empLogin) throws IOException {
        String empId = generateEmployID();
        employ.setEmpId(empId);
        upload(employ.getFile());
        employ.setImgUrl(filePath);
        String encr = EncryptPassword.getCode(empLogin.getPassword());
        empLogin.setPassword(encr.trim());
        SessionFactory sf = SessionHelper.getConnection();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        session.save(employ);
        trans.commit();
        session.close();
        
        sf = SessionHelper.getConnection();
        session = sf.openSession();
        Transaction trans1 = session.beginTransaction();
        session.save(empLogin);
        trans1.commit();
        session.close();
        System.out.println("Save " + employ);
        System.out.println("Save " + empLogin);
        return "EmpLogin.jsf?faces-redirect=true";
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

	@Override
	public String empLoginDao(EmpLogin login) {
		System.out.println(" Employ Login "+login);
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(EmpLogin.class);
		cr.add(Restrictions.eq("username",login.getUsername()));
		cr.add(Restrictions.eq("password",EncryptPassword.getCode(login.getPassword())));
		cr.setProjection(Projections.rowCount());
		long count = (long) cr.uniqueResult();
		if(count == 1) {
			System.out.println("Insid if "+count);
			return "sucess.jsf?faces-redirect=true";
		}else {
			System.out.println("Outside if "+count);
			return "EmployDetails.jsf?faces-redirect=true";
		}
	}
}

