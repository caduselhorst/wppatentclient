/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.threads;

import br.com.wppatend.wppatendclient.models.Config;
import br.com.wppatend.wppatendclient.models.Protocolo;
import br.com.wppatend.wppatendclient.restapiclient.RestApiClient;
import br.com.wppatend.wppatendclient.restapiclient.User;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author pge
 */
public class ThreadConsultaProtocolo extends Observable implements Runnable {
    
    private static Logger logger = Logger.getLogger(ThreadConsultaProtocolo.class);
    
    private User user;
    
    private RestApiClient api;
    
    private long timePooling;
    private boolean sair;
    
    public ThreadConsultaProtocolo(User user) {
        this.user = user;
        sair = false;
        try {
            api = new RestApiClient();
            timePooling = Config.getInstance().protocoloTimePooling();
        } catch (Exception e) {
            logger.error("Não foi possível iniciar a aplicação", e);
        }
    }
    
    public void parar() {
        sair = true;
    }

    @Override
    public void run() {
        logger.info("Iniciando verificação de protocolos -> Usuario: " + user.getUserName());
        
        Protocolo p = api.getProtocolo(user.getId());
        while(p == null && !sair) {
            try {
                Thread.sleep(timePooling);
                 p = api.getProtocolo(user.getId());
                 if(p != null)
                    logger.info("Novo protocolo associado ao operador. Protocolo " + p.getNumero());
            } catch (InterruptedException e) {
                logger.warn("Ocorreu um erro de interrupção de thread", e);
            }    
        }

        if(p != null) {
            logger.info("Identificado novo atendimento. Notificando observers");
            setChanged();
            notifyObservers(p);

        }
 
            
        logger.info("Verificação de protocolo finalizada.");
        
    }

    
}
