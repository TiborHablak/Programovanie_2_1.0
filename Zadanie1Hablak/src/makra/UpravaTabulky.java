package makra;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class UpravaTabulky extends DefaultTableCellRenderer {
	private Font boldFont = new Font("Default", Font.BOLD, 12);

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setHorizontalAlignment(SwingConstants.CENTER);

		if (column == 0) {
			c.setFont(boldFont);
		}

		return c;
	}
}