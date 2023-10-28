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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class VypisPodmienka extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean stlaceneOk;
	private JTextField textField;

	public Osoba vratOsobu() {
		stlaceneOk = false;
		setVisible(true);
		if (stlaceneOk) {
			try {
				return new Osoba(Double.valueOf(textField.getText()));
			} catch (Exception e) {
				stlaceneOk = false;
			}
		}
		return null;
	}

	/**
	 * Create the dialog.
	 */
	public VypisPodmienka() {
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

		JLabel popis = new JLabel("Napiš maximálny počet bodov na zobrazenie dát:");
		popis.setHorizontalAlignment(SwingConstants.CENTER);
		popis.setFont(new Font("Tahoma", Font.BOLD, 12));
		popis.setBounds(34, 21, 362, 23);
		getContentPane().add(popis);

		textField = new JTextField();
		textField.setBounds(183, 59, 76, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
}
