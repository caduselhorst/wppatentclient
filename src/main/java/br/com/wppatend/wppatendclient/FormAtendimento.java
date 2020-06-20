/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient;

import br.com.lgss.icecontrolclient.forms.FormDialogoDeOpcao;
import br.com.wppatend.wppatendclient.models.Protocolo;
import br.com.wppatend.wppatendclient.restapiclient.ApiReturn;
import br.com.wppatend.wppatendclient.restapiclient.EstadoOperadorInfo;
import br.com.wppatend.wppatendclient.restapiclient.RestApiClient;
import br.com.wppatend.wppatendclient.restapiclient.User;
import br.com.wppatend.wppatendclient.threads.ThreadConsultaProtocolo;
import br.com.wppatend.wppatendclient.threads.ThreadPrintaChat;
import java.awt.Color;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pge
 */
public class FormAtendimento extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form FormAtendimento
     */
    public FormAtendimento(User user) {
        this.user = user;
        initComponents();
        setaNomeOperador();
        instanciaApiClient();
        iniciaEstadoOperador();
    }
    
    private void setaNomeOperador() {
        jLabelOperador.setText(String.format("Operador: %1$s (%2$s)", user.getUserName(), user.getName()));
    }
    
    private void instanciaApiClient() {
        try {
            apiClient = new RestApiClient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao iniciar o cliente da API\n"
                    + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void iniciaEstadoOperador() {
        estadoOperadorInfo = new EstadoOperadorInfo();
        estadoOperadorInfo.setIdUser(user.getId());
        estadoOperadorInfo.setDisponivel(false);
        estadoOperadorInfo.setEmAtendimento(false);
    }
    
    private void alteraEstadoOperador() {
        apiClient.alteraEstadoOperador(estadoOperadorInfo.getIdUser(), 
                estadoOperadorInfo.getDisponivel(), estadoOperadorInfo.getEmAtendimento());
    }
    
    private void habilitaAtendimento() {
        jButtonDetalhes.setEnabled(true);
        jButtonFinalizar.setEnabled(true);
        jButtonEnviar.setEnabled(true);
        jTextFieldChat.setEnabled(true);
        jTextFieldChat.requestFocus();
        jTextPaneChat.setEnabled(true);
        jLabelEstadoOperador.setText("Em atendimento");
        jLabelEstadoOperador.setForeground(EM_ATENDIMENTO_COLOR);
    }
    
    private void desabilitaAtendimento() {
        jButtonDetalhes.setEnabled(false);
        jButtonFinalizar.setEnabled(false);
        jButtonEnviar.setEnabled(false);
        jTextFieldChat.setEnabled(false);
        jTextPaneChat.setEnabled(false);
        if(jToggleButton1.isSelected()) {
            jLabelEstadoOperador.setText("On line");
            jLabelEstadoOperador.setForeground(ON_LINE_COLOR);
        } else {
            jLabelEstadoOperador.setText("Disponível");
            jLabelEstadoOperador.setForeground(DISPONIVEL_COLOR);
        }
        jTextFieldIdProtocolo.setText(null);
        jTextFieldCliente.setText(null);
        jTextFieldDataAbertura.setText(null);
        jTextFieldFone.setText(null);
        jTextFieldNroProtocolo.setText(null);
        jTextPaneChat.setText("");
        jLabelCorp.setText("-");
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneChat = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldChat = new javax.swing.JTextField();
        jButtonEnviar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelCorp = new javax.swing.JLabel();
        jButtonDetalhes = new javax.swing.JButton();
        jTextFieldIdProtocolo = new javax.swing.JTextField();
        jTextFieldNroProtocolo = new javax.swing.JTextField();
        jTextFieldDataAbertura = new javax.swing.JTextField();
        jTextFieldCliente = new javax.swing.JTextField();
        jTextFieldFone = new javax.swing.JTextField();
        jButtonFinalizar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelEstadoOperador = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton4 = new javax.swing.JButton();
        jLabelOperador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("WPPAtendimento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jTextPaneChat.setEditable(false);
        jTextPaneChat.setEnabled(false);
        jScrollPane1.setViewportView(jTextPaneChat);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldChat.setEnabled(false);
        jTextFieldChat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldChatKeyPressed(evt);
            }
        });

        jButtonEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat.png"))); // NOI18N
        jButtonEnviar.setMnemonic('E');
        jButtonEnviar.setText("Enviar");
        jButtonEnviar.setEnabled(false);
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldChat, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Protocolo"));

        jLabel1.setText("Id:");

        jLabel2.setText("Número:");

        jLabel3.setText("Data abertura:");

        jLabel4.setText("Cliente:");

        jLabel5.setText("Contato:");

        jLabel6.setText("Corporativo:");

        jLabelCorp.setText("-");

        jButtonDetalhes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/empresa.png"))); // NOI18N
        jButtonDetalhes.setMnemonic('D');
        jButtonDetalhes.setText("Detalhes");
        jButtonDetalhes.setEnabled(false);
        jButtonDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetalhesActionPerformed(evt);
            }
        });

        jTextFieldIdProtocolo.setEditable(false);

        jTextFieldNroProtocolo.setEditable(false);

        jTextFieldDataAbertura.setEditable(false);
        jTextFieldDataAbertura.setToolTipText("");

        jTextFieldCliente.setEditable(false);

        jTextFieldFone.setEditable(false);
        jTextFieldFone.setToolTipText("");

        jButtonFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/finalizar.png"))); // NOI18N
        jButtonFinalizar.setText("Finalizar atendimento");
        jButtonFinalizar.setEnabled(false);
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonFinalizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCliente)
                            .addComponent(jTextFieldFone)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextFieldIdProtocolo, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldNroProtocolo, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldDataAbertura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                    .addComponent(jLabelCorp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldIdProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelCorp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDetalhes)
                    .addComponent(jButtonFinalizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado do operador"));

        jLabelEstadoOperador.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabelEstadoOperador.setForeground(new java.awt.Color(128, 124, 157));
        jLabelEstadoOperador.setText("On line");

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pause.png"))); // NOI18N
        jToggleButton1.setSelected(true);
        jToggleButton1.setText("Pausa");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jButton4.setText("Sair");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabelOperador.setText("Operador: ag001 (Carlos Eduardo Selhorst)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelOperador)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelEstadoOperador, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstadoOperador)
                    .addComponent(jLabelOperador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
            ApiReturn ret = apiClient.logout(user.getId());
            
            if(ret.isError()) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar o logout remoto:\n"
                    + ret.getMessage() + "\nContate o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                System.exit(0);
            }
            
        } catch (Exception e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar o logout remoto:\n"
                    + e.getMessage() + "\nContate o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            //RestApiClient client = new RestApiClient();
            
            jButton4.setEnabled(jToggleButton1.isSelected());
            if(jToggleButton1.isSelected()) {
                jLabelEstadoOperador.setText("On line");
                jLabelEstadoOperador.setForeground(ON_LINE_COLOR);
                estadoOperadorInfo.setDisponivel(false);
                if(consultaProtocolo != null) {
                    consultaProtocolo.parar();
                }
            } else {
                estadoOperadorInfo.setDisponivel(true);
                
                jLabelEstadoOperador.setText("Disponível");
                jLabelEstadoOperador.setForeground(DISPONIVEL_COLOR);
                consultaProtocolo = new ThreadConsultaProtocolo(user);
                consultaProtocolo.addObserver(this);
                Thread t = new Thread(consultaProtocolo);
                t.start();
            }
            alteraEstadoOperador();
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } catch (Exception e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar o comando remoto:\n"
                    + e.getMessage() + "\nContate o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
            
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        try {
            if(FormDialogoDeOpcao.mostraDialogo("Confirmação", "Deseja encerrar esse atendimento?", this) == 0) {
                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                desabilitaAtendimento();
                apiClient.encerraProtocolo(protocolo.getId());
                
                protocolo = null;
                if(monitorChat != null) {
                    monitorChat.finalizar();
                }
                consultaProtocolo = new ThreadConsultaProtocolo(user);
                if(!jToggleButton1.isSelected()) {
                    consultaProtocolo.addObserver(this);
                    Thread t = new Thread(consultaProtocolo);
                    t.start();
                }
                estadoOperadorInfo.setEmAtendimento(false);
                alteraEstadoOperador();
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
        } catch (Exception e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar o comando remoto:\n"
                    + e.getMessage() + "\nContate o administrador do sistema.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButtonFinalizarActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        if(!jTextFieldChat.getText().isEmpty()) {
            try {
                apiClient.enviaMensagem(protocolo.getContato(), jTextFieldChat.getText(), protocolo.getId());
                jTextFieldChat.setText(null);
                jTextFieldChat.requestFocus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar enviar a mensagem:\n"
                        + e.getMessage() + "\nInforme ao administrador do sistema", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jTextFieldChatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChatKeyPressed
        if(evt.getKeyCode() == 10) {
            jButtonEnviarActionPerformed(null);
        }
    }//GEN-LAST:event_jTextFieldChatKeyPressed

    private void jButtonDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetalhesActionPerformed
        FormDetalhes f = new FormDetalhes(this, true, protocolo.getPessoaFisica(), protocolo.getPessoaJuridica());
        f.setVisible(true);
    }//GEN-LAST:event_jButtonDetalhesActionPerformed

    @Override
    public void update(Observable o, Object arg) {
        protocolo = (Protocolo) arg;
        jTextFieldIdProtocolo.setText(String.valueOf(protocolo.getId()));
        jTextFieldCliente.setText(protocolo.getPessoaFisica().getNome());
        jTextFieldDataAbertura.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(protocolo.getDataAbertura()));
        jTextFieldFone.setText(protocolo.getContato());
        jTextFieldNroProtocolo.setText(protocolo.getNumero());
        if(protocolo.getPessoaJuridica() != null) {
            jLabelCorp.setText("Sim");
        } else {
            jLabelCorp.setText("Não");
        }
        habilitaAtendimento();
        monitorChat = new ThreadPrintaChat(protocolo, jTextPaneChat);
        monitorChat.start();
        estadoOperadorInfo.setEmAtendimento(true);
        alteraEstadoOperador();
    }
    
    private User user;
    private RestApiClient apiClient;
    private ThreadConsultaProtocolo consultaProtocolo;
    private ThreadPrintaChat monitorChat;
    private static final Color ON_LINE_COLOR = new Color(128, 124, 157);
    private static final Color DISPONIVEL_COLOR = Color.GREEN;
    private static final Color EM_ATENDIMENTO_COLOR = new Color(255, 140, 0);
    private Protocolo protocolo;
    private EstadoOperadorInfo estadoOperadorInfo;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonDetalhes;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelCorp;
    private javax.swing.JLabel jLabelEstadoOperador;
    private javax.swing.JLabel jLabelOperador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldChat;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldDataAbertura;
    private javax.swing.JTextField jTextFieldFone;
    private javax.swing.JTextField jTextFieldIdProtocolo;
    private javax.swing.JTextField jTextFieldNroProtocolo;
    private javax.swing.JTextPane jTextPaneChat;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    
}
