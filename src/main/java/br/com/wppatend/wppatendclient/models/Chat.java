/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.models;

import java.util.Date;

/**
 *
 * @author pge
 */
public class Chat {

    private Long idchat;
    private Long protocolo;
    private String tipo;
    private String mimetype;
    private String body;
    private String legenda;
    private String tx_rx;
    private Date data_tx_rx;

    public Long getIdchat() {
        return idchat;
    }

    public void setIdchat(Long idchat) {
        this.idchat = idchat;
    }

    public Long getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Long protocolo) {
        this.protocolo = protocolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getTx_rx() {
        return tx_rx;
    }

    public void setTx_rx(String tx_rx) {
        this.tx_rx = tx_rx;
    }

    public Date getData_tx_rx() {
        return data_tx_rx;
    }

    public void setData_tx_rx(Date data_tx_rx) {
        this.data_tx_rx = data_tx_rx;
    }

}
