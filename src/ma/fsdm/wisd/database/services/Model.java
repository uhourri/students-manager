package ma.fsdm.wisd.database.services;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ma.fsdm.wisd.database.entities.Etudiant;

public class Model extends AbstractTableModel {
	private static final long serialVersionUID = 4076239214004055522L;
	
	private String[] columns = new String[] {"Code","Nom","Prenom","Departement"};
	private Vector<String[]> rows = new Vector<String[]>();
	
	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows.get(rowIndex)[columnIndex];
	}
	
	public void loadData(List<Etudiant> etudiants) {
		rows = new Vector<String[]>();
		for(Etudiant e: etudiants) {
			rows.add(new String[] {
					String.valueOf(e.getCode()),
					e.getNom(),
					e.getPrenom(),
					e.getDept().getNomDept()
			});
		}
		fireTableChanged(null);
	}
	
}
