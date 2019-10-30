package ua.nulp.view.cipher;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class DirectSubstitutionPanel extends JPanel {
    private JButton randomButton;
    private JTextField keyField;
    private JTable keyTable;
    private JButton decodeButton;
    private JButton tableDecodeButton;
    private JButton encodeButton;
    private JButton setKeyButton;
    private JButton loadKeyButton;
    private JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));

    public DirectSubstitutionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setRandomButton();
        setKeyField();
        setDecodeButton();
        setEncodeButton();
    }

    public DirectSubstitutionPanel(String alphabet) {
        setLayout(new BorderLayout());
        setRandomButton();
        setKeyField();
        setDecodeButton();
        setTableDecodeButton();
        setEncodeButton();
        setSetKeyButton();
        setLoadKeyButton();
        add(buttonsContainer, BorderLayout.NORTH);
        setKeyTable(alphabet);
    }

    public JTable getKeyTable() {
        return keyTable;
    }

    private void setKeyTable(String alphabet) {
        Object[] characters = Arrays.asList(alphabet.split("")).toArray();
        Object[][] data = {characters};
        this.keyTable = new JTable(data, characters);
        keyTable.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < keyTable.getColumnCount(); i++) {
            keyTable.getColumnModel().getColumn(i).setPreferredWidth(20);
            keyTable.getColumnModel().setColumnSelectionAllowed(true);
        }
        keyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(keyTable.getTableHeader(), BorderLayout.NORTH);
        panel.add(keyTable, BorderLayout.SOUTH);
        add(new JScrollPane(panel), BorderLayout.CENTER);
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    private void setRandomButton() {
        this.randomButton = new JButton("Random");
        buttonsContainer.add(randomButton);
    }

    public JTextField getKeyField() {
        return keyField;
    }

    private void setKeyField() {
        this.keyField = new JTextField(32);
        buttonsContainer.add(keyField);
    }

    private void setDecodeButton() {
        this.decodeButton = new JButton("Decode");
        buttonsContainer.add(decodeButton);
    }

    public JButton getDecodeButton() {
        return this.decodeButton;
    }

    private void setTableDecodeButton() {
        this.tableDecodeButton = new JButton("Decode by Table");
        buttonsContainer.add(decodeButton);
    }

    public JButton getTableDecodeButton() {
        return this.tableDecodeButton;
    }

    private void setEncodeButton() {
        this.encodeButton = new JButton("Encode");
        buttonsContainer.add(encodeButton);
    }

    public JButton getEncodeButton() {
        return this.encodeButton;
    }

    private void setSetKeyButton() {
        this.setKeyButton = new JButton("Set key");
        setKeyButton.addActionListener(e -> keyField.setText(getTableKey()));
        buttonsContainer.add(setKeyButton);
    }

    public JButton getSetKeyButton() {
        return setKeyButton;
    }

    private void setLoadKeyButton() {
        this.loadKeyButton = new JButton("Load key");
        loadKeyButton.addActionListener(e -> loadKey(keyField.getText()));
        buttonsContainer.add(loadKeyButton);
    }

    public JButton getLoadKeyButton() {
        return loadKeyButton;
    }

    public String getKey() {
        return keyField.getText();
    }

    private void loadKey(String key) {
        int cols = keyTable.getColumnCount();
        for (int i = 0; i < cols && i < key.length(); i++) {
            keyTable.setValueAt(String.valueOf(key.charAt(i)), 0, i);
        }
    }

    public void onEditCell(BiConsumer<String, Object> operator) {
        keyTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            String columnName =
                    (String) keyTable.getColumnModel().getColumn(column).getHeaderValue();
            String data = String.valueOf(model.getValueAt(row, column)).toLowerCase();
            if (data.length() > 1) {
                data = data.substring(0, 1);
                keyTable.setValueAt(data, row, column);
            }
            operator.accept(columnName, data);
        });
    }

    public String[][] getTableData() {
        int cols = keyTable.getColumnCount();
        String[][] data = new String[cols][2];
        for (int i = 0; i < cols; i++) {
            data[i][0] = (String) keyTable.getColumnModel().getColumn(i).getHeaderValue();
            data[i][1] = (String) keyTable.getValueAt(0, i);
        }
        return data;
    }

    public String getTableKey() {
        int rows = keyTable.getRowCount();
        int cols = keyTable.getColumnCount();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                builder.append(keyTable.getValueAt(i, j));
            }
        }
        return builder.toString();
    }
}
