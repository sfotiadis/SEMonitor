package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import Models.ReleaseHistory;
import Presenter.Presenter;

@SuppressWarnings("serial")
public class LawView extends View implements ActionListener {
	
	/**
	 * Interactive elements we need to read or change after initialization 
	 */
	private JLabel lblLawName;
	private JTextPane txtLawDescription;
	// User input
	private JRadioButton radioBtnLawTrue;
	private JRadioButton radioBtnLawFalse;
	private JTextPane txtUserComments;


	/**
	 * The number of the selected law
	 */
	private int lawNumber;
	
	/**
	 * The graph data to be presented
	 */
	private List<JPanel> graphs;
	
	/**
	 * The previous validity and comment if existing
	 */
 	private boolean validity;
 	private String comment;
 	
 	// TODO Translate to english
	/**
	 * A static list containing the explanation of the laws
	 */
	private static String[] lawDescriptions = {
		"Ο νόμος ισχύει αν\ra. σε κάθε έκδοση του λογισμικού υπάρχουν αλλαγές " 	// Law 1
		+ "στις λειτουργίες ή στις δομές δεδομένων του εργαλείου και\rb. κάθε "
		+ "χρόνο υπάρχει τουλάχιστον μια έκδοση του λογισμικού.»\r",
		"Ο νόμος ισχύει αν\ra. η πολυπλοκότητα των λειτουργιών ή των δομών " 	// Law 2
		+ "δεδομένων τείνει να αυξάνεται ή\rβ. η πολυπλοκότητα των λειτουργιών "
		+ "και των δομών δεδομένων δεν έχει αυξητική τάση και επιπλέον στο "
		+ "ραβδόγραμμα υπάρχουν εμφανείς δραστηριότητες συντήρησης.",
		"Ο νόμος ισχύει αν η εξέλιξη γίνεται με οργανωμένο συστηματικό τρόπο ο "
		+ "οποίος αντικατοπτρίζεται στην ύπαρξη επαναλαμβανόμενων μοτίβων στα "
		+ "ραβδογράμματα. Τα μοτίβα αυτά συνήθως έχουν την μορφή κυματισμών " // Law 3
		+ "(spikes) οι κορυφές (peaks) των οποίων αντιστοιχούν σε εκδόσεις "
		+ "στις οποίες κυριαρχεί η θετική ανατροφοδότηση που οδηγεί στην αύξηση "
		+ "των λειτουργιών. Οι κοιλάδες (valleys) που ακολουθούν αντιστοιχούν "
		+ "σε εκδόσεις με μικρή, μηδενική ή μείωση των λειτουργιών.",
		"Ο νόμος ισχύει αν αν ο ρυθμός εργασιών των λειτουργιών και "			// Law 4
		+ "των δομών δεδομένων τείνει να είναι σταθερός.",
		"Ο νόμος ισχύει αν αν και στα δυο γραφήματα εκδόσεις με μεγάλη "			// Law 5
		+ "αύξηση ακολουθούνται από εκδόσεις με μικρότερη, μηδενική ή"
		+ " αρνητική αύξηση.",
		"Ο νόμος ισχύει αν αν και στα δυο γραφήματα παρατηρείται "				// Law 6
		+ "συνεχής αύξηση.",
		"Ο νόμος βασίζεται στη αποτίμηση του 2ου και 6ου νόμου.",				// Law 7
		"Ο νόμος ισχύει αν το πλήθος των λειτουργιών μιας μελλοντικής έκδοσης "	// Law 8
		+ "του συστήματος μπορεί να προβλεφτεί με ακρίβεια με βάση μια "
		+ "ανατροφοδοτούμενη φόρμουλα η οποία λαμβάνει υπόψη της το πλήθος "
		+ "των λειτουργιών του συστήματος σε προηγούμενες εκδόσεις."
	};
	
	public void setLawNumber(int n) { 
		 lawNumber = n; 
	}
	
	public void setGraphs(List<JPanel> gd) { 
		// First removing the old panels from the GUI
		for (int i = 0; i < graphs.size(); i++) {
			remove(graphs.get(i));
		}
		graphs = gd;
	}
	
