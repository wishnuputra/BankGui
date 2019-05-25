import acm.gui.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BankGui extends Program {
	private BankDatabase database;
	private JLabel accountLabel;
	private JComboBox<Integer> idBox;
	private BankAccount currentAccount;
	private JTextField amountField;
	
	// set up the components in the window
	public void init() {
		setSize(500,250);
		
//		idField = new JTextField(4);
//		idField.addActionListener(this);
//		idField.setActionCommand("Lookup");
//		add(new JLabel("ID: "), NORTH);
//		add(idField, NORTH);
		
		idBox = new JComboBox<Integer>();
		add(idBox, NORTH);		
		add(new JButton("Lookup"), NORTH);
		
		accountLabel = new JLabel("???");
		accountLabel.setFont(new Font("Serif", Font.BOLD, 20));
		accountLabel.setHorizontalAlignment(JLabel.CENTER);
		add(accountLabel, CENTER);
		
		amountField = new JTextField(7);
		add(new JLabel("Amount: $"), SOUTH);
		add(amountField, SOUTH);
		add(new JButton("Deposit"), SOUTH);
		add(new JButton("Withdraw"), SOUTH);
		
		database = new BankDatabase();
		database.readFile("resource/bankdata.txt", idBox);
		
		
		
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Lookup")) {
			int index = idBox.getSelectedIndex();
			int id = idBox.getItemAt(index);			
			currentAccount = database.lookupAccount(id);
			if (currentAccount != null) {
				accountLabel.setText(currentAccount.toString());
			}
		} else if (command.equals("Deposit")) {
			if (currentAccount != null) {
				double amount = Double.parseDouble(amountField.getText());
				currentAccount.deposit(amount);
				accountLabel.setText(currentAccount.toString());
			}
		} else if (command.equals("Withdraw")) {
			if (currentAccount != null) {
				double amount = Double.parseDouble(amountField.getText());
				currentAccount.withdraw(amount);
				accountLabel.setText(currentAccount.toString());
			}
		}
	}
}