/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.restapiclient;

import br.com.wppatend.wppatendclient.models.Config;
import br.com.wppatend.wppatendclient.models.Finalizacao;
import br.com.wppatend.wppatendclient.models.Protocolo;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


/**
 *
 * @author pge
 */
public class RestApiClient {
    
    private static Logger logger = Logger.getLogger(RestApiClient.class);
    
    private Client client;
    private WebTarget target;
    private String urlApi;
    
    public RestApiClient() throws Exception {
        client = ClientBuilder.newClient();
        urlApi = Config.getInstance().appUrlApi();
    }
    
    public ApiReturn login(String userName, String senha) {
                
        LoginInfo info = new LoginInfo();
        info.setUserName(userName);
        info.setPassWord(senha);
               
        return client.target(urlApi)
                .path("/user/login")
                .request()
                .post(Entity.entity(info, MediaType.APPLICATION_JSON), ApiReturn.class);
        
    }
    
    public ApiReturn logout(Long idUser) {
        
        LogoutInfo info = new LogoutInfo();
        info.setIdUser(idUser);
        
        return client.target(urlApi)
                .path("/user/logout")
                .request()
                .post(Entity.entity(info, MediaType.APPLICATION_JSON), ApiReturn.class);
        
    }
    
    public ApiReturn alteraEstadoOperador(Long idUser, boolean disponivel, boolean emAtendimento) {
        
        EstadoOperadorInfo info = new EstadoOperadorInfo();
        info.setDisponivel(disponivel);
        info.setEmAtendimento(emAtendimento);
        info.setIdUser(idUser);
        
        return client.target(urlApi)
                .path("/user/estado")
                .request()
                .post(Entity.entity(info, MediaType.APPLICATION_JSON), ApiReturn.class);
        
    }
    
    public ApiReturn encerraProtocolo(Long id, Long finalizacao) {
    
        ProtocoloInfo info = new ProtocoloInfo();
        info.setIdProtocolo(id);
        info.setIdFinalizacao(finalizacao);
        return client.target(urlApi)
                .path("/protocolo")
                .request()
                .post(Entity.entity(info, MediaType.APPLICATION_JSON), ApiReturn.class);
        
    }
    
    public Protocolo getProtocolo(Long idUser) {
        
        return client.target(urlApi)
                .path("/protocolo/user/" + idUser)
                .request()
                .get(Protocolo.class);
        
    }
    
    public ChatReturn getChat(Long protocolo, int page) {
        String path = "/chat/" + protocolo + "/" + page  + "/1";
        
        return client.target(urlApi)
                .path(path)
                .request()
                .get(ChatReturn.class);
    }
    
    public ApiReturn enviaMensagem(String telefone, String mensagem, Long protocolo) {
        
        SendMessageInfo info = new SendMessageInfo();
        info.setMessage(mensagem);
        info.setPhoneNumber(telefone);
        info.setProtocolo(protocolo);
        
        return client.target(urlApi)
                .path("/chat/send")
                .request()
                .post(Entity.entity(info, MediaType.APPLICATION_JSON), ApiReturn.class);
        
    }
    
    public List<Finalizacao> getFinalizacoes() {
        String path = "/finalizacoes";
        
        List<HashMap> map = client.target(urlApi)
                .path(path)
                .request()
                .get(List.class);
        
        List<Finalizacao> finalizacoes = new ArrayList<>();
        
        map.forEach(m -> {
            Finalizacao f = new Finalizacao();
            f.setId(Long.parseLong(m.get("id") + ""));
            f.setDescricao((String) m.get("descricao"));
            f.setDeleted((boolean) m.get("deleted") );
            finalizacoes.add(f);
        });
        
        return finalizacoes;
    }
    
}
