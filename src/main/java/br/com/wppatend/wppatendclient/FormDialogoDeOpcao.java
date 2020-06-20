/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lgss.icecontrolclient.forms;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author CARLOS EDUARDO
 */
public class FormDialogoDeOpcao {
    
    private static final Object[] opcoes = new Object[] {"Sim", "Não"};
    
    public static int mostraDialogo(String titulo, String mensagem, Component componente) {
        
        return JOptionPane.showOptionDialog(
                componente,
                mensagem,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);
        
    }
    
}
