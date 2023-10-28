package db;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import makra.UpravaTabulky;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class Databaza {

	private JFrame frame;
	Map<Long, Osoba> studenty;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Databaza window = new Databaza();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Databaza() {
		initialize();
		studenty = new HashMap<Long, Osoba>();
		String[] stlpce = { "<html><b>" + "Čislo študenta" + "</b></html>", "Meno", "Priezvisko", "Body" };
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.setColumnIdentifiers(stlpce);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tModel);
		table.setRowSorter(sorter);
		sorter.toggleSortOrder(0);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuList = new JMenu("Editovať databázu");
		menuBar.add(menuList);

		JMenuItem OsobaPridaj = new JMenuItem("Pridaj osobu");
		OsobaPridaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaOsoba dlg = new NovaOsoba();
				Osoba osoba = dlg.vratOsobu();
				if (osoba != null) {
					if (studenty.containsKey(osoba.cislo_studenta)) {
						JOptionPane.showMessageDialog(null, "Osoba s rovnakým čislom už existuje");
					} else {
						studenty.put(osoba.cislo_studenta, osoba);
						DefaultTableModel tModel = (DefaultTableModel) table.getModel();
						Object[] riadok = new Object[4];
						riadok[0] = osoba.cislo_studenta;
						riadok[1] = osoba.meno;
						riadok[2] = osoba.priezvisko;
						riadok[3] = osoba.body;
						for (int i = 0; i < 4; i++) {
							table.getColumnModel().getColumn(i).setCellRenderer(new UpravaTabulky());

						}
						tModel.addRow(riadok);
						JOptionPane.showMessageDialog(null, "Osoba bola pridaná!");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Osoba nebola pridaná!");
				}
			}
		});
		menuList.add(OsobaPridaj);

		JButton ZmenaBTN = new JButton("Potvrdiť");
		ZmenaBTN.setFont(new Font("Tahoma", Font.BOLD, 10));
		ZmenaBTN.setFocusPainted(false);
		ZmenaBTN.setEnabled(false);
		ZmenaBTN.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ZmenaBTN.setBounds(227, 208, 83, 21);
		frame.getContentPane().add(ZmenaBTN);

		JMenuItem OsobaAktualizovat = new JMenuItem("Aktualizuj osobu");
		OsobaAktualizovat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
				ZmenaBTN.setEnabled(true);
				JLabel edit = new JLabel("EDIT MOD");
				edit.setHorizontalAlignment(SwingConstants.CENTER);
				edit.setAlignmentX(Component.CENTER_ALIGNMENT);
				edit.setFont(new Font("Tahoma", Font.BOLD, 10));
				edit.setBounds(21, 212, 94, 13);
				frame.getContentPane().add(edit);
				Set<Long> editingStudentIDs = new HashSet<>();
				ZmenaBTN.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel tModel = (DefaultTableModel) table.getModel();
						int riadky = tModel.getRowCount();
						Map<Long, Osoba> updatedStudenty = new HashMap<>();

						for (int i = 0; i < riadky; i++) {
							String studentIDStr = tModel.getValueAt(i, 0).toString();
							Long cisloStudenta = Long.parseLong(studentIDStr);
							if (editingStudentIDs.contains(cisloStudenta)) {
								JOptionPane.showMessageDialog(null,
										"Nie je možné aktualizovať údaje, čislo študenta nemôže byť duplicitné.");
								resetTabulky();
							}
							editingStudentIDs.add(cisloStudenta);
							String meno = (String) tModel.getValueAt(i, 1);
							String priezvisko = (String) tModel.getValueAt(i, 2);
							String bodyStr = tModel.getValueAt(i, 3).toString();
							Double body = Double.parseDouble(bodyStr);
							Osoba student = new Osoba(cisloStudenta, meno, priezvisko, body);
							updatedStudenty.put(cisloStudenta, student);

						}

						studenty.clear();
						studenty.putAll(updatedStudenty);
						edit.setText(null);
						ZmenaBTN.setEnabled(false);
						table.setEnabled(false);
						editingStudentIDs.clear();
					}
				});
			}
		});

		menuList.add(OsobaAktualizovat);

		JMenuItem ZrusitZaznam = new JMenuItem("Zrušiť záznam");
		ZrusitZaznam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZrusOsoba dlg = new ZrusOsoba();
				Osoba osoba = dlg.vratOsobu();
				if (osoba == null) {
					JOptionPane.showMessageDialog(null, "Neboli zadane potrebne parametre\nNič sa nevykonalo!");
				} else {
					DefaultTableModel tModel = (DefaultTableModel) table.getModel();
					if (studenty.containsKey(osoba.cislo_studenta)) {
						int cislo = osoba.cislo_studenta.intValue();
						int pocetStlpcov = tModel.getRowCount();
						int Index = 0;
						for (int i = 0; i < pocetStlpcov; i++) {
							int rowCislo = Integer.parseInt(tModel.getValueAt(i, 0).toString());
							if (rowCislo == cislo) {
								Index = i;
								break;
							}
						}
						studenty.remove(osoba.cislo_studenta);
						tModel.removeRow(Index);
						JOptionPane.showMessageDialog(null, "Osoba bola vymazana");
					} else {
						JOptionPane.showMessageDialog(null,
								"Osoba nebola vymazana! \nOsoba s prislušným číslom sa nenachádza v zozname!");
					}
				}
			}
		});
		menuList.add(ZrusitZaznam);

		JButton ResetBTN = new JButton("Reset");
		ResetBTN.setEnabled(false);
		ResetBTN.setFocusPainted(false);
		ResetBTN.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ResetBTN.setFont(new Font("Tahoma", Font.BOLD, 10));
		ResetBTN.setBounds(136, 208, 83, 21);
		frame.getContentPane().add(ResetBTN);

		JMenuItem VypisPodmienka = new JMenuItem("Vypisanie študentov s podmienkou");
		VypisPodmienka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VypisPodmienka dlg = new VypisPodmienka();
				Osoba osoba = dlg.vratOsobu();
				if (osoba == null) {
					JOptionPane.showMessageDialog(null, "Neboli zadane potrebne parametre\nNič sa nevykonalo!");
				} else {
					DefaultTableModel tModel = (DefaultTableModel) table.getModel();
					int cislo = osoba.body.intValue();
					int pocetRiadkov = tModel.getRowCount() - 1;
					for (int i = pocetRiadkov; i >= 0; i--) {
						double doubleValue = Double.parseDouble(tModel.getValueAt(i, 3).toString());
						int intValue = (int) doubleValue;
						if (intValue >= cislo) {
							tModel.removeRow(i);
						}
					}
					JOptionPane.showMessageDialog(null, "Zoznam upravený");
					ResetBTN.setEnabled(true);
					ResetBTN.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (ResetBTN.isEnabled()) {
								resetTabulky();
								ResetBTN.setEnabled(false);
								JOptionPane.showMessageDialog(null, "Zoznam obnovený");
							}
						}
					});
				}

			}
		});
		menuList.add(VypisPodmienka);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 436, 198);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setForeground(Color.DARK_GRAY);
		table.setBounds(new Rectangle(3, 3, 1, 1));
		scrollPane.setViewportView(table);
	}

	private void resetTabulky() {
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.setRowCount(0);
		for (Map.Entry<Long, Osoba> entry : studenty.entrySet()) {
			Osoba osoba = entry.getValue();
			Object[] row = new Object[4];
			row[0] = osoba.cislo_studenta;
			row[1] = osoba.meno;
			row[2] = osoba.priezvisko;
			row[3] = osoba.body;
			tModel.addRow(row);
		}
	}
}
