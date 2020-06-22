/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author pge
 */
public class FinalizacaoComboBoxModel extends DefaultComboBoxModel<Finalizacao> {
    
    private List<Finalizacao> finalizacoes;
    
    public FinalizacaoComboBoxModel() {
        this.finalizacoes = new ArrayList<>();
    }
    
    public FinalizacaoComboBoxModel(List<Finalizacao> finalizacoes) {
        this.finalizacoes = finalizacoes;
    } 

    @Override
    public Finalizacao getElementAt(int index) {
        return finalizacoes.get(index);
    }

    @Override
    public int getSize() {
        return finalizacoes.size();
    }
    
    
}
