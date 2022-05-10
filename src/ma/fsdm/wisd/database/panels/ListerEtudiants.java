package ma.fsdm.wisd.database.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ma.fsdm.wisd.database.entities.Departement;
import ma.fsdm.wisd.database.entities.Etudiant;
import ma.fsdm.wisd.database.services.Metier;
import ma.fsdm.wisd.database.services.Model;

public class ListerEtudiants extends JPanel{

	private static final long serialVersionUID = 1613050683990048651L;
	//private JPanel contentPane;
	private JTable table;
	private Model model;
	private Metier metier = new Metier();


	public ListerEtudiants(String type) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 900, 600);
		//setLocationRelativeTo(null);
		//contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);

		model = new Model();
		
		if(type.equalsIgnoreCase("MC")) {

			JLabel label = new JLabel("Mot Clé : ");
			JTextField textField = new JTextField(12);
			JButton button = new JButton("OK");
			
			JPanel northPanel = new JPanel();
			northPanel.setLayout(new FlowLayout());
			northPanel.add(label);
			northPanel.add(textField);
			northPanel.add(button);
			this.add(northPanel, BorderLayout.NORTH);
			

			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String mc = textField.getText();
					List<Etudiant> etudiants = metier.recupererEtudiantsParMC(mc);
					model.loadData(etudiants);
				}
			});
		}else if(type.equalsIgnoreCase("DEPT")) {

			JLabel label = new JLabel("Dept : ");

			JComboBox<Object> comboDepts = new JComboBox<Object>();
			List<Departement> departements = metier.recupererDepartements();
			for (int i = 0; i < departements.size(); i++) {
				comboDepts.addItem("["+departements.get(i).getCodeDept()+"] "+departements.get(i).getNomDept());
			}
			comboDepts.setToolTipText("");
			comboDepts.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedDept = (String)comboDepts.getSelectedItem();
					String codeDept = selectedDept.substring(selectedDept.indexOf("[") + 1, selectedDept.indexOf("]"));
					List<Etudiant> etudiants = metier.recupererEtudiants(Integer.parseInt(codeDept));
					model.loadData(etudiants);
				}
			});
			
			JPanel northPanel = new JPanel();
			northPanel.setLayout(new FlowLayout());
			northPanel.add(label);
			northPanel.add(comboDepts);
			this.add(northPanel, BorderLayout.NORTH);
		}else{
			List<Etudiant> etudiants = metier.recupererEtudiants();
			model.loadData(etudiants);
		}
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane,BorderLayout.CENTER);
		
		
	}

}
