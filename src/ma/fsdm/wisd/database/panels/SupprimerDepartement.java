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
import ma.fsdm.wisd.database.services.Metier;

import java.awt.Font;

public class SupprimerDepartement extends JPanel {
	private static final long serialVersionUID = 7617603909969555083L;
	
	//private JPanel contentPane;
	private JTextField textNom;
	private JTextField textCode;

	public SupprimerDepartement() throws Exception {
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
		textNom.setEditable(false);
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNom.setBounds(608, 232, 160, 35);
		infosPanel.add(textNom);
		textNom.setColumns(10);
		
		textCode = new JTextField();
		textCode.setEditable(false);
		textCode.setBounds(608, 186, 160, 35);
		infosPanel.add(textCode);
		textCode.setColumns(10);
		
		JLabel codeLabel = new JLabel("Code :");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		codeLabel.setBounds(438, 186, 160, 35);
		infosPanel.add(codeLabel);
		
		JComboBox<Object> comboDepartements = new JComboBox<Object>();
		comboDepartements.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboDepartements.setBounds(66, 184, 330, 35);
		List<Departement> departements = metier.recupererDepartements();
		for (int i = 0; i < departements.size(); i++) {
			comboDepartements.addItem("["+departements.get(i).getCodeDept()+"] "+departements.get(i).getNomDept());
		}
		comboDepartements.setToolTipText("");
		infosPanel.add(comboDepartements);
		comboDepartements.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDepartement = (String)comboDepartements.getSelectedItem();
				String codeDepartement = selectedDepartement.substring(selectedDepartement.indexOf("[") + 1, selectedDepartement.indexOf("]"));
				Departement departement = metier.recupererDepartement(Integer.parseInt(codeDepartement));
				textCode.setText(codeDepartement);
				textNom.setText(departement.getNomDept());
			}
		});
		
		
		JLabel selectionerLabel = new JLabel("Departement \u00E0 supprimer :");
		selectionerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		selectionerLabel.setBounds(66, 138, 330, 35);
		infosPanel.add(selectionerLabel);
		
		Panel buttonsPanel = new Panel();
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton resetButton = new JButton("Vider");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonsPanel.add(resetButton);
		
		JButton deleteButton = new JButton("Supprimer");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonsPanel.add(deleteButton);
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = Integer.parseInt(textCode.getText());
				
				try {
					metier.supprimerDepartement(code);
					resetButton.doClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textNom.setText("");
			}
		});
	}
}
