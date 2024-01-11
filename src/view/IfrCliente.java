package view;

import apoio.Formatacao;
import apoio.Validacao;
import dao.ClienteDAO;
import entidades.Cliente;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Univates - EAD
 * @author Lucas Cenci Beltrame
 */

public class IfrCliente extends javax.swing.JInternalFrame {

    int idCliente = 0;
    
    public IfrCliente() {
        initComponents();
        
        // formata o campo DATA
        Formatacao.formatarCpf(tffDescricaoCliente3);
        Formatacao.formatarTelefone(tffDescricaoCliente4);
        
        new ClienteDAO().popularTabelacliente(tblCliente,"");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        tfdConsultarCliente = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfdDescricaoCliente = new javax.swing.JTextField();
        tfdDescricaoCliente2 = new javax.swing.JTextField();
        tffDescricaoCliente3 = new javax.swing.JFormattedTextField();
        tffDescricaoCliente4 = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setTitle("Cadastro: Clientes");

        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCliente);

        jLabel5.setText("Pesquisar:");

        btnPesquisar.setText("ir");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdConsultarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisar)
                        .addGap(0, 230, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfdConsultarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listagem", jPanel1);

        jLabel1.setText("Nome:");

        jLabel2.setText("E-mail:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Telefone:");

        tffDescricaoCliente3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tffDescricaoCliente3FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdDescricaoCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(tfdDescricaoCliente)
                    .addComponent(tffDescricaoCliente3)
                    .addComponent(tffDescricaoCliente4))
                .addGap(0, 313, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdDescricaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdDescricaoCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tffDescricaoCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tffDescricaoCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manutenção", jPanel2);

        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnSalvar)
                    .addComponent(jButton2)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
       
        //insercao dos valores na tabela do banco de dados
        
        String nomeCliente = tfdDescricaoCliente.getText();
        String emailCliente = tfdDescricaoCliente2.getText();
        String cpfCliente = tffDescricaoCliente3.getText();
        String telefoneCliente = tffDescricaoCliente4.getText();
             
        //criacao - cliente
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setNome(nomeCliente);
        cliente.setE_mail(emailCliente);
        cliente.setCpf(cpfCliente);
        cliente.setTelefone(telefoneCliente);
        
        //criacao - ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
       
       if (idCliente == 0) {
        if (clienteDAO.salvar(cliente) == null) {
           
            tfdDescricaoCliente.setText("");
            tfdDescricaoCliente2.setText("");
            tffDescricaoCliente3.setText("");
            tffDescricaoCliente4.setText("");

            JOptionPane.showMessageDialog(this,"Registro salvo com sucesso!");

            tfdDescricaoCliente.requestFocus();
            tfdDescricaoCliente2.requestFocus();
            tffDescricaoCliente3.requestFocus();
            tffDescricaoCliente4.requestFocus();

            new ClienteDAO().popularTabelacliente(tblCliente, "");
            
        } else {
            JOptionPane.showMessageDialog(this, "Problema ao salvar registro!");
        }
    } else {
        if (clienteDAO.atualizar(cliente) == null) {
           
            tfdDescricaoCliente.setText("");
            tfdDescricaoCliente2.setText("");
            tffDescricaoCliente3.setText("");
            tffDescricaoCliente4.setText("");

            JOptionPane.showMessageDialog(this,"Registro salvo com sucesso!");

            tfdDescricaoCliente.requestFocus();
            tfdDescricaoCliente2.requestFocus();
            tffDescricaoCliente3.requestFocus();
            tffDescricaoCliente4.requestFocus();
            
        } else {
            JOptionPane.showMessageDialog(this, "Problema ao salvar registro!");
        }
    }

      new ClienteDAO().popularTabelacliente(tblCliente, "");

    idCliente = 0;            
    }//GEN-LAST:event_btnSalvarActionPerformed



    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        ArrayList<Cliente> clientes = new ArrayList(); //realiza a mostra ao click button
        
        clientes = new ClienteDAO().consultarTodos();
        
        for (int i = 0; i < clientes.size(); i++) {
        
        System.out.println("Nome: " + clientes.get(i).getNome());
        System.out.println("Email: " + clientes.get(i).getE_mail());
        System.out.println("Cpf: " + clientes.get(i).getCpf());
        System.out.println("Telefone: " + clientes.get(i).getTelefone());
        
        System.out.println("");
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
       
       new ClienteDAO().popularTabelacliente(tblCliente, tfdConsultarCliente.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
        String idTabela = String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 0));
        
        idCliente = Integer.parseInt(idTabela);
        
        Cliente cliente = new ClienteDAO().consultarId(idCliente);
        
        if (cliente != null) {
            jTabbedPane1.setSelectedIndex(1);
            
            tfdDescricaoCliente.setText(cliente.getNome());
            tfdDescricaoCliente2.setText(cliente.getE_mail());
            tffDescricaoCliente3.setText(cliente.getCpf());
            tffDescricaoCliente4.setText(cliente.getTelefone());
                                         
      } else {
            JOptionPane.showMessageDialog(this, "ID do cliente inesistente!");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        String idTabela = String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 0));
        
        idCliente = Integer.parseInt(idTabela);
        
        if (new ClienteDAO().excluir(idCliente) == null) {
            JOptionPane.showMessageDialog(this, "Dados excluidos!");
            
            new ClienteDAO().popularTabelacliente(tblCliente, "");
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao excluir os dados!");
        }
        
        idCliente = 0;
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tffDescricaoCliente3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tffDescricaoCliente3FocusLost
   
        if (!Validacao.validarCPF(Formatacao.removerFormatacao(tffDescricaoCliente3.getText()))) {
            tffDescricaoCliente3.setBackground(Color.RED);
        } else {
            tffDescricaoCliente3.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_tffDescricaoCliente3FocusLost



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField tfdConsultarCliente;
    private javax.swing.JTextField tfdDescricaoCliente;
    private javax.swing.JTextField tfdDescricaoCliente2;
    private javax.swing.JFormattedTextField tffDescricaoCliente3;
    private javax.swing.JFormattedTextField tffDescricaoCliente4;
    // End of variables declaration//GEN-END:variables
}
