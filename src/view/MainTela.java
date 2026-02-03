/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.Conexao;
import dao.DominioDto;
import dao.IConexao;
import dao.IDominio;
import dao.IUsuarioDto;
import dao.IUsuarioPagamentoMesDto;
import dao.UsuarioDto;
import dao.UsuarioPagamentoMesDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import model.SexoModel;
import model.UsuarioModel;
import model.UsuarioPagamentoMesModel;

/**
 *
 * @author joaov
 */
public class MainTela extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainTela.class.getName());
    private IUsuarioDto usuarioDto;
    private IUsuarioPagamentoMesDto usuarioPagamentoDto;
    /**
     * Creates new form DashBoard
     */
    public MainTela() {
        initComponents();
        IConexao sqlLite = new Conexao();
        DominioDto dominioDao = new DominioDto(sqlLite);
        dominioDao.CriarTabelas();
        dominioDao.CargaTabelas();
        usuarioDto = new UsuarioDto(sqlLite);
        usuarioDto.criarTabela();
        usuarioPagamentoDto = new UsuarioPagamentoMesDto(sqlLite);
        usuarioPagamentoDto.criarTabela();
        LoadTabelaUsuario();
        //var listaSexo = dominioDao.BuscarSexo();

        
        
        //DefaultTableModel modelo = (DefaultTableModel) tabelaSexo.getModel(); 
        
        
        //for(SexoModel sexo : listaSexo)
        //{
         //   System.out.print(sexo);
        //    modelo.addRow(new Object[]{sexo.getIdDominio(), sexo.getDescricao()}); 
       // }
        
        
        //modelo.addRow(new Object[]{3, "Outro"});
        //dominioDao.criarTabela();
        //dominioDao.cargaTabela();
        
    }
    
    public List<UsuarioModel> FiltarUsuario(){
        return null;
    }
    public void LoadTabelaUsuario() {
        var usuariosCadastrados = usuarioDto.ListarUsuario();
        DefaultTableModel model = (DefaultTableModel) tableUsuario.getModel();
        model.setRowCount(0);
        List<UsuarioModel> usuariosFiltro = usuariosCadastrados;
        
        var textoFiltro = this.textFiltro.getText();
        
        if(!textoFiltro.isEmpty()){
            usuariosFiltro = usuariosCadastrados.stream().filter(u -> 
                    u.getCPF().contains(textoFiltro) ||
                    u.getNome().contains(textoFiltro) ||
                    u.getEmail().contains(textoFiltro) ||
                    u.getTelefone().contains(textoFiltro)
            ).collect(Collectors.toList());
        }

        
        if(this.radioHomem.isSelected() && !this.radioMulher.isSelected()){
            usuariosFiltro = usuariosFiltro.stream().filter(u -> u.getSexo() == 1).collect(Collectors.toList());
        }
        else if(!this.radioHomem.isSelected() && this.radioMulher.isSelected()){
            usuariosFiltro = usuariosFiltro.stream().filter(u -> u.getSexo() == 2).collect(Collectors.toList());
        }
        else {
            usuariosFiltro = usuariosFiltro;
        }
        
        for(UsuarioModel usuario : usuariosFiltro){
            boolean passouUmMes = true;
            String pago = "Não pago";
            var pagamento = this.usuarioPagamentoDto.listarPorUsuario(usuario.getIdUsuario());
            if(!pagamento.isEmpty())
            {
                var datas = pagamento.stream().map(UsuarioPagamentoMesModel::getMesPagamento).collect(Collectors.toList());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataMaisRecente = datas.stream()
                .map(LocalDate::parse) // ISO padrão
                .max(LocalDate::compareTo)
                .orElseThrow();
                
                LocalDate hoje = LocalDate.now(); 
                passouUmMes = hoje.isAfter(dataMaisRecente.plusMonths(1));
                if(!passouUmMes)
                    pago = "Pago";
            }

            var indexStatus = this.comboStatus.getSelectedIndex();
            if(indexStatus == 1 && !passouUmMes)    
                model.addRow(new Object[]{usuario.getNome(), usuario.getCPF(), usuario.getEmail(), usuario.getTelefone(), pago});
            if(indexStatus == 2 && passouUmMes)    
                model.addRow(new Object[]{usuario.getNome(), usuario.getCPF(), usuario.getEmail(), usuario.getTelefone(), pago}); 
            if(indexStatus == 0)
                model.addRow(new Object[]{usuario.getNome(), usuario.getCPF(), usuario.getEmail(), usuario.getTelefone(), pago}); 
        }
    }
    
    private void btnCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
    CadastroTela tela = new CadastroTela();
    tela.setVisible(true);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableUsuario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        radioHomem = new javax.swing.JRadioButton();
        radioMulher = new javax.swing.JRadioButton();
        textFiltro = new javax.swing.JTextField();
        comboStatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Cadastrar cliente");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Academia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Nome", "CPF", "Email", "Telefone", "Status Pagamento"
            }
        ));
        tableUsuario.setDefaultEditor(Object.class, null);
        tableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUsuarioMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableUsuario);

        jLabel3.setText("Clientes cadastrados");

        radioHomem.setText("Homens");
        radioHomem.addActionListener(this::radioHomemActionPerformed);

        radioMulher.setText("Mulheres");
        radioMulher.addActionListener(this::radioMulherActionPerformed);

        textFiltro.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                textFiltroInputMethodTextChanged(evt);
            }
        });
        textFiltro.addActionListener(this::textFiltroActionPerformed);

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Todos", "Pago", "Não pago" }));
        comboStatus.addActionListener(this::comboStatusActionPerformed);

        jLabel4.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioHomem)
                                .addGap(18, 18, 18)
                                .addComponent(radioMulher)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(radioHomem)
                            .addComponent(radioMulher)
                            .addComponent(textFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CadastroTela tela = new CadastroTela(this);
        tela.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radioHomemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHomemActionPerformed
        // TODO add your handling code here:
        this.LoadTabelaUsuario();
    }//GEN-LAST:event_radioHomemActionPerformed

    private void radioMulherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMulherActionPerformed
        // TODO add your handling code here:
        this.LoadTabelaUsuario();
    }//GEN-LAST:event_radioMulherActionPerformed

    private void tableUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsuarioMouseClicked
        // TODO add your handling code here:
        int row = tableUsuario.getSelectedRow(); // linha clicada 
        if (row != -1) { // Pegando os valores das colunas 
            //Object valorColuna1 = tableUsuario.getValueAt(row, 0); 
            String cpf = tableUsuario.getValueAt(row, 1).toString(); 
            //System.out.println("Coluna 1: " + valorColuna1); System.out.println("Coluna 2: " + valorColuna2); 
            OpcoesTela tela = new OpcoesTela(cpf, this);
            tela.setVisible(true);
        }
        
    }//GEN-LAST:event_tableUsuarioMouseClicked

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        // TODO add your handling code here:
        this.LoadTabelaUsuario();
    }//GEN-LAST:event_comboStatusActionPerformed

    private void textFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFiltroActionPerformed
        // TODO add your handling code here:
        this.LoadTabelaUsuario();
    }//GEN-LAST:event_textFiltroActionPerformed

    private void textFiltroInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_textFiltroInputMethodTextChanged
        // TODO add your handling code here:
        this.LoadTabelaUsuario();
    }//GEN-LAST:event_textFiltroInputMethodTextChanged

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainTela().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton radioHomem;
    private javax.swing.JRadioButton radioMulher;
    private javax.swing.JTable tableUsuario;
    private javax.swing.JTextField textFiltro;
    // End of variables declaration//GEN-END:variables
}
