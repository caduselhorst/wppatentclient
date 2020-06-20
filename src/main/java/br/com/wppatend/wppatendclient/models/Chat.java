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
    
    private long idchat;
    private long protocolo;
    private String msg_texto;
    private byte[] msg_voz;
    private byte[] msg_arquivo;
    private String tx_rx;
    private Date data_tx_rx;

    public long getIdchat() {
        return idchat;
    }

    public void setIdchat(long idchat) {
        this.idchat = idchat;
    }

    public long getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(long protocolo) {
        this.protocolo = protocolo;
    }

    public String getMsg_texto() {
        return msg_texto;
    }

    public void setMsg_texto(String msg_texto) {
        this.msg_texto = msg_texto;
    }

    public byte[] getMsg_voz() {
        return msg_voz;
    }

    public void setMsg_voz(byte[] msg_voz) {
        this.msg_voz = msg_voz;
    }

    public byte[] getMsg_arquivo() {
        return msg_arquivo;
    }

    public void setMsg_arquivo(byte[] msg_arquivo) {
        this.msg_arquivo = msg_arquivo;
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