	public void setValidity(boolean v) {
		validity = v;
	}
	
	public void setComment(String c) {
		comment = c;
	}
	
	/**
	 * Updates all the components of the view with the values of the variables.
	 */
	@Override
	public void redraw() {
		lblLawName.setText( "Law No. " + (lawNumber + 1));
		txtLawDescription.setText(lawDescriptions[lawNumber]);
		selectRadioButton(validity);
		txtUserComments.setText(comment);
		
		int[][] bounds = { 
				{15, 35, 300, 150},
				{15, 190, 300, 150},
				{15, 345, 300, 150}
		};
		for (int i = 0; i < graphs.size(); i++) {
			graphs.get(i).setBounds(bounds[i][0], bounds[i][1], bounds[i][2], bounds[i][3]);
			add(graphs.get(i));
			graphs.get(i).revalidate();
			graphs.get(i).repaint();
		}
		
		if (lawNumber == ReleaseHistory.LAW_7) {
			radioBtnLawTrue.setEnabled(false);
			radioBtnLawFalse.setEnabled(false);
		} else {
			radioBtnLawTrue.setEnabled(true);
			radioBtnLawFalse.setEnabled(true);
		}
		
		revalidate();
		repaint();
	} 

	private void selectRadioButton(boolean validity) {
		if (validity) 
			radioBtnLawTrue.setSelected(true);
		else
			radioBtnLawFalse.setSelected(true);
	}
	
	public LawView(Presenter callbackPresenter) {
		super(callbackPresenter);
		graphs = new ArrayList<JPanel>();
		
		initializeView();
	}
	
	/**
	 * Called when a button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "saveEvaluation":
				boolean validity = radioBtnLawTrue.isSelected();
				String comment = txtUserComments.getText();
				callbackPresenter.saveLawEvaluation(validity, comment);
			case "goBack":
				callbackPresenter.gotoBackToMainView();
				break;
            default: 
            	Presenter.infoBox("Triggered Action", e.getActionCommand());
		}
	}
	
	private void initializeView() {
		setBounds(5, 5, 630, 525);
		setLayout(null);
		
		lblLawName = new JLabel();
		lblLawName.setBounds(237, 6, 99, 22);
		lblLawName.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblLawName.setBackground(new Color(238, 238, 238));
		add(lblLawName);
		
		txtLawDescription = new JTextPane();
		txtLawDescription.setEditable(false);
		txtLawDescription.setBackground(SystemColor.window);
		txtLawDescription.setBounds(349, 69, 252, 206);
		add(txtLawDescription);
		
		JLabel lblLawDescription = new JLabel("Law description:");
		lblLawDescription.setBounds(349, 45, 204, 16);
		add(lblLawDescription);
		
		radioBtnLawFalse = new JRadioButton("Not valid");
		radioBtnLawFalse.setBounds(467, 287, 99, 23);
		add(radioBtnLawFalse);
		
		radioBtnLawTrue = new JRadioButton("Valid");
		radioBtnLawTrue.setBounds(359, 287, 107, 23);
		add(radioBtnLawTrue);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(radioBtnLawTrue);
	    group.add(radioBtnLawFalse);
	    
		txtUserComments = new JTextPane();
		txtUserComments.setBackground(Color.WHITE);
		txtUserComments.setBounds(348, 348, 253, 105);
		add(txtUserComments);
		
		JLabel lblUserComments = new JLabel("User comments:");
		lblUserComments.setBounds(349, 322, 230, 16);
		add(lblUserComments);
		
		JButton btnApotimisi = new JButton("Save evaluation");
		btnApotimisi.setBounds(349, 465, 142, 29);
		btnApotimisi.setActionCommand("saveEvaluation");
		add(btnApotimisi);
		
		JButton btnBack = new JButton("Go back");
		btnBack.setBounds(484, 465, 117, 29);
		btnBack.setActionCommand("goBack");
		add(btnBack);
		
		btnBack.addActionListener(this);
		btnApotimisi.addActionListener(this);

	}
}
