package com.java.employ;

import java.io.IOException;

public interface EmployDAO {

	public String saveEmpDetailsDao(Employ employ,EmpLogin empLogin) throws IOException;
	public String empLoginDao(EmpLogin login);
}
