/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.restapiclient;

/**
 *
 * @author pge
 */
public class EstadoOperadorInfo {

    private Long idUser;
    private Boolean disponivel;
    private Boolean emAtendimento;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Boolean getEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(Boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    
}
