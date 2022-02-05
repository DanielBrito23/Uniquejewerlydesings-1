/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.vista;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 *
 * @author corin
 */
public class MenuPrincipal extends javax.swing.JFrame {

    
    public JPanel getContent() {
        return content;
    }

    /**
     * Creates new form MenuPrincipal
     */
    public void setContent(JPanel content) {    
        this.content = content;
    }

    public JMenuItem getBtnEditPro() {
        return btnEditPro;
    }

    public void setBtnEditPro(JMenuItem btnEditPro) {
        this.btnEditPro = btnEditPro;
    }

    public JMenuItem getBtnDeletePro() {
        return btnDeletePro;
    }

    public void setBtnDeletePro(JMenuItem btnDeletePro) {
        this.btnDeletePro = btnDeletePro;
    }
    
    public MenuPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public JMenuItem getBtnNewCustom() {
        return btnNewCustom;
    }

    public JMenuItem getJDeleteCustom() {
        return JDeleteCustom;
    }

    public void setJDeleteCustom(JMenuItem JDeleteCustom) {
        this.JDeleteCustom = JDeleteCustom;
    }

    public JMenuItem getJDeleteProduct() {
        return btnDeletePro;
    }

    public void setJDeleteProduct(JMenuItem JDeleteProduct) {
        this.btnDeletePro = JDeleteProduct;
    }

    public JMenuItem getJEditCustom() {
        return JEditCustom;
    }

    public void setJEditCustom(JMenuItem JEditCustom) {
        this.JEditCustom = JEditCustom;
    }

    public JMenuItem getJEditProduct() {
        return btnEditPro;
    }

    public void setJEditProduct(JMenuItem JEditProduct) {
        this.btnEditPro = JEditProduct;
    }

    public JMenu getJMCustoms() {
        return JMCustoms;
    }

    public void setJMCustoms(JMenu JMCustoms) {
        this.JMCustoms = JMCustoms;
    }

    public JMenu getJMInvoice() {
        return JMInvoice;
    }

    public void setJMInvoice(JMenu JMInvoice) {
        this.JMInvoice = JMInvoice;
    }

    public JMenu getJProducts() {
        return JProducts;
    }

    public void setJProducts(JMenu JProducts) {
        this.JProducts = JProducts;
    }

    public JMenuItem getJmInvoiceList() {
        return JmInvoiceList;
    }

    public void setJmInvoiceList(JMenuItem JmInvoiceList) {
        this.JmInvoiceList = JmInvoiceList;
    }

    public JMenu getJminventory() {
        return Jminventory;
    }

    public void setJminventory(JMenu Jminventory) {
        this.Jminventory = Jminventory;
    }

   

    public JMenuItem getBtnNewProduct() {
        return btnNewProduct;
    }

