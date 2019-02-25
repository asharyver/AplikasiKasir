/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import lib.Database;

/**
 *
 * @author ashary
 */
public class Transaksi extends javax.swing.JDialog {
    
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private int idUser;
    private int idOrder;
    
    public void showDataToTable() {
        try {
            conn = Database.getCon();
            pstmt = conn.prepareStatement("SELECT `tbl_order`.`id_order`, `tbl_order`.`id_user`, `detail_order`.`jumlah`, `masakan`.`nama_masakan`, `masakan`.`harga` FROM `tbl_order` INNER JOIN `detail_order` ON `detail_order`.`id_order` = `tbl_order`.`id_order` INNER JOIN `masakan` ON `masakan`.`id_masakan` = `detail_order`.`id_makanan` WHERE `tbl_order`.`id_meja` = ? ");
            DefaultTableModel tb = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            tb.addColumn("No");
            tb.addColumn("Nama Menu");
            tb.addColumn("Jumlah");
            tb.addColumn("Harga");
            TableMenu.setModel(tb);
            TableMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            TableMenu.setDragEnabled(false);
            pstmt.setInt(1, Integer.parseInt(NomorMeja.getText()));
            rs = pstmt.executeQuery();
            int total = 0;
            while (rs.next()) {
                idUser = rs.getInt("id_user");
                idOrder = rs.getInt("id_order");
                total += rs.getInt("jumlah") * rs.getInt("harga");
                tb.addRow(new Object[]{
                    rs.getString("id_order"),
                    rs.getString("nama_masakan"),
                    rs.getString("jumlah"),
                    rs.getString("harga"),
                });
            }
            Jumlah.setText(String.valueOf(total));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(
                Level.SEVERE, 
                "Data tidak dapat ditampilkan. {0}", 
                ex.getMessage());
        }
    }

    /**
     * Creates new form Transaksi
     * @param parent
     * @param modal
     */
    public Transaksi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomorMejaLabel = new javax.swing.JLabel();
        NomorMeja = new javax.swing.JTextField();
        Proses = new javax.swing.JButton();
        ScrollPanelTable = new javax.swing.JScrollPane();
        TableMenu = new javax.swing.JTable();
        Cetak = new javax.swing.JButton();
        JumlahLabel = new javax.swing.JLabel();
        Jumlah = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        NomorMejaLabel.setText("Nomor Meja");

        NomorMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomorMejaActionPerformed(evt);
            }
        });

        Proses.setText("Cari");
        Proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProsesActionPerformed(evt);
            }
        });

        TableMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableMenu.setEnabled(false);
        ScrollPanelTable.setViewportView(TableMenu);

        Cetak.setText("Cetak");
        Cetak.setEnabled(false);
        Cetak.setOpaque(false);
        Cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CetakActionPerformed(evt);
            }
        });

        JumlahLabel.setText("Jumlah: ");

        Jumlah.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPanelTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NomorMejaLabel)
                .addGap(58, 58, 58)
                .addComponent(NomorMeja)
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Proses)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JumlahLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Jumlah)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomorMejaLabel)
                    .addComponent(NomorMeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Proses)
                    .addComponent(Cetak))
                .addGap(30, 30, 30)
                .addComponent(ScrollPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JumlahLabel)
                    .addComponent(Jumlah))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NomorMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomorMejaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomorMejaActionPerformed

    private void ProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProsesActionPerformed
        // TODO add your handling code here:
        if (NomorMeja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nomor meja harus diisi !");
        } else {
            Cetak.setEnabled(true);
            Proses.setEnabled(false);
            showDataToTable();
        }
    }//GEN-LAST:event_ProsesActionPerformed

    private void CetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CetakActionPerformed
        // TODO add your handling code here:
        try {
            conn = Database.getCon();
            pstmt = conn.prepareStatement("UPDATE `tbl_order` SET `status_order` = ? WHERE `id_meja` = ?");
            pstmt.setInt(1, 0);
            pstmt.setInt(2, Integer.parseInt(NomorMeja.getText()));
            pstmt.executeUpdate();
            pstmt.close();
            
            // Update Meja
            pstmt = conn.prepareStatement("UPDATE `meja` SET `status` = ? WHERE `id_meja` = ?");
            pstmt.setInt(1, 0);
            pstmt.setInt(2, Integer.parseInt(NomorMeja.getText()));
            pstmt.executeUpdate();
            pstmt.close();
            
            // Update User
            pstmt = conn.prepareStatement("UPDATE `user` SET `id_meja` = ? WHERE `id_user` = ?");
            pstmt.setInt(1, 0);
            pstmt.setInt(2, idUser);
            pstmt.executeUpdate();
            pstmt.close();
            
            // Insert Data
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);
            pstmt = conn.prepareStatement("INSERT INTO `transaksi` (`id_user`, `id_order`, `tanggal`, `total_bayar`) VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, idOrder);
            pstmt.setString(3, currentTime);
            pstmt.setInt(4, Integer.parseInt(Jumlah.getText()));
            pstmt.executeUpdate();
            dispose();
            JOptionPane.showMessageDialog(this, "Order telah selesai");
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            Transaksi dialog = new Transaksi(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cetak;
    private javax.swing.JLabel Jumlah;
    private javax.swing.JLabel JumlahLabel;
    private javax.swing.JTextField NomorMeja;
    private javax.swing.JLabel NomorMejaLabel;
    private javax.swing.JButton Proses;
    private javax.swing.JScrollPane ScrollPanelTable;
    private javax.swing.JTable TableMenu;
    // End of variables declaration//GEN-END:variables
}
