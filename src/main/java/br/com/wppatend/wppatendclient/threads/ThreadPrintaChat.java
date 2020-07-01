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
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTML;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
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
                    String url = "";
                    try {
                        boolean isFile = false;
                        if(chat.getTx_rx().equals("RECEBIDA")) {
                            switch (chat.getTipo()) {
                                case "chat": {
                                    
                                    msg = sdf.format(chat.getData_tx_rx()) + " - " + protocolo.getPessoaFisica().getNome() + " - " + chat.getBody() + "\r\n";
                                    isFile = false;
                                    logger.debug("Mensagem recebida - texto -> " + msg);
                                    break;
                                }
                                
                                default: {
                                    msg = sdf.format(chat.getData_tx_rx()) + " - " + protocolo.getPessoaFisica().getNome() + " - ";
                                    url = getURIFileAfterSave(chat).toURL().toString() + "\r\n";
                                    isFile = true;
                                    logger.debug("Mensagem recebida - outras -> " + msg + "(" + url + ")");
                                    break;
                                }
                            }
                            StyleConstants.setForeground(style, CLIENTE_MSG);
                        } else {
                            
                            switch (chat.getTipo()) {
                                case "chat": {
                                    msg = sdf.format(chat.getData_tx_rx()) + " - EU" + " - " + chat.getBody() + "\r\n";
                                    isFile = false;
                                    break;
                                }
                                
                                default: {
                                    msg = sdf.format(chat.getData_tx_rx()) + " - EU" + " - <a ref=\"" + getURIFileAfterSave(chat) + "\">" + getURIFileAfterSave(chat) + "</a>\r\n";
                                    break;
                                }
                            }
                            StyleConstants.setForeground(style, MINHA_MSG);
                        }
                    
                        if(isFile) {
                            logger.debug("Insere url arquivo -- Length -> " + textPane.getText().length());
                            textPane.getStyledDocument().insertString(textPane.getText().length(), msg, style);
                            SimpleAttributeSet attrs = new SimpleAttributeSet();
                            StyleConstants.setUnderline(attrs, true);
                            attrs.addAttribute(HTML.Attribute.HREF, url);
                            textPane.getStyledDocument().insertString(textPane.getText().length(), url, attrs);
                        } else {
                            logger.debug("Insere mensagem -- Length -> " + textPane.getText().length());
                            textPane.getStyledDocument().insertString(textPane.getText().length(), msg, style);
                        }
                        
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
    
    private URI getURIFileAfterSave(Chat chat) throws IOException {
        String contentType = StringUtils.split(StringUtils.split(StringUtils.split(chat.getBody(), ",")[0], ":")[1], ";")[0];
        File file = null;
        switch(chat.getTipo()) {
            case "document": {
                file = new File("media/documents/" + chat.getProtocolo() + "_" + chat.getIdchat() + "." + Config.getInstance().getFileExtensionByMimeType(contentType));
                break;
            }
            case "ptt": {
                file = new File("media/audios/" + chat.getProtocolo() + "_" + chat.getIdchat() + "." + Config.getInstance().getFileExtensionByMimeType(contentType));
                break;
            }
            case "image" : {
                file = new File("media/images/" + chat.getProtocolo() + "_" + chat.getIdchat() + "." + Config.getInstance().getFileExtensionByMimeType(contentType));
                break;
            }
            case "audio": {
                file = new File("media/audios/" + chat.getProtocolo() + "_" + chat.getIdchat() + "." + Config.getInstance().getFileExtensionByMimeType(contentType));
                break;
            }
            
        }
        if(file == null) {
            return null;
        } else {
            PrintStream out = new PrintStream(file);
            out.write(Base64.decodeBase64(StringUtils.split(chat.getBody(), ",")[1]));
            out.flush();
            out.close();
            return file.toURI();
        }
    }
    
}
