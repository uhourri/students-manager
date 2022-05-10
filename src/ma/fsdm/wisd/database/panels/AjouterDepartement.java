package ma.fsdm.wisd.database.panels;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ma.fsdm.wisd.database.entities.Departement;
import ma.fsdm.wisd.database.services.Metier;

import java.awt.Font;

public class AjouterDepartement extends JPanel {
	private static final long serialVersionUID = 7619903329069555983L;
	
	//private JPanel contentPane;
	private JTextField textNom;

	public AjouterDepartement() throws Exception {
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
				
				try {
					if(!nom.isEmpty()) {
						metier.ajouterDepartement(new Departement(nom));
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
			}
		});
	}
}
