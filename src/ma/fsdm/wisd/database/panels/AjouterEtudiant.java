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

public class AjouterEtudiant extends JPanel {
	private static final long serialVersionUID = 7617603329069555983L;
	
	//private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrenom;

	public AjouterEtudiant() throws Exception {
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
		
		JLabel lblNewLabel = new JLabel("Nom : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(280, 175, 160, 35);
		infosPanel.add(lblNewLabel);
		
		textNom = new JTextField();
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNom.setBounds(450, 175, 160, 35);
		infosPanel.add(textNom);
		textNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Prenom : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(280, 221, 160, 35);
		infosPanel.add(lblNewLabel_1);
		
		textPrenom = new JTextField();
		textPrenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPrenom.setBounds(450, 221, 160, 35);
		infosPanel.add(textPrenom);
		textPrenom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dept : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(280, 267, 160, 35);
		infosPanel.add(lblNewLabel_2);
		
		JComboBox<Object> comboDept = new JComboBox<Object>();
		comboDept.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboDept.setBounds(450, 267, 160, 35);
		
		List<Departement> departements = metier.recupererDepartements();
		for (int i = 0; i < departements.size(); i++) {
			comboDept.addItem(departements.get(i).getNomDept());
		}
		
		comboDept.setToolTipText("");
		infosPanel.add(comboDept);
		
		Panel buttonsPanel = new Panel();
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton resetButton = new JButton("Vider");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonsPanel.add(resetButton);
		
		JButton saveButton = new JButton("Ajouter");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonsPanel.add(saveButton);
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = textNom.getText();
				String prenom = textPrenom.getText();
				
				String selectedDept = (String) comboDept.getSelectedItem();
				int dept = metier.recupererDepartementParNom(selectedDept).getCodeDept();
				
				try {
					if(!nom.isEmpty() && !prenom.isEmpty()) {
						metier.ajouterEtudiant(new Etudiant(nom, prenom), dept);
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
