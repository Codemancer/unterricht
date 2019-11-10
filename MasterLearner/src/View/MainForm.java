package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ViewModel.QuestionVM;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem menuOpen;
	private JMenuItem menuExit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);		
		setExtendedState( getExtendedState() | JFrame.MAXIMIZED_BOTH ); // Fenster maximieren
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		menuOpen = new JMenuItem("\u00D6ffnen");
		menuOpen.addActionListener(this);
		mnDatei.add(menuOpen);
		
		JSeparator separator = new JSeparator();
		mnDatei.add(separator);
		
		menuExit = new JMenuItem("Beenden");
		menuExit.addActionListener(this);
		mnDatei.add(menuExit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList( QuestionVM.getInstance().getQuestions() );
		list.setBounds(0, 0, 205, 1003);
		contentPane.add(list);		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Object sender = arg0.getSource();
		
		if ( sender == menuOpen ) onActionOpenFile(); 		
		if ( sender == menuExit ) System.exit(0); 
	
	}
	
	private void onActionOpenFile() {
		JFileChooser fc = new JFileChooser();
		if ( fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) {
			QuestionVM.getInstance().LoadFile(fc.getSelectedFile());			
		}
	}
}
