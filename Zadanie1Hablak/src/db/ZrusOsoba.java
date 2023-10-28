package db;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ZrusOsoba extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean stlaceneOk;
	private JTextField cisloStudenta_textField;

	public Osoba vratOsobu() {
		stlaceneOk = false;
		setVisible(true);
		if (stlaceneOk) {
			try {
				return new Osoba(Long.valueOf(cisloStudenta_textField.getText()));
			} catch (Exception e) {
				stlaceneOk = false;
			}
		}
		return null;
	}

	public ZrusOsoba() {
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

		JLabel ID = new JLabel("Zadaj čislo študenta:");
		ID.setHorizontalAlignment(SwingConstants.CENTER);
		ID.setBounds(105, 63, 131, 20);
		getContentPane().add(ID);

		cisloStudenta_textField = new JTextField();
		cisloStudenta_textField.setBounds(244, 64, 76, 19);
		getContentPane().add(cisloStudenta_textField);
		cisloStudenta_textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Pre zrušenie záznamu zadajte číslo prisluchajucého študenta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(8, 10, 428, 29);
		getContentPane().add(lblNewLabel);
	}
}
