package db;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NovaOsoba extends JDialog {

	private static final long serialVersionUID = 1L;
	private boolean stlaceneOk;
	private JTextField cisloStudenta_textField;
	private JTextField meno_textField;
	private JTextField priezvisko_textField;
	private JTextField body_textField;

	public Osoba vratOsobu() {
		stlaceneOk = false;
		setVisible(true);
		if (stlaceneOk) {
			try {
				return new Osoba(Long.valueOf(cisloStudenta_textField.getText()), meno_textField.getText(),
						priezvisko_textField.getText(), Double.valueOf(body_textField.getText()));
			} catch (Exception e) {
				stlaceneOk = false;
			}
		}
		return null;
	}

	public NovaOsoba() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-8, 222, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						stlaceneOk = true;
						setVisible(false);
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);

					}
				});
				buttonPane.add(cancelButton);
			}
		}

		JLabel cisloStudenta = new JLabel("Číslo študenta:");
		cisloStudenta.setBounds(118, 63, 94, 19);
		getContentPane().add(cisloStudenta);

		JLabel meno = new JLabel("Meno:");
		meno.setBounds(118, 95, 51, 19);
		getContentPane().add(meno);

		JLabel priezivsko = new JLabel("Priezvisko:");
		priezivsko.setBounds(118, 124, 79, 17);
		getContentPane().add(priezivsko);

		JLabel body = new JLabel("Body:");
		body.setBounds(118, 151, 63, 16);
		getContentPane().add(body);

		cisloStudenta_textField = new JTextField();
		cisloStudenta_textField.setFont(new Font("Tahoma", Font.BOLD, 10));
		cisloStudenta_textField.setBounds(220, 63, 76, 19);
		getContentPane().add(cisloStudenta_textField);
		cisloStudenta_textField.setColumns(10);

		meno_textField = new JTextField();
		meno_textField.setBounds(220, 92, 76, 19);
		getContentPane().add(meno_textField);
		meno_textField.setColumns(10);

		priezvisko_textField = new JTextField();
		priezvisko_textField.setBounds(220, 121, 76, 19);
		getContentPane().add(priezvisko_textField);
		priezvisko_textField.setColumns(10);

		body_textField = new JTextField();
		body_textField.setBounds(220, 150, 76, 19);
		getContentPane().add(body_textField);
		body_textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Pre pridanie osoby prosím vyplnte nasledujúci formulár:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(8, 10, 420, 26);
		getContentPane().add(lblNewLabel);

	}
}
