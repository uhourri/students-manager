package ma.fsdm.wisd.database.panels;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ma.fsdm.wisd.database.entities.Departement;
import ma.fsdm.wisd.database.entities.Etudiant;
import ma.fsdm.wisd.database.services.Metier;

import java.awt.Font;

public class ModifierEtudiant extends JPanel {
	private static final long serialVersionUID = 7617603329969555083L;
	
	//private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textCode;

	public ModifierEtudiant() throws Exception {
		Metier metier = new Metier();
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setSize(900, 600);
		//this.setLocationRelativeTo(null);
		//contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		this.setLayout(new BorderLayout(0, 0));
		
		Panel infosPanel = new Panel();
		this.add(infosPanel, BorderLayout.CENTER);
		infosPanel.setLayout(null);
		
		JLabel nomLabel = new JLabel("Nom : ");
		nomLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nomLabel.setBounds(438, 232, 160, 35);
		infosPanel.add(nomLabel);
		
		textNom = new JTextField();
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNom.setBounds(608, 232, 160, 35);
		infosPanel.add(textNom);
		textNom.setColumns(10);
		
		JLabel prenomLabel = new JLabel("Prenom : ");
		prenomLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		prenomLabel.setBounds(438, 278, 160, 35);
		infosPanel.add(prenomLabel);
		
		textPrenom = new JTextField();
		textPrenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPrenom.setBounds(608, 278, 160, 35);
		infosPanel.add(textPrenom);
		textPrenom.setColumns(10);
		
		JLabel deptLabel = new JLabel("Dept : ");
		deptLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deptLabel.setBounds(438, 324, 160, 35);
		infosPanel.add(deptLabel);
		
		JComboBox<Object> comboDept = new JComboBox<Object>();
		comboDept.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboDept.setBounds(608, 324, 160, 35);
		List<Departement> departements = metier.recupererDepartements();
		for (int i = 0; i < departements.size(); i++) {
			comboDept.addItem(departements.get(i).getNomDept());
		}
		comboDept.setToolTipText("");
		infosPanel.add(comboDept);
		
		textCode = new JTextField();
		textCode.setEditable(false);
		textCode.setBounds(608, 186, 160, 35);
		infosPanel.add(textCode);
		textCode.setColumns(10);
		
		JLabel codeLabel = new JLabel("Code :");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		codeLabel.setBounds(438, 186, 160, 35);
		infosPanel.add(codeLabel);
		
		JComboBox<Object> comboEtudiants = new JComboBox<Object>();
		comboEtudiants.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboEtudiants.setBounds(66, 184, 330, 35);
		List<Etudiant> etudiants = metier.recupererEtudiants();
		for (int i = 0; i < etudiants.size(); i++) {
			comboEtudiants.addItem("["+etudiants.get(i).getCode()+"] "+etudiants.get(i).getPrenom()+" "+etudiants.get(i).getNom());
		}
		comboEtudiants.setToolTipText("");
		infosPanel.add(comboEtudiants);
		comboEtudiants.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedEtudiant = (String)comboEtudiants.getSelectedItem();
				String codeEtudiant = selectedEtudiant.substring(selectedEtudiant.indexOf("[") + 1, selectedEtudiant.indexOf("]"));
				Etudiant etudiant = metier.recupererEtudiant(Integer.parseInt(codeEtudiant));
				textCode.setText(codeEtudiant);
				textNom.setText(etudiant.getNom());
				textPrenom.setText(etudiant.getPrenom());
				comboDept.setSelectedItem(etudiant.getDept().getNomDept());
			}
		});
		
		
		JLabel selectionerLabel = new JLabel("Étudiant à modifier :");
		selectionerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		selectionerLabel.setBounds(66, 138, 330, 35);
		infosPanel.add(selectionerLabel);
		
		Panel buttonsPanel = new Panel();
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton resetButton = new JButton("Vider");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonsPanel.add(resetButton);
		
		JButton editButton = new JButton("Modifier");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonsPanel.add(editButton);
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = Integer.parseInt(textCode.getText());
				String nom = textNom.getText();
				String prenom = textPrenom.getText();
				
				String selectedDept = (String) comboDept.getSelectedItem();
				int dept = metier.recupererDepartementParNom(selectedDept).getCodeDept();
				
				try {
					if(!nom.isEmpty() && !prenom.isEmpty()) {
						metier.modifierEtudiant(code, nom, prenom, dept);
						resetButton.doClick();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textNom.setText("");
				textPrenom.setText("");
				comboDept.setSelectedIndex(0);
			}
		});
	}
}