    public void setBtnNewProduct(JMenuItem btnNewProduct) {
        this.btnNewProduct = btnNewProduct;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JMenuItem getjMenuItem1() {
        return jMenuItem1;
    }

    public void setjMenuItem1(JMenuItem jMenuItem1) {
        this.jMenuItem1 = jMenuItem1;
    }

    public JPanel getjPanel1() {
        return content;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.content = jPanel1;
    }

    public JPopupMenu.Separator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JPopupMenu.Separator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JPopupMenu.Separator getjSeparator2() {
        return jSeparator2;
    }

    public void setjSeparator2(JPopupMenu.Separator jSeparator2) {
        this.jSeparator2 = jSeparator2;
    }

    public JSeparator getjSeparator3() {
        return jSeparator3;
    }

    public void setjSeparator3(JSeparator jSeparator3) {
        this.jSeparator3 = jSeparator3;
    }

    public JPopupMenu.Separator getjSeparator4() {
        return jSeparator4;
    }

    public void setjSeparator4(JPopupMenu.Separator jSeparator4) {
        this.jSeparator4 = jSeparator4;
    }

    public JSeparator getjSeparator5() {
        return jSeparator5;
    }

    public void setjSeparator5(JSeparator jSeparator5) {
        this.jSeparator5 = jSeparator5;
    }

    public JPopupMenu.Separator getjSeparator6() {
        return jSeparator6;
    }

    public void setjSeparator6(JPopupMenu.Separator jSeparator6) {
        this.jSeparator6 = jSeparator6;
    }

    public JPopupMenu.Separator getjSeparator7() {
        return jSeparator7;
    }

    public void setjSeparator7(JPopupMenu.Separator jSeparator7) {
        this.jSeparator7 = jSeparator7;
    }

    public JPopupMenu.Separator getjSeparator8() {
        return jSeparator8;
    }

    public void setjSeparator8(JPopupMenu.Separator jSeparator8) {
        this.jSeparator8 = jSeparator8;
    }

    public JPopupMenu.Separator getjSeparator9() {
        return jSeparator9;
    }

    public void setjSeparator9(JPopupMenu.Separator jSeparator9) {
        this.jSeparator9 = jSeparator9;
    }

    public void setBtnNewCustom(JMenuItem btnNewCustom) {
        this.btnNewCustom = btnNewCustom;
    }

    public JMenuItem getJListCustom() {
        return JListCustom;
    }

    public void setJListCustom(JMenuItem JListCustom) {
        this.JListCustom = JListCustom;
    }

    public JMenuItem getBtnListProducts() {
        return BtnListProducts;
    }

    public void setBtnListProducts(JMenuItem BtnListProducts) {
        this.BtnListProducts = BtnListProducts;
    }

    public JMenuItem getBtnnewInvoice() {
        return btnnewInvoice;
    }

    public void setBtnnewInvoice(JMenuItem btnnewInvoice) {
        this.btnnewInvoice = btnnewInvoice;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        content = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Jminventory = new javax.swing.JMenu();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        BtnListProducts = new javax.swing.JMenuItem();
        JProducts = new javax.swing.JMenu();
        btnNewProduct = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        btnEditPro = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        btnDeletePro = new javax.swing.JMenuItem();
        JMCustoms = new javax.swing.JMenu();
        btnNewCustom = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        JEditCustom = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        JDeleteCustom = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        JListCustom = new javax.swing.JMenuItem();
        JMInvoice = new javax.swing.JMenu();
        btnnewInvoice = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        JmInvoiceList = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Unique Jewerly Desings");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1015, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
        );

        Jminventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/inventory.png"))); // NOI18N
        Jminventory.setText("Inventory");
        Jminventory.add(jSeparator6);

        BtnListProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/jewelry.png"))); // NOI18N
        BtnListProducts.setText("Products Stock");
        BtnListProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListProductsActionPerformed(evt);
            }
        });
        Jminventory.add(BtnListProducts);

        JProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/products.png"))); // NOI18N
        JProducts.setText("Products");

        btnNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/plus.png"))); // NOI18N
        btnNewProduct.setText("New Product");
        btnNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProductActionPerformed(evt);
            }
        });
        JProducts.add(btnNewProduct);

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        JProducts.add(jSeparator8);

        btnEditPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/edit product.png"))); // NOI18N
        btnEditPro.setText("Edit Product");
        JProducts.add(btnEditPro);

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        JProducts.add(jSeparator9);

        btnDeletePro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/delete product.png"))); // NOI18N
        btnDeletePro.setText("Delete Product");
        JProducts.add(btnDeletePro);

        Jminventory.add(JProducts);

        jMenuBar1.add(Jminventory);

        JMCustoms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/customs.png"))); // NOI18N
        JMCustoms.setText("Customs");

        btnNewCustom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/new customer.png"))); // NOI18N
        btnNewCustom.setText("New Custom");
        btnNewCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCustomActionPerformed(evt);
            }
        });
        JMCustoms.add(btnNewCustom);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        JMCustoms.add(jSeparator4);

        JEditCustom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/edit.png"))); // NOI18N
        JEditCustom.setText("Edit Custom");
        JMCustoms.add(JEditCustom);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        JMCustoms.add(jSeparator2);

        JDeleteCustom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/delete customer.png"))); // NOI18N
        JDeleteCustom.setText("Delete Custom");
        JDeleteCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JDeleteCustomActionPerformed(evt);
            }
        });
        JMCustoms.add(JDeleteCustom);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        JMCustoms.add(jSeparator1);

        JListCustom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/list customers.png"))); // NOI18N
        JListCustom.setText("List Custom");
        JMCustoms.add(JListCustom);

        jMenuBar1.add(JMCustoms);

        JMInvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/new Invoice.png"))); // NOI18N
        JMInvoice.setText("Invoice");

        btnnewInvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/new Invoice.png"))); // NOI18N
        btnnewInvoice.setText("New Invoice");
        btnnewInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewInvoiceActionPerformed(evt);
            }
        });
        JMInvoice.add(btnnewInvoice);

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        JMInvoice.add(jSeparator7);

        JmInvoiceList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconsMenu/list Invoice.png"))); // NOI18N
        JmInvoiceList.setText("Invoice List");
        JmInvoiceList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmInvoiceListActionPerformed(evt);
            }
        });
        JMInvoice.add(JmInvoiceList);

        jMenuBar1.add(JMInvoice);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCustomActionPerformed
        
    }//GEN-LAST:event_btnNewCustomActionPerformed

    private void JDeleteCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JDeleteCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JDeleteCustomActionPerformed

    private void JmInvoiceListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmInvoiceListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmInvoiceListActionPerformed

    private void JnewInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JnewInvoiceActionPerformed
        
    }//GEN-LAST:event_JnewInvoiceActionPerformed

    private void btnNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProductActionPerformed
        
    }//GEN-LAST:event_btnNewProductActionPerformed

    private void BtnListProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListProductsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnListProductsActionPerformed

    private void btnnewInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewInvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewInvoiceActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BtnListProducts;
    private javax.swing.JMenuItem JDeleteCustom;
    private javax.swing.JMenuItem JEditCustom;
    private javax.swing.JMenuItem JListCustom;
    private javax.swing.JMenu JMCustoms;
    private javax.swing.JMenu JMInvoice;
    private javax.swing.JMenu JProducts;
    private javax.swing.JMenuItem JmInvoiceList;
    private javax.swing.JMenu Jminventory;
    private javax.swing.JMenuItem btnDeletePro;
    private javax.swing.JMenuItem btnEditPro;
    private javax.swing.JMenuItem btnNewCustom;
    private javax.swing.JMenuItem btnNewProduct;
    private javax.swing.JMenuItem btnnewInvoice;
    private javax.swing.JPanel content;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
