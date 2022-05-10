package ma.fsdm.wisd.database.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ma.fsdm.wisd.database.panels.AjouterDepartement;
import ma.fsdm.wisd.database.panels.AjouterEtudiant;
import ma.fsdm.wisd.database.panels.ListerEtudiants;
import ma.fsdm.wisd.database.panels.ModifierDepartement;
import ma.fsdm.wisd.database.panels.ModifierEtudiant;
import ma.fsdm.wisd.database.panels.SupprimerDepartement;
import ma.fsdm.wisd.database.panels.SupprimerEtudiant;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2984156290867685030L;
	private JPanel contentPane;
	JLayeredPane layeredPane;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(15, 38, 859, 512);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(5, 5, 874, 22);
		contentPane.add(menuBar);
		
		JMenu etudiantMenu = new JMenu("Etudiant");
		menuBar.add(etudiantMenu);
		
		JMenuItem ajouterEtudiantMenuItem = new JMenuItem("Ajouter");
		etudiantMenu.add(ajouterEtudiantMenuItem);
		
		ajouterEtudiantMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new AjouterEtudiant());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem modifierEtudiantMenuItem = new JMenuItem("Modifier");
		etudiantMenu.add(modifierEtudiantMenuItem);
		
		modifierEtudiantMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new ModifierEtudiant());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem supprimerEtudiantMenuItem = new JMenuItem("Supprimer");
		etudiantMenu.add(supprimerEtudiantMenuItem);
		
		supprimerEtudiantMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new SupprimerEtudiant());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu autreMenu = new JMenu("Lister");
		etudiantMenu.add(autreMenu);
		
		JMenuItem parMCMenuItem = new JMenuItem("par mot cl\u00E9");
		autreMenu.add(parMCMenuItem);
		
		parMCMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new ListerEtudiants("mc"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem parDepartementMenuItem = new JMenuItem("par departement");
		autreMenu.add(parDepartementMenuItem);
		
		parDepartementMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new ListerEtudiants("dept"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem tousEtdMenuItem = new JMenuItem("tous les \u00E9tudiants");
		autreMenu.add(tousEtdMenuItem);
		
		tousEtdMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new ListerEtudiants("tous"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu departementMenu = new JMenu("Departement");
		menuBar.add(departementMenu);
		
		JMenuItem ajouterDepartementMenuItem = new JMenuItem("Ajouter");
		departementMenu.add(ajouterDepartementMenuItem);
		
		ajouterDepartementMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new AjouterDepartement());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem modifierDepartementMenuItem = new JMenuItem("Modifier");
		departementMenu.add(modifierDepartementMenuItem);
		
		modifierDepartementMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new ModifierDepartement());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem supprimerDepartementMenuItem = new JMenuItem("Supprimer");
		departementMenu.add(supprimerDepartementMenuItem);
		
		supprimerDepartementMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(new SupprimerDepartement());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);		
		
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
