package ma.fsdm.wisd.database.entities;

import java.io.Serializable;

public class Departement implements Serializable {
	private static final long serialVersionUID = 3545516895394117148L;
	
	private int codeDept;
	private String nomDept;
	
	public int getCodeDept() {
		return codeDept;
	}
	public void setCodeDept(int codeDept) {
		this.codeDept = codeDept;
	}
	public String getNomDept() {
		return nomDept;
	}
	public void setNomDept(String nomDept) {
		this.nomDept = nomDept;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Departement(String nomDept) {
		super();
		this.nomDept = nomDept;
	}
	public Departement() {
		super();
	}
	
	
}
