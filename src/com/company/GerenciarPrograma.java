package com.company;

import com.company.view.EmpregadoGUI;

import javax.swing.*;

public class GerenciarPrograma {

    public static void main(String[] args) {
        JFrame telaCadastro = new JFrame();
        telaCadastro.setContentPane(new EmpregadoGUI().getPanelTelaCadastro());
        telaCadastro.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        telaCadastro.setSize(550,370);
        telaCadastro.setTitle("Cadastro de Empregados");
        telaCadastro.setVisible(true);
    }
}
