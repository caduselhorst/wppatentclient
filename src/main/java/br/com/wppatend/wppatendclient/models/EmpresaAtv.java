/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.models;

/**
 *
 * @author pge
 */
public class EmpresaAtv {

    private Long idempresaatv;
    private String cnae;
    private String descricao;
    private Boolean principal;

    public EmpresaAtv() {
    }

    public EmpresaAtv(String cnae, String descricao) {
        this.descricao = descricao;
        this.cnae = cnae;
    }

    public Long getIdempresa_atv() {
        return idempresaatv;
    }

    public void setIdempresa_atv(Long idempresaatv) {
        this.idempresaatv = idempresaatv;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

}
