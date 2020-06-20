/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.threads;

import br.com.wppatend.wppatendclient.models.Chat;
import br.com.wppatend.wppatendclient.models.Config;
import br.com.wppatend.wppatendclient.models.Protocolo;
import br.com.wppatend.wppatendclient.restapiclient.ChatReturn;
import br.com.wppatend.wppatendclient.restapiclient.RestApiClient;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.apache.log4j.Logger;

/**
 *
 * @author pge
 */
public class ThreadPrintaChat extends Thread {
    
    private static final long POOLING = 1000;
    
    
    private static final Color MINHA_MSG = Color.BLUE;
    private static final Color CLIENTE_MSG = new Color(255, 140, 0);
    
    private static Logger logger = Logger.getLogger(ThreadPrintaChat.class);
    private Protocolo protocolo;
    private RestApiClient client;
    private JTextPane textPane;
    private boolean sair;
    private long poolingtime;
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ThreadPrintaChat(Protocolo protocolo, JTextPane textPane) {
        this.protocolo = protocolo;
        this.textPane = textPane;
        sair = false;
    }
    
    public void finalizar() {
        sair = true;
    }

    @Override
    public void run() {
        logger.info("Iniciando processamento do chat");
        try {
            textPane.getStyledDocument().insertString(0, "***** NOVO ATENDIMENTO *****", textPane.getCharacterAttributes());
        } catch (BadLocationException ex) {
            logger.error("Erro ao inserir dados no painel de chat", ex);
        }
        try {
            poolingtime = Config.getInstance().chatTimePooling();
        } catch (Exception e) {
            logger.warn("Ocorreu um erro ao definir o tempo de pooling pela configuração. Assumindo tempo interno.", e);
            poolingtime = POOLING;
        }
        
        try {
            client = new RestApiClient();
        } catch (Exception e) {
            logger.fatal("Nao foi possível iniciar o monitor do chat", e);
            throw new RuntimeException("Nao foi possível iniciar o monitor do chat", e);
        }
        int pageNumber = 1;
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Style", null);
        StyleConstants.setForeground(style, CLIENTE_MSG);
        while(!sair) {
            try {
                ChatReturn ret = client.getChat(protocolo.getId(), pageNumber);
                if(ret != null && !ret.getContent().isEmpty()) {
                    Chat chat = ret.getContent().get(0);
                    String msg = "";
                    try {
                        if(chat.getTx_rx().equals("RECEBIDA")) {
                            msg = "\n" + sdf.format(chat.getData_tx_rx()) + " - " + protocolo.getPessoaFisica().getNome() + " - " + chat.getMsg_texto();
                            StyleConstants.setForeground(style, CLIENTE_MSG);
                        } else {
                            msg = "\n" + sdf.format(chat.getData_tx_rx()) + " - EU" + " - " + chat.getMsg_texto();
                            StyleConstants.setForeground(style, MINHA_MSG);
                        }
                    
                        textPane.getStyledDocument().insertString(textPane.getText().length(), msg, style);
                    } catch (BadLocationException ex) {
                        logger.error("Erro ao inserir dados no painel de chat", ex);
                    }
                    pageNumber++;
                }
            } catch (Exception e) {
                logger.error("Ocorreu um erro ao carregar o chat", e);
                String erro = "Ocorreu um erro ao carregar o chat\n" + e.getMessage() + "\n";
                AttributeSet as = textPane.getCharacterAttributes();
                try {
                    textPane.getStyledDocument().insertString(textPane.getText().length()-1, erro, as);
                } catch (BadLocationException ex) {
                    logger.error("Erro ao inserir dados no painel de chat", ex);
                }
            }
            try {
                Thread.sleep(poolingtime);
            } catch (InterruptedException e) {
                logger.warn("Erro de interrupção de thread", e);
            }
        }
        
    }
    
}
